/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package interfaz;

import java.awt.*;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * @author César Martín Guijarro
    Este panel contiene el tablero de juego, el número de vidas y el número de puntos.
 */
public class JPanelPartida extends javax.swing.JPanel {

    private jPanelInicio pnlAnterior;
    private Main jFrameMain;

    private ImageIcon imgNuevaCadena = (new ImageIcon("src/main/java/assets/imgNuevaCadena.png"));
    private ImageIcon imgInfo = (new ImageIcon("src/main/java/assets/imgInfo.png"));

    private Thread hiloMusica;

    private String ruta = "src/assets/";
    private PanelTablero panelTablero;

    private int numeroVidas = 5;
    private JPanel panelCorazones = new JPanel();

    private int puntos = 0;

    //Label con el número de vidas
    private JLabel labelVidas = new JLabel();
    private JLabel labelFotoVidas = new JLabel();

    //Label con el número de puntos
    JLabel labelPuntos = new JLabel();
    private  JLabel labelFotoPuntos = new JLabel();

//    private VidasPanel corazones = vidasPanel(numeroVidas);

    private JLabel jLabelFondo = new javax.swing.JLabel();
    public JPanelPartida(jPanelInicio pnlAnterior, PanelTablero panelTablero, Main jFrameMain) {
        this.pnlAnterior = pnlAnterior;
        this.jFrameMain = jFrameMain;
        this.panelTablero = panelTablero;
        initComponents();

        this.setBackground(Color.decode("#292930"));

        //Numero de Vidas
        labelVidas.setText("" + numeroVidas);
        labelVidas.setBounds((int)(((1183*0.2)/2)-50), 150, 100, 20);
        labelVidas.setVisible(true);
        labelVidas.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(labelVidas);

        //Corazones
        panelCorazones.setBounds((int)(((1183*0.2)/2)-100), 200, 200, 40);
        panelCorazones.setVisible(true);
        this.add(panelCorazones);


        //Dimensiones ventana: 1183, 750
        //Foto de vidas
        labelFotoVidas.setBounds((int)(((1183*0.1)/2)-50), 100, 100, 50);
        MetodosGUI.ponerImagenLabel(labelFotoVidas, new ImageIcon("src/assets/vidas.png"));
        labelFotoVidas.setVisible(true);
        this.add(labelFotoVidas);
        //Numero de puntos
        labelPuntos.setText("" + puntos);
        labelPuntos.setBounds((int)((1183*0.92)), 150, 100, 20);
        labelPuntos.setVisible(true);
        labelPuntos.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(labelPuntos);

        //Dimensiones ventana: 1183, 750
        //Foto de puntos
        labelFotoPuntos.setBounds((int)((1183*0.94))-50, 100, 100, 50);
        MetodosGUI.ponerImagenLabel(labelFotoPuntos, new ImageIcon("src/assets/puntos.png"));
        labelFotoPuntos.setVisible(true);
        this.add(labelFotoPuntos);

        pnlInput.setBackground(new Color(0, 0, 0, 220));

        int centroX = (int) (this.getWidth() / 2 );
        int centroY = (int) (this.getHeight() / 2 );
        this.panelTablero.setLabelPuntos(labelPuntos);
        this.panelTablero.setLabelVidas(labelVidas);
        this.panelTablero.setPanelCorazones(panelCorazones);
        reajustarPanel();
        //panelPartida.animacionCarga();
        this.add(this.panelTablero);
        iniciarTransicion();
        jLabelFondo.setBounds(0,  0, this.getPreferredSize().width, this.getPreferredSize().height);
        MetodosGUI.ponerImagenLabel(jLabelFondo, new ImageIcon(ruta+ "fondo.png"));
        add(jLabelFondo);
    }

    public void reajustarPanel(){
        double aspectRatio = panelTablero.getBotonesColumnas() / (panelTablero.getBotonesFilas()+0.0);
        System.out.println("aspectRatio: " + aspectRatio);
        int anchuraReal = getPreferredSize().width;
        int altura = getPreferredSize().height-36;
        int anchura = Math.round((float)(anchuraReal*0.8));
        double aspectRatioPanel = anchura / (altura+0.0);
        System.out.println("aspectRatioPanel: " + aspectRatioPanel);
        if (aspectRatio > aspectRatioPanel) { //Si es más ancho que alto
            panelTablero.setSize(anchura, Math.round((float)(anchura / aspectRatio)));
            panelTablero.setDimX(anchura);
            panelTablero.setDimY(Math.round((float)(anchura / aspectRatio)));



        } else { //Si es más alto que ancho
            panelTablero.setSize((int) (altura * aspectRatio),altura);
            panelTablero.setDimX((int) (altura * aspectRatio));
            panelTablero.setDimY(altura);

//            panelPartida.setSize((int) (getPreferredSize().height * aspectRatio), getHeight());


        }
        panelTablero.setLocation((anchuraReal - panelTablero.getWidth()) / 2, (altura - panelTablero.getHeight()) / 2);
        panelTablero.setDimXPadre(anchuraReal);
        panelTablero.setDimYPadre(altura);
        panelTablero.setDesplazamientoXPadre(-(anchuraReal - panelTablero.getWidth()) / 2);
        panelTablero.setDesplazamientoYPadre(-(altura - panelTablero.getHeight()) / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInput = new javax.swing.JPanel();
        jLblIntroduzcaCadena = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1183, 750));
        setLayout(null);

