import sys.process._



object Main {
  def main(args: Array[String]): Unit = {
    val json = "{\"nombre\":\"alejandro\",\"puntuacion\":355,\"fecha\":\"2023-04-10T19:47:05.080539\",\"duracion\":434}"
    println(HttpRequest.post(json, "http://localhost:8000/records"))
    println(HttpRequest.get("http://localhost:8000/records"))
  }
}

//    val json ="{\"user\":\"bob\",\"pass\":\"123\"}"
    //    val json = "{\"id\":\"testid427\",\"nombre\":\"SLM\",\"punt\":10,\"fecha\":\"2000/07/11\",\"duracion\":200}"

//    val json = "{\"ala\": \"este\",\"pasillo\": 2,\"piso\": 2}"
//    Seq("curl", "-H", "Content-Type: application/json", "-d", s"$json","http://localhost:8000/taquillas").!

//    print(json)
//    Seq("curl", "-H", "Content-Type: application/json", "-d", json, "http://localhost:8000/taquillas").!
//Seq("cmd","/c","""curl http://localhost:8000/taquillas -H "Content-Type: application/json" -d "{"ala": "este","pasillo": 2,"piso": 2}"""").!


//    val data = Json.obj(
//      "ala" -> Json.fromString("este"),
//      "pasillo" -> Json.fromInt(2),
//      "piso" -> Json.fromInt(2)
//    ).toString
//    val data = """{"ala": "este","pasillo": 2,"piso": 2}"""



    //    Seq("curl", "-X", "POST", "-H", "Content-Type: application/json", "-d", data, "http://localhost:8000/taquillas").!


    //    println(json)
//    Seq("curl", "-H", "Content-Type:application/json", "-d", s"$json", "https://express1688023182.azurewebsites.net/marcador").!

//      Seq("curl", "-H", "Content-Type: application/json", "-d", s"@$json","https://httpbin.org/anything").!
//    Seq("cmd", "/c", s"curl -H \"Content-Type: application/json\" -X POST -d '{\"user\":\"bob\",\"pass\":\"123\"}' https://httpbin.org/anything").!!

