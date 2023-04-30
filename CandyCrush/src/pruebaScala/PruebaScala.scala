package pruebaScala
import pruebaJava.PruebaJava

class PruebaScala{
  def main(args: Array[String]): Unit = {
    val miInstancia = new PruebaJava()
    miInstancia.miFuncion("Hola desde Scala")
  }
}

object PruebaScala {
  def miFuncion(mensaje: String): Unit = {
    println("Ejecutando funciones de Scala")
    println("Mensaje recibido desde Java con la función Scala " + mensaje)
  }
}
