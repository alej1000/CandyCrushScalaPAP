/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

/**
 *
 * @author César
 */
import javax.swing.JFrame;

public class MiVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    public MiVentana() {
        int[] lista = {1,3,6,3,0,0,5,5,0,3,5,0,2,4,0,7,5,9,0,7};
        MiPanel miPanel = new MiPanel(10, 2,700,400,lista); // Crea un objeto MiPanel con 10 botones en el eje horizontal y 10 en el eje vertical
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la acción por defecto al cerrar la ventana
        this.add(miPanel); // Agrega el panel al JFrame
        this.pack(); // Ajusta el tamaño del JFrame al tamaño del panel
        this.setVisible(true); // Hace visible el JFrame
    
    }

    public static void main(String[] args) {
        new MiVentana(); // Crea una nueva ventana
    }
}
