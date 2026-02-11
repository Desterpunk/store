# 🔧 SOLUCIÓN: Persistencia de Datos en Netlify

## 📌 El Problema
Los productos se guardaban en `data.json` (archivo local) que Netlify borraba en cada redeploy. **Resultado:** los datos desaparecían.

## ✅ La Solución
Migración a **Supabase** (base de datos gratuita en la nube). Los datos ahora persisten de forma permanente.

---

## 🚀 PASOS A SEGUIR (20 minutos total)

### 1️⃣ Crear Supabase (5 minutos)
```
1. Ve a https://supabase.com
2. Regístrate gratis
3. Crea un proyecto nuevo
4. Copia la URL y la clave anon (los necesitarás)
```

### 2️⃣ Configurar Base de Datos (8 minutos)

**A) Crear tablas - Abre SQL Editor en Supabase y ejecuta:**
```sql
CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  username TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  is_admin BOOLEAN DEFAULT false,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE products (
  id UUID PRIMARY KEY,
  name TEXT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  image_path TEXT,
  created_at TIMESTAMP DEFAULT NOW()
);

INSERT INTO users (username, password, is_admin) VALUES
  ('admin', 'admin123', true),
  ('user', 'user123', false);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_products_created_at ON products(created_at DESC);
```

**B) Crear bucket para imágenes:**
```
1. Ve a Storage en Supabase
2. Crea bucket "uploads"
3. Marca como "Public bucket" ✅
```

### 3️⃣ Configurar Netlify (7 minutos)
```
1. Ve a https://app.netlify.com → Tu sitio → Site settings
2. Build & deploy → Environment
3. Agrega estas variables:
   - SUPABASE_URL = https://tu-proyecto.supabase.co
   - SUPABASE_KEY = tu-clave-anon
4. Guarda
5. Ve a Deploys → Trigger deploy (redeploy manual)
```

---

## 🖼️ ¿Cómo funcionan las imágenes?

Tu tienda ahora **soporta 2 opciones** para imágenes:

### Opción 1: URLs de la web ✅ (SÍ funcionan)
Puedes copiar una URL de cualquier imagen de internet:
- `https://ejemplo.com/imagen.jpg`
- `https://i.pinimg.com/...`
- `https://images.unsplash.com/...`

Simplemente pega la URL en el campo de imagen. **No necesitas subir el archivo.**

### Opción 2: Subir imágenes locales ✅
También puedes subir imágenes desde tu computadora. Se guardarán en **Supabase Storage** (nube) y obtendrán una URL pública permanente.

### ¿Cuál usar?

| Caso | Recomendación |
|------|---------------|
| Usar imágenes de Google/Pinterest | Opción 1 (URL de web) |
| Subir mis propias fotos | Opción 2 (subir archivo) |
| Garantizar que no desaparezca | Opción 2 (Supabase Storage) |
| Algo rápido | Opción 1 (URL de web) |

---

## ✨ Lo que cambió en el código

| Archivo | Cambio |
|---------|--------|
| **server.js** | Ahora usa Supabase + permite URLs de web |
| **netlify/functions/server.js** | Ahora usa Supabase + permite URLs de web |
| **public/store.html** | `imagePath` → `image_path` |
| **public/dashboard.html** | `imagePath` → `image_path` |
| **package.json** | Agregado `@supabase/supabase-js` |

---

## 🔑 Variables de Entorno Necesarias

**En Netlify (sitio en producción):**
```
SUPABASE_URL = https://tu-proyecto.supabase.co
SUPABASE_KEY = tu-clave-anon
```

**Para desarrollo local (archivo .env):**
```
SUPABASE_URL=https://tu-proyecto.supabase.co
SUPABASE_KEY=tu-clave-anon
SUPABASE_SERVICE_ROLE_KEY=tu-service-role-key
```

⚠️ **NO subas .env a GitHub** (ya está en .gitignore)

---

## 🛠️ Scripts Disponibles

### Migrar datos viejos (opcional)
```bash
npm run migrate
```
Esto copia productos y usuarios de `data.json` a Supabase.

### Instalar dependencias
```bash
npm install
```

### Iniciar servidor local
```bash
npm start
```

---

## ✅ Verificar que funciona

1. Crea un producto (nombre + precio + imagen)
2. Fuerza redeploy en Netlify
3. Recarga la página
4. Si el producto **sigue ahí** → ✅ **FUNCIONA**

---

## 🆘 Si algo falla

**Error: "Supabase URL no configurado"**
→ Verifica que agregaste las variables en Netlify correctamente

**Error: "Las imágenes no se suben"**
→ Asegúrate que el bucket "uploads" está marcado como "Public bucket"

**Error: "No puedo conectarme a Supabase"**
→ Verifica que la URL y clave son correctas (sin espacios)

**Los productos no aparecen después del redeploy**
→ Fuerza un redeploy en Netlify después de agregar las variables

---

## 📊 Antes vs Después

| Aspecto | Antes | Después |
|---------|-------|---------|
| Almacenamiento | Archivo JSON local | PostgreSQL remoto |
| Persistencia | ❌ Se pierde en redeploy | ✅ Permanente |
| Imágenes | Carpeta local /uploads | URL web o Supabase Storage |
| Costo | Espacio del servidor | ✅ Gratuito ($0) |
| Escalabilidad | Limitada | ✅ Ilimitada |
| Backup | ❌ Manual | ✅ Automático |

---

## 📚 Recursos

- **Supabase Docs:** https://supabase.com/docs
- **Netlify Docs:** https://docs.netlify.com
- **Discord Supabase:** https://discord.supabase.io

---

## 🎯 Resumen

**Hicimos:** Migración completa a Supabase + soporte para URLs de web  
**Se cambió:** 5 archivos (server.js, HTML, package.json)  
**Se creó:** Script de migración (migrate.js)  
**Tiempo para activar:** 20 minutos  
**Resultado:** ✅ Datos persistentes + imágenes flexibles

**¡Listo para implementar!**

