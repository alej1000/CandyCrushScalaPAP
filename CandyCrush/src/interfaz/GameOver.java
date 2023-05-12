package interfaz;

import javax.swing.*;
import conexionDeScala.HttpRequest;

import org.json.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


public class GameOver {

    public static void solicitarInfo(int puntaje, int tiempo){
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
        String foto = JOptionPane.showInputDialog("Ingrese la url de su foto");
        java.util.Date fecha = new java.util.Date();
        java.text.SimpleDateFormat sdfDia = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdfHora = new java.text.SimpleDateFormat("HH:mm:ss");
        String fechaFormateada = sdfDia.format(fecha);
        String horaFormateada = sdfHora.format(fecha);
        String fechaHoraFormateada = fechaFormateada + "T" + horaFormateada;
        String json = "{\"nombre\":\""+nombre+"\",\"puntuacion\":"+puntaje+",\"duracion\":"+tiempo+",\"fecha\":\""+fechaHoraFormateada+"\",\"picture\":\""+foto+"\"}";
        String salida = HttpRequest.post(json,"http://cundycrosh.uah:8000/records");
        System.out.println(salida);
    }

    public static void mostrarPuntajes(){
        String salida = HttpRequest.getJson("http://cundycrosh.uah:8000/records");
        System.out.println(salida);

        // Convertir el String JSON en un objeto JSONObject
        JSONObject json = new JSONObject(salida);

        // Obtener la matriz de datos
        JSONArray data = json.getJSONArray("data");

        // Crear un modelo de tabla y establecer las columnas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_player");
        model.addColumn("Nombre");
        model.addColumn("Puntuación");
        model.addColumn("Fecha");
        model.addColumn("Duración");

        // Iterar sobre cada objeto en la matriz data y agregar una fila a la tabla
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            int id_player = obj.getInt("id_player");
            String nombre = obj.getString("nombre");
            int puntuacion = obj.getInt("puntuacion");
            String fecha = obj.getString("fecha");
            int duracion = obj.getInt("duracion");
            Object[] fila = {id_player, nombre, puntuacion, fecha, duracion};
            model.addRow(fila);
        }

        // Crear una tabla y establecer el modelo de tabla
        JTable table = new JTable(model);

        // Agregar un MouseListener a la tabla para detectar clicks dobles
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow(); // Obtener la fila seleccionada
                    int id_player = (int) table.getValueAt(row, 0); // Obtener el valor de la celda "id_player"
                    String url = "http://cundycrosh.uah:8000/puntuacion/" + id_player; // Crear la URL con el id_player
                    try {
                        Desktop.getDesktop().browse(new URI(url)); // Abrir la URL en el navegador
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
        });

        // Mostrar la tabla en una ventana
        JFrame frame = new JFrame("Tabla de Puntuaciones");
        frame.getContentPane().add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
