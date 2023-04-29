//import org.json4s._
//import org.json4s.native.Serialization
//import org.json4s.native.Serialization.write
//import org.json4s.native.JsonMethods.parse
//
//import java.io.FileWriter
//import org.json.simple.JSONObject;
//
//case class Record(nombre: String, puntuacion: Int)
//
//object Record extends App {
//
//  val records = List(Record("Juan", 100), Record("Pedro", 90), Record("María", 80))
//
//  def insertRecord(records: List[Record], newRecord: Record): List[Record] = records match {
//    case Nil => List(newRecord)
//    case head :: tail =>
//      if (newRecord.puntuacion > head.puntuacion) newRecord :: records
//      else head :: insertRecord(tail, newRecord)
//  }
//
//  val newRecord = Record("Luis", 95)
//  val newRecords = insertRecord(records, newRecord)
//
//  implicit val formats = DefaultFormats
//  val json = write(newRecords)
//  println(json)
//
//  val recordsFromJson = parse(json).extract[List[Record]]
//  println(recordsFromJson)
//}
//
//
//
//object EscribirJSON {
//  def escribir(args: Array[String]): Unit = {
//    val jsonObject = new JSONObject()
//    jsonObject.put("nombre", "Juan")
//    jsonObject.put("edad", 25)
//    jsonObject.put("ciudad", "Bogotá")
//
//    try {
//      val file = new FileWriter("archivo.json")
//      file.write(jsonObject.toJSONString)
//      println("Se ha escrito en el archivo JSON correctamente.")
//      file.close()
//    } catch {
//      case e: Exception =>
//        println("Ha ocurrido un error al escribir en el archivo JSON: " + e)
//    }
//  }
//}
