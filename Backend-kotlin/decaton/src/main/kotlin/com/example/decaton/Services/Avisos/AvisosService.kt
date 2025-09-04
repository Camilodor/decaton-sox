package com.example.decaton.Services.Avisos

import com.example.decaton.Models.Avisos.Avisos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class AvisosService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate


    fun obteneravisos(): List<Avisos>{
        val sql="SELECT * FROM avisos"
        return jdbcTemplate.query(sql, {rs, _ ->
            Avisos(
                id = rs.getInt("id"),
                user_id = rs.getInt("user_id").takeIf { !rs.wasNull() },
                tipo_aviso_id = rs.getInt("tipo_aviso_id").takeIf { !rs.wasNull() },
                estado_id = rs.getInt("estado_id").takeIf { !rs.wasNull() },
                titulo = rs.getString("titulo"),
                descripcion = rs.getString("descripcion"),
                fecha_publicacion = rs.getTimestamp("fecha_publicacion").toLocalDateTime(),
                fecha_necesaria = rs.getTimestamp("fecha_necesaria")?.toLocalDateTime()

            )
        })
    }


    fun obteneravisosid(id: Int): Avisos?{
        val sql ="SELECT * FROM avisos WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) {rs, _ ->
            Avisos(
                id = rs.getInt("id"),
                user_id = rs.getInt("user_id").takeIf { !rs.wasNull() },
                tipo_aviso_id = rs.getInt("tipo_aviso_id").takeIf { !rs.wasNull() },
                estado_id = rs.getInt("estado_id").takeIf { !rs.wasNull() },
                titulo = rs.getString("titulo"),
                descripcion = rs.getString("descripcion"),
                fecha_publicacion = rs.getTimestamp("fecha_publicacion").toLocalDateTime(),
                fecha_necesaria = rs.getTimestamp("fecha_necesaria")?.toLocalDateTime()
            )
        }.firstOrNull()
    }



    fun crearavisos(avisos: Avisos): Int{
        val sql = """
            INSERT INTO avisos (user_id, tipo_aviso_id, estado_id, titulo, descripcion, fecha_publicacion, fecha_necesaria)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()
        return jdbcTemplate.update (
            sql,
            avisos.user_id,
            avisos.tipo_aviso_id,
            avisos.estado_id,
            avisos.titulo,
            avisos.descripcion,
            avisos.fecha_publicacion,
            avisos.fecha_necesaria,

        )



    }




    fun actualizaravisos(id: Int,  avisos: Avisos): Int{
        val sql = """
            UPDATE avisos SET user_id = ?, tipo_aviso_id = ?, estado_id = ?, titulo = ?, descripcion = ?, 
                              fecha_publicacion = ?, fecha_necesaria = ?
            WHERE id = ?
        """.trimIndent()
        return jdbcTemplate.update(
            sql,
            avisos.user_id, avisos.tipo_aviso_id, avisos.estado_id,
            avisos.titulo, avisos.descripcion,
            avisos.fecha_publicacion, avisos.fecha_necesaria,
            id
        )

    }


    fun eliminarAviso(id: Int): Int {
        val sql = "DELETE FROM avisos WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}