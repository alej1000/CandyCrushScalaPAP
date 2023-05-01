///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package interfaz;
//
///**
// *
// * @author César
// */
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//public class MiPanel extends JPanel {
//
//    private static final long serialVersionUID = 1L;
//    private final int BOTON_SIZE;
//
//    public MiPanel(int botonesX, int botonesY) {
//        BOTON_SIZE = 500 / Math.max(botonesX, botonesY); // Tamaño máximo de botón que se ajusta al panel
//        this.setPreferredSize(new Dimension(500, 500)); // Establece el tamaño del panel
//        this.setLayout(new GridLayout(botonesY, botonesX)); // Establece el layout de la grilla
//        for (int i = 0; i < botonesX * botonesY; i++) {
//            JButton boton = new JButton(); // Crea un nuevo botón
//            boton.setPreferredSize(new Dimension(BOTON_SIZE, BOTON_SIZE)); // Establece el tamaño del botón
//            boton.setBackground(Color.WHITE); // Establece el fondo blanco del botón
//            this.add(boton); // Agrega el botón al panel
//        }
//    }
//}

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MiPanel extends JPanel implements ActionListener {

    private JButton[][] botones;
    private int botonesX, botonesY;
    private int[] lista;
    private int dimX, dimY;
    private boolean animacionTerminada = false;
    private int contador;

    //        setBackground(new Color(0, 0, 0, 40)); // set the background color to transparent
    public MiPanel(int botonesX, int botonesY, int dimX, int dimY, int[] lista) {
        this.botonesX = botonesX;
        this.botonesY = botonesY;
        this.lista = lista;
        this.dimX = dimX;   //tamaño del panel horizontalmente
        this.dimY = dimY;   //tamaño del panel verticalmente
        int tamBoton = Math.min(dimX / botonesX, dimY / botonesY);
        botones = new JButton[botonesX][botonesY];
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                reescalar();
            }
        });

        for (int i = 0; i < botonesX; i++) {
            for (int j = 0; j < botonesY; j++) {
                int indice = i * botonesY + j; // calcula el índice correspondiente en la lista
                JButton boton = new JButton();
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                boton.setFocusPainted(false);
                boton.setOpaque(false);
                botones[i][j] = boton;
                botones[i][j].setActionCommand(i + "," + j); // almacenamos la coordenada del botón en la propiedad actionCommand
                botones[i][j].addActionListener(this); // agregamos ActionListener
                botones[i][j].setBounds(i * tamBoton, -2 * tamBoton, tamBoton, tamBoton);
                botones[i][j].setText(Integer.toString(lista[indice])); // establece el número correspondiente en el botón
                add(botones[i][j]);
            }
        }

        animacionCarga();
    }

    public void reescalar() {
        if (animacionTerminada) {
            int ancho = getWidth();
            int alto = getHeight();
            int tamBoton = Math.min(ancho / botonesX, alto / botonesY);

            for (int i = 0; i < botonesX; i++) {
                for (int j = 0; j < botonesY; j++) {
                    botones[i][j].setBounds(i * tamBoton, j * tamBoton, tamBoton, tamBoton);
                }
            }
        }
    }

    public JButton getBoton(int x, int y) {
        return botones[x][y];
    }

    public JButton[][] getBotones() {
        return botones;
    }

    public Dimension getPreferredSize() {
        return new Dimension(dimX, dimY);
    }

    public void establecerDimension() {
        this.setPreferredSize(getPreferredSize());
    }

    public int[] getLista() {
        return lista;
    }

    public void setLista(int[] lista) {
        this.lista = lista;

        // actualiza los labels de los botones
        for (int i = 0; i < botonesX; i++) {
            for (int j = 0; j < botonesY; j++) {
                int indice = i * botonesY + j; // calcula el índice correspondiente en la lista
                botones[i][j].setText(Integer.toString(lista[indice])); // establece el número correspondiente en el botón
            }
        }
    }

    public void actualizarLabels() {
        // actualiza los labels de los botones
        for (int i = 0; i < botonesX; i++) {
            for (int j = 0; j < botonesY; j++) {
                int indice = i * botonesY + j; // calcula el índice correspondiente en la lista
                botones[i][j].setText(Integer.toString(lista[indice])); // establece el número correspondiente en el botón
            }
        }
    }

    public void test(int x, int y) {
        // realiza la acción deseada con las coordenadas x e y
        System.out.println("Botón (" + x + ", " + y + ") pulsado.");
    }

    public void actionPerformed(ActionEvent e) {
        // obtiene el botón pulsado
        JButton botonPulsado = (JButton) e.getSource();

        // obtiene la coordenada del botón pulsado desde la propiedad actionCommand
        String coordenada = botonPulsado.getActionCommand();
        int x = Integer.parseInt(coordenada.split(",")[0]);
        int y = Integer.parseInt(coordenada.split(",")[1]);

        test(x, y); // llama al método test con las coordenadas del botón pulsado
//        moverBoton(botonPulsado,2);
        gravedad();
    }

    private void animacionCarga() {
//        CountDownLatch latch = new CountDownLatch(botonesX * botonesY);
        contador = botonesX * botonesY;
        int delay = 80;

        new Thread(new Runnable() { //hilo usado para insertar un delay entre cada animación
            @Override
            public void run() {
                try {
                    int ancho = dimX;
                    int alto = dimY;
                    int tamBoton = Math.min(ancho / botonesX, alto / botonesY);

                    for (int i = botonesY - 1; i >= 0; i--) {
                        if (i % 2 == 0) { // alternar el orden de las filas
                            for (int j = 0; j < botonesX; j++) {
                                JButton boton = botones[j][i];
                                new HiloAnimacion(boton, j * tamBoton, i * tamBoton, 1.1).start();
                                contador = contador - 1;
                                System.out.println(tamBoton);
                                System.out.println("Pos x = " + i * tamBoton);
                                System.out.println("Pos Y=" + j * tamBoton);
                                try {
                                    Thread.sleep(delay); // pausa para dar efecto de animación
                                    System.out.println("adios");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (int j = botonesX - 1; j >= 0; j--) {
                                JButton boton = botones[j][i];
                                new HiloAnimacion(boton, j * tamBoton, i * tamBoton, 1.1).start();
                                contador = contador - 1;

                                try {
                                    Thread.sleep(delay); // pausa para dar efecto de animación
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        new Thread(new Runnable() { //hilo usado para esperar a que terminen los hilos de animación
            @Override
            public void run() {
                try {
//                    latch.await(); // espera a que todos los hilos terminen
                    while (contador > 0) {
                        System.out.print("");   //para que no se salte el while
                    }
                    animacionTerminada = true; // todos los hilos han terminado, ponemos el booleano en true
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void gravedad() {
        // Recorremos toda la lista para averiguar cuántas filas debe caer cada botón
        int[][] desplazamientos = new int[botonesX][botonesY];
        for (int i = 0; i < botonesX; i++) {    //dentro de cada fila
            for (int j = 0; j < botonesY; j++) {   //para cada elemento de la columna
                int indice = i * botonesY + j;      //indice en la lista original
                if (lista[indice] > 0) {
                    int n = 0;
                    for (int k = 1 + j; k < botonesY; k++) {
                        int indiceLocal = i * botonesY + k;
                        if (lista[indiceLocal] == 0) {
                            n++;
                        }
                    }
                    desplazamientos[i][j] = n;
                } else {
                    desplazamientos[i][j] = -1;
                }
            }
        }

        // Creamos un hilo para cada botón que deba moverse
        for (int i = 0; i < botonesX; i++) {
            for (int j = botonesY - 1; j >= 0; j--) {
                int n = desplazamientos[i][j];
                if (n > 0) {
                    int indice = i * botonesY + j;
                    moverBoton(botones[i][j], n);
//                new Thread(() -> moverBoton(boton, n)).start();
                }
            }
        }
    }

    private void moverBoton(JButton boton, int n) {
        // Mover el botón n filas hacia abajo con una animación
        new HiloAnimacion(boton, boton.getX(), boton.getY() + boton.getHeight() * n, 1.1).start();

    }

}
