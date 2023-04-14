import scala.util.Random
object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    //val miMatriz = new Matrix(6,10)
    //miMatriz.toString()
//    val miMat = generarMatriz(6,10)
//    val miPrueba = new Prueba(6,10,miMat)
//    miPrueba.imprimir()




    println("Bienvenido a Cundy Crosh 2.0 🍬🍬🍬")

    if(args.length < 4+1){ //Si se pasan los argumentos por consola //filas,columnas,aleatorio-manual,dificultad: 1-2
        val filas = args(1).toInt
        val columnas = args(2).toInt
        val modoDeJuego = args(3).charAt(0)
        val dificultad = args(4).toInt
        if(dificultad == 2 || dificultad==1){
          val tablero = new Matrix(filas,columnas,dificultad)
          partida(tablero,5,modoDeJuego)
        }else{
          println("Se pondrá dificultad 2 por defecto")
          val tablero = new Matrix(filas,columnas,2)
          partida(tablero,5,modoDeJuego)
        }

    }else{ // Si no es por consola
      println("Introduce cuantas filas quieres")
      val filas = scala.io.StdIn.readInt()

      println("Introduce cuantas columnas quieres")
      val columnas = scala.io.StdIn.readInt()

      println("Intruce el modo de juego(a o m): ")
      val modoDeJuego = scala.io.StdIn.readChar()

      println("Introduce la dificultad (1 o 2): ")
      val dificultad = scala.io.StdIn.readInt()
      if (dificultad == 2 || dificultad == 1) {
        val tablero = new Matrix(filas, columnas, dificultad)
        partida(tablero, 5, modoDeJuego)
      } else {
        println("Se pondrá dificultad 2 por defecto")
        val tablero = new Matrix(filas, columnas, 2)
        partida(tablero, 5, modoDeJuego)
      }
    }


    def partida(tablero: Matrix, vidas: Int,modoDeJuego:Char): Unit = {
      if (vidas == 0) {
        println("Has perdido")
        return
      }
      println("Vidas: " + vidas)
      tablero.toString()
      if(modoDeJuego == 'm'){ //Es manual
        println("Introduce la fila")
        val fila = scala.io.StdIn.readInt()
        println("Introduce la columna")
        val columna = scala.io.StdIn.readInt()
      }else{ //Es automático
        val rand = new Random()
        val fila = rand.nextInt(tablero.getNumFilas())
        val columna = rand.nextInt(tablero.getNumColumnas())

      }

      //Ver como hacer solo una llamada
      val cuantosEliminados = tablero.consulta(fila, columna, vidas)._2 //consulta es el eliminarPosicion
      if (cuantosEliminados>2) {
        partida(tablero, vidas,modoDeJuego)
      }else{
        partida(tablero, vidas-1,modoDeJuego)
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


  def generarMatriz(filas: Int, columnas: Int): List[Int] = {
    if (filas == 0) Nil
    else {
      concatenar(generarColumnas(columnas), generarMatriz(filas - 1, columnas))
    }
  }

  // Method to generate a list of random integers with given length
  def generarColumnas(n: Int): List[Int] = {
    val rand = new scala.util.Random()
    if (n == 0) Nil
    else rand.nextInt(100) :: generarColumnas(n - 1) //Genero un número aleatorio entre 0 y 99
  }

  def concatenar(x: List[Int], y: List[Int]): List[Int] = {
    x match {
      case Nil => y
      case head :: tail => head :: concatenar(tail, y)
    }
  }

  def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0 //Si la lista está vacía la longitud es 0
      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
    }
  }

}
