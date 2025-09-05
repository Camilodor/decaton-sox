package com.example.decaton.Controllers




import com.example.decaton.Modelos.Notificaciones
import com.example.decaton.Services.NotificacionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notificaciones")
class NotificacionController {

    @Autowired
    lateinit var notificacionService: NotificacionService


    @GetMapping
    fun obtenerNotificaciones(): List<Notificaciones> {
        return notificacionService.obtenerNotificaciones()
    }

    // Obtener notificación por id
    @GetMapping("/{id}")
    fun obtenerNotificacionPorId(@PathVariable id: Int): Notificaciones? {
        return notificacionService.obtenerNotificacionPorId(id)
    }

    // Crear notificación
    @PostMapping
    fun crearNotificacion(@RequestBody notificacion: Notificaciones): String {
        val filas = notificacionService.crearNotificacion(notificacion)
        return if (filas > 0) "Notificación creada con éxito" else "Error al crear notificación"
    }

    // Actualizar notificación
    @PutMapping("/{id}")
    fun actualizarNotificacion(@PathVariable id: Int, @RequestBody notificacion: Notificaciones): String {
        val filas = notificacionService.actualizarNotificacion(id, notificacion)
        return if (filas > 0) "Notificación actualizada con éxito" else "No se encontró notificación con id $id"
    }

    // Eliminar notificación
    @DeleteMapping("/{id}")
    fun eliminarNotificacion(@PathVariable id: Int): String {
        val filas = notificacionService.eliminarNotificacion(id)
        return if (filas > 0) "Notificación eliminada con éxito" else "No se encontró notificación con id $id"
    }
}