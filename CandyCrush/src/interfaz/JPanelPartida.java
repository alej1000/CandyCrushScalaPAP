/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package interfaz;

import java.awt.Color;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Image;

/**
 *
 * @author César Martín Guijarro Panel donde se ejecuta el ejercicio número 2.
 */
public class JPanelPartida extends javax.swing.JPanel {
    

    private jPanelInicio pnlAnterior;
    private Main jFrameMain;

    private ImageIcon imgNuevaCadena = (new ImageIcon("src/main/java/assets/imgNuevaCadena.png"));
    private ImageIcon imgInfo = (new ImageIcon("src/main/java/assets/imgInfo.png"));

    private Thread hiloMusica;

    private String ruta = "src/assets/";
    private MiPanel panelPartida;

    private int numeroVidas = 5;

    private int puntos = 0;


    public JPanelPartida(jPanelInicio pnlAnterior, MiPanel panelPartida, Main jFrameMain) {
        this.pnlAnterior = pnlAnterior;
        this.jFrameMain = jFrameMain;
        this.panelPartida = panelPartida;
        initComponents();

        this.setBackground(Color.decode("#292930"));

        JLabel labelVidas = new JLabel();
        labelVidas.setText("Vidas: " + numeroVidas);
        labelVidas.setBounds(100, 200, 100, 20);
        labelVidas.setVisible(true);
        this.add(labelVidas);

        JLabel labelPuntos = new JLabel();
        labelPuntos.setText("Puntos: " + puntos);
        labelPuntos.setBounds(100, 250, 100, 20);
        labelPuntos.setVisible(true);
        this.add(labelPuntos);

        pnlInput.setBackground(new Color(0, 0, 0, 220));

        int centroX = (int) (this.getWidth() / 2 );
        int centroY = (int) (this.getHeight() / 2 );
        //MiPanel miPanel = new MiPanel(columnas, filas,700,400,matrix);
        this.panelPartida.setLabelPuntos(labelPuntos);
        this.panelPartida.setLabelVidas(labelVidas);
//        panelPartida.setSize(panelPartida.getPreferredSize());
//        panelPartida.setLocation(200, 100);
//        panelPartida.setLocation(centroX - panelPartida.getWidth() / 2, centroY - panelPartida.getHeight() / 2);
        reajustarPanel();
        panelPartida.animacionCarga();
        this.add(this.panelPartida);
//        panelPartida.setBounds(360, 1700,700,400);
//        jPanel1.establecerDimension();
        iniciarTransicion();
        hiloMusica = new Thread(() -> {
            Clip miClip = null;
            try {
                while (true) {
                    miClip = MetodosGUI.reproducirSonido(ruta + "musicaDeFondo.wav",true);
                    Thread.sleep(58000);
                }
            } catch (InterruptedException e) {
                miClip.stop();
//                e.printStackTrace();
            }
        });
        hiloMusica.start();
        JLabel jLabelFondo = new javax.swing.JLabel();
        jLabelFondo.setBounds(0,  0, this.getPreferredSize().width, this.getPreferredSize().height);
        MetodosGUI.ponerImagenLabel(jLabelFondo, new ImageIcon(ruta+ "fondo.png"));
        add(jLabelFondo);
    }

    public void reajustarPanel(){
//        1183, 750
        double aspectRatio = panelPartida.getBotonesColumnas() / (panelPartida.getBotonesFilas()+0.0);
        System.out.println("aspectRatio: " + aspectRatio);
        int anchuraReal = getPreferredSize().width;
        int altura = getPreferredSize().height-36;
        int anchura = Math.round((float)(anchuraReal*0.8));
        double aspectRatioPanel = anchura / (altura+0.0);
        System.out.println("aspectRatioPanel: " + aspectRatioPanel);
        if (aspectRatio > aspectRatioPanel) {
            panelPartida.setSize(anchura, Math.round((float)(anchura / aspectRatio)));
            panelPartida.setDimX(anchura);
            panelPartida.setDimY(Math.round((float)(anchura / aspectRatio)));
        } else {
            panelPartida.setSize((int) (altura * aspectRatio),altura);
            panelPartida.setDimX((int) (altura * aspectRatio));
            panelPartida.setDimY(altura);

//            panelPartida.setSize((int) (getPreferredSize().height * aspectRatio), getHeight());


        }
        panelPartida.setLocation((anchuraReal - panelPartida.getWidth()) / 2, (altura - panelPartida.getHeight()) / 2);
        panelPartida.setDimXPadre(anchuraReal);
        panelPartida.setDimYPadre(altura);
        panelPartida.setDesplazamientoXPadre(-(anchuraReal - panelPartida.getWidth()) / 2);
        panelPartida.setDesplazamientoYPadre(-(altura - panelPartida.getHeight()) / 2);
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

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        //Al pulsar el botón se inicia la animación de regreso a la página principal
        MetodosGUI.reproducirSonido(ruta+"sonidoClick2.wav");
        new HiloAnimacion(btnRegresar, -96, 36, 1.2).start();   //se esconde el botón de regreso
        hiloMusica.interrupt(); //se detiene la música
        new Thread(new Runnable() { //hilo usado para insertar un delay entre cada animación
            @Override
            public void run() {
                try {
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
                    Thread.sleep(130);

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
}