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

import conexionDeScala.Main;
import conexionDeScala.Matrix;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MiPanel extends JPanel implements ActionListener {

    private JButton[][] botones;
    private int botonesColumnas, botonesFilas;
    private int[] lista;
    private int dimX, dimY;
    private boolean animacionTerminada = false;
    private int contador;
    private boolean gravedadFin = true;

    private int vidas =5;
    private JLabel labelVidas;

    private  int numeroPuntos=0;
    private JLabel labelPuntos;

    private int dificultad=2;
    private Matrix matriz;
//    private String ruta = "src/main/java/assets/";

    private boolean botonesActivos = false;
    private String ruta = "src/assets/";
    private ImageIcon[] imagenes =  new ImageIcon[17];
    private ImageIcon[] imagenesReescaladas = new ImageIcon[17];

    //        setBackground(new Color(0, 0, 0, 40)); // set the background color to transparent
    public MiPanel(int botonesX, int botonesFilas, int dimX, int dimY, Matrix matriz, JLabel labelVidas, JLabel labelPuntos) {
        this.botonesColumnas = botonesX;
        this.botonesFilas = botonesFilas;
        this.matriz = matriz;
        this.labelVidas= labelVidas;
        this.labelPuntos= labelPuntos;
        this.lista = convertirListaScalaAJava(matriz.getData());
        this.dimX = dimX;   //tamaño del panel horizontalmente
        this.dimY = dimY;   //tamaño del panel verticalmente
        for (int i = 0; i < imagenes.length; i++) {
            imagenes[i] = new ImageIcon(ruta + "candy" + i + ".png");
        }
        int tamBoton = Math.min(dimX / botonesX, dimY / botonesFilas);
        for (int i = 0; i < imagenesReescaladas.length; i++) {
            imagenesReescaladas[i] = MetodosGUI.reescalarImagen(imagenes[i], tamBoton, tamBoton);
        }
        botones = new JButton[botonesFilas][botonesX];
//        MouseListener mouseListener = new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                // Código para manejar el evento de mouse entered
//                JButton button = (JButton) e.getSource();
//                MetodosGUI.agrandarBoton(button);
//                System.out.println("Entraste al boton");
//            }
//            public void mouseExited(MouseEvent e) {
//                // Código para manejar el evento de mouse entered
//                JButton button = (JButton) e.getSource();
//                MetodosGUI.encogerBoton(button);
//                System.out.println("Saliste del boton");
//            }
//        };
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                reescalar();
            }
        });

        for (int i = 0; i < botonesFilas; i++) { //El bucle de la fila
            for (int j = 0; j < botonesX; j++) { //El bucle de la columna
                int indice = i * botonesX + j; // calcula el índice correspondiente en la lista
                JButton boton = new JButton();
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                boton.setFocusPainted(false);
                boton.setOpaque(false);
                botones[i][j] = boton;
                botones[i][j].setActionCommand(i + "," + j); // almacenamos la coordenada del botón en la propiedad actionCommand
                botones[i][j].addActionListener(this); // agregamos ActionListener
                botones[i][j].setBounds(j * tamBoton, -2 * tamBoton, tamBoton, tamBoton);
//                botones[i][j].addMouseListener(mouseListener);

                botones[i][j].setIcon(imagenesReescaladas[lista[indice]]);
                //MetodosGUI.ponerImagenbutton(botones[i][j], imagenes[lista[indice]]);
                //botones[i][j].setText(""+lista[indice]);

                add(botones[i][j]);
            }
        }

        //animacionCarga();
    }


    public void reescalar() { // reescala los botones cuando se cambia el tamaño del panel
        int ancho = getWidth();
        int alto = getHeight();
        int tamBoton = Math.min(ancho / botonesColumnas, alto / botonesFilas);
        if (animacionTerminada) {


            for (int i = 0; i < botonesFilas; i++) {
                for (int j = 0; j < botonesColumnas; j++) {
                    botones[i][j].setBounds(j * tamBoton, i * tamBoton, tamBoton, tamBoton);
                }
            }
        }else {
            for (int i = 0; i < botonesFilas; i++) {
                for (int j = 0; j < botonesColumnas; j++) {
                    botones[i][j].setBounds(j * tamBoton, -tamBoton*5, tamBoton, tamBoton);
                }
            }
        }
        actualizarLabels();
    }

    public JButton getBoton(int fila, int columna) {
        return botones[fila][columna];
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
        actualizarLabels();
    }

    public void actualizarLabels() {
        System.out.println("Lista:"+Arrays.toString(lista));
        int ancho = getWidth();
        int alto = getHeight();
        int tamBoton = Math.min(ancho / botonesColumnas, alto / botonesFilas);
        // actualiza los labels de los botones
        for (int i = 0; i < imagenesReescaladas.length; i++) {
            imagenesReescaladas[i] = MetodosGUI.reescalarImagen(imagenes[i], tamBoton, tamBoton);
        }
        for (int i = 0; i < botonesFilas; i++) {
            for (int j = 0; j < botonesColumnas; j++) {
                int indice = i * botonesColumnas + j; // calcula el índice correspondiente en la lista
                int finalI = i;
                int finalJ = j;
                new Thread(() -> {
                   botones[finalI][finalJ].setIcon(imagenesReescaladas[lista[indice]]);
                   //botones[finalI][finalJ].setText(""+lista[indice]);
                }).start();

            }
        }

    }
    public void actualizarLabels(int[] listaNueva) {
        System.out.println("ListaNueva:"+Arrays.toString(listaNueva));
        int ancho = getWidth();
        int alto = getHeight();
        int tamBoton = Math.min(ancho / botonesColumnas, alto / botonesFilas);
        // actualiza los labels de los botones
        for (int i = 0; i < imagenesReescaladas.length; i++) {
            imagenesReescaladas[i] = MetodosGUI.reescalarImagen(imagenes[i], tamBoton, tamBoton);
        }
        for (int i = 0; i < botonesFilas; i++) {
            for (int j = 0; j < botonesColumnas; j++) {
                int indice = i * botonesColumnas + j; // calcula el índice correspondiente en la lista
                int finalI = i;
                int finalJ = j;
                new Thread(() -> {
                    botones[finalI][finalJ].setIcon(imagenesReescaladas[listaNueva[indice]]);
                    //botones[finalI][finalJ].setText(""+lista[indice]);
                }).start();

            }
        }

    }

    public void test(int fila, int columna) {
        // realiza la acción deseada con las coordenadas x e y
        if (vidas>0 && botonesActivos){
            botonesActivos = false;
            System.out.println("Botón (" + fila + ", " + columna + ") pulsado.");
            scala.Tuple5<Matrix,Object,Object,Object,Matrix> tupla = matriz.consulta(fila,columna, vidas);
            matriz = tupla._1();
            Matrix matrizCeros = tupla._5();
            int[] listaCeros = convertirListaScalaAJava(matrizCeros.getData());
            int[] listaNueva = convertirListaScalaAJava(matriz.getData());
            System.out.println("ListaCeros:"+Arrays.toString(listaCeros));
            System.out.println("ListaNueva: "+Arrays.toString(listaNueva));



            lista = listaCeros;
            actualizarLabels();
            this.gravedadFin = false;
    //        new Thread(() -> {
    //            try{
    //                Thread.sleep(600);
    //            }catch (Exception e){
    //                e.printStackTrace();
    //            }
    //            actualizarLabels(listaNueva);
    //            lista = listaNueva;
    //            reescalar();
    //        }).start();

            gravedad(listaNueva);

            this.vidas = (int) tupla._2();
            this.labelVidas.setText("Vidas: "+this.vidas);
            this.numeroPuntos = Main.sumarPuntos(numeroPuntos,(int) tupla._3(),(int) tupla._4(), dificultad); //tupla._3 -> contadorEliminados; tupla._4 -> elementoEliminado
            this.labelPuntos.setText("Puntos: "+this.numeroPuntos);

            if(this.vidas == 0){
                gameOver();
            }
        }



        //Hacemos parar el programa durante 1000 milisegundos

        //lista = listaNueva;
        //reescalar();

        //matriz.toString();
//        ConexionScala$ instanciaScala = ConexionScala$.MODULE$; //Metodo Module$ de la clase objeto
//        //MODULE$ en java apunta a la unica instancia del objeto singleton
//
//        instanciaScala.miFuncion("Hola desde Java");
    }

    public void actionPerformed(ActionEvent e) {
        // obtiene el botón pulsado
        JButton botonPulsado = (JButton) e.getSource();

        // obtiene la coordenada del botón pulsado desde la propiedad actionCommand
        String coordenada = botonPulsado.getActionCommand();
        int x = Integer.parseInt(coordenada.split(",")[0]);
        int y = Integer.parseInt(coordenada.split(",")[1]);

        MetodosGUI.reproducirSonido(ruta + "sonidoClick2.wav");
        test(x, y); // llama al método test con las coordenadas del botón pulsado
    }


