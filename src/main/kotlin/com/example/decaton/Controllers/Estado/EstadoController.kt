package com.example.decaton.Controllers.Estado

import com.example.decaton.Models.Estado.Estado
import com.example.decaton.Services.Estado.EstadoService
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
@RequestMapping("/estados")
class EstadoController {

    @Autowired
    lateinit var estadoService: EstadoService

    // GET /estados → Listar todos
    @GetMapping
    fun obtenerEstados(): List<Estado> = estadoService.obtenerEstados()

    // GET /estados/{id} → Obtener uno
    @GetMapping("/{id}")
    fun obtenerEstado(@PathVariable id: Int): Estado? = estadoService.obtenerEstadoPorId(id)

    // POST /estados → Crear
    @PostMapping
    fun crearEstado(@RequestBody estado: Estado): String {
        val filas = estadoService.crearEstado(estado)
        return if (filas > 0) "Estado creado con éxito" else "No se pudo crear estado"
    }

    // PUT /estados/{id} → Actualizar
    @PutMapping("/{id}")
    fun actualizarEstado(@PathVariable id: Int, @RequestBody estado: Estado): String {
        val filas = estadoService.actualizarEstado(id, estado)
        return if (filas > 0) "Estado actualizado con éxito" else "No se encontró estado con id $id"
    }

    // DELETE /estados/{id} → Eliminar
    @DeleteMapping("/{id}")
    fun eliminarEstado(@PathVariable id: Int): String {
        val filas = estadoService.eliminarEstado(id)
        return if (filas > 0) "Estado eliminado con éxito" else "No se encontró estado con id $id"
    }
}