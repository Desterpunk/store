# 🛒 Store Manager - Tienda Online

Sistema de gestión de tienda en línea con interfaz web moderna, desplegado en Netlify.

## 🚀 Características

- ✅ Panel de administrador (CRUD de productos)
- ✅ Panel de cliente (carrito de compras)
- ✅ Autenticación de usuarios
- ✅ Integración con WhatsApp (3213391720)
- ✅ Interfaz responsive (móvil + desktop)
- ✅ 100% gratuito en Netlify

## 🔐 Acceso

**Administrador:**
- Usuario: `admin`
- Contraseña: `admin123`

**Cliente de prueba:**
- Usuario: `user`
- Contraseña: `user123`

## 📋 Requisitos

- Node.js 18+
- npm
- Git

## 🛠️ Instalación Local

```bash
# Instalar dependencias
npm install

# Ejecutar servidor local
npm start
```

La aplicación estará disponible en `http://localhost:5000`

## 🌐 Desplegar en Netlify

1. Crea una cuenta en [GitHub](https://github.com)
2. Crea un repositorio llamado `store-manager`
3. Sube tu código:
   ```bash
   git init
   git add .
   git commit -m "Store Manager"
   git remote add origin https://github.com/TU-USUARIO/store-manager.git
   git push -u origin main
   ```
4. Ve a [Netlify](https://app.netlify.com)
5. Sign up with GitHub
6. New site from Git → selecciona `store-manager`
7. ¡Deploy!

Tu app estará en: `https://store-manager-xxxxx.netlify.app`

## 📁 Estructura

```
Store/
├── server.js                    # Backend (desarrollo local)
├── netlify/functions/server.js  # Backend serverless
├── public/
│   ├── index.html              # Login
│   └── dashboard.html          # Panel principal
├── package.json                # Dependencias
└── netlify.toml                # Configuración Netlify
```

## 🔄 Actualizar el código

```bash
git add .
git commit -m "Tu descripción"
git push
```

Netlify se actualiza automáticamente en 1-2 minutos.

## ⚠️ Notas

- Los datos se guardan en memoria (se pierden con redeploy)
- Para datos persistentes, conecta MongoDB Atlas (gratis)
- Las imágenes no persisten, considera usar Cloudinary (gratis)

## 📞 WhatsApp

Los pedidos se envían al número: **3213391720**

## 📄 Licencia

Proyecto de código abierto.

