package com.example.decaton.Services.Rol

import com.example.decaton.Models.Rol.Rol
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class RolService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerRoles(): List<Rol> {
        val sql = "SELECT * FROM roles"
        return jdbcTemplate.query(sql) { rs, _ ->
            Rol(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre")
            )
        }
    }

    fun obtenerRolPorId(id: Int): Rol? {
        val sql = "SELECT * FROM roles WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Rol(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre")
            )
        }.firstOrNull()
    }

    fun crearRol(rol: Rol): Int {
        val sql = "INSERT INTO roles (nombre) VALUES (?)"
        return jdbcTemplate.update(sql, rol.nombre)
    }

    fun actualizarRol(id: Int, rol: Rol): Int {
        val sql = "UPDATE roles SET nombre = ? WHERE id = ?"
        return jdbcTemplate.update(sql, rol.nombre, id)
    }

    fun eliminarRol(id: Int): Int {
        val sql = "DELETE FROM roles WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}