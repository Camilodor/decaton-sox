<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class EstadosSeeders extends Seeder
{
    public function run(): void
    {
        DB::table('estados')->insert([
            ['nombre' => 'pendiente', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'en progreso', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'atendido', 'created_at' => now(), 'updated_at' => now()],
        ]);
    }
}
