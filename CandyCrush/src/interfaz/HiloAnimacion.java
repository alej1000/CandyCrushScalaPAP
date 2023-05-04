/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import static java.lang.Math.sqrt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author César
 */
public class HiloAnimacion extends Thread {

    private JComponent componente;
    private JLabel label;
    private int posFinal;
    private int coordXFinal;
    private int coordYFinal;
    private double aceleracion;
    private boolean interrumpido = false;



    public HiloAnimacion(JComponent componente, int coordXFinal, int coordYFinal, double aceleracion) {
        this.componente = componente;
        this.label = label;
        this.coordXFinal = coordXFinal;
        this.coordYFinal = coordYFinal;
        this.aceleracion = aceleracion;
        
    }

    public void run() {
    animacion();
}
    public void animacion(){
        double distancia = sqrt(((coordXFinal - componente.getX()) * (coordXFinal - componente.getX())) + ((coordYFinal - componente.getY()) * (coordYFinal - componente.getY())));
        double coseno = (float) ((componente.getX() - coordXFinal) / distancia);
        double seno = (componente.getY() - coordYFinal) / distancia;
        while (distancia > 0.49) {         //mientras que el label no haya llegado a su pos final
            //se calcula la nueva posición del label usando
            //MRUA para que quede fluido
            distancia = distancia / aceleracion;
            int componenteNuevaPosX = (int) (coseno * distancia) + coordXFinal;
            int componenteNuevaPosY = (int) (seno * distancia) + coordYFinal;
            if (distancia > 0.49) {
                componente.setLocation(componenteNuevaPosX, componenteNuevaPosY);
                try {
                    Thread.sleep(14);   //descanso de 14 ms para que no sea
                    //un movimiento instantáneo
                } catch (InterruptedException ex) {
//                        Logger.getLogger(HiloAnimacion.class.getName()).log(Level.SEVERE, null, ex);
                    interrumpido = true;
                    break;
                }
            }
        }
        if (!interrumpido) {
            componente.setLocation(coordXFinal, coordYFinal);

        }

    }
    }
