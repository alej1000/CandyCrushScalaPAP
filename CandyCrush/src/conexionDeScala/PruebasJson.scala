package conexionDeScala

object PruebasJson {
  def main(args: Array[String]): Unit = {
    println("Ejecutando xD")
    val json:List[String]= HttpRequest.get("http://cundycrosh.uah:8000/records/arcade")
    println(json)

  }
}
