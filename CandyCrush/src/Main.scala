
object Main { //Object, instancia unica que se utiliza en todo el programa
  def main(args: Array[String]): Unit = {

    val miMatriz = new Matrix(6,10)
    miMatriz.toString()
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



}

