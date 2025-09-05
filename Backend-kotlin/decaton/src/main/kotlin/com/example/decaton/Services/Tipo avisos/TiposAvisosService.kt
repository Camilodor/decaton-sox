package com.example.decaton.Services

import com.example.decaton.Modelos.TiposAvisos
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TiposAvisosService {

    private val tiposAvisos = mutableListOf<TiposAvisos>()
    private val logger = LoggerFactory.getLogger(TiposAvisosService::class.java)

    fun obtenerTiposAvisos(): List<TiposAvisos> = tiposAvisos

    fun obtenerPorId(id: Int): TiposAvisos? =
        tiposAvisos.find { it.id == id }

    fun crear(tipoAviso: TiposAvisos): TiposAvisos {
        val nuevo = tipoAviso.copy(id = (tiposAvisos.size + 1))
        tiposAvisos.add(nuevo)

        // 🔔 Simulación de notificación con logs
        logger.info("📢 Nuevo tipo de aviso creado (id=${nuevo.id}): " +
                "alerta=${nuevo.alerta}, reunion=${nuevo.reunion}, ayuda=${nuevo.ayuda}, compra=${nuevo.compra}")

        return nuevo
    }

    fun actualizar(id: Int, tipoAviso: TiposAvisos): TiposAvisos? {
        val index = tiposAvisos.indexOfFirst { it.id == id }
        return if (index != -1) {
            val actualizado = tipoAviso.copy(id = id)
            tiposAvisos[index] = actualizado

            logger.info("♻️ Tipo de aviso actualizado (id=$id): ${actualizado}")
            actualizado
        } else null
    }

    fun eliminar(id: Int): Boolean {
        val eliminado = tiposAvisos.removeIf { it.id == id }
        if (eliminado) {
            logger.warn("🗑️ Tipo de aviso eliminado (id=$id)")
        } else {
            logger.error("❌ Intento de eliminar tipo de aviso inexistente (id=$id)")
        }
        return eliminado
    }
}
