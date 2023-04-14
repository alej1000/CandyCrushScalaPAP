//En la clase se guardan las funciones que se pueden usar al instanciar la clase en el main (si son publicas)
class Matrix (private val rows: Int,private val cols: Int,private val data: List[Int],private val dificultad:Int) {
  def this(rows: Int, cols: Int,dificultad:Int) = {
    this(rows, cols, Matrix.generarMatriz(rows, cols),dificultad)
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


  def imprimir(data:List[Int]): Unit = {
    val num = cols
    val l = data
    if (Matrix.longitud(l) == 0) return
    if (Matrix.longitud(l) % num == 0) {
      val chunk = toma(num, l)
      imprimirBonito(chunk)
      imprimir(deja(num, l))
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

    toma(cols, deja(fila * cols, data)) //Coge los 8 primeros elementos de la lista que queda de dejar todo menos los 8 primeros
    //La matriz tiene 8 filas y 8 columnas
    //toma(numColumnas,deja(filaElegida * numColumnas,matriz))
  }

  def getColumna(columna: Int): List[Int] = {
    getColumna(data, columna)
  }
  def getColumna(l: List[Int], index: Int): List[Int] = {
    if (index >= cols || index < 0) throw new Error("El índice es mayor que el número de elementos de la lista")
    if (Matrix.longitud(l) == 0) Nil
    else getElem(l, index) :: getColumna(deja(cols, l), index)

  }

  def test():Matrix={
    val matriz = new Matrix(8,8,1)
    matriz
  }

  def consulta(fila:Int,columna:Int, vidas:Int):Int={ //7: Bomba 8: TNT 9: Rompecabezas
    val elemento = getElem(fila,columna)
    elemento match {
      case 7 => {
        println("Has encontrado una bomba")
        println("Has perdido")
        0
      }
      case 8 => {
        println("Has encontrado una bomba")
        println("Has perdido")
        0
      }
      case 9 => {
        println("Has encontrado una bomba")
        println("Has perdido")
        0
      }
      case default => {
        println("Has encontrado un número")
        println("Te quedan "+(vidas-1)+" vidas")
        elemento
      }
    }
  }

  private def reemplazarElemento(fila: Int, columna: Int, elemento: Int): List[Int] = {
    val index: Int = fila * cols + columna
    val listaDeElemento:List[Int] = elemento :: Nil
    val matriz: List[Int] = Matrix.concatenar(Matrix.concatenar(toma(index,data),listaDeElemento) , deja(index,data))
    //matriz es los primeros elementos, el elemento a intercambiar en la posición correspondiente, el resto de elementos
    matriz
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
    //Tendré que hacer que mire el nivel de dificultad y genere entre 4 y 6 
    else rand.nextInt(6) :: generarColumnas(n - 1) //Genero un número aleatorio
  }


  private def generarPila(filas:Int,columnas:Int):List[Int]={
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


  private def eliminarElemento(fila:Int,columna:Int,matriz:List[Int]):(List[Int],List[Int])={
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay un 1, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    val posicionesAEliminar:List[Int] = Nil
    eliminarElementoAux(fila,columna,matriz,fila,columna,posicionesAEliminar)
  }

  private def eliminarElementoAux(fila:Int,columna:Int,matriz:List[Int],filaOrigen:Int,columnaOrigen:Int,elementosVisitados:List[Int]):(List[Int],List[Int])= {
    val elementoOrigen = getElem(filaOrigen, columnaOrigen)
    val elemento = getElem(fila, columna)
    val index: Int = fila * cols + columna
    //con backtracking miramos arriba abajo izquierda y derecha
    //si el elemento es igual al de la posicion de origen y en la lista "elementosVisitados" hay un 0, entonces en la lista "elementosVisitados" el indice actual y hacemos recursión
    //si en la lista "elementosVisitados" hay está el indice, entonces hacemos backtrack
    //si el elemento es distinto al de la posicion de origen, entonces hacemos backtrack y añadimos el indice actual a la lista "elementosVisitados"
    if (fila < 0 || fila >= rows || columna < 0 || columna >= cols) (matriz, elementosVisitados)
    if (!estaEnLista(index,elementosVisitados)) {
      if (elemento == elementoOrigen) {
        val matriz0: List[Int] = reemplazarElemento(fila, columna, 0)
        val elementosVisitados0: List[Int] = index :: elementosVisitados
        val (matriz1: List[Int], elementosVisitados1: List[Int]) = eliminarElementoAux(fila, columna - 1, matriz0, filaOrigen, columnaOrigen, elementosVisitados0)
        val (matriz2: List[Int], elementosVisitados2: List[Int]) = eliminarElementoAux(fila, columna + 1, matriz1, filaOrigen, columnaOrigen, index :: elementosVisitados1)
        val (matriz3: List[Int], elementosVisitados3: List[Int]) = eliminarElementoAux(fila - 1, columna, matriz2, filaOrigen, columnaOrigen, index :: elementosVisitados2)
        val (matriz4: List[Int], elementosVisitados4: List[Int]) = eliminarElementoAux(fila + 1, columna, matriz3, filaOrigen, columnaOrigen, index :: elementosVisitados3)
        return (matriz4, elementosVisitados4)
      }
      else {index::elementosVisitados}
    }
    (matriz,elementosVisitados)
  }


}
//UPS CUDA SE CUELA

def eliminarFila(fila: Int, matriz: List[Int], cols: Int): List[Int] = {

  // Encontramos el índice inicial y final de la fila
  val inicio = fila * cols
  val fin = inicio + cols - 1
  val lista0:List[]

  // Eliminamos los elementos de la fila
  val listaSinFila = concatenar(toma(inicio),Aqui tengo que crear una lista de 0 deja(fin + 1))
  listaSinFila

}


private def eliminarBomba(indiceOrigen:Int,fila:Int,columna:Int,eliminarFila:Int):List[Int]={
  val indiceActual:Int = fila*cols+columna
  if(eliminarFila==1){
    //Elimino la fila
    if(indiceActual==indiceOrigen){
      reemplazarElemento(fila,columna,0)
      eliminarBomba(indiceOrigen,fila-1,columna,eliminarFila)
      eliminarBomba(indice)
    }
  }else{
    //Elimino la columna

  }
}



  private def eliminarBomba(indiceOrigen:Int,fila:Int,columna:Int,eliminarFila:Int): Unit = { //Borra la linea o la columa aleatoriamente
    val index:Int = fila*cols+columna
    printf("Entro en eliminarBomba\n")

    if(eliminarFila==1){
      //Elimino la fila
      printf("Voy a eliminar fila\n")
      if(index==indiceOrigen) { //Si es la posicion de origen llamo a eliminar a la izquierda y a la derecha
        reemplazarElemento(fila,columna,0)
        eliminarBomba(indiceOrigen, fila, columna - 1, eliminarFila)
        eliminarBomba(indiceOrigen, fila, columna + 1, eliminarFila)

      }else{ //Sino
        //Cambio esa posición por 0
        if(columna==0 || columna==cols-1){ //Si es la primera o la última columna
          reemplazarElemento(fila,columna,0)
        }else{ //Si no es la primera o la última columna
          if(getElem(fila,columna)==getElem(fila-1,columna) && getElem(fila,columna)==getElem(fila+1,columna)){ //Si el elemento de la izquierda y el de la derecha son iguales
            reemplazarElemento(fila,columna,0)
            eliminarBomba(indiceOrigen,fila,columna-1,eliminarFila)
            eliminarBomba(indiceOrigen,fila,columna+1,eliminarFila)
          }
        }
      }
    }else{
      //Elimino la columna
      printf("Voy a eliminar columna\n")
      if(index==indiceOrigen) {
        reemplazarElemento(fila,columna,0)
        eliminarBomba(indiceOrigen,fila-1,columna,eliminarFila)
        eliminarBomba(indiceOrigen,fila+1,columna,eliminarFila)
      } else { //Sino
        //Cambio esa posición por 0
        if (columna == 0 || columna == cols - 1) { //Si es la primera o la última columna
          reemplazarElemento(fila, columna, 0)
        } else { //Si no es la primera o la última columna
          if (getElem(fila,columna) == getElem(fila,columna - 1) && getElem(fila,columna) == getElem(fila,columna + 1)) { //Si el elemento de la izquierda y el de la derecha son iguales
            reemplazarElemento(fila, columna, 0)
            eliminarBomba(indiceOrigen, fila-1, columna, eliminarFila)
            eliminarBomba(indiceOrigen, fila+1, columna, eliminarFila)
          }
        }
      }
    }


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




}
