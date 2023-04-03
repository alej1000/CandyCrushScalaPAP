object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = { //Funcion principal del programa -> Si se ejecuta en terminal: scala Main.scala sino no hace falta llamarla
    println("Hello world!")
    //Aquí dentro podríamos ejectuar el bucle principal del programa -> Bucle while mirando las vidas
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


}