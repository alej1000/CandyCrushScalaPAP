import java.io.{BufferedReader, InputStreamReader, OutputStream}
import java.net.{HttpURLConnection, URL}

object HttpRequest {
  def post(inputJson: String, link: String): String = {
    try {
      val url = new URL(link)
      val conn = url.openConnection.asInstanceOf[HttpURLConnection]
      conn.setDoOutput(true)
      conn.setRequestMethod("POST")
      conn.setRequestProperty("Content-Type", "application/json")
      val os = conn.getOutputStream
      os.write(inputJson.getBytes)
      os.flush()
      if (conn.getResponseCode != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode)
      }
      val br = new BufferedReader(new InputStreamReader(conn.getInputStream))
      val respuesta = readAll(br)
      conn.disconnect()
      respuesta
    } catch {
      case e: Exception =>
        e.printStackTrace()
        "Error"
    }
  }

  def get(link: String): String = {
    try {
      val url = new URL(link)
      val conn = url.openConnection.asInstanceOf[HttpURLConnection]
      conn.setRequestMethod("GET")
      if (conn.getResponseCode != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode)
      }
      val br = new BufferedReader(new InputStreamReader(conn.getInputStream))
      val respuesta = readAll(br)
      conn.disconnect()
      respuesta
    } catch {
      case e: Exception =>
        e.printStackTrace()
        "Error"
    }
  }

  private def readAll(br: BufferedReader): String = {
    val line = br.readLine()
    if (line == null) "" else line + readAll(br)
  }
}
