package Ejercicios_pilares

class Ejercicios {

    // ============= INMUTABILIDAD =============
    fun ejemploInmutabilidad() {
        println("\n--- Inmutabilidad ---")
        data class Pedido(val id: Int, val items: List<String>, val estado: String)

        val pedidoInicial = Pedido(1, listOf("Teclado", "Mouse"), "CREADO")
        val pedidoPagado = pedidoInicial.copy(estado = "PAGADO")
        val pedidoEnviado = pedidoPagado.copy(estado = "ENVIADO")

        println(pedidoInicial) // CREADO
        println(pedidoPagado)  // PAGADO
        println(pedidoEnviado) // ENVIADO

        val rolesBase = listOf("LECTOR")
        val rolesConEditor = rolesBase + "EDITOR"
        val rolesFinales = rolesConEditor + "ADMIN"

        println(rolesBase)
        println(rolesConEditor)
        println(rolesFinales)
    }

    // ============= FUNCIONES PURAS =============
    fun ejemploFuncionesPuras() {
        println("\n--- Funciones Puras ---")
        fun precioConIVA(precioBase: Double, iva: Double): Double = precioBase * (1 + iva)
        println(precioConIVA(100.0, 0.19))

        fun normalizarNombre(nombre: String): String =
            nombre.trim().lowercase().replaceFirstChar { it.titlecase() }
        println(normalizarNombre("  keVIN "))
    }

    // ============= LAMBDAS =============
    fun ejemploLambdas() {
        println("\n--- Expresiones Lambda ---")
        val esPasswordSegura: (String) -> Boolean = { pwd ->
            pwd.length >= 8 && pwd.any { it.isDigit() } && pwd.any { it.isUpperCase() }
        }
        println(esPasswordSegura("Kotlin2025"))

        data class UsuarioDTO(val email: String)
        val emails = listOf("a@dom.com", "b@dom.com")
        val usuariosDTO = emails.map { UsuarioDTO(it) }
        println(usuariosDTO)
    }

    // ============= FUNCIONES DE ORDEN SUPERIOR =============
    fun ejemploFuncionesOrdenSuperior() {
        println("\n--- Funciones de Orden Superior ---")
        fun transformarPuntajes(p: List<Int>, f: (Int) -> Int): List<Int> = p.map(f)
        val puntajes = listOf(50, 70, 90)
        println(transformarPuntajes(puntajes) { it + 5 })

        fun minLengthValidator(min: Int): (String) -> Boolean = { it.length >= min }
        val valida8 = minLengthValidator(8)
        println(valida8("abcdefg"))
        println(valida8("abcdefgh"))
    }

    // ============= EVALUACIÓN PEREZOSA =============
    fun ejemploLazy() {
        println("\n--- Evaluación Perezosa ---")
        val configuracion by lazy {
            println("Cargando configuración...")
            mapOf("tema" to "oscuro", "lenguaje" to "es")
        }
        println("Antes de acceder a configuracion")
        println(configuracion)

        val ids = generateSequence(1000) { it + 1 }
        println(ids.take(4).toList())
    }

    // ============= COMPOSICIÓN =============
    fun ejemploComposicion() {
        println("\n--- Composición de Funciones ---")
        fun String.soloLetras() = filter { it.isLetter() }
        fun String.capitalizeFirst() = replaceFirstChar { it.titlecase() }

        val limpio = "  jUaN-123  ".trim().soloLetras().lowercase().capitalizeFirst()
        println(limpio)

        val productos = listOf("  mouse gaming  ", "TECLADO mecánico", "Pad ")
        val normalizados = productos
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.lowercase().replaceFirstChar { c -> c.titlecase() } }

        println(normalizados)
    }

    fun ejecutarEjercicios() {
        ejemploInmutabilidad()
        ejemploFuncionesPuras()
        ejemploLambdas()
        ejemploFuncionesOrdenSuperior()
        ejemploLazy()
        ejemploComposicion()
    }
}