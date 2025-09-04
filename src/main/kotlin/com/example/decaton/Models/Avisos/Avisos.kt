package com.example.decaton.Models.Avisos

import java.time.LocalDateTime

data class Avisos(
    val id: Int? = null,
    val user_id: Int? = null,
    val tipo_aviso_id: Int? = null,
    val estado_id: Int? = null,
    val titulo: String,
    val descripcion: String? = null,
    val fecha_publicacion: LocalDateTime,
    val fecha_necesaria: LocalDateTime? = null
)
