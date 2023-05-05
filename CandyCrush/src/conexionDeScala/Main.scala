package conexionDeScala

import scala.io._
import java.io._
import scala.concurrent.duration._
import java.util.Calendar
import java.text.SimpleDateFormat

object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    //val miMatriz = new Matrix(6,10)
    //miMatriz.toString()
    //    val miMat = generarMatriz(6,10)
    //    val miPrueba = new Prueba(6,10,miMat)
    //    miPrueba.imprimir()


    val startTime = System.nanoTime()

    println("Bienvenido a Cundy Crosh 2.0 🍬🍬🍬")
    val puntosIniciales: Int = 0
    if (args.length > 3) { //Si se pasan los argumentos por consola //filas,columnas,aleatorio-manual,dificultad: 1-2
      val filas = args(0).toInt
      val columnas = args(1).toInt
      val modoDeJuego = args(2).charAt(0)
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

      println("Intruce el modo de juego(a o m): ")
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
        controlFinal("Records.txt", puntosTotales, modoDeJuego)
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
      } else { //Es automático
        val (tableroNew: Matrix, vidasNew: Int, puntosSumados: Int) = modoAutomatico(tablero, vidas, puntosTotales, dificultad: Int)
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

    def strLength(str: String): Int = {
      if (str == "") {
        0
      } else {
        1 + strLength(str.tail)
      }
    }

    def modoAutomatico(tablero: Matrix, vidas: Int, puntosTotales: Int, dificultad: Int): (Matrix, Int, Int) = {
      val (fila: Int, columna: Int) = consultarMejorOpcion(tablero)
      println("La mejor opción es la fila: " + fila + " y la columna: " + columna + "")
      val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int,matriz0:Matrix) = tablero.consulta(fila, columna, vidas)
      scala.io.StdIn.readLine() //Para que pare y se pueda ver
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

    def controlFinal(filename: String, puntuacionFinal: Int, modoDeJuego: Char): Unit = {
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
        guardarPuntuaciones(filename, nombre, puntuacionFinal, horaFin, duracionPartida)
        val nuevasPuntuaciones: List[String] = cargarPuntuaciones(filename)
        println("\nRecords:")
        //println("Nombre\t\tPuntuacion\t\tDuracion\t\tFecha")
        //printf("%s %20s %20s %20s\n", "Nombre", "Puntuacion", "Duracion", "Fecha")

        mostrarPuntuaciones(nuevasPuntuaciones)
      } else { // Es automatico
        println("La puntuacion final es: " + puntuacionFinal)
        println("El Robotito duró: " + duracionPartida + " segundos jugando")
        val nombre: String = "AutoGod"
        guardarPuntuaciones(filename, nombre, puntuacionFinal, horaFin, duracionPartida)
        val nuevasPuntuaciones: List[String] = cargarPuntuaciones(filename) //Cuando sea la mas alta salta un mensaje de nuevo Record
        mostrarPuntuaciones(nuevasPuntuaciones)
      }
    }

    def guardarPuntuaciones(filename: String, nombre: String, puntuacion: Int, horaFin: String, duracionPartida: Long): Unit = {
      val file = new File(filename)
      if (!file.exists()) {
        file.createNewFile()
      }
      val lastRecords: List[String] = cargarPuntuaciones(filename)

      def getBestScore(lastRecords: List[String]): Int = {
        if (Matrix.isEmpty(lastRecords) || lastRecords.head == "") return -1
        val (_, mejorPuntuacion: Int, _, _) = buscarClaveValor(lastRecords.head)
        mejorPuntuacion
      }

      val mejorPuntuacion = getBestScore(lastRecords)
      val puntuacionesActualizadas: String = guardarPuntuacionesAux(filename, lastRecords, nombre, puntuacion, horaFin, duracionPartida, "", mejorPuntuacion)
      val writer = new FileWriter(new File(filename)) //True para que no borre lo que ya hay -> Append
      writer.write(puntuacionesActualizadas)
      writer.close()
    }

    def guardarPuntuacionesAux(filename: String, lastRecords: List[String], nombre: String, puntuacion: Int, horaFin: String, duracionPartida: Long, puntuacionesAcum: String, mejorPuntuacion: Int): String = {
      if (Matrix.isEmpty(lastRecords) || lastRecords.head == "") { //Si he acabado
        if (puntuacion == -1) { //Si puse mi ultima puntuacion acabo
          return puntuacionesAcum
        } else { //Si no he puesto mi ultima puntuacion
          return puntuacionesAcum + (nombre + ": " + puntuacion + " @" + horaFin + " &" + duracionPartida + "\n")
        }
      }
      val (first: String, score: Int, time: String, duracion: Long) = buscarClaveValor(lastRecords.head)
      if (score > puntuacion) {
        val cadena: String = puntuacionesAcum + (first + ": " + score + " @" + time + " &" + duracion + "\n")
        guardarPuntuacionesAux(filename, lastRecords.tail, nombre, puntuacion, horaFin, duracionPartida, cadena, mejorPuntuacion)
      } else { //Superé la puntuación
        if (puntuacion > mejorPuntuacion && mejorPuntuacion != -1) { //Si haces nuevo record
          println("Nuevo Record!!🏆")
        }
        val cadena: String = puntuacionesAcum + (nombre + ": " + puntuacion + " @" + horaFin + " &" + duracionPartida + " \n")
        guardarPuntuacionesAux(filename, lastRecords, "-1", -1, horaFin, duracionPartida, cadena, mejorPuntuacion)
      }
    }

    def buscarClaveValor(str: String): (String, Int, String, Long) = { //Devuelve (nombre, puntuacion,tiempo,duracion)
      //El formato de guardado tendrá que ser nombre:puntuacion@tiempo&duracion
      if (str == "") return (str, -1, str, -1) //Si no hay nada

      def buscarClaveValorRec(str: String, i: Int): (String, Int, String, Long) = { //Busca nombre:resto -> resto = puntuacion@tiempo&duracion

        if (i >= strLength(str)) {
          throw new IllegalArgumentException("No se encontró el carácter ':' en el String.")
        } else if (str.charAt(i) == ':') {
          val key = str.substring(0, i).trim
          val value = str.substring(i + 1).trim

          def buscarClaveValorRecAux(str: String, i: Int): (Int, String, Long) = { //Busca puntuacion@resto -> resto = tiempo&duracion

            if (i >= strLength(str)) {
              throw new IllegalArgumentException("No se encontró el carácter '@' en el String.")
            } else if (str.charAt(i) == '@') {
              val scoreValue = str.substring(0, i).trim.toInt
              val time = str.substring(i + 1).trim

              def buscarClaveValorRecAux2(str: String, i: Int): (String, Long) = { //Busca tiempo&duracion

                if (i >= strLength(str)) {
                  throw new IllegalArgumentException("No se encontró el carácter '&' en el String.")
                } else if (str.charAt(i) == '&') {
                  val timeValue = str.substring(0, i).trim
                  val durationValue = str.substring(i + 1).trim.toLong
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

    def cargarPuntuaciones(filename: String): List[String] = {
      val file = new File(filename)
      if (!file.exists()) {
        println("No hay puntuaciones guardadas")
        return List()
      } else {
        val source = Source.fromFile(filename)
        try {
          val lines: List[String] = source.getLines.toList
          return lines
        } finally {
          source.close()
        }
      }
    }

    //    def mostrarPuntuaciones(puntuaciones: List[String]): Unit = {
    //      if (!Matrix.isEmpty(puntuaciones)) {
    //        //println(puntuaciones.head)
    //        def mostrarPuntuacionesFormato(puntuacion:String): Unit ={
    //          val (nombre:String,puntos:Int,fecha:String,duracion:Long) = buscarClaveValor(puntuacion)
    //          //println(nombre + "\t\t" + puntos + "\t\t" + duracion + "\t\t" + fecha)
    //          printf("%s %15d %15d %25s \n",nombre,puntos,duracion,fecha)
    //        }
    //        mostrarPuntuacionesFormato(puntuaciones.head)
    //        mostrarPuntuaciones(puntuaciones.tail)
    //      }
    //    }

    def repeat(s: String, n: Int): String = {
      s * n
    }

    def mostrarPuntuaciones(listaRecords: List[String]): Unit = {
      //Función que imprime la pila de llamadas de manera magistral
      val name: String = "Nombre"
      val score: String = "Puntuación"
      val date: String = "Fecha"
      val duration: String = "Duración"

      //    var maxLength: Int = 0


      //    var maxValues: (Int, Int, Int, Int) = (name.length, score.length, date.length, duration.length)
      //    for (llamada <- pilaLlamadas) {
      //      val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(llamada)
      //      maxValues = (
      //        Math.max(maxValues._1, nombre.length),
      //        Math.max(maxValues._2, puntuacion.toString.length),
      //        Math.max(maxValues._3, fecha.length),
      //        Math.max(maxValues._4, duracion.toString.length)
      //      )
      //    }
      // Busca el máximo valor de cada campo
      def maxValuesRec(pilaLlamadas: List[String], maxValues: (Int, Int, Int, Int)): (Int, Int, Int, Int) = {
        if (Matrix.isEmpty(pilaLlamadas)) {
          maxValues
        } else {
          val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(pilaLlamadas.head)
          val newMaxValues = (
            Math.max(maxValues._1, strLength(nombre)),
            Math.max(maxValues._2, strLength(puntuacion.toString)),
            Math.max(maxValues._3, strLength(fecha)),
            Math.max(maxValues._4, strLength(duracion.toString))
          )
          maxValuesRec(pilaLlamadas.tail, newMaxValues)
        }
      }

      // Uso:
      val maxValues = maxValuesRec(listaRecords, (name.length, score.length, date.length, duration.length))


      // Calcula el tamaño de cada columna y el tamaño total de cada fila
      val colSizes: List[Int] = List(maxValues._1, maxValues._2, maxValues._3, maxValues._4)
      val totalRowSize: Int = colSizes.sum + (colSizes.length - 1) * 3 + 4 //TODO: Revisar esto

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

      // Imprime cada fila
      //    //for (i <- pilaLlamadas.size - 1 to 0 by -1) { //Imprime de abajo a arriba (formato pila)
      //    for(i <- 0 until pilaLlamadas.size) { //TODO: usar pilaLlamadas.indices -> ???
      //      val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(pilaLlamadas(i))
      //
      //      // Formatea cada columna para que tenga el tamaño correcto
      //      val nombreFormatted = nombre + repeat(" ", maxValues._1 - nombre.length)
      //      val puntuacionFormatted = puntuacion.toString + repeat(" ", maxValues._2 - puntuacion.toString.length)
      //      val fechaFormatted = fecha + repeat(" ", maxValues._3 - fecha.length)
      //      val duracionFormatted = duracion.toString + repeat(" ", maxValues._4 - duracion.toString.length)
      //
      //      // Imprime una línea con la fila centrada dentro de ella
      //      printf("┃ %s │ %s │ %s │ %s   ┃\n", nombreFormatted, puntuacionFormatted, fechaFormatted, duracionFormatted)
      //
      ////      if (i > 0) { //Cuando era de abajo a arriba
      //      if(i<pilaLlamadas.size-1) {
      //        // Imprime una línea horizontal entre filas
      //        printf("┣%s┫\n", repeat("━", totalRowSize))
      //      }
      //    }

      def printRows(pilaLlamadas: List[String], maxValues: (Int, Int, Int, Int)): Unit = {
        if (!Matrix.isEmpty(pilaLlamadas)) {
          val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(pilaLlamadas.head)

          // Formatea cada columna para que tenga el tamaño correcto
          val nombreFormatted = nombre + repeat(" ", maxValues._1 - strLength(nombre))
          val puntuacionFormatted = puntuacion.toString + repeat(" ", maxValues._2 - strLength(puntuacion.toString))
          val fechaFormatted = fecha + repeat(" ", maxValues._3 - strLength(fecha))
          val duracionFormatted = duracion.toString + repeat(" ", maxValues._4 - strLength(duracion.toString))

          // Imprime una línea con la fila centrada dentro de ella
          printf("┃ %s │ %s │ %s │ %s   ┃\n", nombreFormatted, puntuacionFormatted, fechaFormatted, duracionFormatted)

          if (pilaLlamadas.size > 1) { //TODO: Meter longitud en Object de Matrix y no en Class
            // Imprime una línea horizontal entre filas
            printf("┣%s┫\n", repeat("━", totalRowSize))
          }

          printRows(pilaLlamadas.tail, maxValues)
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
      val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      //      println("Duración de la partida: " + duracion.toSeconds + " segundos")
      //      println("Fin de la partida: " + formatter.format(endDateTime))
      (formatter.format(endDateTime), duracion.toSeconds)
    }

    //Fin de la ejecucion (Fin del main)
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





//Funcion principal del programa -> Si se ejecuta en terminal: scala Main.scala sino no hace falta llamarla
//Aquí dentro podríamos ejectuar el bucle principal del programa -> Bucle while mirando las vidas

//Inicializar semilla randoms

//Inicializar tablero con numeros aleatorios
// generarMatriz(10, 10, 0) //Genera la matriz con 0s
// List l = generarMatriz(10, 10) //Genera la matriz con numeros aleatorios
//generamos una magtriz y lo guardamos en una variable
// val matriz:List[Int] = generarMatriz(6, 10)
// imprimir(matriz,10) //Imprime la matriz
// println(getElem(matriz, 34)) //Imprime el elemento de la posicion 0
//Bucle principal del juego -> while(vida > 0) -> Se pueden bucles while? o tiene que ser recursivo?
//Recursivo: funcion_principal(tablero,fila,columna,vidas)
//Mostrar tablero

//Solicitar al usuario que introduzca una fila y una columna

//Llamo a la funcion principal que empieza el juego -> Si es con bucle se llamaría aqui sino, es la funcion recursiva


/*def crearMatriz[T](filas: Int, columnas: Int, valor: T): Array[Array[T]] = { //No sé si están permitidos los array porque ya tienen el concat
  if (filas == 0) {
    Array.empty
  } else {
    val fila = Array.fill(columnas)(valor)
    val resto = crearMatriz(filas - 1, columnas, valor)
    Array.concat(Array(fila), resto)
  }
}*/


// def eliminarPosicion(tablero:List[Int], fila:Int, columna:Int):List[Int] = {
//   //Elimina (pone a 0) la posicion indicada
//   buscarJuntos(tablero, fila, columna,0) //Puedo hacer que devuelva el contador despues de haberlas eliminado? -> Asi ya tengo el numero de fichas que he eliminado
//   tablero
// }

// def activarGravedad(tablero:List[Int]):List[Int]= {
//   //Baja las fichas que estén encima de una celda vacía
//   tablero
// }

// def buscarJuntos(tablero:List[List[Int]], fila:Int, columna:Int,contador:Int):Int = {
//   //Busca las fichas que estén juntas a la indicada y las elimina
//   contador
// }

//
//  def generarMatriz(filas: Int, columnas: Int): List[Int] = {
//    if (filas == 0) Nil
//    else {
//      concatenar(generarColumnas(columnas), generarMatriz(filas - 1, columnas))
//    }
//  }
//
//  // Method to generate a list of random integers with given length
//  def generarColumnas(n: Int): List[Int] = {
//    val rand = new scala.util.Random()
//    if (n == 0) Nil
//    else rand.nextInt(100) :: generarColumnas(n - 1) //Genero un número aleatorio entre 0 y 99
//  }
//
//  def concatenar(x: List[Int], y: List[Int]): List[Int] = {
//    x match {
//      case Nil => y
//      case head :: tail => head :: concatenar(tail, y)
//    }
//  }
//
//  def longitud[T](l: List[T]): Int = {
//    l match {
//      case Nil => 0 //Si la lista está vacía la longitud es 0
//      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
//    }
//  }
//
//}
