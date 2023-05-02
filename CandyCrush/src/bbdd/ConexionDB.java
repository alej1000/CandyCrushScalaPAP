package bbdd;

//import jbdc

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static void main (String[] args){

    }

    public Connection establecerConexion(){
        Connection conexion = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("pruebapap.postgres.database.azure.com", "sergiocaro", "rico-gay-3000");
            System.out.println("Conexion establecida");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al establecer la conexion");
            System.out.println(e.getMessage());
        }
        return conexion;
    }
}
