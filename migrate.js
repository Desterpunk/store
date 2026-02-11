// migrate.js - Script para migrar datos de data.json a Supabase

const fs = require('fs');
const path = require('path');
const { createClient } = require('@supabase/supabase-js');
require('dotenv').config();

const supabaseUrl = process.env.SUPABASE_URL;
const supabaseKey = process.env.SUPABASE_SERVICE_ROLE_KEY;

if (!supabaseUrl || !supabaseKey) {
  console.error('❌ Error: SUPABASE_URL y SUPABASE_SERVICE_ROLE_KEY no están configuradas');
  console.error('Por favor, crea un archivo .env con estas variables');
  process.exit(1);
}

const supabase = createClient(supabaseUrl, supabaseKey);

async function migrate() {
  try {
    console.log('🔄 Iniciando migración de datos...\n');

    // Leer data.json
    const dataPath = path.join(__dirname, 'data.json');
    if (!fs.existsSync(dataPath)) {
      console.log('⚠️  No se encontró data.json. Creando base de datos vacía...');
      console.log('✅ Migración completada (sin datos previos)');
      return;
    }

    const data = JSON.parse(fs.readFileSync(dataPath, 'utf8'));

    // Migrar usuarios
    if (data.users && data.users.length > 0) {
      console.log(`📝 Migrando ${data.users.length} usuarios...`);

      for (const user of data.users) {
        const { error } = await supabase
          .from('users')
          .upsert({
            username: user.username,
            password: user.password,
            is_admin: user.isAdmin || false
          }, { onConflict: 'username' });

        if (error) {
          console.log(`  ⚠️  Usuario "${user.username}": ${error.message}`);
        } else {
          console.log(`  ✅ Usuario "${user.username}" migrado`);
        }
      }
    }

    // Migrar productos
    if (data.products && data.products.length > 0) {
      console.log(`\n📦 Migrando ${data.products.length} productos...`);

      for (const product of data.products) {
        const { error } = await supabase
          .from('products')
          .upsert({
            id: product.id,
            name: product.name,
            price: product.price,
            image_path: product.imagePath || ''
          }, { onConflict: 'id' });

        if (error) {
          console.log(`  ⚠️  Producto "${product.name}": ${error.message}`);
        } else {
          console.log(`  ✅ Producto "${product.name}" migrado`);
        }
      }
    }

    console.log('\n✅ ¡Migración completada exitosamente!');
    console.log('🔗 Verifica tus datos en Supabase Dashboard: https://app.supabase.com');

  } catch (err) {
    console.error('❌ Error durante la migración:', err.message);
    process.exit(1);
  }
}

migrate();

