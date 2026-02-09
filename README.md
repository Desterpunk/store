# Store - Sistema de Tienda

## Descripción
Sistema de tienda con interfaz gráfica en Java Swing que permite:

### Funcionalidades Admin
- Agregar productos
- Editar productos (nombre, precio, imagen)
- Eliminar productos
- Gestión completa del inventario

### Funcionalidades Cliente
- Ver catálogo de productos
- Agregar productos al carrito
- Quitar productos del carrito
- Realizar compra y enviar orden por WhatsApp

## Credenciales de Acceso

### Administrador
- Usuario: `admin`
- Contraseña: `admin123`

### Cliente de Prueba
- Usuario: `user`
- Contraseña: `user123`

También puedes registrar nuevos usuarios desde la pantalla de login.

## Cómo Ejecutar

1. Asegúrate de tener JDK 11 o superior instalado
2. Ejecuta el proyecto con Gradle:
   ```
   .\gradlew run
   ```
   O desde IntelliJ IDEA: Click derecho en Main.java -> Run 'Main'

## Características Técnicas

- **Persistencia**: Los productos se guardan en un archivo JSON (`products.json`)
- **Imágenes**: Las imágenes de productos se copian a la carpeta `images/`
- **WhatsApp**: Al finalizar la compra, se abre WhatsApp Web con el resumen de la orden al número 3213391720

## Estructura del Proyecto

```
src/main/java/org/example/
├── Main.java                 # Punto de entrada
├── model/
│   ├── Product.java         # Modelo de producto
│   ├── User.java            # Modelo de usuario
│   └── CartItem.java        # Modelo de item del carrito
├── service/
│   ├── ProductService.java  # Servicio de gestión de productos
│   ├── UserService.java     # Servicio de autenticación
│   └── CartService.java     # Servicio del carrito de compras
└── ui/
    ├── LoginFrame.java      # Pantalla de login
    ├── AdminFrame.java      # Pantalla de administración
    └── CustomerFrame.java   # Pantalla de cliente/compras
```

## Uso

1. **Login**: Inicia sesión como admin o usuario regular
2. **Admin**: 
   - Agrega productos con nombre, precio e imagen
   - Edita o elimina productos existentes
3. **Cliente**: 
   - Navega por el catálogo
   - Agrega productos al carrito
   - Finaliza la compra (se abrirá WhatsApp con el resumen)

## Tecnologías
- Java 11+
- Swing (GUI)
- Gson (Serialización JSON)
- Gradle (Build tool)
