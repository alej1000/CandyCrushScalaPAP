import Matrix.{concatenar, generarMatriz}

import scala.util.Random

//En la clase se guardan las funciones que se pueden usar al instanciar la clase en el main (si son publicas)
class Matrix (private val rows: Int,private val cols: Int,private val data: List[Int],private val dificultad:Int) {
  def this(rows: Int, cols: Int, dificultad: Int) = {
    this(rows, cols, Matrix.generarMatriz(rows, cols, dificultad), dificultad)
  }

  //  def imprimir(): Unit = {
  //    imprimirMatriz(data)
  //  }
  //
  //  def imprimirMatriz(matriz: List[Int]): Unit = {
  //    matriz match {
  //      case Nil => println()
  //      case head :: tail => {
  //        //print("|"+head+"|")
  //        printf("| %3s |", head)
  //        if (longitud(tail) % cols == 0) println()
  //        imprimirMatriz(tail)
  //      }
  //    }
  //  }

  override def toString(): String = {
    imprimir(data, cols)
    "Success"
  }

  def getNumColumnas(): Int = cols

  def getNumFilas(): Int = rows

  def getData(): List[Int] = data

// Este es el primer imprimir bonito pero usa bucles
//  def imprimir(data: List[Int], cols: Int): Unit = {
//    val rows = data.length / cols
//    if (rows * cols != data.length) {
//      throw new Error("La lista no tiene una longitud de múltiplo " + cols + "*" + rows)
//    }
//    imprimirRec(data, cols, rows, 0, 0)
//  }
//
//  def imprimirRec(data: List[Int], cols: Int, rows: Int, rowIndex: Int, colIndex: Int): Unit = {
//    if (rowIndex >= rows) {
//      return
//    }
//    if (colIndex == 0) {
//      // imprimir números de columna solo en la primera fila
//      if (rowIndex == 0) {
//        printf("%4s", "")
//        for (i <- 0 until cols) {
//          printf("%4d", i)
//        }
//        println()
//      }
//
//      // imprimir línea separadora superior solo en la primera fila
//      if (rowIndex == 0) {
//        printf("%s", " " * 4)
//        for (i <- 0 until cols) {
//          printf("%s", "-" * 4)
//        }
//        println()
//      }
//    }
//    if (colIndex == 0) {
//      // imprimir número de fila
//      printf("%4d", rowIndex)
//    }
//    // imprimir celda
//    printf("%4d", data(rowIndex * cols + colIndex))
//
//    // imprimir línea separadora lateral
//    if (colIndex == cols - 1) {
//      printf("%s", "|")
//      println()
//    } else {
//      printf("%s", "|")
//    }
//
//    // imprimir línea separadora entre filas
//    if (colIndex == cols - 1) {
//      printf("%s", " " * 4)
//      for (i <- 0 until cols) {
//        printf("%s", "-" * 4)
//      }
//      println()
//    }
//
//    imprimirRec(data, cols, rows, rowIndex + (colIndex + 1) / cols, (colIndex + 1) % cols)
//  }
//
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



  //  def imprimir(data: List[Int]): Unit = {
//    //val num = cols
//    //val l = data
//    if (Matrix.longitud(data) == 0) return
//    if (Matrix.longitud(data) % cols == 0) {
//      val chunk = Matrix.toma(cols, data)
//      imprimirBonito(chunk)
//      imprimir(Matrix.deja(cols, data))
//    } else {
//      throw new Error("La lista no tiene una longitud de mútiplo " + cols + "*" + rows)
//    }
//  }
//
//  def imprimirBonito(l: List[Int]): Unit = {
//    if (Matrix.longitud(l) == 0) {
//      println()
//      return
//    }
//    printf("|%3s |", l.head)
//    imprimirBonito(l.tail)
//  }


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

    Matrix.toma(cols, Matrix.deja(fila * cols, data)) //Coge los 8 primeros elementos de la lista que queda de dejar todo menos los 8 primeros
    //La matriz tiene 8 filas y 8 columnas
    //toma(numColumnas,deja(filaElegida * numColumnas,matriz))
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

