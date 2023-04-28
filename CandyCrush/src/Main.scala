import scala.util.Random
import scala.io._
import java.io._

object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    //val miMatriz = new Matrix(6,10)
    //miMatriz.toString()
//    val miMat = generarMatriz(6,10)
//    val miPrueba = new Prueba(6,10,miMat)
//    miPrueba.imprimir()




    println("Bienvenido a Cundy Crosh 2.0 🍬🍬🍬")
    val puntosIniciales:Int = 0
    if(args.length > 4){ //Si se pasan los argumentos por consola //filas,columnas,aleatorio-manual,dificultad: 1-2
        val filas = args(0).toInt
        val columnas = args(1).toInt
        val modoDeJuego = args(2).charAt(0)
        val dificultad = args(3).toInt
        if(dificultad == 2 || dificultad==1){
          val tablero = new Matrix(filas,columnas,dificultad)
          partida(tablero,5,modoDeJuego,puntosIniciales,dificultad)
        }else{
          println("Se pondrá dificultad 2 por defecto")
          val tablero = new Matrix(filas,columnas,2)
          partida(tablero,5,modoDeJuego,puntosIniciales,2)
        }

    }else{ // Si no es por consola
      val filas = introducirInt("Introduce cuantas filas quieres")

      val columnas = introducirInt("Introduce cuantas columnas quieres")

      println("Intruce el modo de juego(a o m): ")
      val modoDeJuego = scala.io.StdIn.readChar()

      println("Introduce la dificultad (1 o 2): ")
      val dificultad = introducirInt("Introduce la dificultad (1 o 2):")
      if (dificultad == 2 || dificultad == 1) {
        val tablero = new Matrix(filas, columnas, dificultad)
        partida(tablero, 5, modoDeJuego,puntosIniciales,dificultad)
      } else {
        println("Se pondrá dificultad 2 por defecto")
        val tablero = new Matrix(filas, columnas, 2)
        partida(tablero, 5, modoDeJuego,puntosIniciales,2)
      }
    }


    def partida(tablero: Matrix, vidas: Int,modoDeJuego:Char,puntosTotales:Int,dificultad:Int): Unit = {
      if (vidas == 0) {
        println("Has perdido")
        controlFinal("Records.txt",puntosTotales)
        return
      }
      //println("Vidas: " + vidas)
      print("Vidas restantes: ")
      mostrarVidas(vidas)
      println()
      println("Puntos: "+puntosTotales)
      tablero.toString()
      if(modoDeJuego == 'm'){ //Es manual
//        println("Introduce la fila")
        val fila = introducirInt("Introduce la fila")
//        println("Introduce la columna")
        val columna = introducirInt("Introduce la columna")
        //Ver como hacer solo una llamada
        val (tableroNew: Matrix, vidasNew: Int,contadorEliminados:Int,elementoEliminado:Int) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
        val puntosSumados:Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado)

        if(dificultad==1) partida(tableroNew, vidasNew, modoDeJuego,puntosSumados,dificultad)
        else partida(tableroNew, vidasNew, modoDeJuego,puntosSumados * 2,dificultad)

        //partida(tableroNew, vidasNew, modoDeJuego,puntosSumados)
      }else{ //Es automático
//        val rand = new Random()
//        val fila = rand.nextInt(tablero.getNumFilas())
//        val columna = rand.nextInt(tablero.getNumColumnas())
//        //Ver como hacer solo una llamada
//        val (tableroNew: Matrix, vidasNew: Int) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
//        partida(tableroNew, vidasNew, modoDeJuego)
        modoAutomatico(tablero,vidas,puntosTotales,dificultad:Int)
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

    def modoAutomatico(tablero:Matrix, vidas:Int,puntosTotales:Int,dificultad:Int): Unit = {
      val (fila:Int,columna:Int) = consultarMejorOpcion(tablero)
      println("La mejor opción es la fila: " + fila + " y la columna: " + columna + "")
      val (tableroNew: Matrix, vidasNew: Int,contadorEliminados:Int,elementoEliminado:Int) = tablero.consulta(fila, columna, vidas)
      scala.io.StdIn.readLine()//Para que pare y se pueda ver
      val puntosSumados:Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado)

      if(dificultad==1) partida(tableroNew, vidasNew, 'a',puntosSumados,dificultad)
      else partida(tableroNew, vidasNew, 'a',puntosSumados * 2,dificultad)
      //partida(tableroNew, vidasNew, 'a',puntosSumados)
    }

    def consultarMejorOpcion(tablero:Matrix): (Int,Int) = {
      val (fila:Int,columna:Int,_) = consultarMejorOpcionAux(tablero,0,0,0,0,0)
      (fila,columna)
    }
    def consultarMejorOpcionAux(tablero:Matrix,fila:Int,columna:Int,mejorContador:Int,mejorFila:Int,mejorColumna:Int): (Int,Int,Int) ={
      val contadorActual = tablero.detectorEliminacion(fila, columna)._2
      if(fila == tablero.getNumFilas()-1 && columna == tablero.getNumColumnas()-1){ //Si llego al final del tablero el contador es el que tengo
        return (mejorFila,mejorColumna,mejorContador)
      }else if(columna>=tablero.getNumColumnas()){ //Si llego al final de la fila
        if(mejorContador < contadorActual) return consultarMejorOpcionAux(tablero,fila+1,0,contadorActual,fila,columna)
         return consultarMejorOpcionAux(tablero,fila+1,0,mejorContador,mejorFila,mejorColumna)
      }else{  //Si no llego al final de la fila
        if(mejorContador < contadorActual) return consultarMejorOpcionAux(tablero,fila,columna+1,contadorActual,fila,columna)
        return consultarMejorOpcionAux(tablero,fila,columna+1,mejorContador,mejorFila,mejorColumna)
      }
    }


    def mostrarVidas(vidas:Int): Unit = {
      if(vidas>0) {
        mostrarVidas(vidas-1)
        print("❤️")
      }
    }

    def controlFinal(filename:String,puntuacionFinal:Int): Unit ={
      val puntuaciones:List[String] = cargarPuntuaciones(filename)
      mostrarPuntuaciones(puntuaciones)
      println("Introduce tu nombre para que figure en los records")
      val nombre:String = scala.io.StdIn.readLine()
      guardarPuntuaciones(filename,nombre,puntuacionFinal)
      val nuevasPuntuaciones:List[String] = cargarPuntuaciones(filename) //Cuando sea la mas alta salta un mensaje de nuevo Record
      mostrarPuntuaciones(nuevasPuntuaciones)

    }

    def guardarPuntuaciones(filename:String,nombre:String,puntuacion:Int): Unit ={
      val file = new File(filename) //Si no existe lo crea
      if (!file.exists()) {
        file.createNewFile()
      }
      val writer = new FileWriter(new File(filename),true) //True para que no borre lo que ya hay -> Append
      writer.write("Records\n")
      writer.write(nombre + ": " + puntuacion+"\n")
      writer.close()
    }
    //TODO: Quitar las funciones de listas -> empty, isEmpty
    def cargarPuntuaciones(filename: String): List[String] = {
      val file = new File(filename)
      if (!file.exists()) {
        println("No hay puntuaciones guardadas")
        return List()
      }else {
        val source = Source.fromFile(filename)
        try {
          val lines: List[String] = source.getLines.toList
          return lines
        } finally {
          source.close()
        }
      }
    }



    //TODO: Quitar las funciones de listas -> isEmpty
    def mostrarPuntuaciones(puntuaciones:List[String]): Unit ={
      if(!puntuaciones.isEmpty){
        println(puntuaciones.head)
        mostrarPuntuaciones(puntuaciones.tail)
      }
    }


    def sumarPuntos(puntos: Int, contadorEliminados: Int, elementoEliminado: Int): Int = {
      println("ContadorEliminados: "+contadorEliminados)
      if (contadorEliminados > 0) {
        if(contadorEliminados>=10){ //Sumo un punto por cada 10 eliminados más los 10 eliminados
          return 11 + sumarPuntos(puntos,contadorEliminados-10,elementoEliminado)
        } else { //Sumo un punto por cada bloque eliminado
          return contadorEliminados + sumarPuntos(puntos, 0, elementoEliminado)
        }
      } else {
        println("ElementoEliminado: "+elementoEliminado)

        if (elementoEliminado == 7) { //Eliminé bomba
          return 5 + sumarPuntos(puntos, 0, 0)
        }
        if (elementoEliminado == 8) { //Eliminé TNT
          return 10 + sumarPuntos(puntos, 0, 0)
        }
        if (elementoEliminado >= 11 && elementoEliminado <= 16) { //Eliminé rompecabezas
          return 15 + sumarPuntos(puntos, 0, 0)
        }
        puntos
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

  }



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
