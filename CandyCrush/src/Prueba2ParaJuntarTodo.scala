import scala.util.Random
class MatrixOld(private val rows: Int, private val cols: Int, private val data: List[Int]) {

  // Constructor to create a matrix with given dimensions
  /*def this(rows: Int, cols: Int) = {
    this(rows, cols, generarMatriz(rows, cols))
  }

  // Constructor to create a matrix with given dimensions and data
  def this(rows: Int, cols: Int, data: List[Int]) = {
    this(rows, cols, data)
    if (data.length != rows * cols) {
      throw new IllegalArgumentException("Data length does not match matrix dimensions.")
    }
  }*/

  // Method to generate a random matrix with given dimensions
  def generarMatriz(filas: Int, columnas: Int): List[Int] = {
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

  // Method to concatenate two lists
  private def concatenar(x: List[Int], y: List[Int]): List[Int] = {
    x match {
      case Nil => y
      case head :: tail => head :: concatenar(tail, y)
    }
  }

  // Method to get the length of a list
  private def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0
      case _ :: tail => 1 + longitud(tail)
    }
  }

  // Method to get an element at a given index in the matrix
  def getElement(row: Int, col: Int): Int = {
    if (row < 0 || row >= rows || col < 0 || col >= cols) {
      throw new IndexOutOfBoundsException("Invalid row or column index.")
    } else {
      getElem(data, row * cols + col)
    }
  }

  // Method to get a row of the matrix as a list
  def getRow(row: Int): List[Int] = {
    if (row < 0 || row >= rows) {
      throw new IndexOutOfBoundsException("Invalid row index.")
    } else {
      toma(cols, deja(row * cols, data))
    }
  }

  // Method to get a column of the matrix as a list
  def getColumn(col: Int): List[Int] = {
    if (col < 0 || col >= cols) {
      throw new IndexOutOfBoundsException("Invalid column index.")
    } else {
      getColumna(data, col, cols)
    }
  }

  // Method to convert the matrix to a string
  /*override def toString(): String = {
  val num = cols
  val l = data
  if (longitud(l) == 0) return ""
  if (longitud(l) % num == 0){
      val chunk = toma(num,l)
      imprimirBonito(chunk)
      toString(deja(num,l),num)
  } else {
      throw new Error("La lista no tiene una longitud de mútiplo 4")
  }
  }*/

  def imprimirBonito(l:List[Int]):Unit={
    if (l.length == 0){
      println()
      return
    }
    printf("|%3s |",l.head)
    imprimirBonito(l.tail)
  }

  def getElem(matriz: List[Int],index: Int): Int = { //Prefiero crearla con fila:Int y columna:Int, antes que pasarle el indice directamente
    matriz match {
      case Nil => throw new Exception("El índice no es válido")
      case head :: tail => {
        if (index == 0) head
        else getElem(tail,index - 1)
      }
    }
  }


  def getFila(fila: Int): List[Int] = {

    toma(cols, deja(fila * cols, data)) //Coge los 8 primeros elementos de la lista que queda de dejar todo menos los 8 primeros
    //La matriz tiene 8 filas y 8 columnas
    //toma(numColumnas,deja(filaElegida * numColumnas,matriz))
  }

  def getColumna(l:List[Int], index:Int, numColumns:Int):List[Int] = {
    if (index>=numColumns) throw new Error("El índice es mayor que el número de elementos de la lista")
    if (l.length == 0) Nil
    else getElem(l,index) :: getColumna(deja(numColumns,l),index,numColumns)

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
}