  def test(): Matrix = {
    val matriz = new Matrix(8, 8, 1)
    matriz
  }

  def consulta(fila: Int, columna: Int, vidas: Int): (Matrix, Int, Int,Int) = { //7: Bomba 8: TNT 9: Rompecabezas
    /**
     * Metodo que consulta el elemento de la matriz en la posicion fila,columna
     * Si es una bomba, se elimina la bomba y se activa la gravedad
     * Si es un TNT, se elimina el TNT y se activa la gravedad
     * Si es un rompecabezas, se elimina el rompecabezas y se activa la gravedad
     * Si es un elemento normal, se activa la gravedad
     * @param fila
     * @param columna
     * @param vidas
     * @return (Matrix,Int,Int,Int) -> (matriz,vidas,contador,elemento)
     */
    val elemento = getElem(fila, columna)
    val rand = new Random()
    elemento match {
      case 7 => {
        println("Has encontrado una bomba")
        val (matriz:Matrix,contador:Int) =eliminarBomba(fila, columna, rand.nextInt(1))
        val matrizGrav: List[Int] = matriz.activarGravedad(0, data)
        (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,7)

      }
      case 8 => {
        println("Has encontrado un TNT")
        val (matriz:Matrix,contador:Int) =eliminarTNT(fila, columna, data)
        val matrizGrav: List[Int] = matriz.activarGravedad(0, data)
        (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,8)

      }
//      case 9 => {
//        println("Has encontrado un rompecabezas")
//        val matriz:Matrix =eliminarRompecabezas(fila, columna, data)
//        println("Matriz después de eliminar el rompecabezas")
//        matriz.toString()
//        println("Matriz después de activar la gravedad")
//        val matrizGrav: List[Int] = matriz.activarGravedad(0, data)
//        (new Matrix(rows, cols, matrizGrav, dificultad), vidas)
//
//      }
      case default => {
        if(elemento>=11 && elemento<=16){ //Es rompecabezas
          println("Has encontrado un rompecabezas")
          val elemento:Int = getElem(fila,columna)
          val (matriz: Matrix,contador:Int) = eliminarRompecabezas(fila, columna, data)
          val matrizGrav: List[Int] = matriz.activarGravedad(0, matriz.data)
          return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elemento)
        }
        val elementoEliminado:Int = getElem(fila,columna)
        val (matriz: Matrix, contador: Int) = eliminarElemento(fila, columna, data)
        if (contador <= 1) {
          println("No se ha eliminado ningún elemento")
          (this, vidas - 1,0,0)
        }
        else {
          println("Se han eliminado " + contador + " elementos")
          if (contador==5){
            //añadimos una bomba (7) a la matriz en la posición que se introdujo
            //Se pone matriz.data porque se añade la bomba a la matriz que se ha modificado en la función eliminarElemento
            val matrizBomba:Matrix=(new Matrix(rows,cols,reemplazarElemento(fila,columna,7,matriz.data),dificultad))
            val matrizGrav: List[Int] = matrizBomba.activarGravedad(0, matriz.data)
            return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elementoEliminado)
          }
          if (contador==6){
            //añadimos una TNT (8) a la matriz en la posición que se introdujo

            val matrizTNT:Matrix=(new Matrix(rows,cols,reemplazarElemento(fila,columna,8,matriz.data),dificultad))
            val matrizGrav: List[Int] = matrizTNT.activarGravedad(0, matriz.data)
            return (new Matrix(rows, cols, matrizGrav, dificultad), vidas,contador,elementoEliminado)
          }
          if(contador>=7){
            //Añadimos Rompecabezas (11,12,13,14,15,16) en la posición que se introdujo
            if(dificultad==1){
              val matrizRompecabezas: Matrix = (new Matrix(rows, cols, reemplazarElemento(fila, columna, rand.nextInt(4) + 11, matriz.data), dificultad))
              val matrizGrav: List[Int] = matrizRompecabezas.activarGravedad(0, matriz.data)
              return (new Matrix(rows, cols, matrizGrav, dificultad), vidas, contador, elementoEliminado)
            }else{
              val matrizRompecabezas: Matrix = (new Matrix(rows, cols, reemplazarElemento(fila, columna, rand.nextInt(6) + 11, matriz.data), dificultad))
              val matrizGrav: List[Int] = matrizRompecabezas.activarGravedad(0, matriz.data)
              return (new Matrix(rows, cols, matrizGrav, dificultad), vidas, contador, elementoEliminado)
            }
          }
          val matrizGrav:List[Int] = matriz.activarGravedad(0,matriz.data)
          (new Matrix(rows, cols, matrizGrav,dificultad), vidas,contador,elementoEliminado)
        }

      }
    }
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int): List[Int] = {
    val index: Int = fila * cols + columna
    val listaDeElemento: List[Int] = elemento :: Nil
    //val matriz: List[Int] = Matrix.concatenar(Matrix.concatenar(Matrix.toma(index,data),listaDeElemento) , Matrix.deja(index,data))
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index, data), elemento :: Matrix.deja(index + 1, data))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int, datos: List[Int]): List[Int] = {
    val index: Int = fila * cols + columna
    val listaDeElemento: List[Int] = elemento :: Nil
    //val matriz: List[Int] = Matrix.concatenar(Matrix.concatenar(Matrix.toma(index,data),listaDeElemento) , Matrix.deja(index,data))
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index, datos), elemento :: Matrix.deja(index + 1, datos))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }


  def eliminarFila(fila: Int, matriz: List[Int], cols: Int): List[Int] = {

    // Encontramos el índice inicial y final de la fila
    val inicio = fila * cols //Inicio de la fila a eliminar
    val fin = inicio + cols - 1
    val lista0: List[Int] = Matrix.generarPila(1, cols) //Genero una lista con 0 del tamaño de columnas que he eliminado

    // Eliminamos los elementos de la fila
    val listaSinFila = Matrix.concatenar(Matrix.concatenar(Matrix.toma(inicio, matriz), lista0), Matrix.deja(fin + 1, matriz))
    listaSinFila

  }

  //Esta mal seguro, está sumando 1 al indice y eso sería avanzar columnas no filas-> indice + columnas podría ser
  //  def eliminarColumna(index: Int, columnas: Int, matriz: List[Int]): List[Int] = {
  //    if (isEmpty(matriz)) Nil
  //    else {
  //      val fila = index / columnas
  //      val inicio = fila * columnas + index % columnas //fila * columna + columna -> indice que quiero eliminar
  //      val fin = inicio + columnas - 1
  //      val lista0: List[Int] = Matrix.generarPila(fin - inicio, 1)
  //      Matrix.concatenar(Matrix.concatenar(Matrix.toma(inicio,matriz), lista0), eliminarColumna(index + 1, columnas, Matrix.deja(fin + 1,matriz)))
  //    }
  //  }
  def eliminarColumna(indice: Int, columnas: Int): List[Int] = {
    if (Matrix.isEmpty(data)) Nil
    else {
      //eliminarColumnaAux(indice, columnas, data) //col sería el primer indice de la columna a eliminar
      val columna:Int = indice % columnas
      eliminarColumnaAux(columna,0,data)
    }
  }

