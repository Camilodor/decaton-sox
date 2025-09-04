<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class UsuariosSeeders extends Seeder
{
    public function run(): void
    {
        DB::table('users')->insert([
            [
                'nombre' => 'Admin',
                'email' => 'admin@vecindario.com',
                'password' => 'admin123',
                'role_id' => 1,
                'created_at' => now(),
                'updated_at' => now()
            ],
            [
                'nombre' => 'Vecino1',
                'email' => 'vecino1@vecindario.com',
                'password' => 'vecino123',
                'role_id' => 2,
                'created_at' => now(),
                'updated_at' => now()
            ],
            [
                'nombre' => 'Vecino2',
                'email' => 'vecino2@vecindario.com',
                'password' => 'vecino123',
                'role_id' => 2,
                'created_at' => now(),
                'updated_at' => now()
            ],
        ]);
    }
}
