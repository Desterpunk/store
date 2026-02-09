# 📦 Sistema de Tienda - Resumen del Proyecto

## ✅ Proyecto Completado

Se ha creado exitosamente un sistema completo de tienda con interfaz gráfica en Java Swing.

## 🎯 Funcionalidades Implementadas

### 👨‍💼 Panel de Administrador (Usuario: admin / Contraseña: admin123)
✅ Agregar productos
✅ Editar productos (nombre, precio, imagen)
✅ Eliminar productos
✅ Vista de lista de productos con imágenes
✅ Persistencia de datos en JSON

### 👤 Panel de Cliente (Usuario: user / Contraseña: user123)
✅ Ver catálogo de productos con imágenes
✅ Agregar productos al carrito
✅ Incremento automático de cantidad para productos duplicados
✅ Quitar productos del carrito
✅ Visualización del total de compra
✅ Envío de orden por WhatsApp al número 3213391720
✅ Apertura automática de WhatsApp Web con resumen de compra

### 🔐 Sistema de Autenticación
✅ Login de usuarios
✅ Registro de nuevos usuarios
✅ Diferenciación entre admin y clientes
✅ Cierre de sesión

## 📁 Estructura de Archivos Creados

```
Store/
├── src/main/java/org/example/
│   ├── Main.java                    ✅ Punto de entrada
│   ├── model/
│   │   ├── Product.java            ✅ Modelo de producto
│   │   ├── User.java               ✅ Modelo de usuario
│   │   └── CartItem.java           ✅ Modelo de item del carrito
│   ├── service/
│   │   ├── ProductService.java     ✅ Gestión de productos + JSON
│   │   ├── UserService.java        ✅ Autenticación de usuarios
│   │   └── CartService.java        ✅ Gestión del carrito
│   └── ui/
│       ├── LoginFrame.java         ✅ Pantalla de login
│       ├── AdminFrame.java         ✅ Panel administrativo
│       └── CustomerFrame.java      ✅ Panel de cliente/compras
├── build.gradle.kts                 ✅ Configuración con Gson
├── README.md                        ✅ Documentación principal
├── GUIA_DE_USO.md                  ✅ Guía detallada de uso
└── run.bat                         ✅ Script de ejecución rápida
```

## 🚀 Cómo Ejecutar

### Opción 1: Script BAT (Windows)
```
Doble clic en run.bat
```

### Opción 2: Gradle
```powershell
.\gradlew run
```

### Opción 3: IntelliJ IDEA
```
Click derecho en Main.java → Run 'Main'
```

## 🔑 Credenciales de Acceso

| Rol          | Usuario | Contraseña | Permisos                    |
|--------------|---------|------------|-----------------------------|
| Administrador| admin   | admin123   | CRUD completo de productos |
| Cliente      | user    | user123    | Ver y comprar productos    |

## 📱 Integración WhatsApp

**Número configurado**: 3213391720

Al realizar una compra, se genera automáticamente un mensaje con:
- Lista de productos con cantidades
- Subtotales por producto
- Total de la compra
- Formato con emojis para mejor visualización

## 💾 Persistencia de Datos

- **Productos**: Se guardan en `products.json` (se crea automáticamente)
- **Imágenes**: Se copian a la carpeta `images/` (se crea automáticamente)
- **Usuarios**: En memoria (se pierden al cerrar la aplicación)

## 🎨 Características Técnicas

### Frontend
- **Framework**: Java Swing
- **Look & Feel**: System native
- **Componentes**: JFrame, JList, JScrollPane, JSplitPane, etc.
- **Renderers**: Custom cell renderers para mostrar imágenes

### Backend
- **Serialización**: Gson para JSON
- **Arquitectura**: MVC (Model-View-Controller)
- **Patrones**: Service Layer, Repository Pattern

### Integraciones
- **WhatsApp**: Integración vía URL scheme (wa.me)
- **File System**: Gestión de imágenes con UUID
- **Browser**: Desktop API para abrir navegador

## ✨ Características Destacadas

1. **Interfaz Intuitiva**: Diseño limpio y fácil de usar
2. **Gestión de Imágenes**: Copia automática y resize de imágenes
3. **Carrito Inteligente**: Agrupa productos duplicados incrementando cantidad
4. **Persistencia Automática**: Guarda cambios inmediatamente
5. **WhatsApp Integration**: Envío directo de órdenes
6. **Doble Panel**: Vista separada para admin y clientes
7. **Seguridad Básica**: Autenticación de usuarios
8. **Extensible**: Código modular y bien organizado

## 🔄 Flujo de Uso Completo

### Admin
1. Login como admin
2. Agregar productos (nombre, precio, imagen)
3. Ver lista de productos
4. Editar/eliminar según necesidad
5. Los cambios se guardan automáticamente

### Cliente
1. Login como cliente o registrarse
2. Ver catálogo de productos
3. Agregar productos al carrito
4. Revisar carrito y total
5. Confirmar compra
6. WhatsApp se abre con el resumen
7. Enviar mensaje al vendedor

## 📊 Próximas Mejoras Posibles

- [ ] Persistencia de usuarios en base de datos
- [ ] Historial de compras
- [ ] Categorías de productos
- [ ] Búsqueda y filtros
- [ ] Gestión de stock/inventario
- [ ] Reportes de ventas para admin
- [ ] Múltiples imágenes por producto
- [ ] Sistema de descuentos
- [ ] Exportar órdenes a PDF

## 🐛 Estado del Proyecto

✅ **Compilación**: Exitosa
✅ **Tests**: Build successful
✅ **Funcionalidad**: Completa según especificaciones
✅ **Documentación**: README + Guía de uso
✅ **Deployment**: Listo para ejecutar

## 📞 Soporte

Para cualquier duda o problema:
1. Revisar `GUIA_DE_USO.md`
2. Revisar `README.md`
3. Verificar logs en consola
4. Comprobar que Java 11+ esté instalado

---

**¡El proyecto está listo para usar! 🎉**

Ejecuta `run.bat` o `.\gradlew run` para comenzar.
