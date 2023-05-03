package bbdd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {

    private final String url = "jdbc:postgresql://127.0.0.1:5432/PL3";
    private static String user;
    private static String password;
    private final Logger logger = Logger.getLogger(ConexionDB.class.getName());
    private Connection connection = null;

    /**
     * Conecta con al Base de Datos
     *
     * @return a Connection object
     */

    public static void main(String[] args) {
        ConexionDB db = new ConexionDB();
        db.connect();
        db.disconnect();
    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://pruebapap.postgres.database.azure.com", "sergiocaro", "rico-gay-3000");
            //connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/PL3",
                    //user, password);
        } catch (SQLException se) {
            System.out.println("No se pudo establecer la conexiÃ³n");
            se.printStackTrace();
            System.exit(1);
        }
        if (connection != null)
            System.out.println("Conectados a la base de datos");
        else
            System.out.println("Problemas al conectarnos a la base de datos");


        return connection;
    }

    /*
     * Se desconecta de la base de datos
     */
    public void disconnect() {
        try {
            connection.close();
            System.out.println("Desconectado!!");

            //Errores
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido cerrar la conexión", ex);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Excepción capturada", ex);
        }
    }
}