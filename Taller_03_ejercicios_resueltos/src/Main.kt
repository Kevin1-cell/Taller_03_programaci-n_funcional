import Ejercicios_pilares.Ejercicios
import Gestion_usuarios.Gestion_usuarios
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val ejercicios = Ejercicios()
    val gestion = Gestion_usuarios()

    while (true) {
        println("\n===== MENÚ PRINCIPAL =====")
        println("1. Ejecutar ejercicios de los pilares")
        println("2. Ejecutar gestión de usuarios")
        println("0. Salir")
        print("Seleccione una opción: ")

        when (scanner.nextLine()) {
            "1" -> {
                println("\n>>> Ejecutando Ejercicios <<<")
                ejercicios.ejecutarEjercicios()
            }
            "2" -> {
                println("\n>>> Ejecutando Gestión de Usuarios <<<")
                // Simulamos algunas operaciones para mostrar el flujo
                val u1 = gestion.registrar("Kevin Santiago Aguirre Giraldo", "  KEVIN@MAIL.COM ")
                val u2 = gestion.registrar("Ana María", null)

                gestion.actualizarEmail(u2.id, "ana@example.com")
                gestion.asignarRol(u1.id, "ADMIN")
                gestion.asignarRol(u1.id, "EDITOR")
                gestion.asignarRol(u2.id, "LECTOR")

                gestion.listar().forEach {
                    println("(${it.id}) ${it.nombre} <${it.email}> roles=${it.roles}")
                }
            }
            "0" -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("❌ Opción no válida, intenta de nuevo.")
        }
    }
}
