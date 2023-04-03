object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = { //Funcion principal del programa -> Si se ejecuta en terminal: scala Main.scala sino no hace falta llamarla
    println("Hello world!")
    //Aquí dentro podríamos ejectuar el bucle principal del programa -> Bucle while mirando las vidas

    //Inicializar semilla randoms

    //Inicializar tablero con numeros aleatorios
    crearMatriz(10, 10, 0)

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

  def eliminarPosicion(tablero:List[List[Int]], fila:Int, columna:Int):List[List[Int]] = {
    //Elimina (pone a 0) la posicion indicada
    buscarJuntos(tablero, fila, columna,0) //Puedo hacer que devuelva el contador despues de haberlas eliminado?
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