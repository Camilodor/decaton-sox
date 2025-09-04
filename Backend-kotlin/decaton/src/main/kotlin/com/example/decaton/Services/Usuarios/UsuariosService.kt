package com.example.decaton.Services.Usuarios

import com.example.decaton.Models.Usuarios.Usuarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UsuariosService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerUsuarios(): List<Usuarios> {
        val sql = "SELECT * FROM users"
        return jdbcTemplate.query(sql) { rs, _ ->
            Usuarios(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre"),
                email = rs.getString("email"),
                password = rs.getString("password"),
                role_id = rs.getInt("role_id")
            )
        }
    }

    fun obtenerUsuarioPorId(id: Int): Usuarios? {
        val sql = "SELECT * FROM users WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Usuarios(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre"),
                email = rs.getString("email"),
                password = rs.getString("password"),
                role_id = rs.getInt("role_id")
            )
        }.firstOrNull()
    }

    fun crearUsuario(user: Usuarios): Int {
        val sql = """
            INSERT INTO users (nombre, email, password, role_id)
            VALUES (?, ?, ?, ?)
        """.trimIndent()
        return jdbcTemplate.update(
            sql,
            user.nombre,
            user.email,
            user.password,
            user.role_id
        )
    }

    fun actualizarUsuario(id: Int, user: Usuarios): Int {
        val sql = """
            UPDATE users SET nombre = ?, email = ?, password = ?, role_id = ?
            WHERE id = ?
        """.trimIndent()
        return jdbcTemplate.update(
            sql,
            user.nombre,
            user.email,
            user.password,
            user.role_id,
            id
        )
    }

    fun eliminarUsuario(id: Int): Int {
        val sql = "DELETE FROM users WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }

    fun login(email: String, password: String): Usuarios? {
        val sql = "SELECT * FROM users WHERE email = ? AND password = ?"
        return jdbcTemplate.query(sql, arrayOf(email, password)) { rs, _ ->
            Usuarios(
                id = rs.getInt("id"),
                nombre = rs.getString("nombre"),
                email = rs.getString("email"),
                password = rs.getString("password"),
                role_id = rs.getInt("role_id")
            )
        }.firstOrNull()
    }
}