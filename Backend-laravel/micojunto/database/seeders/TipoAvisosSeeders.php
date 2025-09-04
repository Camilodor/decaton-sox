<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class TipoAvisosSeeders extends Seeder
{
    public function run(): void
    {
        DB::table('tipo_avisos')->insert([
            ['nombre' => 'ayuda', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'alerta', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'compra', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'reunion', 'created_at' => now(), 'updated_at' => now()],
        ]);
    }
}