//    def partida(tablero: Matrix, vidas: Int, modoDeJuego: Char, puntosTotales: Int, dificultad: Int): Unit = {
//        if (vidas == 0) {
//            println("Has perdido 😭")
//            controlFinal("Records.txt", puntosTotales,modoDeJuego)
//            return
//        }
//        print("Vidas restantes: ")
//        mostrarVidas(vidas)
//        println()
//        println("Puntos: " + puntosTotales)
//        tablero.toString()
//        if(modoDeJuego == 'm'){ //Es manual
//            val fila = introducirInt("Introduce la fila")
//            val columna = introducirInt("Introduce la columna")
//            val (tableroNew: Matrix, vidasNew: Int, contadorEliminados: Int, elementoEliminado: Int) = tablero.consulta(fila, columna, vidas) //consulta es el eliminarPosicion
//            val puntosSumados: Int = sumarPuntos(puntosTotales, contadorEliminados, elementoEliminado,dificultad)
//            partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
//        } else { //Es automático
//            val (tableroNew: Matrix, vidasNew: Int, puntosSumados: Int) = modoAutomatico(tablero, vidas, puntosTotales, dificultad: Int)
//            partida(tableroNew, vidasNew, modoDeJuego, puntosSumados, dificultad)
//        }
//    }
    public void animacionCarga() {
//        CountDownLatch latch = new CountDownLatch(botonesX * botonesY);
        AtomicInteger contador = new AtomicInteger(botonesColumnas * botonesFilas);

        int delay = 45;

        new Thread(new Runnable() { //hilo usado para insertar un delay entre cada animación
            @Override
            public void run() {
                try {
                    int ancho = dimX;
                    int alto = dimY;
                    int tamBoton = Math.min(ancho / botonesColumnas, alto / botonesFilas);

                    for (int i = botonesFilas - 1; i >= 0; i--) {
                        if (i % 2 == 0) { // alternar el orden de las filas
                            for (int j = 0; j < botonesColumnas; j++) {
                                JButton boton = botones[i][j];
                                new HiloAnimacion(boton, j * tamBoton, i * tamBoton, 1.1).start();
                                contador.decrementAndGet();
                                try {
                                    Thread.sleep(delay); // pausa para dar efecto de animación
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (int j = botonesColumnas - 1; j >= 0; j--) {
                                JButton boton = botones[i][j];
                                new HiloAnimacion(boton, j * tamBoton, i * tamBoton, 1.1).start();
                                contador.decrementAndGet();
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
                    while (contador.get() > 0) {
                        Thread.sleep(150);
                        MetodosGUI.reproducirSonido(ruta + "slide sound effect.wav");

                    }
                    botonesActivos = true; // activamos los botones
                    animacionTerminada = true; // todos los hilos han terminado, ponemos el booleano en true
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void gravedad(int[] listaNueva) { //Muestra la animación de caída de los caramelos
        // Recorremos toda la lista para averiguar cuántas filas debe caer cada botón
        int celdasAMover = 0;
        int[][] desplazamientos = new int[botonesFilas][botonesColumnas];
        for (int i = 0; i < botonesFilas; i++) {    //dentro de cada fila
            for (int j = 0; j < botonesColumnas; j++) {   //para cada elemento de la columna
                int indice = i * botonesColumnas + j;      //indice en la lista original
                if (lista[indice] > 0) {
                    int n = 0;
                    for (int k = 1 + i; k < botonesFilas; k++) {
                        int indiceLocal = k * botonesColumnas + j;
                        if (lista[indiceLocal] == 0) {
                        if (n == 0)   celdasAMover++;
                            n++;
                        }
                    }
                    desplazamientos[i][j] = n;
                } else {
                    desplazamientos[i][j] = -1;
                }
            }
        }
        AtomicInteger contador = new AtomicInteger(celdasAMover);

// Creamos un hilo para cada botón que deba moverse
        for (int i = 0; i < botonesFilas; i++) {
            for (int j = botonesColumnas - 1; j >= 0; j--) {
                int indice = i * botonesColumnas + j;
                int n = desplazamientos[i][j];
                if (n > 0) {
                    int iFinal = i;
                    int jFinal = j;
                    Thread thread = new Thread(() -> {
                        moverBoton(botones[iFinal][jFinal], n);
                        contador.decrementAndGet();

                    });
                    thread.start();

                }
            }
        }
        new Thread(() -> {
            //hilo que debe entrar en el while cuando todos los hilos de movimiento hayan terminado
            while (contador.get() > 0) {
                //System.out.print(contador.get());   //para que no se salte el while
            }
            actualizarLabels(listaNueva);
            lista = listaNueva;
            reescalar();
            botonesActivos = true;
        }).start();
    }

    private void moverBoton(JButton boton, int n) {
        // Mover el botón n filas hacia abajo con una animación
        new HiloAnimacion(boton, boton.getX(), boton.getY() + boton.getHeight() * n, 1.15).animacion();
        }


    private int[] convertirListaScalaAJava(scala.collection.immutable.List<Object> listaScala){
        int[] listaJava = new int[listaScala.size()];
        for(int i = 0; i < listaScala.size(); i++){
            listaJava[i] = (int) listaScala.apply(i);
        }
        return listaJava;
    }

    private void gameOver(){
        javax.swing.JOptionPane.showMessageDialog(null, "GAME OVER");
//        for(int i = 0; i< botonesY; i++){
//            for(int j = 0; j< botonesX; j++){
//                botones[i][j].setEnabled(false);
//            }
//        }
        //System.exit(0);
    }

    public void setLabelVidas(JLabel labelVidas) {
        this.labelVidas = labelVidas;
    }

    public void setLabelPuntos(JLabel labelPuntos) {
        this.labelPuntos = labelPuntos;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }

    public int getBotonesColumnas() {
        return botonesColumnas;
    }

    public int getBotonesFilas() {
        return botonesFilas;
    }
}
