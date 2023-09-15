package logicaScala

import Matrix.{concatenar, generarMatriz}

import scala.util.Random

//En la clase se guardan las funciones que se pueden usar al instanciar la clase en el main (si son publicas)
class Matrix (private val rows: Int,private val cols: Int,private val data: List[Int],private val dificultad:Int) {
  def this(rows: Int, cols: Int, dificultad: Int) = {
    this(rows, cols, Matrix.generarMatriz(rows, cols, dificultad), dificultad)
  }

  override def toString(): String = {
    imprimir(data, cols)
    "Success"
  }

  def getNumColumnas(): Int = cols

  def getNumFilas(): Int = rows

  def getData(): List[Int] = data

  def imprimir(data: List[Int], cols: Int): Unit = {
    val rows = data.length / cols
    if (rows * cols != data.length) {
      throw new Error("La lista no tiene una longitud de múltiplo " + cols + "*" + rows)
    }
    imprimirRec(data, cols, rows, 0, 0)
  }

  def imprimirRec(data: List[Int], cols: Int, rows: Int, rowIndex: Int, colIndex: Int): Unit = {
    if (rowIndex >= rows) {
      return
    }
    if (colIndex == 0) {
      // imprimir números de columna solo en la primera fila
      if (rowIndex == 0) {
        printf("%4s", "")
        imprimirNumerosDeColumna(cols, 0)
        println()
      }

      // imprimir línea separadora superior solo en la primera fila
      if (rowIndex == 0) {
        printf("%s", " " * 4)
        imprimirLineaSeparadora(cols)
        println()
      }
    }
    if (colIndex == 0) {
      // imprimir número de fila
      printf("%4d", rowIndex)
    }
    // imprimir celda
    if(data(rowIndex * cols + colIndex) == 7) printf("%4s", "B")
    else if(data(rowIndex * cols + colIndex) == 8) printf("%4s", "TNT")
    else if(data(rowIndex * cols + colIndex)>=11 && data(rowIndex * cols + colIndex)<=16) printf("%4s","R"+ data(rowIndex * cols + colIndex)%10)
    else printf("%4d", data(rowIndex * cols + colIndex))

    // imprimir línea separadora lateral
    if (colIndex == cols - 1) {
      printf("%s", "|")
      println()
    } else {
      printf("%s", "|")
    }

    // imprimir línea separadora entre filas
    if (colIndex == cols - 1) {
      printf("%s", " " * 4)
      imprimirLineaSeparadora(cols)
      println()
    }

    imprimirRec(data, cols, rows, rowIndex + (colIndex + 1) / cols, (colIndex + 1) % cols)
  }

  def imprimirNumerosDeColumna(cols: Int, index: Int): Unit = {
    if (index >= cols) {
      return
    }
    printf("%4d ", index)
    imprimirNumerosDeColumna(cols, index + 1)
  }

  def imprimirLineaSeparadora(cols: Int): Unit = {
    if (cols == 0) {
      return
    }
    printf("%s", "-" * 5)
    imprimirLineaSeparadora(cols - 1)
  }

  def getElem(index: Int): Int = {
    getElem(data, index)
  }

  def getElem(matriz: List[Int], index: Int): Int = { //Prefiero crearla con fila:Int y columna:Int, antes que pasarle el indice directamente
    matriz match {
      case Nil => throw new Exception("El índice no es válido")
      case head :: tail => {
        if (index == 0) head
        else getElem(tail, index - 1)
      }
    }
  }

  def getElem(fila: Int, columna: Int): Int = {
    if (fila == null || columna == null) {
      throw new Exception("La posición seleccionada no existe")
    }
    if (fila < 0 || columna < 0) {
      throw new Exception("La posición seleccionada no existe")
    }
    if (fila > rows || columna > cols) {
      throw new Exception("La posición seleccionada no existe")
    }
    val index: Int = fila * cols + columna
    getElem(index)

  }

