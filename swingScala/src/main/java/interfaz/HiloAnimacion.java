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

    private JComponent coomponente;
    private JLabel label;
    private int posFinal;
    private int coordXFinal;
    private int coordYFinal;
    private double aceleracion;
    private boolean interrumpido = false;

    public HiloAnimacion(JPanel panel, JLabel label, int posFinal) {
        this.coomponente = panel;
        this.label = label;
        this.posFinal = posFinal;
    }

    public HiloAnimacion(JComponent componente, int coordXFinal, int coordYFinal, double aceleracion) {
        this.coomponente = componente;
        this.label = label;
        this.coordXFinal = coordXFinal;
        this.coordYFinal = coordYFinal;
        this.aceleracion = aceleracion;
        
    }

    public void run() {

        if (label != null && coomponente != null) {
            int primeraPosLbl = label.getX();       //primera posición que tuvo el
            //label a la hora de crearse el hilo

            while (label.getX() > posFinal) {         //mientras que el label no haya llegado a su pos final
                //se calcula la nueva posición del label usando
                //MRUA para que quede fluido
                int lblNuevaPos = (int) ((int) label.getX() / 1.1);
                //se aplica el mismo movimiento relativo al label
                int pnlNuevaPos = (int) ((coomponente.getWidth() - label.getWidth()) * ((float) lblNuevaPos / primeraPosLbl) - (coomponente.getWidth() - label.getWidth()));
                label.setLocation(lblNuevaPos, posFinal);
                coomponente.setLocation(pnlNuevaPos, posFinal);
                try {
                    Thread.sleep(14);   //descanso de 14 ms para que no sea
                    //un movimiento instantáneo
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloAnimacion.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
            if (!Thread.interrupted()) {
                coomponente.setVisible(false);
            }
        } else {
            double distancia = sqrt(((coordXFinal - coomponente.getX()) * (coordXFinal - coomponente.getX())) + ((coordYFinal - coomponente.getY()) * (coordYFinal - coomponente.getY())));
            double coseno = (float) ((coomponente.getX() - coordXFinal) / distancia);
            double seno = (coomponente.getY() - coordYFinal) / distancia;
            while (distancia > 0.49) {         //mientras que el label no haya llegado a su pos final
                //se calcula la nueva posición del label usando
                //MRUA para que quede fluido
                distancia = distancia / aceleracion;
                int componenteNuevaPosX = (int) (coseno * distancia) + coordXFinal;
                int componenteNuevaPosY = (int) (seno * distancia) + coordYFinal;
                if (distancia > 0.49) {
                    coomponente.setLocation(componenteNuevaPosX, componenteNuevaPosY);
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
                coomponente.setLocation(coordXFinal, coordYFinal);

            }

        }

    }
}
