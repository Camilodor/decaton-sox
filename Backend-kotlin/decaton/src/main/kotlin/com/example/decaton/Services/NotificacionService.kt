package com.example.decaton.Services


import com.example.decaton.Modelos.Notificaciones
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service


@Service
class NotificacionService {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerNotificaciones(): List<Notificaciones> {
        val sql = "SELECT * FROM notificaciones"
        return jdbcTemplate.query(sql) { rs, _ ->
            Notificaciones(
                id = rs.getInt("id"),
                userId = rs.getInt("user_id"),
                avisoId = rs.getInt("aviso_id"),
                mensaje = rs.getString("mensaje"),
                fechaNotificacion = rs.getString("fecha_notificacion"),
                estadoId = rs.getInt("estado_id"),
                createdAt = rs.getString("created_at"),
                updatedAt = rs.getString("updated_at")
            )
        }
    }

    fun obtenerNotificacionPorId(id: Int): Notificaciones? {
        val sql = "SELECT * FROM notificaciones WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Notificaciones(
                id = rs.getInt("id"),
                userId = rs.getInt("user_id"),
                avisoId = rs.getInt("aviso_id"),
                mensaje = rs.getString("mensaje"),
                fechaNotificacion = rs.getString("fecha_notificacion"),
                estadoId = rs.getInt("estado_id"),
                createdAt = rs.getString("created_at"),
                updatedAt = rs.getString("updated_at")
            )
        }.firstOrNull()
    }

    fun crearNotificacion(notificacion: Notificaciones): Int {
        val sql = """
            INSERT INTO notificaciones
            (user_id, aviso_id, mensaje, fecha_notificacion, estado_id, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, NOW(), NOW())
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            notificacion.userId,
            notificacion.avisoId,
            notificacion.mensaje,
            notificacion.fechaNotificacion,
            notificacion.estadoId
        )
    }

    fun actualizarNotificacion(id: Int, notificacion: Notificaciones): Int {
        val sql = """
            UPDATE notificaciones SET
                user_id = ?, aviso_id = ?, mensaje = ?, fecha_notificacion = ?, estado_id = ?, updated_at = NOW()
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            notificacion.userId,
            notificacion.avisoId,
            notificacion.mensaje,
            notificacion.fechaNotificacion,
            notificacion.estadoId,
            id
        )
    }

    fun eliminarNotificacion(id: Int): Int {
        val sql = "DELETE FROM notificaciones WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }

}