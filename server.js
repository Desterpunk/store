// server.js - Backend Express.js para Store Manager

const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');
const bodyParser = require('body-parser');
const multer = require('multer');
const uuid = require('uuid');

const app = express();

// Middlewares
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public'));

// Configurar multer para subir imágenes
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    const dir = path.join(__dirname, 'uploads');
    if (!fs.existsSync(dir)) {
      fs.mkdirSync(dir, { recursive: true });
    }
    cb(null, dir);
  },
  filename: (req, file, cb) => {
    cb(null, uuid.v4() + path.extname(file.originalname));
  }
});

const upload = multer({ storage });

// Archivo de datos
const dataFile = path.join(__dirname, 'data.json');

// Inicializar datos si no existen
if (!fs.existsSync(dataFile)) {
  const initialData = {
    products: [],
    users: [
      { username: 'admin', password: 'admin123', isAdmin: true },
      { username: 'user', password: 'user123', isAdmin: false }
    ]
  };
  fs.writeFileSync(dataFile, JSON.stringify(initialData, null, 2));
}

// Helpers
const getData = () => JSON.parse(fs.readFileSync(dataFile, 'utf8'));
const saveData = (data) => fs.writeFileSync(dataFile, JSON.stringify(data, null, 2));

// RUTAS DE AUTENTICACIÓN
app.post('/api/login', (req, res) => {
  const { username, password } = req.body;
  const data = getData();
  const user = data.users.find(u => u.username === username && u.password === password);

  if (user) {
    res.json({ success: true, isAdmin: user.isAdmin, username: user.username });
  } else {
    res.status(401).json({ success: false, message: 'Usuario o contraseña incorrectos' });
  }
});

app.post('/api/register', (req, res) => {
  const { username, password } = req.body;
  const data = getData();

  if (data.users.find(u => u.username === username)) {
    return res.status(400).json({ success: false, message: 'Usuario ya existe' });
  }

  data.users.push({ username, password, isAdmin: false });
  saveData(data);
  res.json({ success: true });
});

// RUTAS DE PRODUCTOS
app.get('/api/products', (req, res) => {
  const data = getData();
  res.json(data.products);
});

app.post('/api/products', upload.single('image'), (req, res) => {
  const { name, price } = req.body;
  const data = getData();

  const product = {
    id: uuid.v4(),
    name,
    price: parseFloat(price),
    imagePath: req.file ? `/uploads/${req.file.filename}` : ''
  };

  data.products.push(product);
  saveData(data);
  res.json({ success: true, product });
});

app.put('/api/products/:id', upload.single('image'), (req, res) => {
  const { name, price } = req.body;
  const data = getData();
  const product = data.products.find(p => p.id === req.params.id);

  if (!product) {
    return res.status(404).json({ success: false, message: 'Producto no encontrado' });
  }

  product.name = name;
  product.price = parseFloat(price);
  if (req.file) {
    product.imagePath = `/uploads/${req.file.filename}`;
  }

  saveData(data);
  res.json({ success: true, product });
});

app.delete('/api/products/:id', (req, res) => {
  const data = getData();
  data.products = data.products.filter(p => p.id !== req.params.id);
  saveData(data);
  res.json({ success: true });
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

// Servir archivos estáticos
app.use('/uploads', express.static(path.join(__dirname, 'uploads')));

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`✅ Servidor corriendo en puerto ${PORT}`);
});
