package com.example.decaton.Services.Comentario




import com.example.decaton.Models.Comentarios.Comentarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class ComentarioService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerComentarios(): List<Comentarios> {
        val sql = "SELECT * FROM comentarios"
        return jdbcTemplate.query(sql) { rs, _ ->
            Comentarios(
                id = rs.getInt("id"),
                user_id = rs.getInt("user_id").takeIf { !rs.wasNull() },
                aviso_id = rs.getInt("aviso_id").takeIf { !rs.wasNull() },
                contenido = rs.getString("contenido")
            )
        }
    }

    fun obtenerComentarioPorId(id: Int): Comentarios? {
        val sql = "SELECT * FROM comentarios WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Comentarios(
                id = rs.getInt("id"),
                user_id = rs.getInt("user_id").takeIf { !rs.wasNull() },
                aviso_id = rs.getInt("aviso_id").takeIf { !rs.wasNull() },
                contenido = rs.getString("contenido")
            )
        }.firstOrNull()
    }

    fun crearComentario(comentario: Comentarios): Int {
        val sql = """
            INSERT INTO comentarios (user_id, aviso_id, contenido)
            VALUES (?, ?, ?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            comentario.user_id,
            comentario.aviso_id,
            comentario.contenido
        )
    }

    fun actualizarComentario(id: Int, comentario: Comentarios): Int {
        val sql = """
            UPDATE comentarios SET 
                user_id = ?, aviso_id = ?, contenido = ?
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            comentario.user_id,
            comentario.aviso_id,
            comentario.contenido,
            id
        )
    }

    fun eliminarComentario(id: Int): Int {
        val sql = "DELETE FROM comentarios WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}