  def getFila(fila: Int): List[Int] = {
    Matrix.toma(cols, Matrix.deja(fila * cols, data)) //Coge los numColumnas primeros elementos de la lista que queda de dejar todo menos los numColumnas primeros
  }

  def getColumna(columna: Int): List[Int] = {
    getColumna(data, columna)
  }

  def getColumna(l: List[Int], index: Int): List[Int] = {
    if (index >= cols || index < 0) throw new Error("El índice es mayor que el número de elementos de la lista")
    if (Matrix.longitud(l) == 0) Nil
    else getElem(l, index) :: getColumna(Matrix.deja(cols, l), index)
  }

  def reemplazarColumna(col:Int,nuevaColumna:List[Int],matriz:List[Int]):List[Int]= {
    reemplazarColumnaAux(col, nuevaColumna, matriz, 0)
  }

  def reemplazarColumnaAux(indiceCol:Int,nuevaColumna:List[Int],matriz:List[Int],fila:Int) : List[Int] = {
    if (indiceCol >= cols || indiceCol < 0) throw new Error("El índice es mayor que el número de elementos de la lista")
    if(fila>=rows) return matriz
    val cambioElemento:List[Int]= reemplazarElemento(fila,indiceCol,getElem(nuevaColumna,fila),matriz)
    reemplazarColumnaAux(indiceCol, nuevaColumna, cambioElemento, fila+1)
  }

