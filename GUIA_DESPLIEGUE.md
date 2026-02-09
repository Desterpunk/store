# 🚀 Guía de Despliegue Gratuito - Store Manager

## 📦 Opciones para Compartir tu Aplicación

Tienes **3 opciones principales** para compartir tu aplicación de tienda con clientes:

---

## ⭐ OPCIÓN 1: ARCHIVO JAR EJECUTABLE (MÁS FÁCIL Y RECOMENDADO)

### ✅ Ventajas
- No requiere hosting
- Los clientes solo necesitan Java instalado
- Un solo archivo .jar para compartir
- Funciona offline
- Gratis 100%

### 📝 Cómo Crear el JAR

Ya he configurado el proyecto. Solo ejecuta:

```powershell
.\gradlew jar
```

El archivo JAR se creará en: `build\libs\Store-1.0-SNAPSHOT.jar`

### 📤 Cómo Compartir

**Opción A - Google Drive:**
1. Sube el archivo JAR a Google Drive
2. Comparte el enlace con tus clientes
3. Los clientes descargan y ejecutan con doble clic

**Opción B - Dropbox:**
1. Sube a Dropbox
2. Comparte enlace público
3. Los clientes descargan el JAR

**Opción C - OneDrive:**
1. Sube a OneDrive
2. Obtén enlace para compartir
3. Envía a tus clientes

**Opción D - WeTransfer (hasta 2GB gratis):**
1. Ve a https://wetransfer.com
2. Sube el JAR
3. Envía por email a tus clientes

### 💻 Cómo los Clientes Ejecutan la App

**Windows:**
```
Doble clic en Store-1.0-SNAPSHOT.jar
```

O desde terminal:
```powershell
java -jar Store-1.0-SNAPSHOT.jar
```

**Requisito:** Los clientes necesitan tener **Java 11 o superior** instalado.

---

## 🌐 OPCIÓN 2: CONVERTIR A APLICACIÓN WEB (GitHub Pages + Replit)

### Plataformas Gratuitas:

#### A) **Replit** (Recomendado para apps Java)
- **URL:** https://replit.com
- **Plan Gratis:** ✅ Sí
- **Límites:** Apps simples gratis, siempre activo
- **Ventajas:** No requiere configuración compleja

**Pasos:**
1. Crea cuenta en Replit.com
2. Crea nuevo Repl → Java
3. Sube tu código
4. Agrega `run.sh` con: `./gradlew run`
5. Comparte URL pública

#### B) **Render** (Para aplicaciones web)
- **URL:** https://render.com
- **Plan Gratis:** ✅ Sí (con limitaciones)
- **Ventajas:** Hosting profesional

#### C) **Railway** 
- **URL:** https://railway.app
- **Plan Gratis:** ✅ $5 crédito mensual
- **Ventajas:** Fácil despliegue

---

## 💿 OPCIÓN 3: CREAR INSTALADOR PARA WINDOWS

Convertir tu app en un .EXE instalable profesional.

### Herramientas Gratuitas:

#### A) **jpackage** (Incluido en JDK 14+)
Crea instaladores nativos para Windows, Mac y Linux.

#### B) **Launch4j** (Windows EXE)
- **URL:** https://launch4j.sourceforge.net
- Convierte JAR a EXE
- Los clientes no necesitan Java instalado

---

## 🎯 RECOMENDACIÓN PARA TU CASO

### **Para Comenzar: OPCIÓN 1 (JAR + Google Drive)**

**¿Por qué?**
- ✅ Más fácil y rápido
- ✅ No requiere servidor
- ✅ 100% gratis sin límites
- ✅ Los clientes pueden trabajar offline
- ✅ Todas las funciones (carrito, WhatsApp, imágenes) funcionan

### **Pasos Rápidos:**

1. **Crear el JAR:**
   ```powershell
   cd C:\Users\jhon.serna_amaris\Desktop\Store
   .\gradlew clean jar
   ```

2. **Encontrar el archivo:**
   - Ubicación: `build\libs\Store-1.0-SNAPSHOT.jar`

3. **Subir a Google Drive:**
   - Ve a https://drive.google.com
   - Arrastra el archivo JAR
   - Click derecho → Obtener enlace
   - Cambia a "Cualquiera con el enlace"

