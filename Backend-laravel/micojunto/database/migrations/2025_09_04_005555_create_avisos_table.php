<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('avisos', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('user_id')->nullable();
            $table->unsignedBigInteger('tipo_aviso_id')->nullable();
            $table->unsignedBigInteger('estado_id')->nullable();
            $table->string('titulo', 150);
            $table->text('descripcion')->nullable();
            $table->datetime('fecha_publicacion');
            $table->datetime('fecha_necesaria')->nullable();
            $table->timestamps();

            $table->foreign('user_id')->references('id')->on('users')->onDelete('set null');
            $table->foreign('tipo_aviso_id')->references('id')->on('tipo_avisos')->onDelete('set null');
            $table->foreign('estado_id')->references('id')->on('estados')->onDelete('set null');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('avisos');
    }
};
