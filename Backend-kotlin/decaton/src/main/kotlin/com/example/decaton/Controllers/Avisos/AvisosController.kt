package com.example.decaton.Controllers.Avisos


import com.example.decaton.Models.Avisos.Avisos
import com.example.decaton.Services.Avisos.AvisosService
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
@RequestMapping("/avisos")
class AvisosController {

    @Autowired
    lateinit var avisosService: AvisosService

    @GetMapping
    fun obtenerAvisos(): List<Avisos> = avisosService.obteneravisos()


    @GetMapping("/{id}")
    fun obtenerAviso(@PathVariable id: Int): Avisos? = avisosService.obteneravisosid(id)


    @PostMapping
    fun crearAviso(@RequestBody avisos: Avisos): String {
        val filas = avisosService.crearavisos(avisos)
        return if (filas > 0) "Aviso creado con éxito" else "No se pudo crear aviso"
    }

    @PutMapping("/{id}")
    fun actualizarAviso(@PathVariable id: Int, @RequestBody avisos: Avisos): String {
        val filas = avisosService.actualizaravisos(id, avisos)
        return if (filas > 0) "Aviso actualizado con éxito" else "No se encontró aviso con id $id"
    }

    @DeleteMapping("/{id}")
    fun eliminarAviso(@PathVariable id: Int): String {
        val filas = avisosService.eliminarAviso(id)
        return if (filas > 0) "Aviso eliminado con éxito" else "No se encontró aviso con id $id"
    }
}

