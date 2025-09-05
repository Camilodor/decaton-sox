package com.example.decaton.Controllers.Usuarios

import com.example.decaton.Models.Usuarios.LoginRequest
import com.example.decaton.Models.Usuarios.Usuarios
import com.example.decaton.Services.Usuarios.UsuariosService
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
@RequestMapping("/usuarios")
class UsuariosController {


    @Autowired
    lateinit var userService: UsuariosService

    // GET /users → Listar todos los usuarios
    @GetMapping
    fun obtenerUsuarios(): List<Usuarios> = userService.obtenerUsuarios()

    // GET /users/{id} → Obtener usuario por ID
    @GetMapping("/{id}")
    fun obtenerUsuario(@PathVariable id: Int): Usuarios? = userService.obtenerUsuarioPorId(id)

    // POST /users → Crear usuario
    @PostMapping
    fun crearUsuario(@RequestBody user: Usuarios): String {
        val filas = userService.crearUsuario(user)
        return if (filas > 0) "Usuario creado con éxito" else "No se pudo crear usuario"
    }

    // PUT /users/{id} → Actualizar usuario
    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Int, @RequestBody user: Usuarios): String {
        val filas = userService.actualizarUsuario(id, user)
        return if (filas > 0) "Usuario actualizado con éxito" else "No se encontró usuario con id $id"
    }

    // DELETE /users/{id} → Eliminar usuario
    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Int): String {
        val filas = userService.eliminarUsuario(id)
        return if (filas > 0) "Usuario eliminado con éxito" else "No se encontró usuario con id $id"
    }
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): String {
        val usuario = userService.login(loginRequest.email, loginRequest.password)

        return if (usuario != null) {
            "Bienvenido ${usuario.nombre}"
        } else {
            "Credenciales inválidas"
        }
    }
}


