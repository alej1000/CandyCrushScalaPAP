package pruebaJava;

import pruebaScala.PruebaScala$;

public class PruebaJava {

    public void miFuncion(String mensaje) {
        System.out.println("Ejecutando funciones de Java");
        System.out.println("Mensaje recibido desde Scala con la función java: " + mensaje);
    }
    public static void main(String args[]){
        //Module es la raiz de todos los objetos
        PruebaScala$ instanciaScala = PruebaScala$.MODULE$; //Metodo Module$ de la clase objeto
        //MODULE$ en java apunta a la unica instancia del objeto singleton
        instanciaScala.miFuncion("Hola desde Java");
    }

}
