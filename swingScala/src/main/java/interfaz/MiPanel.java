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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MiPanel extends JPanel implements ActionListener {

    private JButton[][] botones;
    private int botonesX, botonesY;
    private int[] lista;

    public MiPanel(int botonesX, int botonesY,int[] lista) {
        this.botonesX = botonesX;
        this.botonesY = botonesY;
        this.lista = lista;
        botones = new JButton[botonesX][botonesY];
//        lista = new int[botonesX * botonesY]; // inicializa la lista con la longitud adecuada

        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                reescalar();
            }
        });

        for (int i = 0; i < botonesX; i++) {
            for (int j = 0; j < botonesY; j++) {
                int indice = i * botonesY + j; // calcula el índice correspondiente en la lista
                botones[i][j] = new JButton();
                botones[i][j].setActionCommand(i + "," + j); // almacenamos la coordenada del botón en la propiedad actionCommand
                botones[i][j].addActionListener(this); // agregamos ActionListener
                botones[i][j].setText(Integer.toString(lista[indice])); // establece el número correspondiente en el botón
                add(botones[i][j]);
            }
        }

        reescalar();
    }

    public void reescalar() {
        int ancho = getWidth();
        int alto = getHeight();
        int tamBoton = Math.min(ancho / botonesX, alto / botonesY);

        for (int i = 0; i < botonesX; i++) {
            for (int j = 0; j < botonesY; j++) {
                botones[i][j].setBounds(i * tamBoton, j * tamBoton, tamBoton, tamBoton);
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
        return new Dimension(500, 500);
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
    
private void gravedad() {
    // Recorrer toda la lista y averiguar para cada botón, cuántas filas (n) debe descender según los ceros que tenga debajo suya en su columna).
    int[][] matriz = new int[botonesX][botonesY];
    for (int i = 0; i < botonesX; i++) {
        for (int j = 0; j < botonesY; j++) {
            matriz[i][j] = lista[i * botonesY + j];
        }
    }
    int[] cerosDebajo = new int[botonesX];
    for (int i = 0; i < botonesX; i++) {
        cerosDebajo[i] = 0;
        for (int j = botonesY - 1; j >= 0; j--) {
            if (matriz[i][j] == 0) {
                cerosDebajo[i]++;
            } else {
                break;
            }
        }
    }
    // Una vez averiguado, crear tantos hilos como botones se han de mover y llamar simultáneamente con cada boton a moverBoton(boton, n)
    for (int i = 0; i < botonesX; i++) {
        int filas = cerosDebajo[i];
        if (filas > 0) {
            for (int j = botonesY - 1; j >= 0; j--) {
                int indice = i * botonesY + j;
                if (lista[indice] != 0) {
                    JButton boton = botones[i][j];
                    moverBoton(boton, filas);
                }
            }
        }
    }
}


private void moverBoton(JButton boton, int n) {
    // Mover el botón n filas hacia abajo con una animación
    new HiloAnimacion(boton, boton.getX(), boton.getY() + boton.getHeight() * n, 1.1).start();
}


}

    



