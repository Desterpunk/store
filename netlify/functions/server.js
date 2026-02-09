// netlify/functions/server.js - Adaptor para Netlify Functions

const express = require('express');
const serverless = require('serverless-http');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();

// Middlewares
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Datos en memoria (se pierden entre llamadas en Netlify)
// Para producción, usa una base de datos
let products = [];
let users = [
  { username: 'admin', password: 'admin123', isAdmin: true },
  { username: 'user', password: 'user123', isAdmin: false }
];

// Rutas con prefijo /api/
app.post('/api/login', (req, res) => {
  const { username, password } = req.body;
  const user = users.find(u => u.username === username && u.password === password);

  if (user) {
    res.json({ success: true, isAdmin: user.isAdmin, username: user.username });
  } else {
    res.status(401).json({ success: false, message: 'Usuario o contraseña incorrectos' });
  }
});

app.post('/api/register', (req, res) => {
  const { username, password } = req.body;

  if (users.find(u => u.username === username)) {
    return res.status(400).json({ success: false, message: 'Usuario ya existe' });
  }

  users.push({ username, password, isAdmin: false });
  res.json({ success: true });
});

app.get('/api/products', (req, res) => {
  res.json(products);
});

app.post('/api/products', (req, res) => {
  const { name, price } = req.body;

  const product = {
    id: Math.random().toString(36).substr(2, 9),
    name,
    price: parseFloat(price),
    imagePath: ''
  };

  products.push(product);
  res.json({ success: true, product });
});

app.put('/api/products/:id', (req, res) => {
  const { name, price } = req.body;
  const product = products.find(p => p.id === req.params.id);

  if (!product) {
    return res.status(404).json({ success: false, message: 'Producto no encontrado' });
  }

  product.name = name;
  product.price = parseFloat(price);

  res.json({ success: true, product });
});

app.delete('/api/products/:id', (req, res) => {
  products = products.filter(p => p.id !== req.params.id);
  res.json({ success: true });
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
  const whatsappUrl = `https://wa.me/573213391720?text=${encodedMessage}`;

  res.json({ success: true, whatsappUrl });
});

module.exports.handler = serverless(app);
