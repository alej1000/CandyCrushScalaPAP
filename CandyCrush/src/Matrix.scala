//En la clase se guardan las funciones que se pueden usar al instanciar la clase en el main (si son publicas)
class Matrix (private val rows: Int,private val cols: Int,private val data: List[Int],private val dificultad:Int) {
  def this(rows: Int, cols: Int,dificultad:Int) = {
    this(rows, cols, Matrix.generarMatriz(rows, cols,dificultad),dificultad)
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
    imprimir(data)
    "Success"
  }

  def getNumColumnas():Int = cols
  def getNumFilas():Int = rows

  def imprimir(data:List[Int]): Unit = {
    //val num = cols
    //val l = data
    if (Matrix.longitud(data) == 0) return
    if (Matrix.longitud(data) % cols == 0) {
      val chunk = Matrix.toma(cols, data)
      imprimirBonito(chunk)
      imprimir(Matrix.deja(cols, data))
    } else {
      throw new Error("La lista no tiene una longitud de mútiplo "+cols+"*"+rows)
    }
  }

  def imprimirBonito(l: List[Int]): Unit = {
    if (Matrix.longitud(l) == 0) {
      println()
      return
    }
    printf("|%3s |", l.head)
    imprimirBonito(l.tail)
  }



  def getElem(index:Int):Int={
    getElem(data,index)
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

  def getElem(fila:Int,columna:Int):Int = {
    if (fila == null || columna == null){ throw new Exception("La posición seleccionada no existe")}
    if (fila < 0 || columna < 0){throw new Exception("La posición seleccionada no existe")}
    if (fila > rows || columna > cols){throw new Exception("La posición seleccionada no existe")}
    val index:Int = fila*cols+columna
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

  def test():Matrix={
    val matriz = new Matrix(8,8,1)
    matriz
  }

  def consulta(fila:Int,columna:Int, vidas:Int):(Matrix,Int)={ //7: Bomba 8: TNT 9: Rompecabezas
    val elemento = getElem(fila,columna)
    elemento match {
      case 7 => {
        (eliminarBomba(fila,columna,vidas),vidas)

      }
      case 8 => {
        println("Has encontrado una bomba")
        println("Has perdido")
        (eliminarBomba(fila,columna,vidas),vidas)

      }
      case 9 => {
        println("Has encontrado una bomba")
        println("Has perdido")
        (eliminarBomba(fila,columna,vidas),vidas)

      }
      case default => {
        val (matriz:Matrix,contador:Int) = eliminarElemento(fila,columna,data)
        if (contador <= 1){
          println("No se ha eliminado ningún elemento")
          (this,vidas-1)
        }
        else{
          println("Se han eliminado "+contador+" elementos")
          (matriz,vidas)
        }

      }
    }
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int): List[Int] = {
    val index: Int = fila * cols + columna
    val listaDeElemento:List[Int] = elemento :: Nil
    //val matriz: List[Int] = Matrix.concatenar(Matrix.concatenar(Matrix.toma(index,data),listaDeElemento) , Matrix.deja(index,data))
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index,data), elemento::Matrix.deja(index+1,data))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }
  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int, datos:List[Int]): List[Int] = {
    val index: Int = fila * cols + columna
    val listaDeElemento:List[Int] = elemento :: Nil
    //val matriz: List[Int] = Matrix.concatenar(Matrix.concatenar(Matrix.toma(index,data),listaDeElemento) , Matrix.deja(index,data))
    val matriz: List[Int] = Matrix.concatenar(Matrix.toma(index,datos), elemento::Matrix.deja(index+1,datos))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
  }


  def eliminarFila(fila: Int, matriz: List[Int], cols: Int): List[Int] = {

    // Encontramos el índice inicial y final de la fila
    val inicio = fila * cols //Inicio de la fila a eliminar
    val fin = inicio + cols - 1
    val lista0: List[Int] = Matrix.generarPila(1, cols) //Genero una lista con 0 del tamaño de columnas que he eliminado

    // Eliminamos los elementos de la fila
    val listaSinFila = Matrix.concatenar(Matrix.concatenar(Matrix.toma(inicio,matriz), lista0), Matrix.deja(fin + 1,matriz))
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
  def eliminarColumna(col:Int,columnas:Int,matriz:List[Int]):List[Int]={
    if (isEmpty(matriz)) Nil
    else{
      eliminarColumnaAux(col,columnas,matriz) //col sería el primer indice de la columna a eliminar
    }
  }
  def eliminarColumnaAux(index: Int, columnas: Int, matriz: List[Int]):List[Int]={
    if(index>=columnas) matriz
    else{
      val fila = index / columnas
      val col = index % columnas
      eliminarColumnaAux(fila,col,reemplazarElemento(fila,col,0,matriz))
    }
  }


  //Supongo que habrá que pasarle la matriz a eliminar, de momento coge los datos de la clase Matrix
  private def eliminarBomba(fila: Int, columna: Int, eliminarFila: Int): Matrix = {
    if (eliminarFila == 1) {
      //Elimino la fila
      //val matriz = new Matrix(rows,cols,data,dificultad)
      val lista:List[Int]=this.eliminarFila(fila,data,cols)
      new Matrix(rows,cols,lista,dificultad)
    } else {
      //Elimino la columna
      val indiceOrigen:Int=fila*cols+columna
      val lista:List[Int]= eliminarColumna(indiceOrigen,cols,data)
      new Matrix(rows,cols,lista,dificultad)
    }
  }

  def isEmpty[Int](list: List[Int]): Boolean = list match {
    case Nil => true
    case _ :: _ => false
  }

  private def eliminarElemento(fila: Int, columna: Int, matriz: List[Int]): (Matrix,Int) = {
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay un 1, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    val posicionesAEliminar: List[Int] = Nil
    val (lista:List[Int],_,contador:Int) = eliminarElementoAux(fila, columna, matriz, fila, columna, posicionesAEliminar, 0) //Lista con los elementos ya eliminados y contador de elementos eliminados
    if (contador == 1) {
      (this,0)
    } else {
      println("Se han eliminado " + contador + " elementos")
      (new Matrix(rows, cols, lista, dificultad),contador)
    }
  }
  private def eliminarElementoAux(fila: Int, columna: Int, matriz: List[Int], filaOrigen: Int, columnaOrigen: Int, elementosVisitados: List[Int], contador: Int): (List[Int], List[Int], Int) = {
    /**
     * @param fila: Fila actual
     * @param columna: Columna actual
     * @param matriz: Matriz
     * @param filaOrigen: Fila de origen
     * @param columnaOrigen: Columna de origen
     * @param elementosVisitados: Lista con los elementos visitados
     * @param contador: Contador de elementos eliminados
     * @return: Lista con los elementos eliminados, lista con los elementos visitados y contador de elementos eliminados
     *
     */

    val elementoOrigen = getElem(filaOrigen, columnaOrigen)
    if (fila < 0 || fila >= rows || columna < 0 || columna >= cols){ return(matriz, elementosVisitados, contador)}
    val elemento = getElem(fila, columna)
    val index: Int = fila * cols + columna
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay está el indice, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    if (!Matrix.estaEnLista(index, elementosVisitados)) {
      if (elemento == elementoOrigen) {
        val matriz0: List[Int] = reemplazarElemento(fila, columna, 0,matriz)
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
      if (dificultad == 1) (rand.nextInt(3) + 1) :: generarColumnas(n - 1, dificultad) //Genero un número aleatorio entre 1 y 4
      else (rand.nextInt(5) + 1) :: generarColumnas(n - 1, dificultad) //Genero un número aleatorio entre 1 y 6
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


