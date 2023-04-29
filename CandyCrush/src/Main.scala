import scala.util.Random
import scala.io._
import java.io._
import pruebaJava.PruebaJava

object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    //val miMatriz = new Matrix(6,10)
    //miMatriz.toString()
    //    val miMat = generarMatriz(6,10)
    //    val miPrueba = new Prueba(6,10,miMat)
    //    miPrueba.imprimir()



    //Para comnectarse a las funciones java
      val miInstancia = new PruebaJava()
      miInstancia.miFuncion("Hola desde Scala")





    println("Bienvenido a Cundy Crosh 2.0 🍬🍬🍬")
    val puntosIniciales: Int = 0
    if (args.length > 4) { //Si se pasan los argumentos por consola //filas,columnas,aleatorio-manual,dificultad: 1-2
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


    }else{ // Si no es por consola
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
        println("Has perdido")
        controlFinal("Records.txt", puntosTotales,modoDeJuego)
        return
      }
      //println("Vidas: " + vidas)
      print("Vidas restantes: ")
      mostrarVidas(vidas)
      println()
      println("Puntos: " + puntosTotales)
      tablero.toString()
      if(modoDeJuego == 'm'){ //Es manual
//        println("Introduce la fila")
        val fila = introducirInt("Introduce la fila")
//        println("Introduce la columna")
        val columna = introducirInt("Introduce la columna")
        //Ver como hacer solo una llamada
        val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
        val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado,dificultad)
        partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)

        //partida(tableroNew, vidasNew, modoDeJuego,puntosSumados)
      } else { //Es automático
        //        val rand = new Random()
        //        val fila = rand.nextInt(tablero.getNumFilas())
        //        val columna = rand.nextInt(tablero.getNumColumnas())
        //        //Ver como hacer solo una llamada
        //        val (tableroNew: Matrix, vidasNew: Int) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
        //        partida(tableroNew, vidasNew, modoDeJuego)
        val (tableroNew: Matrix, vidasNew: Int, puntosSumados: Int) = modoAutomatico(tablero, vidas, puntosTotales, dificultad: Int)
        partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
      }
    }

    def introducirInt(cadena:String): Int = {
      println(cadena+"\n")
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
      val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int) = tablero.consulta(fila, columna, vidas)
      scala.io.StdIn.readLine() //Para que pare y se pueda ver
      val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado,dificultad)
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

    def controlFinal(filename: String, puntuacionFinal: Int,modoDeJuego:Char): Unit = {
      if(modoDeJuego == 'm'){
        val puntuaciones: List[String] = cargarPuntuaciones(filename)
        println("Ultimos records: ")
        mostrarPuntuaciones(puntuaciones)
        println("Tú puntuacion:" + puntuacionFinal)
        println("Introduce tu nombre para que figure en los records")
        val nombre: String = scala.io.StdIn.readLine()
        guardarPuntuaciones(filename,nombre,puntuacionFinal)
        val nuevasPuntuaciones: List[String] = cargarPuntuaciones(filename) //TODO:Cuando sea la mas alta salta un mensaje de nuevo Record
        println("Records:")
        mostrarPuntuaciones(nuevasPuntuaciones)
      }else{ // Es automatico
        val nombre: String = "AutoGod"
        guardarPuntuaciones(filename,nombre,puntuacionFinal)
        val nuevasPuntuaciones: List[String] = cargarPuntuaciones(filename) //Cuando sea la mas alta salta un mensaje de nuevo Record
        mostrarPuntuaciones(nuevasPuntuaciones)
      }
    }


    def guardarPuntuaciones(filename:String,nombre:String,puntuacion:Int):Unit ={
      val file = new File(filename)
      if(!file.exists()){
        file.createNewFile()
      }
      val lastRecords:List[String] = cargarPuntuaciones(filename)
      val puntuacionesActualizadas:String = guardarPuntuacionesAux(filename,lastRecords,nombre,puntuacion,"")
      val writer = new FileWriter(new File(filename)) //True para que no borre lo que ya hay -> Append
      writer.write(puntuacionesActualizadas)
      writer.close()
    }

    def guardarPuntuacionesAux(filename:String,lastRecords:List[String],nombre:String,puntuacion:Int,puntuacionesAcum:String):String ={
      if(lastRecords.isEmpty) { //Si he acabado
        if(puntuacion == -1){ //Si puse mi ultima puntuacion acabo
          return puntuacionesAcum
        }else{ //Si no he puesto mi ultima puntuacion
          return puntuacionesAcum + (nombre + ":" + puntuacion + "\n")
        }
      }
      val (first: String, score: Int) = buscarClaveValor(lastRecords.head)
      if(score > puntuacion){
        val cadena:String = puntuacionesAcum + (first + ":" + score + "\n")
        guardarPuntuacionesAux(filename,lastRecords.tail,nombre,puntuacion,cadena)
      }else{
        val cadena:String = puntuacionesAcum + (nombre+ ":" + puntuacion + "\n")
        guardarPuntuacionesAux(filename,lastRecords,"-1",-1,cadena)
      }
    }

    def buscarClaveValor(str: String): (String, Int) = {
      def buscarClaveValorRec(str: String, i: Int): (String, Int) = {
        if (i >= str.length) {
          throw new IllegalArgumentException("No se encontró el carácter ':' en el String.")
        } else if (str.charAt(i) == ':') {
          val key = str.substring(0, i).trim
          val value = str.substring(i + 1).trim.toInt
          (key, value)
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
          println("Las puntuaciones que devuelve cargarPuntuaciones son: "+lines)
          return lines
        } finally {
          source.close()
        }
      }
    }

    //TODO: Quitar las funciones de listas -> isEmpty
    def mostrarPuntuaciones(puntuaciones: List[String]): Unit = {
      if (!puntuaciones.isEmpty) {
        println(puntuaciones.head)
        mostrarPuntuaciones(puntuaciones.tail)
      }
    }

    def sumarPuntos(puntos: Int, contadorEliminados: Int, elementoEliminado: Int,dificultad:Int): Int = {
      elementoEliminado match {
        case 7 => puntos + (5 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 5 por bomba + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
        case 8 => puntos + (10 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 10 por TNT + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
        case rompeCabezas if (rompeCabezas >= 11 && rompeCabezas <= 16) => puntos + (15 + contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 15 por rompecabezas + 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
        case _ => puntos + (contadorEliminados + contadorEliminados / 10) * dificultad //Sumo 1 por cada elemento eliminado + 1 por cada 10 elementos eliminados
      }
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
