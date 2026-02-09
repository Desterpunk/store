# 🛒 Store Manager - Tienda Online

Sistema de gestión de tienda en línea con interfaz web moderna, desplegado en Netlify.

[![Netlify Status](https://img.shields.io/badge/Netlify-Deployed-success)](https://superstore124.netlify.app/)

## 🚀 Demo en Vivo

**URL:** https://superstore124.netlify.app/

## 🔐 Credenciales de Acceso

**Administrador:**
- Usuario: `admin`
- Contraseña: `admin123`

**Cliente de prueba:**
- Usuario: `user`
- Contraseña: `user123`

## ✨ Características

### Panel de Administrador
- ✅ Agregar productos (nombre, precio, imagen)
- ✅ Editar productos existentes
- ✅ Eliminar productos
- ✅ Vista de catálogo con imágenes

### Panel de Cliente
- ✅ Ver productos disponibles
- ✅ Agregar productos al carrito
- ✅ Gestión del carrito (agregar/quitar)
- ✅ Cálculo automático de totales
- ✅ Envío de orden por WhatsApp (3213391720)

### General
- ✅ Autenticación de usuarios
- ✅ Registro de nuevos clientes
- ✅ Interfaz responsive (móvil + desktop)
- ✅ Completamente gratuito

## 🖼️ Imágenes de Productos

Las imágenes funcionan mediante URLs externas. Puedes usar servicios gratuitos como:

- **Imgur:** https://imgur.com (Recomendado)
- **Postimages:** https://postimages.org
- **ImgBB:** https://imgbb.com
- **Unsplash:** https://unsplash.com (Imágenes profesionales)

### Cómo agregar imágenes:

1. Sube tu imagen a Imgur o similar
2. Copia la URL directa de la imagen
3. Pégala en el campo "URL de Imagen" al agregar/editar producto

**Ejemplo de URL válida:**
```
https://i.imgur.com/abc123.jpg
```

## 🛠️ Instalación Local

```bash
# Instalar dependencias
npm install

# Ejecutar servidor local
npm start
```

La aplicación estará disponible en `http://localhost:5000`

## 🌐 Desplegar en Netlify

### Requisitos
- Cuenta en [GitHub](https://github.com)
- Cuenta en [Netlify](https://netlify.com)

### Pasos

1. **Fork/Clone este repositorio**

2. **Sube a tu GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Store Manager"
   git remote add origin https://github.com/TU-USUARIO/store-manager.git
   git push -u origin main
   ```

3. **Conecta con Netlify:**
   - Ve a https://app.netlify.com
   - New site from Git
   - Selecciona tu repositorio
   - Deploy!

4. **¡Listo!** Tu app estará en: `https://tu-app.netlify.app`

## 📁 Estructura del Proyecto

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

## 🔄 Actualizar el Código

```bash
git add .
git commit -m "Tu descripción"
git push
```

Netlify se actualiza automáticamente en 1-2 minutos.

## ⚠️ Limitaciones

- **Datos en memoria:** Se pierden con cada redeploy de Netlify
  - Solución futura: Conectar MongoDB Atlas (gratis)
  
- **Imágenes:** Deben estar hospedadas externamente
  - Usa Imgur, Postimages, etc.

## 📞 WhatsApp Integration

Los pedidos se envían automáticamente al número: **3213391720**

Formato del mensaje:
```
🛒 *Orden de Compra*

• Producto 1 x2 - $199.98
• Producto 2 x1 - $99.99

*Total: $299.97*
```

## 🧪 Tecnologías

- **Frontend:** HTML5, CSS3, JavaScript
- **Backend:** Node.js, Express
- **Serverless:** Netlify Functions
- **Deployment:** Netlify
- **Costo:** $0 (100% Gratis)

## 📝 Notas

- Los usuarios nuevos se registran como clientes (no admin)
- El carrito se vacía después de confirmar compra
- Las sesiones se manejan con localStorage
- Interfaz en español con soporte completo de tildes y emojis

## 🤝 Contribuir

¿Encontraste un bug? ¿Tienes una sugerencia?
- Abre un Issue
- Envía un Pull Request

## 📄 Licencia

MIT - Proyecto de código abierto

---

**Desarrollado con ❤️ para emprendedores**

¿Necesitas ayuda? Abre un issue en GitHub
