<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class RolesSeeders extends Seeder
{
    public function run(): void
    {
        DB::table('roles')->insert([
            ['nombre' => 'admin', 'created_at' => now(), 'updated_at' => now()],
            ['nombre' => 'vecino', 'created_at' => now(), 'updated_at' => now()],
        ]);
    }
}
