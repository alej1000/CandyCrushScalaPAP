package conexionDeScala

import conexionDeJava.ConexionJava

object ConexionScala {
  def main(args: Array[String]): Unit = {
    val miInstancia = new ConexionJava()
    miInstancia.miFuncion("Hola desde Scala")
  }

  def miFuncion(mensaje: String): Unit = {
    println("Ejecutando funciones de Scala")
    println("Mensaje recibido desde Java con la función Scala " + mensaje)
  }
}
