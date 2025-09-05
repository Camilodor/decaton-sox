package com.example.decaton.Services.Estado

import com.example.decaton.Models.Estado.Estado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class EstadoService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerEstados(): List<Estado> {
        val sql = "SELECT * FROM estados"
        return jdbcTemplate.query(sql) { rs, _ ->
            Estado(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre")
            )
        }
    }

    fun obtenerEstadoPorId(id: Int): Estado? {
        val sql = "SELECT * FROM estados WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Estado(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre")
            )
        }.firstOrNull()
    }

    fun crearEstado(estado: Estado): Int {
        val sql = "INSERT INTO estados (nombre) VALUES (?)"
        return jdbcTemplate.update(sql, estado.nombre)
    }

    fun actualizarEstado(id: Int, estado: Estado): Int {
        val sql = "UPDATE estados SET nombre = ? WHERE id = ?"
        return jdbcTemplate.update(sql, estado.nombre, id)
    }

    fun eliminarEstado(id: Int): Int {
        val sql = "DELETE FROM estados WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}