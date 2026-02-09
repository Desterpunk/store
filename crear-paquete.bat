@echo off
echo ========================================
echo   Creando Paquete de Distribucion
echo   Store Manager v1.0
echo ========================================
echo.

REM Crear carpeta de distribucion
if exist "Store-Distribution" rmdir /s /q "Store-Distribution"
mkdir "Store-Distribution"

echo [1/5] Generando archivo JAR...
call gradlew.bat clean jar
if errorlevel 1 (
    echo ERROR: No se pudo generar el JAR
    pause
    exit /b 1
)

echo [2/5] Copiando JAR ejecutable...
copy "build\libs\Store-1.0-SNAPSHOT.jar" "Store-Distribution\" >nul

echo [3/5] Copiando instrucciones para clientes...
copy "INSTRUCCIONES_CLIENTE.txt" "Store-Distribution\INSTRUCCIONES.txt" >nul

echo [4/5] Creando archivo LEEME.txt...
(
echo ========================================
echo   STORE MANAGER v1.0
echo ========================================
echo.
echo INICIO RAPIDO:
echo 1. Instala Java 11+ desde https://adoptium.net
echo 2. Doble clic en Store-1.0-SNAPSHOT.jar
echo 3. Login: admin / admin123
echo.
echo Lee INSTRUCCIONES.txt para detalles completos.
echo.
echo Soporte: [TU CONTACTO AQUI]
echo ========================================
) > "Store-Distribution\LEEME.txt"

echo [5/5] Creando archivo ZIP...
powershell Compress-Archive -Path "Store-Distribution\*" -DestinationPath "Store-Manager-v1.0.zip" -Force

echo.
echo ========================================
echo   PAQUETE CREADO EXITOSAMENTE!
echo ========================================
echo.
echo Archivo creado: Store-Manager-v1.0.zip
echo Tamano:
dir "Store-Manager-v1.0.zip" | find ".zip"
echo.
echo PROXIMOS PASOS:
echo 1. Sube Store-Manager-v1.0.zip a Google Drive
echo 2. Comparte el enlace con tus clientes
echo 3. Envia las instrucciones
echo.
echo ========================================
pause
