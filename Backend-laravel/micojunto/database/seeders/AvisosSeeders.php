<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class AvisosSeeders extends Seeder
{
    public function run(): void
    {
        // Supongamos que ya existen usuarios, tipos de aviso y estados
        // Aquí tomamos los primeros registros de cada tabla
        $userId = DB::table('users')->first()->id ?? 1;
        $tipoAvisoId = DB::table('tipo_avisos')->where('nombre', 'ayuda')->first()->id ?? 1;
        $estadoId = DB::table('estados')->where('nombre', 'pendiente')->first()->id ?? 1;

        // Crear un aviso de ejemplo
        $avisoId = DB::table('avisos')->insertGetId([
            'user_id' => $userId,
            'tipo_aviso_id' => $tipoAvisoId,
            'estado_id' => $estadoId,
            'titulo' => 'Ayuda para subir muebles',
            'descripcion' => 'Necesito ayuda para subir un mueble al apartamento este sábado por la mañana.',
            'fecha_publicacion' => now(),
            'fecha_necesaria' => now()->addDays(2),
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        // Crear notificación para todos los usuarios
        $usuarios = DB::table('users')->pluck('id');
        foreach ($usuarios as $uid) {
            DB::table('notificaciones')->insert([
                'user_id' => $uid,
                'aviso_id' => $avisoId,
                'mensaje' => 'Nuevo aviso de ayuda: Ayuda para subir muebles',
                'fecha_notificacion' => now(),
                'estado_id' => $estadoId,
                'created_at' => now(),
                'updated_at' => now(),
            ]);
        }
    }
}
