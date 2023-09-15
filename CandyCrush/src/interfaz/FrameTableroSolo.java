/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

/**
 *
 * @author César
 */
import logicaScala.Matrix;

import javax.swing.*;
import java.util.Random;

public class FrameTableroSolo extends JFrame {

    private static final long serialVersionUID = 1L;

    public FrameTableroSolo() {
        Random random = new Random();
        int filas = 9;
        int columnas = 14;

        Matrix matrix = new Matrix(filas, columnas,2);


        JLabel labelVidas = new JLabel();
        JLabel labelPuntos = new JLabel();


        PanelTablero panelTablero =new PanelTablero(columnas, filas,700,400,matrix,labelVidas,labelPuntos); // Crea un objeto MiPanel con 10 botones en el eje horizontal y 10 en el eje vertical
        panelTablero.animacionCarga();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la acción por defecto al cerrar la ventana
        this.add(panelTablero); // Agrega el panel al JFrame
        this.pack(); // Ajusta el tamaño del JFrame al tamaño del panel
        this.setVisible(true); // Hace visible el JFrame
    
    }

    public static void main(String[] args) {
        new FrameTableroSolo(); // Crea una nueva ventana
    }
}