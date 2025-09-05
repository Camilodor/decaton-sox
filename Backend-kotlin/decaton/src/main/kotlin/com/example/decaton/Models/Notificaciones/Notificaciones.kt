package com.example.decaton.Modelos

data class Notificaciones(
    val id: Int? = null,
    val userId: Int,
    val avisoId: Int,
    val mensaje: String,
    val fechaNotificacion: String, // también podría ser LocalDate
    val estadoId: Int,
    val createdAt: String? = null, // o LocalDateTime
    val updatedAt: String? = null  // o LocalDateTime

)

