package conexionDeJava;

import conexionDeScala.ConexionScala$;

public class ConexionJava {

    public void miFuncion(String mensaje) {
        System.out.println("Ejecutando funciones de Java");
        System.out.println("Mensaje recibido desde Scala con la función java: " + mensaje);
    }
    public static void main(String args[]){
        //Module es la raiz de todos los objetos
        ConexionScala$ instanciaScala = ConexionScala$.MODULE$; //Metodo Module$ de la clase objeto
        //MODULE$ en java apunta a la unica instancia del objeto singleton
        instanciaScala.miFuncion("Hola desde Java");
    }

}
