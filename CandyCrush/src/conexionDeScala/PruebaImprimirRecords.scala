package conexionDeScala

import scala.io.Source
import java.io._

object PruebaImprimirRecords{

        def main(args:Array[String]):Unit={

        def cargarPuntuaciones(filename:String):List[String]={
        val file=new File(filename)
        if(!file.exists()){
        println("No hay puntuaciones guardadas")
        return List()
        }else{
        val source=Source.fromFile(filename)
        try{
        val lines:List[String]=source.getLines.toList
        return lines
        }finally{
        source.close()
        }
        }
        }
        val puntuaciones:List[String]=cargarPuntuaciones("Records.txt")
        mostrarPuntuaciones(puntuaciones)

        }

        def buscarClaveValor(str:String):(String,Int,String,Long)={ //Devuelve (nombre, puntuacion,tiempo,duracion)
        //El formato de guardado tendrá que ser nombre:puntuacion@tiempo&duracion
        if(str=="")return(str,-1,str,-1) //Si no hay nada

        def buscarClaveValorRec(str:String,i:Int):(String,Int,String,Long)={ //Busca nombre:resto -> resto = puntuacion@tiempo&duracion

        if(i>=strLength(str)){
        throw new IllegalArgumentException("No se encontró el carácter ':' en el String.")
        }else if(str.charAt(i)==':'){
        val key=str.substring(0,i).trim
        val value=str.substring(i+1).trim

        def buscarClaveValorRecAux(str:String,i:Int):(Int,String,Long)={ //Busca puntuacion@resto -> resto = tiempo&duracion

        if(i>=strLength(str)){
        throw new IllegalArgumentException("No se encontró el carácter '@' en el String.")
        }else if(str.charAt(i)=='@'){
        val scoreValue=str.substring(0,i).trim.toInt
        val time=str.substring(i+1).trim

        def buscarClaveValorRecAux2(str:String,i:Int):(String,Long)={ //Busca tiempo&duracion

        if(i>=strLength(str)){
        throw new IllegalArgumentException("No se encontró el carácter '&' en el String.")
        }else if(str.charAt(i)=='&'){
        val timeValue=str.substring(0,i).trim
        val durationValue=str.substring(i+1).trim.toLong
        (timeValue,durationValue)
        }else{
        buscarClaveValorRecAux2(str,i+1)
        }
        }

        val(timeValue:String,durationValue:Long)=buscarClaveValorRecAux2(time,0)
        (scoreValue,timeValue,durationValue)
        }else{
        buscarClaveValorRecAux(str,i+1)
        }
        }

        val(scoreValue:Int,timeValue:String,durationValue:Long)=buscarClaveValorRecAux(value,0)
        (key,scoreValue,timeValue,durationValue)
        }else{
        buscarClaveValorRec(str,i+1)
        }

        }

        buscarClaveValorRec(str,0)
        }

        def strLength(str:String):Int={
        if(str==""){
        0
        }else{
        1+strLength(str.tail)
        }
        }

        def repeat(s:String,n:Int):String={
        s*n
        }

