// server.js - Backend Express.js para Store Manager con Supabase

const express = require('express');
const cors = require('cors');
const path = require('path');
const bodyParser = require('body-parser');
const multer = require('multer');
const { v4: uuidv4 } = require('uuid');
const { createClient } = require('@supabase/supabase-js');

const app = express();

// Middlewares
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public'));

// Inicializar cliente Supabase
const supabaseUrl = process.env.SUPABASE_URL || '';
const supabaseKey = process.env.SUPABASE_KEY || '';

if (!supabaseUrl || !supabaseKey) {
  console.warn('⚠️ Variables SUPABASE_URL y SUPABASE_KEY no configuradas. Usa variables de entorno.');
}

const supabase = createClient(supabaseUrl, supabaseKey);

// Configurar multer para subir imágenes (almacenamiento temporal)
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

    // Verificar si usuario existe
    const { data: existingUser } = await supabase
      .from('users')
      .select('*')
      .eq('username', username)
      .single();

    if (existingUser) {
      return res.status(400).json({ success: false, message: 'Usuario ya existe' });
    }

    // Crear nuevo usuario
    const { data, error } = await supabase
      .from('users')
      .insert([{ username, password, isAdmin: false }]);

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
    const { name, price } = req.body;
    const productId = uuidv4();
    let imagePath = '';

    // Subir imagen a Supabase Storage si existe
    if (req.file) {
      const filename = `${productId}${path.extname(req.file.originalname)}`;

      const { error: uploadError } = await supabase.storage
        .from('uploads')
        .upload(filename, req.file.buffer, {
          contentType: req.file.mimetype
        });

      if (uploadError) {
        return res.status(400).json({ success: false, message: 'Error al subir imagen', error: uploadError.message });
      }

      // Obtener URL pública
      const { data: urlData } = supabase.storage
        .from('uploads')
        .getPublicUrl(filename);

      imagePath = urlData.publicUrl;
    }

    // Crear producto
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
    const { name, price } = req.body;
    const productId = req.params.id;

    // Obtener producto actual
    const { data: currentProduct, error: fetchError } = await supabase
      .from('products')
      .select('*')
      .eq('id', productId)
      .single();

    if (fetchError || !currentProduct) {
      return res.status(404).json({ success: false, message: 'Producto no encontrado' });
    }

    let imagePath = currentProduct.image_path;

    // Subir nueva imagen si existe
    if (req.file) {
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

    // Actualizar producto
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

// RUTA PARA ENVIAR A WHATSAPP
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
  const whatsappUrl = `https://wa.me/573213391720?text=${encodedMessage}`;

  res.json({ success: true, whatsappUrl });
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`✅ Servidor corriendo en puerto ${PORT}`);
  console.log(`📊 Base de datos: ${supabaseUrl ? 'Supabase conectado' : 'No conectado - revisa variables de entorno'}`);
});

