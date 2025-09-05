package com.example.decaton.Controllers.Comentario

import com.example.decaton.Models.Comentarios.Comentarios
import com.example.decaton.Services.Comentario.ComentarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping


@RestController
@RequestMapping("/comentario")
class ComentarioController {

    @Autowired
    lateinit var comentarioService: ComentarioService

    @GetMapping()
    fun obtenerComentarios(): List<Comentarios> = comentarioService.obtenerComentarios()

    @GetMapping("/{id}")
    fun obtenerComentario(@PathVariable id: Int): Comentarios? =
        comentarioService.obtenerComentarioPorId(id)

    @PostMapping()
    fun crearComentario(@RequestBody comentario: Comentarios): String {
        val filas = comentarioService.crearComentario(comentario)
        return if (filas > 0) "Comentario creado con éxito" else "No se pudo crear comentario"
    }

    @PutMapping("/{id}")
    fun actualizarComentario(@PathVariable id: Int, @RequestBody comentario: Comentarios): String {
        val filas = comentarioService.actualizarComentario(id, comentario)
        return if (filas > 0) "Comentario actualizado con éxito" else "No se encontró comentario con id $id"
    }

    @DeleteMapping("/{id}")
    fun eliminarComentario(@PathVariable id: Int): String {
        val filas = comentarioService.eliminarComentario(id)
        return if (filas > 0) "Comentario eliminado con éxito" else "No se encontró comentario con id $id"
    }
}