        def mostrarPuntuaciones(listaRecords:List[String]):Unit={
        //Función que imprime la pila de llamadas de manera magistral
        val name:String="Nombre"
        val score:String="Puntuación"
        val date:String="Fecha"
        val duration:String="Duración"

//    var maxLength: Int = 0


//    var maxValues: (Int, Int, Int, Int) = (name.length, score.length, date.length, duration.length)
//    for (llamada <- pilaLlamadas) {
//      val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(llamada)
//      maxValues = (
//        Math.max(maxValues._1, nombre.length),
//        Math.max(maxValues._2, puntuacion.toString.length),
//        Math.max(maxValues._3, fecha.length),
//        Math.max(maxValues._4, duracion.toString.length)
//      )
//    }
        // Busca el máximo valor de cada campo
        def maxValuesRec(pilaLlamadas:List[String],maxValues:(Int,Int,Int,Int)):(Int,Int,Int,Int)={
        if(pilaLlamadas.isEmpty){
        maxValues
        }else{
        val(nombre,puntuacion,fecha,duracion)=buscarClaveValor(pilaLlamadas.head)
        val newMaxValues=(
        Math.max(maxValues._1,nombre.length),
        Math.max(maxValues._2,puntuacion.toString.length),
        Math.max(maxValues._3,fecha.length),
        Math.max(maxValues._4,duracion.toString.length)
        )
        maxValuesRec(pilaLlamadas.tail,newMaxValues)
        }
        }

        // Uso:
        val maxValues=maxValuesRec(listaRecords,(name.length,score.length,date.length,duration.length))


        // Calcula el tamaño de cada columna y el tamaño total de cada fila
        val colSizes:List[Int]=List(maxValues._1,maxValues._2,maxValues._3,maxValues._4)
        val totalRowSize:Int=colSizes.sum+(colSizes.length-1)*3+4

        // Imprime el borde superior de la tabla
        println()
        //printf(" %s%s%s \n", repeat(" ", ((totalRowSize - (name.length + score.length + date.length + duration.length - 3)) / 2)), s"$name $score $date $duration", repeat(" ", ((totalRowSize - (name.length + score.length + date.length + duration.length - 3)) / 2)))

        printf("┏%s┓\n",repeat("━",totalRowSize))

        val nameFormatted=name+repeat(" ",maxValues._1-name.length)
        val scoreFormatted=score+repeat(" ",maxValues._2-score.length)
        val dateFormatted=date+repeat(" ",maxValues._3-date.length)
        val durationFormatted=duration+repeat(" ",maxValues._4-duration.length)

        // Imprime una línea con la fila centrada dentro de ella
        printf("┃ %s │ %s │ %s │ %s   ┃\n",nameFormatted,scoreFormatted,dateFormatted,durationFormatted)

        printf("┣%s┫\n",repeat("━",totalRowSize))

        // Imprime cada fila
//    //for (i <- pilaLlamadas.size - 1 to 0 by -1) { //Imprime de abajo a arriba (formato pila)
//    for(i <- 0 until pilaLlamadas.size) { //TODO: usar pilaLlamadas.indices -> ???
//      val (nombre, puntuacion, fecha, duracion) = buscarClaveValor(pilaLlamadas(i))
//
//      // Formatea cada columna para que tenga el tamaño correcto
//      val nombreFormatted = nombre + repeat(" ", maxValues._1 - nombre.length)
//      val puntuacionFormatted = puntuacion.toString + repeat(" ", maxValues._2 - puntuacion.toString.length)
//      val fechaFormatted = fecha + repeat(" ", maxValues._3 - fecha.length)
//      val duracionFormatted = duracion.toString + repeat(" ", maxValues._4 - duracion.toString.length)
//
//      // Imprime una línea con la fila centrada dentro de ella
//      printf("┃ %s │ %s │ %s │ %s   ┃\n", nombreFormatted, puntuacionFormatted, fechaFormatted, duracionFormatted)
//
////      if (i > 0) { //Cuando era de abajo a arriba
//      if(i<pilaLlamadas.size-1) {
//        // Imprime una línea horizontal entre filas
//        printf("┣%s┫\n", repeat("━", totalRowSize))
//      }
//    }

        def printRows(pilaLlamadas:List[String],maxValues:(Int,Int,Int,Int)):Unit={
        if(pilaLlamadas.nonEmpty){
        val(nombre,puntuacion,fecha,duracion)=buscarClaveValor(pilaLlamadas.head)

        // Formatea cada columna para que tenga el tamaño correcto
        val nombreFormatted=nombre+repeat(" ",maxValues._1-nombre.length)
        val puntuacionFormatted=puntuacion.toString+repeat(" ",maxValues._2-puntuacion.toString.length)
        val fechaFormatted=fecha+repeat(" ",maxValues._3-fecha.length)
        val duracionFormatted=duracion.toString+repeat(" ",maxValues._4-duracion.toString.length)

        // Imprime una línea con la fila centrada dentro de ella
        printf("┃ %s │ %s │ %s │ %s   ┃\n",nombreFormatted,puntuacionFormatted,fechaFormatted,duracionFormatted)

        if(pilaLlamadas.size>1){
        // Imprime una línea horizontal entre filas
        printf("┣%s┫\n",repeat("━",totalRowSize))
        }

        printRows(pilaLlamadas.tail,maxValues)
        }
        }
        printRows(listaRecords,maxValues)

        // Imprime el borde inferior de la tabla
        printf("┗%s┛\n",repeat("━",totalRowSize))
        }


        }
