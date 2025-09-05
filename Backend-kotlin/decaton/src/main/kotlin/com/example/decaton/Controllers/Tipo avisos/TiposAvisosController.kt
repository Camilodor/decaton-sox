package com.example.decaton.Controllers

import com.example.decaton.Modelos.TiposAvisos
import com.example.decaton.Services.TiposAvisosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipos-avisos")
class TiposAvisosController {

    @Autowired
    lateinit var tiposAvisosService: TiposAvisosService

    @GetMapping
    fun obtenerTodos(): List<TiposAvisos> =
        tiposAvisosService.obtenerTiposAvisos()

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): TiposAvisos? =
        tiposAvisosService.obtenerPorId(id)

    @PostMapping
    fun crear(@RequestBody tipoAviso: TiposAvisos): TiposAvisos =
        tiposAvisosService.crear(tipoAviso)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody tipoAviso: TiposAvisos): TiposAvisos? =
        tiposAvisosService.actualizar(id, tipoAviso)

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int): Boolean =
        tiposAvisosService.eliminar(id)
}
