# 🎯 GUÍA RÁPIDA: Cómo Compartir tu Aplicación con Clientes

## ✅ ARCHIVO LISTO PARA COMPARTIR

**Ubicación:** `build\libs\Store-1.0-SNAPSHOT.jar`
**Tamaño:** ~309 KB (muy ligero)
**Estado:** ✅ Probado y funcionando

---

## 🚀 PASO A PASO: Distribución Gratis

### OPCIÓN 1: Google Drive (RECOMENDADO) 🌟

#### Paso 1: Subir a Google Drive
```
1. Ve a https://drive.google.com
2. Inicia sesión con tu cuenta Gmail
3. Click en "Nuevo" → "Subir archivo"
4. Selecciona: build\libs\Store-1.0-SNAPSHOT.jar
5. Espera a que termine de subir
```

#### Paso 2: Obtener Enlace para Compartir
```
1. Click derecho en el archivo subido
2. Selecciona "Compartir" o "Obtener enlace"
3. Cambia permisos a: "Cualquiera con el enlace"
4. Copia el enlace
```

#### Paso 3: Compartir con Clientes
```
Envía este mensaje a tus clientes:

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🛒 STORE MANAGER - Descarga

Hola! Te comparto la aplicación Store Manager.

📥 DESCARGA AQUÍ:
[PEGA TU ENLACE DE GOOGLE DRIVE]

📋 REQUISITOS:
- Java 11 o superior (descargar de: https://adoptium.net)

🔐 ACCESO:
- Admin: admin / admin123
- Cliente: user / user123

📖 Instrucciones completas adjuntas

¿Necesitas ayuda? Escríbeme!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

---

### OPCIÓN 2: Dropbox

```
1. Ve a https://www.dropbox.com
2. Sube el archivo JAR
3. Click derecho → "Compartir"
4. "Crear enlace" → Copia
5. Envía a tus clientes
```

---

### OPCIÓN 3: OneDrive (Microsoft)

```
1. Ve a https://onedrive.live.com
2. Sube el archivo JAR
3. Click derecho → "Compartir"
4. Copia el enlace
5. Envía a tus clientes
```

---

### OPCIÓN 4: WeTransfer (Sin Registro)

```
1. Ve a https://wetransfer.com
2. Click en "Añadir archivos"
3. Selecciona el JAR
4. Ingresa email del destinatario o "Obtener enlace"
5. Click "Transferir"
6. Copia y comparte el enlace
```

---

## 📦 CREAR PAQUETE COMPLETO PARA DISTRIBUCIÓN

Crea una carpeta con todo lo necesario:

```
Store-Manager-v1.0/
├── Store-1.0-SNAPSHOT.jar  ← Aplicación
├── INSTRUCCIONES.txt       ← Guía de uso
└── LEEME.txt               ← Información rápida
```

### Cómo Crear el Paquete:

1. **Crea una carpeta:**
   ```
   Nueva carpeta llamada: Store-Manager-v1.0
   ```

2. **Copia los archivos:**
   - `build\libs\Store-1.0-SNAPSHOT.jar`
   - `INSTRUCCIONES_CLIENTE.txt` (renombra a INSTRUCCIONES.txt)

3. **Crea LEEME.txt:**
   ```
   STORE MANAGER v1.0
   
   INICIO RÁPIDO:
   1. Instala Java 11+ desde https://adoptium.net
   2. Doble clic en Store-1.0-SNAPSHOT.jar
   3. Login: admin / admin123
   
   Lee INSTRUCCIONES.txt para más detalles.
   ```

4. **Comprime a ZIP:**
   - Click derecho en la carpeta
   - "Enviar a" → "Carpeta comprimida"
   - Resultado: `Store-Manager-v1.0.zip`

5. **Comparte el ZIP:**
   - Sube a Google Drive/Dropbox
   - Comparte enlace con clientes

---

## 🌐 OPCIÓN AVANZADA: Crear Página de Descarga

### Usando GitHub Pages (Gratis)

#### Paso 1: Crear Repositorio en GitHub
```
1. Ve a https://github.com
2. Crea cuenta si no tienes
3. Click "New repository"
4. Nombre: store-manager
5. Marca "Public"
6. Click "Create repository"
```

#### Paso 2: Subir Archivos
```
1. Click "uploading an existing file"
2. Arrastra el JAR y archivos de docs
3. Commit changes
```

#### Paso 3: Habilitar GitHub Pages
```
1. Settings → Pages
2. Source: main branch
3. Guarda
```

#### Paso 4: Crear index.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Store Manager - Descarga</title>
</head>
<body>
    <h1>🛒 Store Manager</h1>
    <p>Sistema de gestión de tienda</p>
    <a href="Store-1.0-SNAPSHOT.jar" download>
        📥 Descargar Aplicación
    </a>
    <h2>Requisitos:</h2>
    <ul>
        <li>Java 11+</li>
        <li>Windows/Mac/Linux</li>
    </ul>
</body>
</html>
```

