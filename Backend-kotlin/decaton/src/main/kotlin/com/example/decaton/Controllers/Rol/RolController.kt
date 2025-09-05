package com.example.decaton.Controllers.Rol

import com.example.decaton.Models.Rol.Rol
import com.example.decaton.Services.Rol.RolService
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
@RequestMapping("/roles")
class RolController {

    @Autowired
    lateinit var rolService: RolService

    // GET /roles → Listar todos
    @GetMapping
    fun obtenerRoles(): List<Rol> = rolService.obtenerRoles()

    // GET /roles/{id} → Obtener uno
    @GetMapping("/{id}")
    fun obtenerRol(@PathVariable id: Int): Rol? = rolService.obtenerRolPorId(id)

    // POST /roles → Crear
    @PostMapping
    fun crearRol(@RequestBody rol: Rol): String {
        val filas = rolService.crearRol(rol)
        return if (filas > 0) "Rol creado con éxito" else "No se pudo crear rol"
    }

    // PUT /roles/{id} → Actualizar
    @PutMapping("/{id}")
    fun actualizarRol(@PathVariable id: Int, @RequestBody rol: Rol): String {
        val filas = rolService.actualizarRol(id, rol)
        return if (filas > 0) "Rol actualizado con éxito" else "No se encontró rol con id $id"
    }

    // DELETE /roles/{id} → Eliminar
    @DeleteMapping("/{id}")
    fun eliminarRol(@PathVariable id: Int): String {
        val filas = rolService.eliminarRol(id)
        return if (filas > 0) "Rol eliminado con éxito" else "No se encontró rol con id $id"
    }
}