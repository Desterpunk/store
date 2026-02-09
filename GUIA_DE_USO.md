# Guía de Uso - Sistema de Tienda

## 🚀 Inicio Rápido

### Opción 1: Ejecutar desde IntelliJ IDEA
1. Abre el proyecto en IntelliJ IDEA
2. Haz clic derecho en `src/main/java/org/example/Main.java`
3. Selecciona "Run 'Main.main()'"

### Opción 2: Ejecutar desde línea de comandos
```powershell
.\gradlew run
```

## 👤 Usuarios Predefinidos

### Administrador
- **Usuario**: admin
- **Contraseña**: admin123
- **Permisos**: Gestión completa de productos

### Cliente
- **Usuario**: user
- **Contraseña**: user123
- **Permisos**: Ver productos y comprar

## 📋 Funcionalidades

### Panel de Administrador

#### ➕ Agregar Producto
1. Inicia sesión como admin
2. Haz clic en "Agregar Producto"
3. Completa los campos:
   - **Nombre**: Nombre del producto
   - **Precio**: Precio en formato numérico (ej: 29.99)
   - **Imagen**: Haz clic en "Buscar..." para seleccionar una imagen (formatos: JPG, PNG, GIF)
4. Haz clic en "Guardar"

#### ✏️ Editar Producto
1. Selecciona un producto de la lista
2. Haz clic en "Editar Producto"
3. Modifica los campos necesarios
4. Haz clic en "Guardar"

#### 🗑️ Eliminar Producto
1. Selecciona un producto de la lista
2. Haz clic en "Eliminar Producto"
3. Confirma la eliminación

### Panel de Cliente

#### 🛒 Agregar al Carrito
1. Inicia sesión como cliente
2. Selecciona un producto de la lista de "Productos Disponibles"
3. Haz clic en "Agregar al Carrito"
4. El producto aparecerá en tu carrito con cantidad 1
5. Si agregas el mismo producto varias veces, incrementará la cantidad

#### ➖ Quitar del Carrito
1. Selecciona un producto en el "Carrito de Compras"
2. Haz clic en "Quitar del Carrito"

#### 💳 Realizar Compra
1. Asegúrate de tener productos en el carrito
2. Haz clic en "Comprar"
3. Confirma la compra
4. Se abrirá WhatsApp Web con el resumen de tu orden
5. El mensaje se enviará al número **3213391720**

## 📱 Integración con WhatsApp

Al realizar una compra, se generará un mensaje automático con el siguiente formato:

```
🛒 *Orden de Compra*

• Producto 1 x2 - $59.98
• Producto 2 x1 - $29.99

*Total: $89.97*
```

El mensaje se abrirá en WhatsApp Web (navegador) o en la aplicación de WhatsApp Desktop si está instalada.

## 💾 Persistencia de Datos

### Archivo de Productos
- **Ubicación**: `products.json` (raíz del proyecto)
- **Formato**: JSON
- Los productos se guardan automáticamente al agregar, editar o eliminar

### Imágenes
- **Ubicación**: Carpeta `images/` (raíz del proyecto)
- Al seleccionar una imagen, se copia automáticamente a esta carpeta
- Los archivos se renombran con UUID para evitar conflictos

## 🔧 Solución de Problemas

### La aplicación no inicia
```powershell
# Limpia y reconstruye el proyecto
.\gradlew clean build
.\gradlew run
```

### Error al abrir WhatsApp
- Asegúrate de tener un navegador web instalado
- Verifica que tu navegador permita abrir enlaces externos
- Comprueba tu conexión a internet

### Las imágenes no se muestran
- Verifica que el archivo de imagen exista en la carpeta `images/`
- Los formatos soportados son: JPG, PNG, GIF
- Asegúrate de que la ruta de la imagen sea correcta

### Los productos no se guardan
- Verifica que tengas permisos de escritura en la carpeta del proyecto
- Comprueba que el archivo `products.json` no esté en uso por otra aplicación

## 📝 Notas Adicionales

- Los productos se ordenan en el orden en que fueron agregados
- El carrito se vacía automáticamente después de confirmar una compra
- Las sesiones no persisten, cada inicio de sesión es independiente
- Puedes registrar nuevos usuarios desde la pantalla de login
- Los nuevos usuarios siempre se crean como clientes (no admin)

## 🎨 Personalización

### Cambiar el número de WhatsApp
Edita el archivo `CustomerFrame.java` línea 168:
```java
String phoneNumber = "573213391720"; // Cambia este número
```

### Modificar usuarios predefinidos
Edita el archivo `UserService.java` en el constructor:
```java
users.put("admin", new User("admin", "admin123", true));
users.put("user", new User("user", "user123", false));
```

## 🐛 Reporte de Problemas

Si encuentras algún problema:
1. Verifica los logs en la consola
2. Revisa que todas las dependencias estén instaladas
3. Asegúrate de tener Java 11 o superior
