@echo off
echo ========================================
echo   SUBIR CORRECCIONES A NETLIFY
echo ========================================
echo.

echo [1/3] Agregando cambios...
git add .

echo [2/3] Creando commit...
git commit -m "Fix: Corregir rutas API para funciones serverless Netlify"

echo [3/3] Subiendo a GitHub...
git push

echo.
echo ========================================
echo   CAMBIOS SUBIDOS EXITOSAMENTE!
echo ========================================
echo.
echo Netlify se actualizara en 1-2 minutos
echo.
echo Verifica en: https://superstore124.netlify.app/
echo.
pause