Tu página estará en: `https://[tu-usuario].github.io/store-manager`

---

## 📧 EMAIL TEMPLATE PARA CLIENTES

```
Asunto: Store Manager - Aplicación de Gestión de Tienda

Hola [Nombre],

Te comparto la aplicación Store Manager para gestionar tu tienda.

📥 DESCARGA:
[Enlace de Google Drive/Dropbox]

📋 REQUISITOS:
• Java 11 o superior
• Descarga Java: https://adoptium.net

🔐 CREDENCIALES INICIALES:
• Administrador: admin / admin123
• Cliente de prueba: user / user123

✨ FUNCIONALIDADES:
✓ Gestión de productos (agregar, editar, eliminar)
✓ Upload de imágenes
✓ Carrito de compras
✓ Envío de órdenes por WhatsApp

📖 Instrucciones detalladas incluidas en el paquete.

¿Necesitas ayuda con la instalación?
Contáctame por WhatsApp: [TU NÚMERO]

Saludos!
```

---

## 🎁 BONUS: Auto-Actualizaciones Simples

Para actualizar la app en el futuro:

1. **Haz cambios en el código**
2. **Regenera el JAR:**
   ```powershell
   .\gradlew clean jar
   ```
3. **Renombra con versión:**
   - `Store-1.1.0.jar`
   - `Store-1.2.0.jar`
4. **Sube la nueva versión**
5. **Notifica a clientes por email**

---

## 📊 TRACKING DE DISTRIBUCIÓN

Lleva un registro de a quién enviaste la app:

```
Cliente         | Fecha      | Versión | Estado
----------------|------------|---------|--------
Juan Pérez      | 09/02/2026 | 1.0     | ✅ OK
María García    | 09/02/2026 | 1.0     | ⏳ Pendiente
Carlos López    | 10/02/2026 | 1.0     | ✅ OK
```

---

## ✅ CHECKLIST ANTES DE COMPARTIR

Marca cada item antes de distribuir:

- [ ] JAR generado y probado
- [ ] Instrucciones creadas
- [ ] Credenciales de admin documentadas
- [ ] Número de WhatsApp configurado (3213391720)
- [ ] ZIP creado (si aplica)
- [ ] Subido a plataforma de distribución
- [ ] Enlace probado y funcional
- [ ] Email/mensaje preparado
- [ ] Soporte técnico listo

---

## 🎯 RESUMEN EJECUTIVO

**LO MÁS FÁCIL Y RÁPIDO:**

1. Sube `build\libs\Store-1.0-SNAPSHOT.jar` a Google Drive
2. Obtén enlace para compartir
3. Envía enlace + instrucciones por WhatsApp/Email
4. ¡Listo!

**Tiempo estimado:** 5 minutos
**Costo:** $0 (100% gratis)

---

¡Tu aplicación está lista para llegar a miles de clientes! 🚀
