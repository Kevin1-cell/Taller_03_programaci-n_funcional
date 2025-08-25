package Gestion_usuarios

data class Usuario(
    val id: Int,
    var nombre: String,
    var email: String?,
    val roles: List<String> = emptyList()
)

class Gestion_usuarios {
    // Internamente mutable, pero exponemos operaciones que devuelven datos nuevos o resultados
    private val datos = mutableListOf<Usuario>()

    fun registrar(nombre: String, email: String?): Usuario {
        val nuevo = Usuario(
            id = (datos.maxOfOrNull { it.id } ?: 0) + 1,
            nombre = nombre,
            email = null
        ).apply {
            // apply: inicializamos campos encadenando lógica
            this.email = email?.trim()?.lowercase()
        }.also {
            // also: realizamos un efecto colateral controlado (log)
            println("[LOG] Registrando usuario: $it")
        }

        datos += nuevo
        return nuevo
    }

    fun actualizarEmail(id: Int, nuevoEmail: String?): Boolean {
        val u = datos.find { it.id == id } ?: return false
        // let: solo si no es nulo ni vacío lo aplicamos
        nuevoEmail?.trim()?.takeIf { it.isNotEmpty() }?.let { mailOk ->
            u.email = mailOk.lowercase()
            println("[LOG] Email actualizado para ${u.id} => ${u.email}")
            return true
        }
        return false
    }

    fun asignarRol(id: Int, rol: String): Boolean {
        val u = datos.find { it.id == id } ?: return false
        // with: trabajamos varias veces con el mismo objeto
        with(u) {
            if (!roles.contains(rol)) {
                val nuevosRoles = roles + rol
                // run: calculamos y devolvemos un resultado en un bloque
                return run {
                    val actualizado = copy(roles = nuevosRoles)
                    // reemplazo in-place de la referencia interna
                    val idx = datos.indexOfFirst { it.id == id }
                    if (idx != -1) datos[idx] = actualizado
                    println("[LOG] Rol agregado a ${actualizado.id}: ${actualizado.roles}")
                    true
                }
            } else {
                println("[LOG] Usuario $id ya tiene el rol $rol")
                return false
            }
        }
    }

    fun listar(): List<Usuario> = datos.toList()
}