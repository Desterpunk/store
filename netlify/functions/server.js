// netlify/functions/server.js - Adaptor para Netlify Functions con Supabase

const express = require('express');
const serverless = require('serverless-http');
const cors = require('cors');
const bodyParser = require('body-parser');
const { createClient } = require('@supabase/supabase-js');
const multer = require('multer');
const { v4: uuidv4 } = require('uuid');
const path = require('path');

const app = express();

// Middlewares
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Inicializar cliente Supabase
const supabaseUrl = process.env.SUPABASE_URL || '';
const supabaseKey = process.env.SUPABASE_KEY || '';

const supabase = createClient(supabaseUrl, supabaseKey);

// Configurar multer para almacenamiento en memoria
const storage = multer.memoryStorage();
const upload = multer({ storage });

// RUTAS DE AUTENTICACIÓN
app.post('/api/login', async (req, res) => {
  try {
    const { username, password } = req.body;

    const { data, error } = await supabase
      .from('users')
      .select('*')
      .eq('username', username)
      .eq('password', password)
      .single();

    if (error || !data) {
      return res.status(401).json({ success: false, message: 'Usuario o contraseña incorrectos' });
    }

    res.json({ success: true, isAdmin: data.is_admin, username: data.username });
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

app.post('/api/register', async (req, res) => {
  try {
    const { username, password } = req.body;

    const { data: existingUser } = await supabase
      .from('users')
      .select('*')
      .eq('username', username)
      .single();

    if (existingUser) {
      return res.status(400).json({ success: false, message: 'Usuario ya existe' });
    }

    const { data, error } = await supabase
      .from('users')
      .insert([{ username, password, is_admin: false }]);

    if (error) {
      return res.status(400).json({ success: false, message: 'Error al crear usuario', error: error.message });
    }

    res.json({ success: true });
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

// RUTAS DE PRODUCTOS
app.get('/api/products', async (req, res) => {
  try {
    const { data, error } = await supabase
      .from('products')
      .select('*')
      .order('created_at', { ascending: false });

    if (error) {
      return res.status(400).json({ success: false, message: 'Error al obtener productos', error: error.message });
    }

    res.json(data || []);
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

app.post('/api/products', upload.single('image'), async (req, res) => {
  try {
    const { name, price, imageUrl } = req.body;
    const productId = uuidv4();
    let imagePath = '';

    // Opción 1: Usar URL de imagen de la web
    if (imageUrl && imageUrl.trim()) {
      imagePath = imageUrl.trim();
    }
    // Opción 2: Subir imagen a Supabase Storage
    else if (req.file) {
      const filename = `${productId}${path.extname(req.file.originalname)}`;

      const { error: uploadError } = await supabase.storage
        .from('uploads')
        .upload(filename, req.file.buffer, {
          contentType: req.file.mimetype
        });

      if (uploadError) {
        return res.status(400).json({ success: false, message: 'Error al subir imagen', error: uploadError.message });
      }

      const { data: urlData } = supabase.storage
        .from('uploads')
        .getPublicUrl(filename);

      imagePath = urlData.publicUrl;
    }

    const { data, error } = await supabase
      .from('products')
      .insert([{
        id: productId,
        name,
        price: parseFloat(price),
        image_path: imagePath
      }])
      .select()
      .single();

    if (error) {
      return res.status(400).json({ success: false, message: 'Error al crear producto', error: error.message });
    }

    res.json({ success: true, product: data });
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

app.put('/api/products/:id', upload.single('image'), async (req, res) => {
  try {
    const { name, price, imageUrl } = req.body;
    const productId = req.params.id;

    const { data: currentProduct, error: fetchError } = await supabase
      .from('products')
      .select('*')
      .eq('id', productId)
      .single();

    if (fetchError || !currentProduct) {
      return res.status(404).json({ success: false, message: 'Producto no encontrado' });
    }

    let imagePath = currentProduct.image_path;

    // Opción 1: Usar URL de imagen de la web
    if (imageUrl && imageUrl.trim()) {
      imagePath = imageUrl.trim();
    }
    // Opción 2: Subir nueva imagen a Supabase Storage
    else if (req.file) {
      const filename = `${productId}${path.extname(req.file.originalname)}`;

      const { error: uploadError } = await supabase.storage
        .from('uploads')
        .upload(filename, req.file.buffer, {
          contentType: req.file.mimetype,
          upsert: true
        });

      if (uploadError) {
        return res.status(400).json({ success: false, message: 'Error al subir imagen', error: uploadError.message });
      }

      const { data: urlData } = supabase.storage
        .from('uploads')
        .getPublicUrl(filename);

      imagePath = urlData.publicUrl;
    }

    const { data, error } = await supabase
      .from('products')
      .update({
        name,
        price: parseFloat(price),
        image_path: imagePath
      })
      .eq('id', productId)
      .select()
      .single();

    if (error) {
      return res.status(400).json({ success: false, message: 'Error al actualizar producto', error: error.message });
    }

    res.json({ success: true, product: data });
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

app.delete('/api/products/:id', async (req, res) => {
  try {
    const { error } = await supabase
      .from('products')
      .delete()
      .eq('id', req.params.id);

    if (error) {
      return res.status(400).json({ success: false, message: 'Error al eliminar producto', error: error.message });
    }

    res.json({ success: true });
  } catch (err) {
    res.status(500).json({ success: false, message: 'Error en servidor', error: err.message });
  }
});

app.post('/api/checkout', (req, res) => {
  const { items } = req.body;

  let message = '🛒 *Orden de Compra*\n\n';
  let total = 0;

  items.forEach(item => {
    message += `• ${item.name} x${item.quantity} - $${(item.price * item.quantity).toFixed(2)}\n`;
    total += item.price * item.quantity;
  });

  message += `\n*Total: $${total.toFixed(2)}*`;

  const encodedMessage = encodeURIComponent(message);
  const whatsappUrl = `https://wa.me/573134079282?text=${encodedMessage}`;

  res.json({ success: true, whatsappUrl });
});

module.exports.handler = serverless(app);