  def consulta(fila: Int, columna: Int, vidas: Int): (Matrix, Int, Int,Int,Matrix) = { //7: Bomba 8: TNT 9: Rompecabezas
    /**
     * Se elimina el elemento y se activa la gravedad. En caso de borrar más de
     * 4 bloques se creará el bloque especial correspondiente
     * @Param fila Fila del elemento
     * @Param columna Columna del elemento
     * @Param vidas Vidas del jugador
     * @return Matrix: la matriz resultante
     *         vidas: las vidas del jugador
     *         contador: el número de bloques eliminados
     *         elemento: el elemento que se ha eliminado
     *         matriz: la matriz antes de aplicar la gravedad
     */

    println("fila:",fila)
    println("columna:",columna)
    val elemento = getElem(fila, columna)
    val rand = new Random()
    elemento match {
      case 7 => {
        println("Has encontrado una bomba")
        val (matriz:Matrix,contador:Int) =eliminarBomba(fila, columna, rand.nextInt(2))
        val matrizGrav: List[Int] = matriz.activarGravedad(0, data)
        (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,7,matriz)

      }
      case 8 => {
        println("Has encontrado un TNT")
        val (matriz:Matrix,contador:Int) =eliminarTNT(fila, columna, data)
        val matrizGrav: List[Int] = matriz.activarGravedad(0, data)
        (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,8,matriz)

      }
      case default => {
        if(elemento>=11 && elemento<=16){ //Es rompecabezas
          println("Has encontrado un rompecabezas")
          val elemento:Int = getElem(fila,columna)
          val (matriz: Matrix,contador:Int) = eliminarRompecabezas(fila, columna, data)
          val matrizGrav: List[Int] = matriz.activarGravedad(0, matriz.data)
          return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elemento,matriz)
        }
        val elementoEliminado:Int = getElem(fila,columna)
        val (matriz: Matrix, contador: Int) = eliminarElemento(fila, columna, data)
        if (contador <= 1) {
          println("No se ha eliminado ningún elemento")
          (this, vidas - 1,0,0,this)
        }
        else {
          println("Se han eliminado " + contador + " elementos")
          if (contador==5){
            //añadimos una bomba (7) a la matriz en la posición que se introdujo
            //Se pone matriz.data porque se añade la bomba a la matriz que se ha modificado en la función eliminarElemento
            val matrizBomba:Matrix=(new Matrix(rows,cols,reemplazarElemento(fila,columna,7,matriz.data),dificultad))
            val matrizGrav: List[Int] = matrizBomba.activarGravedad(0, matriz.data)
            return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elementoEliminado,matrizBomba)
          }
          if (contador==6){
            //añadimos una TNT (8) a la matriz en la posición que se introdujo

            val matrizTNT:Matrix=(new Matrix(rows,cols,reemplazarElemento(fila,columna,8,matriz.data),dificultad))
            val matrizGrav: List[Int] = matrizTNT.activarGravedad(0, matriz.data)
            return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elementoEliminado,matrizTNT)
          }
          if(contador>=7){
            //Añadimos Rompecabezas (11,12,13,14,15,16) en la posición que se introdujo
            if(dificultad==1){
              val matrizRompecabezas: Matrix = (new Matrix(rows, cols, reemplazarElemento(fila, columna, rand.nextInt(4) + 11, matriz.data), dificultad))
              val matrizGrav: List[Int] = matrizRompecabezas.activarGravedad(0, matriz.data)
              return (new Matrix(rows, cols, matrizGrav, dificultad), vidas, contador, elementoEliminado,matrizRompecabezas)
            }else{
              val matrizRompecabezas: Matrix = (new Matrix(rows, cols, reemplazarElemento(fila, columna, rand.nextInt(6) + 11, matriz.data), dificultad))
              val matrizGrav: List[Int] = matrizRompecabezas.activarGravedad(0, matriz.data)
              return (new Matrix(rows, cols, matrizGrav, dificultad), vidas, contador, elementoEliminado,matrizRompecabezas)
            }
          }
          val matrizGrav:List[Int] = matriz.activarGravedad(0,matriz.data)
          (new Matrix(rows, cols, matrizGrav,dificultad), vidas,contador,elementoEliminado,matriz)
        }

      }
    }
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int): List[Int] = {
    val index: Int = fila * cols + columna
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index, data), elemento :: Matrix.deja(index + 1, data))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int, datos: List[Int]): List[Int] = {
    val index: Int = fila * cols + columna
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index, datos), elemento :: Matrix.deja(index + 1, datos))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }


  def eliminarFila(fila: Int, matriz: List[Int], cols: Int): List[Int] = {

    // Encontramos el índice inicial y final de la fila
    val inicio = fila * cols //Inicio de la fila a eliminar
    val fin = inicio + cols - 1
    val lista0: List[Int] = Matrix.generarLista0s(1, cols) //Genero una lista con 0 del tamaño de columnas que he eliminado

    // Eliminamos los elementos de la fila
    val listaSinFila = Matrix.concatenar(Matrix.concatenar(Matrix.toma(inicio, matriz), lista0), Matrix.deja(fin + 1, matriz))
    listaSinFila

  }

  def eliminarColumna(indice: Int, columnas: Int): List[Int] = {
    if (Matrix.isEmpty(data)) Nil
    else {
      //eliminarColumnaAux(indice, columnas, data) //col sería el primer indice de la columna a eliminar
      val columna:Int = indice % columnas
      eliminarColumnaAux(columna,0,data)
    }
  }

  def eliminarColumnaAux(col:Int,fila:Int,matriz:List[Int]):List[Int]={
    if (fila >= rows) matriz
    else {
      eliminarColumnaAux(col, fila + 1, reemplazarElemento(fila, col, 0, matriz))
    }
  }

  private def eliminarBomba(fila: Int, columna: Int, eliminoFila: Int): (Matrix,Int) = {
    if (eliminoFila == 1) {
      //Elimino la fila
      println("Elimino fila")
      val lista: List[Int] = eliminarFila(fila, data, cols)
      (new Matrix(rows, cols, lista, dificultad),cols) //Devuelvo la matriz sin la fila y el número de columnas eliminadas
    } else {
      //Elimino la columna
      println("Elimino columna")
      val indiceOrigen: Int = fila * cols + columna
      val lista: List[Int] = eliminarColumna(indiceOrigen, cols)
      (new Matrix(rows, cols, lista, dificultad),rows) //Devuelvo la matriz sin la columna y el número de filas eliminadas
    }
  }

  private def eliminarRompecabezas(fila: Int, columna: Int, matriz: List[Int]): (Matrix,Int) = {
    //Recorre la matriz y si encuentra un elemento igual al de la posición de origen, lo cambia por un 0
    val (lista:List[Int],contador:Int)= eliminarRompecabezasAux(fila,columna,matriz,getElem(matriz,fila*cols+columna),1)
    //Elimina el propio elemento rompecabezas
    val rompecabezasEliminado:List[Int]=reemplazarElemento(fila, columna, 0, lista)
    (new Matrix(rows,cols,rompecabezasEliminado,dificultad),contador)

  }
  private def eliminarRompecabezasAux(fila: Int, columna: Int, matriz: List[Int], elemento: Int, contador: Int): (List[Int], Int) = {
    def eliminarRecursivo(matrizRestante: List[Int], contadorActual: Int, resultadoParcial: List[Int]): (List[Int], Int) = {
      if (matrizRestante.isEmpty) (resultadoParcial.reverse, contadorActual)
      else {
        val elementoActual: Int = matrizRestante.head
        if (elementoActual == elemento % 10) {
          //Si elimino elemento sumo 1 al contador
          val listaNueva: List[Int] = 0 :: resultadoParcial
          eliminarRecursivo(matrizRestante.tail, contadorActual + 1, listaNueva)
        } else {
          //Si no elimino elemento, lo añado a la lista directamente sin cambiar nada
          val listaNueva: List[Int] = elementoActual :: resultadoParcial
          eliminarRecursivo(matrizRestante.tail, contadorActual, listaNueva)
        }
      }
    }

    eliminarRecursivo(matriz, contador, Nil)
  }


  private def eliminarTNT(fila:Int,columna:Int,matriz:List[Int]): (Matrix,Int) = {

    val (lista:List[Int],contador:Int) = eliminarTNTAux(-4,-4,fila,columna,matriz,0)
    (new Matrix(rows,cols,lista,dificultad),contador)
  }

  private def eliminarTNTAux(filaRelativa:Int,columnaRelativa:Int,filaOriginal:Int,columnaOriginal:Int,matriz:List[Int],contador:Int):(List[Int],Int)={
    val filaActual:Int = filaRelativa+filaOriginal
    val columnaActual:Int = columnaRelativa+columnaOriginal
    if(filaActual<0 || filaActual>=rows || columnaActual<0 || columnaActual>=cols) { //Si estoy fuera de la matriz
      if (filaActual < 0) return eliminarTNTAux(filaRelativa + 1, columnaRelativa, filaOriginal, columnaOriginal, matriz,contador)
      else if (columnaActual < 0) return eliminarTNTAux(filaRelativa, columnaRelativa + 1, filaOriginal, columnaOriginal, matriz,contador)
      else if (filaActual >= rows) return (matriz,contador)
      else if (columnaActual>=cols) return eliminarTNTAux(filaRelativa+1, -4, filaOriginal, columnaOriginal, matriz,contador)
      else return eliminarTNTAux(filaRelativa + 1, -4, filaOriginal, columnaOriginal, matriz,contador)
    }
    if(filaRelativa>4 && columnaRelativa>4){ //Si estoy en el final de la fila me voy a la siguiente
      return (matriz,contador)
    }else if(columnaRelativa>4){ //Si estoy en la ultima esquina (he acabado)
      return eliminarTNTAux(filaRelativa+1,-4,filaOriginal,columnaOriginal,matriz,contador)
    }else if(filaRelativa>4) return (matriz,contador)
    else{
      if(filaRelativa*filaRelativa+columnaRelativa*columnaRelativa>16) return eliminarTNTAux(filaRelativa,columnaRelativa+1,filaOriginal,columnaOriginal,matriz,contador) //No reemplazo
      val matriz0:List[Int]=reemplazarElemento(filaActual,columnaActual,0,matriz)
      return eliminarTNTAux(filaRelativa,columnaRelativa+1,filaOriginal,columnaOriginal,matriz0,contador+1)
    }
  }

  def detectorEliminacion(fila:Int,columna:Int): (Matrix,Int) ={
    if(getElem(fila,columna)==7){
      val media:Int = (rows+cols)/2
      (this,media)
    }
    else if(getElem(fila,columna)==8) eliminarTNT(fila,columna,data)
    else if(getElem(fila,columna)>=11 && getElem(fila, columna)<=16) eliminarRompecabezas(fila,columna,data)
    else eliminarElemento(fila,columna,data)
  }

  private def eliminarElemento(fila: Int, columna: Int, matriz: List[Int]): (Matrix, Int) = {
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay un 1, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    val posicionesAEliminar: List[Int] = Nil
    val (lista: List[Int], _, contador: Int) = eliminarElementoAux(fila, columna, matriz, fila, columna, posicionesAEliminar, 0) //Lista con los elementos ya eliminados y contador de elementos eliminados
    if (contador == 1) {
      (this, 0)
    } else {
      (new Matrix(rows, cols, lista, dificultad), contador)
    }
  }

  private def eliminarElementoAux(fila: Int, columna: Int, matriz: List[Int], filaOrigen: Int, columnaOrigen: Int, elementosVisitados: List[Int], contador: Int): (List[Int], List[Int], Int) = {
    /**
     * @param fila               : Fila actual
     * @param columna            : Columna actual
     * @param matriz             : Matriz
     * @param filaOrigen         : Fila de origen
     * @param columnaOrigen      : Columna de origen
     * @param elementosVisitados : Lista con los elementos visitados
     * @param contador           : Contador de elementos eliminados
     * @return: Lista con los elementos eliminados, lista con los elementos visitados y contador de elementos eliminados
     *
     */

    val elementoOrigen = getElem(filaOrigen, columnaOrigen)
    if (fila < 0 || fila >= rows || columna < 0 || columna >= cols) {
      return (matriz, elementosVisitados, contador)
    }
    val elemento = getElem(fila, columna)
    val index: Int = fila * cols + columna
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay está el indice, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    if (!Matrix.estaEnLista(index, elementosVisitados)) {
      if (elemento == elementoOrigen) {
        val matriz0: List[Int] = reemplazarElemento(fila, columna, 0, matriz)
        val elementosVisitados0: List[Int] = index :: elementosVisitados
        val contador0: Int = contador + 1
        val (matriz1: List[Int], elementosVisitados1: List[Int], contador1: Int) = eliminarElementoAux(fila, columna - 1, matriz0, filaOrigen, columnaOrigen, elementosVisitados0, contador0)
        val (matriz2: List[Int], elementosVisitados2: List[Int], contador2: Int) = eliminarElementoAux(fila, columna + 1, matriz1, filaOrigen, columnaOrigen, elementosVisitados1, contador1)
        val (matriz3: List[Int], elementosVisitados3: List[Int], contador3: Int) = eliminarElementoAux(fila - 1, columna, matriz2, filaOrigen, columnaOrigen, elementosVisitados2, contador2)
        val (matriz4: List[Int], elementosVisitados4: List[Int], contador4: Int) = eliminarElementoAux(fila + 1, columna, matriz3, filaOrigen, columnaOrigen, elementosVisitados3, contador3)
        return (matriz4, elementosVisitados4, contador4)
      }
      else {
        val elementosVisitadosRet: List[Int] = index :: elementosVisitados
        return (matriz, elementosVisitadosRet, contador)
      }
    }
    (matriz, elementosVisitados, contador)
  }

  def activarGravedad(indiceColumna:Int, datos:List[Int]): List[Int] = {
    if (indiceColumna < 0 || indiceColumna >= cols) {
      datos
    }
    else {
      val columna: List[Int] = getColumna(indiceColumna)
      val (columna0: List[Int],contador) = activarGravedadAux(columna,rows-1,0) //se dejan los ceros arriba
      val columnaRandomizada: List[Int] = randomizarCerosdeColumna(columna0,contador) //se randomizan los ceros
      activarGravedad(indiceColumna+1,reemplazarColumna(indiceColumna, columnaRandomizada,datos))
    }
  }

  def activarGravedadAux(columna:List[Int],indice:Int,contador:Int): (List[Int],Int) = {
    //indice se irá disminuyendo con las recursiones hasta llegar a 0. Empezamos por la última fila
    if (indice < 0) {
      (columna,contador)
    }
    else {
      val elemento = getElem(columna,indice)
      if (elemento == 0) {
        activarGravedadAux(columna,indice-1,contador)
      }
      else {
        val columna0: List[Int] = concatenar(Matrix.toma(rows-1-contador,columna),elemento::Matrix.deja(rows-contador,columna)) //Reemplaza el elemento de la columna por el elemento de la columna que se encuentra a "contador" posiciones por encima
        activarGravedadAux(columna0,indice-1,contador+1)
      }
    }

  }

  def randomizarCerosdeColumna(columna:List[Int],contador:Int): List[Int] = {
    //se crea una lista de contador elementos y se concatena con deja de la columna con el índice contador
    val valoresARandomizar = rows - contador
    if (valoresARandomizar == 0) {
      return columna
    }
    concatenar(generarMatriz(1,valoresARandomizar,dificultad),Matrix.deja(valoresARandomizar,columna))
  }

  //Fin de la clase Matrix
}

