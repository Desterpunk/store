#!/bin/bash
# Script para subir el proyecto a GitHub y Netlify

echo "=========================================="
echo "  Store Manager - Deploy a Netlify"
echo "=========================================="
echo ""

# Verificar que estamos en la carpeta correcta
if [ ! -f "package.json" ]; then
    echo "❌ Error: package.json no encontrado"
    echo "Ejecuta este script desde la carpeta del proyecto"
    exit 1
fi

echo "✅ Iniciando configuración de Git..."
echo ""

# Inicializar git si no está inicializado
if [ ! -d ".git" ]; then
    git init
    echo "✅ Repositorio Git inicializado"
else
    echo "✅ Repositorio Git ya existe"
fi

echo ""
echo "Agregar todos los archivos..."
git add .

echo ""
echo "Crear primer commit..."
git commit -m "Store Manager - Deploy a Netlify" --allow-empty

echo ""
echo "=========================================="
echo "  PRÓXIMOS PASOS:"
echo "=========================================="
echo ""
echo "1. Ve a https://github.com/new"
echo "2. Crea un repositorio llamado 'store-manager'"
echo "3. Copia el enlace (algo como: https://github.com/TU-USUARIO/store-manager.git)"
echo "4. Ejecuta en PowerShell:"
echo ""
echo "   git remote add origin [TU-ENLACE-GITHUB]"
echo "   git branch -M main"
echo "   git push -u origin main"
echo ""
echo "5. Luego ve a https://app.netlify.com"
echo "6. New site from Git → GitHub → store-manager → Deploy!"
echo ""
echo "=========================================="
