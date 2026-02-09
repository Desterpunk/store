# 🚀 GUÍA DE DESPLIEGUE EN NETLIFY

## ¿Qué es Netlify?

Netlify es una plataforma **GRATUITA** que te permite desplegar aplicaciones web con:
- ✅ Hosting ilimitado
- ✅ HTTPS automático
- ✅ Dominio gratis (tuapp.netlify.app)
- ✅ Actualizaciones automáticas desde GitHub
- ✅ Funciones serverless

---

## 📋 REQUISITOS

1. Cuenta de GitHub (gratis en https://github.com)
2. Cuenta de Netlify (gratis en https://netlify.com)
3. Git instalado en tu computadora

---

## 🔧 PASO 1: PREPARAR EL PROYECTO

### 1.1 Actualizar archivos necesarios

```bash
# El proyecto ya tiene:
# - public/index.html (login)
# - public/dashboard.html (aplicación)
# - netlify/functions/server.js (backend)
# - netlify.toml (configuración)
# - package.json (dependencias)
```

### 1.2 Instalar dependencias localmente

```bash
cd C:\Users\jhon.serna_amaris\Desktop\Store
npm install
```

---

## 🌐 PASO 2: SUBIR A GITHUB

### 2.1 Crear repositorio en GitHub

```bash
# Abre terminal en la carpeta del proyecto
cd C:\Users\jhon.serna_amaris\Desktop\Store

# Inicializar git
git init

# Agregar archivos
git add .

# Crear commit
git commit -m "Store Manager - Versión web para Netlify"
```

### 2.2 Crear repositorio en GitHub.com

1. Ve a https://github.com/new
2. Nombre: `store-manager`
3. Descripción: `Sistema de gestión de tienda en línea`
4. Selecciona: Public (para Netlify gratis)
5. Click "Create repository"

### 2.3 Subir código a GitHub

```bash
# Reemplaza TU-USUARIO con tu usuario de GitHub
git remote add origin https://github.com/TU-USUARIO/store-manager.git
git branch -M main
git push -u origin main
```

---

## ⚡ PASO 3: CONECTAR GITHUB CON NETLIFY

### 3.1 Ir a Netlify

1. Ve a https://netlify.com
2. Click "Sign up" (Regístrate)
3. Selecciona "Sign up with GitHub"
4. Autoriza Netlify

### 3.2 Crear nuevo sitio

1. Dashboard de Netlify
2. Click "New site from Git"
3. Selecciona "GitHub"
4. Busca tu repositorio: `store-manager`
5. Selecciona `main` branch

### 3.3 Configurar despliegue

**Build settings:**
- Build command: `npm run build`
- Publish directory: `public`
- Functions directory: `netlify/functions`

Click "Deploy site"

---

## ✅ PASO 4: VERIFICAR DESPLIEGUE

1. Espera a que Netlify compile (2-3 minutos)
2. Verás un enlace como: `https://store-manager-123.netlify.app`
3. ¡Tu aplicación está en línea! 🎉

---

## 🔐 CREDENCIALES

Comparte estos accesos con tus clientes:

**Administrador:**
- Usuario: `admin`
- Contraseña: `admin123`

**Cliente de prueba:**
- Usuario: `user`
- Contraseña: `user123`

---

## 📱 USAR LA APLICACIÓN

1. Abre tu enlace de Netlify
2. Login con tus credenciales
3. ¡Empieza a agregar productos!

---

## 🔄 ACTUALIZAR LA APLICACIÓN

Cada vez que hagas cambios:

```bash
git add .
git commit -m "Descripción del cambio"
git push
```

Netlify se actualizará automáticamente en 1-2 minutos.

---

## ⚠️ LIMITACIONES (Y SOLUCIONES)

### 1. Las imágenes se pierden después de redeploy

**Problema:** Las imágenes se guardan en memoria, no persisten.

**Solución:** Usar un servicio como Cloudinary o Firebase Storage

### 2. Los datos se pierden entre llamadas

**Problema:** El JSON se guarda en memoria, no en base de datos.

**Solución:** Usar MongoDB Atlas (gratis) o Firebase

### 3. Solo 100 funciones serverless gratis/mes

**Solución:** Perfecto para aplicaciones pequeñas. Si necesitas más, actualiza el plan.

---

## 🛠️ MEJORA 1: AGREGAR BASE DE DATOS (MONGODB)

### Paso 1: Crear cuenta en MongoDB Atlas

1. Ve a https://cloud.mongodb.com
2. Registrate gratis
3. Crea un cluster
4. Obtén la URL de conexión

### Paso 2: Actualizar server.js

```javascript
const mongoose = require('mongoose');

mongoose.connect(process.env.MONGODB_URI);

// Definir esquema de productos
const productSchema = new mongoose.Schema({
  name: String,
  price: Number,
  imagePath: String
});

const Product = mongoose.model('Product', productSchema);
```

### Paso 3: Agregar variable en Netlify

1. Dashboard de Netlify
2. Site settings → Environment
3. Agregar: `MONGODB_URI` = Tu URL de MongoDB

---

## 🖼️ MEJORA 2: AGREGAR ALMACENAMIENTO DE IMÁGENES

### Opción A: Cloudinary (Recomendado)

1. Regístrate en https://cloudinary.com (gratis)
2. Obtén API Key
3. Instala: `npm install cloudinary multer-storage-cloudinary`
4. Configura en server.js

### Opción B: Firebase Storage

1. Regístrate en https://firebase.google.com (gratis)
2. Obtén credenciales
3. Configura en server.js

---

## 🚀 DOMINIO PERSONALIZADO

### Obtener dominio gratis

1. Freenom.com - Dominios .tk gratis
2. Raindrop.io - Dominios cortos
3. Comprar dominio barato - $1-3/año en Namecheap

### Conectar a Netlify

1. Netlify Dashboard
2. Domain management
3. Add custom domain
4. Sigue las instrucciones

---

## 📊 MONITOREO

### Ver logs

1. Netlify Dashboard
2. Builds → Recent deploys
3. Ver logs de compilación

### Ver errores

1. Abre tu app
2. Presiona F12
3. Pestaña "Console" para ver errores

---

## 💬 COMPARTIR CON CLIENTES

```
URL: https://tu-store-manager.netlify.app
Usuario admin: admin / admin123
Usuario cliente: user / user123

¡Ahora disponible online! 🎉
```

---

## 🎯 PRÓXIMOS PASOS

1. ✅ Sube a GitHub
2. ✅ Conecta con Netlify
3. ✅ Verifica despliegue
4. ✅ Agrega base de datos
5. ✅ Agrega almacenamiento de imágenes
6. ✅ Obtén dominio personalizado
7. ✅ ¡Comparte con clientes!

---

## 📞 SOPORTE

Si tienes problemas:

1. Verifica logs de Netlify
2. Abre consola del navegador (F12)
3. Lee la documentación de Netlify: https://docs.netlify.com

---

**¡Tu aplicación está lista para el mundo! 🌍**
