class Prueba(val rows: Int,val cols: Int,val data: List[Int]){


  private def generarMatriz(filas: Int, columnas: Int): List[Int] = {
    if (filas == 0) Nil
    else {
      concatenar(generarColumnas(columnas), generarMatriz(filas - 1, columnas))
    }
  }

  // Method to generate a list of random integers with given length
  private def generarColumnas(n: Int): List[Int] = {
    val rand = new scala.util.Random()
    if (n == 0) Nil
    else rand.nextInt(100) :: generarColumnas(n - 1) //Genero un número aleatorio entre 0 y 99
  }

  private def concatenar(x: List[Int], y: List[Int]): List[Int] = {
    x match {
      case Nil => y
      case head :: tail => head :: concatenar(tail, y)
    }
  }

  def imprimir(): Unit = {
    imprimirMatriz(data)
  }

  def imprimirMatriz(matriz: List[Int]): Unit = {
    matriz match {
      case Nil => println()
      case head :: tail => {
        //print("|"+head+"|")
        printf("| %3s |", head)
        if (longitud(tail) % cols == 0) println()
        imprimirMatriz(tail)
      }
    }
  }

  def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0 //Si la lista está vacía la longitud es 0
      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
    }
  }
  /*def this(rows: Int, cols: Int) = {
    this(rows, cols, generarMatriz(rows, cols))
  }*/
}
