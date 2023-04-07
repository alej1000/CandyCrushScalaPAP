import scala.util.Random
object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = { //Funcion principal del programa -> Si se ejecuta en terminal: scala Main.scala sino no hace falta llamarla
    println("Hello world!")
    //Aquí dentro podríamos ejectuar el bucle principal del programa -> Bucle while mirando las vidas

    //Inicializar semilla randoms

    //Inicializar tablero con numeros aleatorios
    crearMatriz(10, 10, 0) //Genera la matriz con 0s
    generarMatriz(10, 10) //Genera la matriz con numeros aleatorios

    //Bucle principal del juego -> while(vida > 0) -> Se pueden bucles while? o tiene que ser recursivo?
    //Recursivo: funcion_principal(tablero,fila,columna,vidas)
      //Mostrar tablero

      //Solicitar al usuario que introduzca una fila y una columna

      //Llamo a la funcion principal que empieza el juego -> Si es con bucle se llamaría aqui sino, es la funcion recursiva

  }

  def concatenar(x: List[Int], y: List[Int]): List[Int] = { //Esto es x:::y
    x match {
      case Nil => y
      //case n::Nil => n::y
      case head :: tail => head :: concatenar(tail, y)
    }
  }

  concatenar(List(1, 2, 3), List(4, 5, 6))

  def invertir(x: List[Int]): List[Int] = {
    x match {
      case Nil => Nil
      case _ => invertir(x.tail) ::: x.head :: Nil
    }
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
  def crearMatriz[T](filas: Int, columnas: Int, valor: T): List[List[T]] = {
    if (filas == 0) {
      Nil
    } else {
      val fila = List.fill(columnas)(valor) //No creo que se pueda usar el fill
      val resto = crearMatriz(filas - 1, columnas, valor)
      fila :: resto
    }
  }

  def mostrarMatriz[T](matriz: List[List[T]]): Unit = {
    matriz match {
      case Nil => println()
      case fila :: resto => {
        fila match {
          case Nil => println()
          case celda :: resto => {
            print(celda + " ")
            mostrarMatriz(List(resto))
          }
        }
        mostrarMatriz(resto)
      }
    }
  }

  //Esto de aquí es de la tarea 4.3 que puede servir para generar y coger los elementos de manera recursiva
  def toma(n: Int, l: List[Int]): List[Int] = {
    //Coge los n primeros elementos de la lista de manera recursiva
    if (n == 0) Nil
    else l.head :: toma(n - 1, l.tail)
  }

  def deja(n: Int, l: List[Int]): List[Int] = {
    //Deja una lista sin los n primeros elementos de la lista de manera recursiva
    if (n == 0) l
    else deja(n - 1, l.tail)
  }

  def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0 //Si la lista está vacía la longitud es 0
      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
    }
  }

  //Genero la matriz aleatoriamente
  def generarMatriz(filas: Int, columnas: Int): List[Int] = {
    if (filas == 0) Nil
    else {
      concatenar(generarColumnas(columnas), generarMatriz(filas - 1, columnas))
    }
  }

  def generarColumnas(n: Int): List[Int] = {
    val rand = new Random()
    if (n == 0) Nil
    else rand.nextInt(100) :: generarColumnas(n - 1) //Genero un número aleatorio entre 0 y 99
  }

  def imprimirMatriz(matriz: List[Int]): Unit = {
    matriz match {
      case Nil => println()
      case head :: tail => {
        //print("|"+head+"|")
        printf("| %3s |", head)
        if (longitud(tail) % 8 == 0) println()
        imprimirMatriz(tail)
      }
    }
  }

  def getElem(index: Int, matriz: List[Int]): Int = { //Prefiero crearla con fila:Int y columna:Int, antes que pasarle el indice directamente
    matriz match {
      case Nil => throw new Exception("El índice no es válido")
      case head :: tail => {
        if (index == 0) head
        else getElem(index - 1, tail)
      }
    }
  }

  def getFila(fila: Int, matriz: List[Int]): List[Int] = {
    toma(8, deja(fila * 8, matriz)) //Coge los 8 primeros elementos de la lista que queda de dejar todo menos los 8 primeros
    //La matriz tiene 8 filas y 8 columnas
    //toma(numColumnas,deja(filaElegida * numColumnas,matriz))
  }

  def getColumna(index: Int, matriz: List[Int]): List[Int] = {
    if (index >= 0 && index < 8 && longitud(matriz) >= 8) { //Si el índice es válido -> 0<=index<numColumnas y longitud>=numColumnas
      getElem(index, matriz) :: getColumna(index, deja(8, matriz))
    } else {
      Nil
    }
  }

  def eliminarPosicion(tablero:List[List[Int]], fila:Int, columna:Int):List[List[Int]] = {
    //Elimina (pone a 0) la posicion indicada
    buscarJuntos(tablero, fila, columna,0) //Puedo hacer que devuelva el contador despues de haberlas eliminado? -> Asi ya tengo el numero de fichas que he eliminado
    tablero
  }

  def activarGravedad(tablero:List[List[Int]]):List[List[Int]] = {
    //Baja las fichas que estén encima de una celda vacía
    tablero
  }

  def buscarJuntos(tablero:List[List[Int]], fila:Int, columna:Int,contador:Int):Int = {
    //Busca las fichas que estén juntas a la indicada y las elimina
    contador
  }



}