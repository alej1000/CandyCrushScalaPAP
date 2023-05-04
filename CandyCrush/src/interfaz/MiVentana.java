/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

/**
 *
 * @author César
 */
import conexionDeScala.Matrix;

import javax.swing.*;
import java.util.Random;

public class MiVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    public MiVentana() {
        Random random = new Random();
        int filas = 9;
        int columnas = 14;
        //int[] lista = new int[filas*columnas];
        //for (int i = 0; i<filas*columnas;i++){
        //    lista[i] = random.nextInt(0, 5);
        //}
        //int[] lista = new int[filas*columnas];
        Matrix matrix = new Matrix(filas, columnas,2);
        //lista = convertirListaScalaAJava(matrix.getData());


        JLabel labelVidas = new JLabel();
        JLabel labelPuntos = new JLabel();


        //MiPanel miPanel = new MiPanel(columnas, filas,700,400,matrix);
        MiPanel miPanel =new MiPanel(columnas, filas,700,400,matrix,labelVidas,labelPuntos); // Crea un objeto MiPanel con 10 botones en el eje horizontal y 10 en el eje vertical
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la acción por defecto al cerrar la ventana
        this.add(miPanel); // Agrega el panel al JFrame
        this.pack(); // Ajusta el tamaño del JFrame al tamaño del panel
        this.setVisible(true); // Hace visible el JFrame
    
    }

    public static void main(String[] args) {
        new MiVentana(); // Crea una nueva ventana
    }
}