//En este objeto se guardan las funciones para usar dentro de la clase
object Matrix {

  def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0 //Si la lista está vacía la longitud es 0
      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
    }
  }

  def isEmpty[Int](list: List[Int]): Boolean = list match {
    case Nil => true
    case _ :: _ => false
  }

  def generarMatriz(filas: Int, columnas: Int,dificultad:Int): List[Int] = {
    if (filas == 0) Nil
    else {
      concatenar(generarColumnas(columnas,dificultad), generarMatriz(filas - 1, columnas,dificultad))
    }
  }


  // Method to generate a list of random integers with given length
  private def generarColumnas(n: Int,dificultad:Int): List[Int] = {
    val rand = new scala.util.Random()
    if (n == 0) Nil
    else {
      if (dificultad == 1) (rand.nextInt(4) + 1) :: generarColumnas(n - 1, dificultad) //Genero un número aleatorio entre 1 y 4
      else (rand.nextInt(6) + 1) :: generarColumnas(n - 1, dificultad) //Genero un número aleatorio entre 1 y 6
    }
  }

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

  private def generarLista0s(filas:Int,columnas:Int):List[Int]={ //Inicializa una lista con ceros
    if(filas==0) Nil
    else concatenar(generarColumnas0s(columnas),generarLista0s(filas-1,columnas))
  }

  private def generarColumnas0s(n:Int):List[Int] = {
    if(n==0) Nil
    else 0 :: generarColumnas0s(n-1)
  }

  private def concatenar(x: List[Int], y: List[Int]): List[Int] = {
    x match {
      case Nil => y
      case head :: tail => head :: concatenar(tail, y)
    }
  }

  def estaEnLista[Int](elem: Int, lista: List[Int]): Boolean = {
    lista match {
      case Nil => false // Si la lista está vacía, el elemento no está
      case head :: tail =>
        if (head == elem) true // Si el primer elemento es igual al elemento buscado, se ha encontrado
        else estaEnLista(elem, tail) // Si no, seguir buscando en la cola de la lista
    }
  }

  //fin del objeto

}