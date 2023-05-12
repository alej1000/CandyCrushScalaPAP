package logicaScala

import scala.io._
import java.io._
import scala.concurrent.duration._
import java.util.Calendar
import java.text.SimpleDateFormat

object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    val startTime = System.nanoTime()
    println("Bienvenido a Cundy Crosh 2.0 🍬🍬🍬")
    val puntosIniciales: Int = 0
    if (args.length > 3) { //Si se pasan los argumentos por consola //filas,columnas,aleatorio-manual,dificultad: 1-2
      val filas = args(0).toInt
      val columnas = args(1).toInt
      val modoDeJuego = charAtRecursive(args(2), 0)
      val dificultad = args(3).toInt
      if (dificultad == 2 || dificultad == 1) {
        val tablero = new Matrix(filas, columnas, dificultad)
        partida(tablero, 5, modoDeJuego, puntosIniciales, dificultad)
      } else {
        println("Se pondrá dificultad 2 por defecto")
        val tablero = new Matrix(filas, columnas, 2)
        partida(tablero, 5, modoDeJuego, puntosIniciales, 2)
      }

    } else { // Si no es por consola
      val filas = introducirInt("Introduce cuantas filas quieres")
      val columnas = introducirInt("Introduce cuantas columnas quieres")

      println("Intruce el modo de juego(a o m o r): ") //Automatico/MAnual/Random
      val modoDeJuego = scala.io.StdIn.readChar()

      val dificultad = introducirInt("Introduce la dificultad (1 o 2):")
      if (dificultad == 2 || dificultad == 1) {
        val tablero = new Matrix(filas, columnas, dificultad)
        partida(tablero, 5, modoDeJuego, puntosIniciales, dificultad)
      } else {
        println("Se pondrá dificultad 2 por defecto")
        val tablero = new Matrix(filas, columnas, 2)
        partida(tablero, 5, modoDeJuego, puntosIniciales, 2)
      }
    }


    def partida(tablero: Matrix, vidas: Int, modoDeJuego: Char, puntosTotales: Int, dificultad: Int): Unit = {
      if (vidas == 0) {
        println("Has perdido 😭")
        controlFinal(puntosTotales, modoDeJuego)
        return
      }
      print("Vidas restantes: ")
      mostrarVidas(vidas)
      println()
      println("Puntos: " + puntosTotales)
      tablero.toString()
      if (modoDeJuego == 'm') { //Es manual
        val fila = introducirInt("Introduce la fila")
        val columna = introducirInt("Introduce la columna")
        val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int,matriz0:Matrix) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
        val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado, dificultad)
        partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
      } else if(modoDeJuego == 'a'){ //Es automático
        val (tableroNew: Matrix, vidasNew: Int, puntosSumados: Int) = modoAutomatico(tablero, vidas, puntosTotales, dificultad: Int)
        partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
      }else{ //Es aleatorio
        val rand = new scala.util.Random()
        val fila = rand.nextInt(tablero.getNumFilas())
        val columna = rand.nextInt(tablero.getNumColumnas())
        val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int, matriz0: Matrix) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
        val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado, dificultad)
        partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
      }


    }

    def introducirInt(cadena: String): Int = {
      println(cadena)
      val num = scala.io.StdIn.readLine()
      try {
        num.toInt
      } catch {
        case e: Exception => {
          println("Introduce un número")
          introducirInt(cadena)
        }
      }
    }

    def modoAutomatico(tablero: Matrix, vidas: Int, puntosTotales: Int, dificultad: Int): (Matrix, Int, Int) = {
      val (fila: Int, columna: Int) = consultarMejorOpcion(tablero)
      println("La mejor opción es la fila: " + fila + " y la columna: " + columna + "")
      val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int,matriz0:Matrix) = tablero.consulta(fila, columna, vidas)
      //scala.io.StdIn.readLine() //Para que pare y se pueda ver
      val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado, dificultad)
      (tableroNew, vidasNew, puntosSumados)
    }

    def consultarMejorOpcion(tablero: Matrix): (Int, Int) = {
      val (fila: Int, columna: Int, _) = consultarMejorOpcionAux(tablero, 0, 0, 0, 0, 0)
      (fila, columna)
    }

    def consultarMejorOpcionAux(tablero: Matrix, fila: Int, columna: Int, mejorContador: Int, mejorFila: Int, mejorColumna: Int): (Int, Int, Int) = {
      val contadorActual = tablero.detectorEliminacion(fila, columna)._2
      if (fila == tablero.getNumFilas() - 1 && columna == tablero.getNumColumnas() - 1) { //Si llego al final del tablero el contador es el que tengo
        return (mejorFila, mejorColumna, mejorContador)
      } else if (columna >= tablero.getNumColumnas()) { //Si llego al final de la fila
        if (mejorContador < contadorActual) return consultarMejorOpcionAux(tablero, fila + 1, 0, contadorActual, fila, columna)
        return consultarMejorOpcionAux(tablero, fila + 1, 0, mejorContador, mejorFila, mejorColumna)
      } else { //Si no llego al final de la fila
        if (mejorContador < contadorActual) return consultarMejorOpcionAux(tablero, fila, columna + 1, contadorActual, fila, columna)
        return consultarMejorOpcionAux(tablero, fila, columna + 1, mejorContador, mejorFila, mejorColumna)
      }
    }

    def mostrarVidas(vidas: Int): Unit = {
      if (vidas > 0) {
        mostrarVidas(vidas - 1)
        print("❤️")
      }
    }

    def controlFinal(puntuacionFinal: Int, modoDeJuego: Char): Unit = {
      val (horaFin: String, duracionPartida: Long) = obtenerTiempos()
      if (modoDeJuego == 'm') { //Es manual
        //        val puntuaciones: List[String] = cargarPuntuaciones(filename)
        //        println("Ultimos records: ")
        //        mostrarPuntuaciones(puntuaciones)
        println("Tú puntuacion: " + puntuacionFinal)
        println("Has durado: " + duracionPartida + " segundos jugando")
        println("Fin de la partida: " + horaFin)
        println("Introduce tu nombre para que figure en los records")
        val nombre: String = scala.io.StdIn.readLine()
        println("Introduce una url de tu imagen de perfil, pulsa enter si no quieres poner ninguna")
        val url: String = scala.io.StdIn.readLine()

        //Subir a la base de datos
        val cadenaJson:String = puntuacionJson(nombre, puntuacionFinal, horaFin, duracionPartida,url)
        println("CadenaJson: "+cadenaJson)
        HttpRequest.post(cadenaJson,"http://cundycrosh.uah:8000/records")

        //Cargar de la base de la api
        val nuevasPuntuaciones: List[String] = HttpRequest.get("http://cundycrosh.uah:8000/records/arcade")

        println("\nRecords:")
        mostrarPuntuaciones(nuevasPuntuaciones)
      } else if(modoDeJuego == 'a'){ // Es automatico
        println("La puntuacion final es: " + puntuacionFinal)
        println("El Robotito duró: " + duracionPartida + " segundos jugando")
        val nombre: String = "AutoGod"
        val url:String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUnaRHgdYnRp7ieQttuFOM2HmOaFJS_IbHiQ&usqp=CAU"

        //Subir a la base de datos
        val cadenaJson: String = puntuacionJson(nombre, puntuacionFinal, horaFin, duracionPartida, url)
        println("CadenaJson: " + cadenaJson)
        HttpRequest.post(cadenaJson, "http://cundycrosh.uah:8000/records")

        //Cargar de la base de la api
        val nuevasPuntuaciones: List[String] = HttpRequest.get("http://cundycrosh.uah:8000/records/arcade")

        println("\nRecords:")
        mostrarPuntuaciones(nuevasPuntuaciones)
      }else{ //Es aleatorio
        println("La puntuacion final es: " + puntuacionFinal)
        println("El Manquito duró: " + duracionPartida + " segundos jugando")
        val nombre: String = "RandomManco"
        val url: String = "https://media.istockphoto.com/id/1098146198/es/vector/robot-roto-la-fijaci%C3%B3n-s%C3%AD-mismo-de-la-historieta.jpg?s=1024x1024&w=is&k=20&c=ZDCw1aAysbZhp1dEEC2gMLE9f_62nQJJSYOVoc1EQTI="

        //Subir a la base de datos
        val cadenaJson: String = puntuacionJson(nombre, puntuacionFinal, horaFin, duracionPartida, url)
        println("CadenaJson: " + cadenaJson)
        HttpRequest.post(cadenaJson, "http://cundycrosh.uah:8000/records")

        //Cargar de la base de la api
        val nuevasPuntuaciones: List[String] = HttpRequest.get("http://cundycrosh.uah:8000/records/arcade")

        println("\nRecords:")
        mostrarPuntuaciones(nuevasPuntuaciones)
      }
    }


    def puntuacionJson(nombre: String, puntuacion: Int, horaFin: String, duracionPartida: Long,url:String):String={
      "{\"nombre\":\""+nombre+"\",\"puntuacion\":"+puntuacion+",\"fecha\":\""+horaFin+"\",\"duracion\":"+duracionPartida+",\"picture\":\""+url+"\"} \n"
    }

    def parsearJson(cadenaJson: String): (String, Int, String, Long) = {
      //if (cadenaJson == "" || cadenaJson == "[]" || cadenaJson == "[" || cadenaJson == "]") return (cadenaJson, -1, cadenaJson, -1) //Si no hay nada

      val nombreInicio = indexOfRecursive(cadenaJson,":", indexOfRecursive(cadenaJson,"nombre")) +2 //Es más dos porque tiene comillas después del :
      val puntuacionInicio = indexOfRecursive(cadenaJson,":", indexOfRecursive(cadenaJson,"puntuacion")) + 1
      val fechaInicio = indexOfRecursive(cadenaJson,":", indexOfRecursive(cadenaJson,"fecha")) + 2
      val duracionInicio = indexOfRecursive(cadenaJson,":", indexOfRecursive(cadenaJson,"duracion")) + 1

      val nombreFin = indexOfRecursive(cadenaJson,"'", nombreInicio+2)
      val puntuacionFin = indexOfRecursive(cadenaJson,",", puntuacionInicio)
      val fechaFin = indexOfRecursive(cadenaJson,"'", fechaInicio+2)
      val duracionFin = indexOfRecursive(cadenaJson,",", duracionInicio)

      val nombre = substringRecursive(cadenaJson,nombreInicio, nombreFin)
      val puntuacion = substringRecursive(cadenaJson,puntuacionInicio, puntuacionFin).toInt
      val fecha = substringRecursive(cadenaJson,fechaInicio, fechaFin)
      val duracion = substringRecursive(cadenaJson,duracionInicio, duracionFin).toLong

      (nombre, puntuacion, fecha, duracion)
    }


    def buscarClaveValor(str: String): (String, Int, String, Long) = { //Devuelve (nombre, puntuacion,tiempo,duracion)
      //El formato de guardado tendrá que ser nombre:puntuacion@tiempo&duracion
      if (str == "") return (str, -1, str, -1) //Si no hay nada

      def buscarClaveValorRec(str: String, i: Int): (String, Int, String, Long) = { //Busca nombre:resto -> resto = puntuacion@tiempo&duracion

        if (i >= strLength(str)) {
          throw new IllegalArgumentException("No se encontró el carácter ':' en el String.")
        } else if (charAtRecursive(str,i) == ':') {
          val key = substringRecursive(str,0, i).trim
          val value = substringRecursive(str,i + 1).trim

          def buscarClaveValorRecAux(str: String, i: Int): (Int, String, Long) = { //Busca puntuacion@resto -> resto = tiempo&duracion

            if (i >= strLength(str)) {
              throw new IllegalArgumentException("No se encontró el carácter '@' en el String.")
            } else if (charAtRecursive(str,i) == '@') {
              val scoreValue = substringRecursive(str,0, i).trim.toInt
              val time = substringRecursive(str,i + 1).trim

              def buscarClaveValorRecAux2(str: String, i: Int): (String, Long) = { //Busca tiempo&duracion

                if (i >= strLength(str)) {
                  throw new IllegalArgumentException("No se encontró el carácter '&' en el String.")
                } else if (charAtRecursive(str,i) == '&') {
                  val timeValue = substringRecursive(str,0, i).trim
                  val durationValue = substringRecursive(str,i + 1).trim.toLong
                  (timeValue, durationValue)
                } else {
                  buscarClaveValorRecAux2(str, i + 1)
                }
              }

              val (timeValue: String, durationValue: Long) = buscarClaveValorRecAux2(time, 0)
              (scoreValue, timeValue, durationValue)
            } else {
              buscarClaveValorRecAux(str, i + 1)
            }
          }

          val (scoreValue: Int, timeValue: String, durationValue: Long) = buscarClaveValorRecAux(value, 0)
          (key, scoreValue, timeValue, durationValue)
        } else {
          buscarClaveValorRec(str, i + 1)
        }

      }

      buscarClaveValorRec(str, 0)
    }

    def repeat(s: String, n: Int): String = {
      s * n
    }

    def mostrarPuntuaciones(listaRecords: List[String]): Unit = {
      //Función que imprime la pila de llamadas de manera magistral
      val name: String = "Nombre"
      val score: String = "Puntuación"
      val date: String = "Fecha"
      val duration: String = "Duración"

      // Busca el máximo valor de cada campo
      def maxValuesRec(listaRecords: List[String], maxValues: (Int, Int, Int, Int)): (Int, Int, Int, Int) = {
        if (Matrix.isEmpty(listaRecords)) {
          maxValues
        } else {
          val (nombre, puntuacion, fecha, duracion) = parsearJson(listaRecords.head)
          //val (nombre, puntuacion, fecha, duracion) = parsearJson(listaRecords.head)
          val newMaxValues = (
            Math.max(maxValues._1, strLength(nombre)),
            Math.max(maxValues._2, strLength(puntuacion.toString)),
            Math.max(maxValues._3, strLength(fecha)),
            Math.max(maxValues._4, strLength(duracion.toString))
          )
          maxValuesRec(listaRecords.tail, newMaxValues)
        }
      }

      // Uso:
      val maxValues = maxValuesRec(listaRecords, (strLength(name), strLength(score), strLength(date), strLength(duration)))


      // Calcula el tamaño de cada columna y el tamaño total de cada fila
      val colSizes: List[Int] = List(maxValues._1, maxValues._2, maxValues._3, maxValues._4)
      val totalRowSize: Int = sumRecursive(colSizes) + (Matrix.longitud(colSizes) - 1) * 3 + 4

      // Imprime el borde superior de la tabla
      println()
      //printf(" %s%s%s \n", repeat(" ", ((totalRowSize - (name.length + score.length + date.length + duration.length - 3)) / 2)), s"$name $score $date $duration", repeat(" ", ((totalRowSize - (name.length + score.length + date.length + duration.length - 3)) / 2)))

      printf("┏%s┓\n", repeat("━", totalRowSize))

      val nameFormatted = name + repeat(" ", maxValues._1 - strLength(name))
      val scoreFormatted = score + repeat(" ", maxValues._2 - strLength(score))
      val dateFormatted = date + repeat(" ", maxValues._3 - strLength(date))
      val durationFormatted = duration + repeat(" ", maxValues._4 - strLength(duration))

      // Imprime una línea con la fila centrada dentro de ella
      printf("┃ %s │ %s │ %s │ %s   ┃\n", nameFormatted, scoreFormatted, dateFormatted, durationFormatted)

      printf("┣%s┫\n", repeat("━", totalRowSize))

      def printRows(listaRecords: List[String], maxValues: (Int, Int, Int, Int)): Unit = {
        if (!Matrix.isEmpty(listaRecords)) {
          val (nombre, puntuacion, fecha, duracion) = parsearJson(listaRecords.head)

          // Formatea cada columna para que tenga el tamaño correcto
          val nombreFormatted = nombre + repeat(" ", maxValues._1 - strLength(nombre))
          val puntuacionFormatted = puntuacion.toString + repeat(" ", maxValues._2 - strLength(puntuacion.toString))
          val fechaFormatted = fecha + repeat(" ", maxValues._3 - strLength(fecha))
          val duracionFormatted = duracion.toString + repeat(" ", maxValues._4 - strLength(duracion.toString))

          // Imprime una línea con la fila centrada dentro de ella
          printf("┃ %s │ %s │ %s │ %s   ┃\n", nombreFormatted, puntuacionFormatted, fechaFormatted, duracionFormatted)

          if (listaRecords.size > 1) { //TODO: Meter longitud en Object de Matrix y no en Class
            // Imprime una línea horizontal entre filas
            printf("┣%s┫\n", repeat("━", totalRowSize))
          }
          printRows(listaRecords.tail, maxValues)
        }
      }

      printRows(listaRecords, maxValues)

      // Imprime el borde inferior de la tabla
      printf("┗%s┛\n", repeat("━", totalRowSize))
    }



    def obtenerTiempos(): (String, Long) = {
      //Miro cuanto ha durado el programa y la fecha y hora de finalización
      val endTime = System.nanoTime()
      val duracion = (endTime - startTime).nanos
      val endDateTime = Calendar.getInstance().getTime
      val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
      (formatter.format(endDateTime), duracion.toSeconds)
    }

    //Fin de la ejecucion (Fin del main)
  }

  def strLength(str: String): Int = {
    def strLength(str:String, index:Int = 0): Int ={
      if(str == "")
        index
      else
        strLength(str.tail,index+1)
    }
    strLength(str,0)
  }

  def charAtRecursive(str: String, index: Int): Char = {

    def charAtRecursiveAux(str:String,index:Int,longitud:Int):Char={
      if(index<0 || index>=longitud)
        throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for string " + str)
      if(index == 0)
        str.head
      else
        charAtRecursiveAux(str.tail,index-1,longitud-1)
    }
    charAtRecursiveAux(str,index,strLength(str))

  }

  def indexOfRecursive(str:String,search:String): Int ={ //No me deja tener las dos funciones sobrecargadas si están inicializadas a algún valor
    indexOfRecursive(str,search,0)
  }
  def indexOfRecursive(str:String,searchChar:Char,index:Int = 0): Int ={ //Sirve solo para el indice de un caracter

    def indexOfRecursiveAux(str:String,searchChar:Char,index:Int,longitud:Int):Int = {
      if(index>=longitud)
        -1
      else if(charAtRecursive(str,index) == searchChar)
        index
      else
        indexOfRecursiveAux(str,searchChar,index+1,longitud)
    }
    indexOfRecursiveAux(str,searchChar,index,strLength(str))
  }

  def indexOfRecursive(str: String, search: String, index: Int): Int = {

    def indexOfRecursiveAux(str:String,search:String,index:Int,longitud:Int,longitudSearch:Int):Int ={
      if(index > longitud - longitudSearch)
        -1
      else if(substringRecursive(str,index,index+longitudSearch) == search)
        index
      else
        indexOfRecursiveAux(str,search,index+1,longitud,longitudSearch)
    }
    indexOfRecursiveAux(str,search,index,strLength(str),strLength(search))
  }

  def substringRecursive(str:String,start:Int,end:Int):String={

    def substringRecursiveAux(str:String,start:Int,end:Int,longitud:Int):String={
      if(start<0 || end>longitud || start>end)
        throw new IndexOutOfBoundsException("Index out of bounds")
      else if(start>=end)
        ""
      else
        charAtRecursive(str,start)+substringRecursiveAux(str,start+1,end,longitud)
    }
    substringRecursiveAux(str,start,end,strLength(str))
  }

  def substringRecursive(str:String,start:Int):String ={
    def substringRecursiveAux(str:String,start:Int,longitud:Int):String={
      if(start<0 || start>longitud)
        throw new IndexOutOfBoundsException("Index out of bounds")
      else if(start>=longitud)
        ""
      else
        charAtRecursive(str,start)+substringRecursiveAux(str,start+1,longitud)
    }
    substringRecursiveAux(str,start,strLength(str))
  }

  def sumRecursive(lst: List[Int]): Int = {
    lst match {
      case Nil => 0 // Caso base: lista vacía, retorna 0
      case head :: tail => head + sumRecursive(tail) // Suma el primer elemento con la suma del resto de la lista
    }
  }

  def sumarPuntos(puntos: Int, contadorEliminados: Int, elementoEliminado: Int, dificultad: Int): Int = {
    elementoEliminado match {
      case 7 => puntos + (5 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 5 por bomba + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
      case 8 => puntos + (10 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 10 por TNT + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
      case rompeCabezas if (rompeCabezas >= 11 && rompeCabezas <= 16) => puntos + (15 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 15 por rompecabezas + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
      case _ => puntos + (contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
    }
  }
}