4. **Compartir con clientes:**
   - Envía el enlace de Google Drive
   - Incluye instrucciones (ver más abajo)

---

## 📋 INSTRUCCIONES PARA TUS CLIENTES

Crea un documento con estas instrucciones:

```
=================================================================
          CÓMO INSTALAR Y USAR STORE MANAGER
=================================================================

REQUISITOS:
- Windows 7 o superior
- Java 11 o superior instalado

SI NO TIENES JAVA:
1. Descarga Java desde: https://adoptium.net
2. Instala Java siguiendo el asistente

CÓMO EJECUTAR LA APLICACIÓN:
1. Descarga el archivo "Store-1.0-SNAPSHOT.jar"
2. Haz doble clic en el archivo
3. La aplicación se abrirá automáticamente

CREDENCIALES DE ACCESO:
- Usuario Admin: admin / admin123
- Usuario Cliente: user / user123

SOPORTE:
- WhatsApp: [TU NÚMERO]
- Email: [TU EMAIL]
=================================================================
```

---

## 🔧 CREACIÓN DEL JAR EJECUTABLE

### Paso 1: Configurar Manifest

Ya está configurado en el proyecto. El archivo JAR incluirá:
- ✅ Todas las clases
- ✅ Dependencias (Gson)
- ✅ Clase Main como punto de entrada

### Paso 2: Generar JAR

```powershell
.\gradlew clean build jar
```

### Paso 3: Probar Localmente

```powershell
java -jar build\libs\Store-1.0-SNAPSHOT.jar
```

Si abre la aplicación, ¡está listo para compartir! ✅

---

## 📊 COMPARACIÓN DE OPCIONES

| Opción | Costo | Dificultad | Offline | Recomendado |
|--------|-------|------------|---------|-------------|
| JAR + Drive | Gratis | ⭐ Fácil | ✅ Sí | ⭐⭐⭐⭐⭐ |
| Replit | Gratis | ⭐⭐ Media | ❌ No | ⭐⭐⭐ |
| EXE Installer | Gratis | ⭐⭐⭐ Alta | ✅ Sí | ⭐⭐⭐⭐ |
| Railway/Render | Gratis* | ⭐⭐⭐ Alta | ❌ No | ⭐⭐ |

*Limitaciones en plan gratis

---

## 🎁 BONUS: CREAR VERSIÓN PORTABLE

Crea una carpeta completa que incluya:
```
Store-Portable/
├── Store-1.0-SNAPSHOT.jar
├── run.bat
├── README.txt (instrucciones)
└── jre/ (opcional: Java incluido)
```

Comprime en ZIP y comparte. Los clientes solo descomprimen y ejecutan.

---

## 🌟 SIGUIENTE NIVEL: DISTRIBUCIÓN PROFESIONAL

Si quieres algo más profesional en el futuro:

1. **Crear Instalador con InnoSetup** (Windows)
2. **Publicar en Microsoft Store** (requiere cuenta de desarrollador)
3. **Crear página web de descarga** (GitHub Pages gratis)
4. **Sistema de actualizaciones automáticas**

---

## ❓ PREGUNTAS FRECUENTES

**Q: ¿Los clientes necesitan instalar algo?**
A: Solo Java 11+. El JAR es portable.

**Q: ¿Funciona en Mac/Linux?**
A: Sí, Java es multiplataforma. Funciona en Windows, Mac y Linux.

**Q: ¿Cuánto pesa el archivo?**
A: Aproximadamente 5-10 MB (muy ligero).

**Q: ¿Se pueden hacer actualizaciones?**
A: Sí, generas un nuevo JAR y lo compartes nuevamente.

**Q: ¿Los datos se guardan?**
A: Sí, en products.json en la misma carpeta del JAR.

---

## 📞 PRÓXIMOS PASOS

1. ✅ Generar el JAR
2. ✅ Probarlo localmente
3. ✅ Subirlo a Google Drive/Dropbox
4. ✅ Crear documento de instrucciones
5. ✅ Compartir con clientes
6. ✅ Recibir feedback y mejorar

---

**¡Tu aplicación está lista para compartir! 🎉**