//  def eliminarColumnaAux(index: Int, columnas: Int, matriz: List[Int]): List[Int] = {
//    if (index >= columnas) matriz
//    else {
//      val fila = index / columnas
//      val col = index % columnas
//      eliminarColumnaAux(col, cols, reemplazarElemento(fila, col, 0, matriz))
//    }
//  }
  def eliminarColumnaAux(col:Int,fila:Int,matriz:List[Int]):List[Int]={
    if (fila >= rows) matriz
    else {
      eliminarColumnaAux(col, fila + 1, reemplazarElemento(fila, col, 0, matriz))
    }
  }


  //Supongo que habrá que pasarle la matriz a eliminar, de momento coge los datos de la clase Matrix
  private def eliminarBomba(fila: Int, columna: Int, eliminoFila: Int): (Matrix,Int) = {
    if (eliminoFila == 1) {
      //Elimino la fila
      //val matriz = new Matrix(rows,cols,data,dificultad)
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
    //val rompecabezasEliminado:List[Int] = eliminarElemento(fila,columna, lista)._1.data //Devuelve la matriz sin el rompecabezas porque ya ha eliminado todos los elementos iguales
    //new Matrix(rows,cols,rompecabezasEliminado,dificultad)

    val rompecabezasEliminado:List[Int]=reemplazarElemento(fila, columna, 0, lista)
    (new Matrix(rows,cols,rompecabezasEliminado,dificultad),contador)

  }
  private def eliminarRompecabezasAux(fila: Int, columna: Int, matriz: List[Int], elemento: Int,contador:Int):(List[Int],Int) = {
    if (Matrix.isEmpty(matriz)) (Nil,contador)
    else {
      val elementoActual: Int = matriz.head
      if (elementoActual == elemento % 10) {
        //reemplazarElemento(fila, columna, 0, matriz) :: eliminarRompecabezas(fila,columna,matriz.tail,elemento)
        //(0 :: eliminarRompecabezasAux(fila, columna, matriz.tail, elemento,contador+1)._1,contador+1) //Si elimino el elemento sumo 1 al contador
        val (lista:List[Int],contadorRecur:Int) = eliminarRompecabezasAux(fila,columna,matriz.tail,elemento,contador+1)
        val listaNueva:List[Int] = 0 :: lista
        val nuevoContador:Int = contadorRecur
        return (listaNueva,nuevoContador)
      }
      else {
        //(elementoActual :: eliminarRompecabezasAux(fila, columna, matriz.tail, elemento,contador)._1,contador) //Si no elimino el elemento el contador no cambia
        val (lista: List[Int], contadorRecur: Int) = eliminarRompecabezasAux(fila, columna, matriz.tail, elemento, contador)
        val listaNueva: List[Int] = elementoActual :: lista
        return (listaNueva, contadorRecur)

      }
    }
  }


  private def eliminarTNT(fila:Int,columna:Int,matriz:List[Int]): (Matrix,Int) = {
    //val inicio:Int= (fila-4) * cols + (columna-4) //Primera esquina a comprobar
    //val fin:Int= (fila+4) * cols + (columna + 4) //Ultima esquina a comprobar
    //val lista:List[Int]=eliminarTNTAux(inicio,fin,fila*cols+columna,fila-4,columna-4,matriz)
    //val lista:List[Int] = eliminarTNTAux(fila-4,fila+4,columna-4,columna+4,fila-4,columna-4,fila,columna,matriz)
    val (lista:List[Int],contador:Int) = eliminarTNTAux(-4,-4,fila,columna,matriz,0)
    (new Matrix(rows,cols,lista,dificultad),contador)
  }
//  private def eliminarTNTAux(filaInicio:Int,filaFin:Int,columnaInicio:Int,columnaFin:Int,filaActual:Int,columnaActual:Int,filaSeleccionada:Int,columnaSeleccionada:Int,matriz:List[Int]):List[Int]={
//    if(filaActual>=filaActual && filaActual<filaFin && filaActual<rows && filaActual>=0){ //Estoy en la fila correcta
//      if((filaActual*filaActual,columnaActual*columnaActual) <= 16){//Pitagoras para ver si estoy en el rango correcto
//        val matriz0:List[Int] = reemplazarElemento(filaActual,columnaActual,0) //Elimino la posicion
//        eliminarTNTAux(filaInicio, filaFin, columnaInicio, columnaFin, filaActual, columnaActual+1, filaSeleccionada, columnaSeleccionada, matriz0)
//      }else{
//        eliminarTNTAux(filaInicio,filaFin, columnaInicio, columnaFin, filaActual, columnaActual+1, filaSeleccionada, columnaSeleccionada, matriz)
//      }
//    }else{
//      matriz
//    }
//  }

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
    else
    eliminarElemento(fila,columna,data)
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
      val (columna0: List[Int],contador) = activarGravedadAux(columna,cols-1,0) //se dejan los ceros arriba
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
  /*
  def activarGravedad2(): Matrix = {
    //Algoritmo que permite añadir gravedad al tablero haciendo que cualquier posición vacía (0) desaparezca para que el usuario pueda continuar su partida.
    //Por cada columna, declara un contador.
    // Recursivamente se recorre la columna de abajo a arriba comprobando si la posición actual es o no una posición vacía (0).
    // En ese caso de ser no vacía, se copiará este elemento en la posición de la columna (de abajo a arriba) que indique el contador,y este se incrementará en 1.
    // En caso de encontrar un valor nulo, se pasará a la siguiente posición de la columna sin incrementar el contador (lo que asegurará que, si más adelante hay un valor no nulo, esta posición será sustituida por el valor apropiado).
    // Esto hará que se depositen en orden todos los elementos que había en la columna, dejando los valores "libres" en las partes altas de la columna, los cuales se eliminarán generando números aleatorios.
    // Gracias al contador, se puede averiguar en qué posiciones se deben generar estos valores aleatorios.

    def moveDown(matriz: List[Int], fila: Int, columna: Int): List[Int] = {
      if (fila == 0 && columna == 0) matriz // si llegamos al principio, regresamos la matriz actual
      else if (fila == 0) moveDown(matriz, rows - 1, columna - 1) // si llegamos al principio de una fila, avanzamos a la fila anterior y la última columna
      else if (matriz(fila * cols + columna) == 0) {
        // si encontramos un cero, buscamos el primer elemento no cero por encima de él
        val firstNonZeroIndex = (0 until fila).reverse.find(i => matriz(i * cols + columna) != 0)
        firstNonZeroIndex match {
          case Some(i) => {
            // intercambiamos el cero con el primer elemento no cero encontrado
            val currentIndex = i * cols + columna
            val nonZeroIndex = fila * cols + columna
            val newMatriz = matriz.updated(nonZeroIndex, matriz(currentIndex)).updated(currentIndex, 0)
            moveDown(newMatriz, i, columna) // hacemos recursión con la posición original del elemento no cero
          }
          case None => moveDown(matriz, fila - 1, columna) // si no encontramos ningún elemento no cero por encima, avanzamos a la posición superior
        }
      }
      else moveDown(matriz, fila - 1, columna) // si el elemento actual no es cero, avanzamos a la posición superior
    }

    val newMatriz = moveDown(data, rows - 1, cols - 1)
    new Matrix(rows, cols, newMatriz, dificultad)
  }
*/



}