        pnlInput.setLayout(null);

        jLblIntroduzcaCadena.setBackground(new Color(255, 102, 102));
        jLblIntroduzcaCadena.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 54)); // NOI18N
        jLblIntroduzcaCadena.setForeground(new Color(255, 255, 255));
        jLblIntroduzcaCadena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblIntroduzcaCadena.setText("INTRODUZCA SU CADENA");
        pnlInput.add(jLblIntroduzcaCadena);
        jLblIntroduzcaCadena.setBounds(40, 20, 580, 70);

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlInput.add(btnCancelar);
        btnCancelar.setBounds(443, 387, 101, 38);

        add(pnlInput);
        pnlInput.setBounds(262, 750, 659, 500);

        btnRegresar.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        btnRegresar.setForeground(new Color(0, 153, 255));
        btnRegresar.setText("◀");
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        add(btnRegresar);
        btnRegresar.setBounds(-96, 36, 60, 60);
    }// </editor-fold>//GEN-END:initComponents


    public void iniciarMusica(){
        hiloMusica = new Thread(() -> {
            Clip miClip = null;
            try {
                while (true) {
                    miClip = MetodosGUI.reproducirSonido(ruta + "musicaDeFondo.wav",true);
                    Thread.sleep(58000);
                }
            } catch (InterruptedException e) {
                miClip.stop();
            }
        });
        hiloMusica.start();
    }
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        //Al pulsar el botón se inicia la animación de regreso a la página principal
        MetodosGUI.reproducirSonido(ruta+"sonidoClick2.wav");
        new HiloAnimacion(btnRegresar, -96, 36, 1.2).start();   //se esconde el botón de regreso
        hiloMusica.interrupt(); //se detiene la música
        panelTablero.pararMusica(); //se detiene el sonido de los hilos cayendo
        new Thread(new Runnable() { //hilo usado para insertar un delay entre cada animación
            @Override
            public void run() {
                try {
                    Thread.sleep(100);

                   new HiloAnimacion(panelTablero, panelTablero.getX(), -2* panelTablero.getDimY(), 1.3).start();
                new HiloAnimacion(jLabelFondo, jLabelFondo.getX(), -2* panelTablero.getDimY(), 1.3).start();
//                    new HiloAnimacion(jLabelCadena1, 182, -200, 1.3).start();
//
//                    Thread.sleep(80);
//                    new HiloAnimacion(jLblEstadoCadena1, 412, -200, 1.3).start();
//
//                    Thread.sleep(80);
//                    new HiloAnimacion(jScrollPaneResultado1, 476, -200, 1.3).start();
//                    new HiloAnimacion(jLabelCadena, 182, -200, 1.3).start();
//
//                    Thread.sleep(80);
//
//                    new HiloAnimacion(jLblEstadoCadena, 412, -200, 1.3).start();
//
//                    Thread.sleep(80);
//                        new HiloAnimacion(lblEstadoIcon, 491, -200, 1.3).start();
//
//
//
//                    Thread.sleep(90);
//
//                    new HiloAnimacion(btnNuevaCadena, 381, -200, 1.2).start();
                    Thread.sleep(230);

                    pnlAnterior.regresar();

                } catch (Exception e) {
                }
            }
        }).start();
    }//GEN-LAST:event_btnRegresarActionPerformed


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        MetodosGUI.reproducirSonido(ruta+"sonidoClick2.wav");
        btnRegresar.setEnabled(true);

        new HiloAnimacion(pnlInput, 262, 750, 1.2).start();


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnRegresar);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnRegresar);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"sonidoClick.wav");

        MetodosGUI.agrandarBoton(btnCancelar);

    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnCancelar);

    }//GEN-LAST:event_btnCancelarMouseExited

    private void iniciarTransicion() {
        /*
        Esta transición se inicia en el momento en el que el usuario clicka el
        botón de ejercicio 1. La animación hace que cada elemento aparezca uno a uno
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

//                    new HiloAnimacion(jLabelCadena, 182, 90, 1.2).start();
//
//                    Thread.sleep(150);
//                    new HiloAnimacion(jLblEstadoCadena, 346, 200, 1.2).start();
//
//                    Thread.sleep(150);
//                    new HiloAnimacion(lblEstadoIcon, 491, 290, 1.2).start();
//
//                    Thread.sleep(150);
//                    new HiloAnimacion(btnNuevaCadena, 381, 562, 1.2).start();

                    Thread.sleep(200);

                    new HiloAnimacion(btnRegresar, 36, 36, 1.2).start();

                } catch (Exception e) {
                }
            }
        }).start();

    }





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLblIntroduzcaCadena;
    private javax.swing.JPanel pnlInput; //Panel GameOver
    // End of variables declaration//GEN-END:variables

    public PanelTablero getPanelTablero() {
        return panelTablero;
    }
}
