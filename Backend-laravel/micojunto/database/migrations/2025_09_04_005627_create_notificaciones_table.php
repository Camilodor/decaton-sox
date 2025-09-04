<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('notificaciones', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('user_id')->nullable();
            $table->unsignedBigInteger('aviso_id')->nullable();
            $table->string('mensaje', 255);
            $table->date('fecha_notificacion');
            $table->unsignedBigInteger('estado_id')->nullable();
            $table->timestamps();

            $table->foreign('user_id')->references('id')->on('users')->onDelete('set null');
            $table->foreign('aviso_id')->references('id')->on('avisos')->onDelete('set null');
            $table->foreign('estado_id')->references('id')->on('estados')->onDelete('set null');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('notificaciones');
    }
};