//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAA AAAA

//En este objeto se guardan las funciones para usar dentro de la clase ->
object Matrix {

  private def longitud[T](l: List[T]): Int = {
    l match {
      case Nil => 0 //Si la lista está vacía la longitud es 0
      case _ :: tail => 1 + longitud(tail) //Si no está vacía la longitud es 1 + la longitud de la cola
    }
  }

  def isEmpty[Int](list: List[Int]): Boolean = list match {
    case Nil => true
    case _ :: _ => false
  }

  private def generarMatriz(filas: Int, columnas: Int,dificultad:Int): List[Int] = {
    if (filas == 0) Nil
    else {
      concatenar(generarColumnas(columnas,dificultad), generarMatriz(filas - 1, columnas,dificultad))
    }
  }


  // Method to generate a list of random integers with given length
  private def generarColumnas(n: Int,dificultad:Int): List[Int] = {
    val rand = new scala.util.Random()
    if (n == 0) Nil
    //Tendré que hacer que mire el nivel de dificultad y genere entre 4 y 6 
    else {
      //(rand.nextInt(5) + 1) :: generarColumnas(n - 1, dificultad) //Genero un número aleatorio
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

  private def generarPila(filas:Int,columnas:Int):List[Int]={ //Inicializa una lista con ceros
    if(filas==0) Nil
    else concatenar(generarColumnasPila(columnas),generarPila(filas-1,columnas))
  }

  private def generarColumnasPila(n:Int):List[Int] = {
    if(n==0) Nil
    else 0 :: generarColumnasPila(n-1)
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



//UPS CUDA SE CUELA




//  __device__ void eliminarBomba(int * pila, int * tablero, int indiceOrigen, int fila, int columna, int eliminaFila) { //Borra la linea o la columa aleatoriamente
//    int index = fila * numColumnas + columna;
//    printf("Entro a eliminarBomba\n");
//
//    if (eliminaFila == 1) {
//      //Eliminar fila
//      printf("Voy a eliminar fila\n");
//      if (index == indiceOrigen) { //Si es la posicion de origen llamo a eliminar a la izquierda y a la derecha
//        tablero[index] = 0;
//        pila[index] = index;
//        eliminarBomba(pila, tablero, indiceOrigen, fila, columna - 1, eliminaFila);
//        eliminarBomba(pila, tablero, indiceOrigen, fila, columna + 1, eliminaFila);
//      }
//      else { //Sino -> borro
//        if (pila[index] == -1) { //Si No lo he comprobado ya
//          if (columna == 0 || columna == numColumnas - 1) { //Si estoy en la primera columna o la ultima los borra y acaba
//            tablero[index] = 0;
//            pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//          }
//          else { //Esta parte de aquí es para que no haya bucles infinitos (No llamar a la funcion con elementos ya llamados)
//            if (index + 1 != indiceOrigen) { //La columna de la derecha NO es la de origen -> llamo a eliminar a la de la derecha
//              tablero[index] = 0;
//              pila[index] = 0;
//              pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//              eliminarBomba(pila, tablero, indiceOrigen, fila, columna + 1, eliminaFila);
//            }
//            if (index - 1 != indiceOrigen) { //La columna de la izquierda NO es la de origen -> Llamo a eliminar la de la izquierda
//              tablero[index] = 0;
//              pila[index] = 0;
//              pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//              eliminarBomba(pila, tablero, indiceOrigen, fila, columna - 1, eliminaFila);
//            }
//          }
//        }
//      }
//    }
//    else {
//      //Eliminar columna
//      printf("Voy a eliminar columna\n");
//      if (index == indiceOrigen) { //Si es la posicion de origen llamo a eliminar encima y abajo
//        tablero[index] = 0;
//        pila[index] = index;
//        eliminarBomba(pila, tablero, indiceOrigen, fila - 1, columna, eliminaFila);
//        eliminarBomba(pila, tablero, indiceOrigen, fila + 1, columna, eliminaFila);
//        printf("Elimino la bomba\n");
//      }
//      else { //Sino -> borro
//        if (pila[index] == -1) { //Si No lo he comprobado ya
//          if (fila == 0 || fila == numFilas - 1) { //Si estoy en la primera o ultima fila los borra y acaba
//            tablero[index] = 0;
//            pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//          }
//          else { //Esta parte de aquí es para que no haya bucles infinitos (No llamar a la funcion con elementos ya llamados)
//            if (index - numColumnas != indiceOrigen) { //La fila de encima NO es la de origen -> llamo a eliminar encima
//              tablero[index] = 0;
//              pila[index] = index;
//              pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//              eliminarBomba(pila, tablero, indiceOrigen, fila - 1, columna, eliminaFila);
//            }
//            if (index + numColumnas != indiceOrigen) {
//              tablero[index] = 0;
//              pila[index] = index;
//              pila[index] = index; //Lo meto en la pila para saber que tengo que activarle la gravedad
//              eliminarBomba(pila, tablero, indiceOrigen, fila + 1, columna, eliminaFila);
//            }
//          }
//        }
//      }
//    }
//    printf("Salgo de eliminarBomba\n");
//
//  }

