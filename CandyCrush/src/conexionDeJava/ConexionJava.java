package conexionDeJava;

import conexionDeScala.ConexionScala$;
import conexionDeScala.Matrix;
import conexionDeScala.Matrix$;
import java.util.Arrays;
import java.util.List;
//import logica.*;
import scala.collection.JavaConversions;
import scala.collection.JavaConverters;
import java.util.List;

//import org.javatuples.Pair; //Importar al proyecto para poder usar tuplas en java

public class ConexionJava {

    public void miFuncion(String mensaje) {
        System.out.println("Ejecutando funciones de Java");
        System.out.println("Mensaje recibido desde Scala con la función java: " + mensaje);
    }
    public static void main(String args[]){
        //Module es la raiz de todos los objetos
        ConexionScala$ instanciaScala = ConexionScala$.MODULE$; //Metodo Module$ de la clase objeto
        Matrix$ instanciaScala2 = Matrix$.MODULE$; //Metodo Module$ de la clase objeto
//        instanciaScala2 = new Matrix$();
        scala.collection.immutable.List<Object> result = Matrix$.MODULE$.generarMatriz(6,6,2);
        int [] listaJava = convertirListaScalaAJava(result);
//        java.util.List<int>> list = JavaConverters.bufferAsJavaList(Matrix$.MODULE$.generarMatriz(6,6,2));
        scala.Tuple3<Object, Object, Object> result2 = Matrix$.MODULE$.test();
        int tumadre = (int) result2._1();
        System.out.println(tumadre);

        System.out.println(listaJava);
        System.out.println(Arrays.toString(listaJava));


        //MODULE$ en java apunta a la unica instancia del objeto singleton
        instanciaScala.miFuncion("Hola desde Java");
    }

    private static int[] convertirListaScalaAJava(scala.collection.immutable.List<Object> listaScala){
        int[] listaJava = new int[listaScala.size()];
        for(int i = 0; i < listaScala.size(); i++){
            listaJava[i] = (int) listaScala.apply(i);
        }
        return listaJava;
    }

//    private static int[] convertirListaScalaAJavaPuta(scala.collection.immutable.List<Object> listaScala){
//        int[] listaJava = new int[Matrix.longitud(listaScala)];
//        for(int i = 0; i < Matrix$.MODULE$.longitud(listaScala);i++){
//            listaJava[i] = (int) Matrix.getElem(listaScala,i);
//        }
//        return
//    }

}
