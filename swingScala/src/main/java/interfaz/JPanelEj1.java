/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author César Martín Guijarro Panel donde se ejecuta el ejercicio número 2.
 */
public class JPanelEj1 extends javax.swing.JPanel {

    private jPanelInicio pnlAnterior;
    private Main jFrameMain;

    private ImageIcon imgNuevaCadena = (new ImageIcon("src/main/java/assets/imgNuevaCadena.png"));
    private ImageIcon imgInfo = (new ImageIcon("src/main/java/assets/imgInfo.png"));


    public JPanelEj1(jPanelInicio pnlAnterior, Main jFrameMain) {
        this.pnlAnterior = pnlAnterior;
        this.jFrameMain = jFrameMain;
        initComponents();

        this.setBackground(Color.decode("#292930"));


        pnlInput.setBackground(new Color(0, 0, 0, 220));

        iniciarTransicion();
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

        jLblIntroduzcaCadena.setBackground(new java.awt.Color(255, 102, 102));
        jLblIntroduzcaCadena.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 54)); // NOI18N
        jLblIntroduzcaCadena.setForeground(new java.awt.Color(255, 255, 255));
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
        btnRegresar.setForeground(new java.awt.Color(0, 153, 255));
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
        MetodosGUI.reproducirSonido("src/main/java/assets/sonidoClick2.wav");
        new HiloAnimacion(btnRegresar, -96, 36, 1.2).start();   //se esconde el botón de regreso

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
        MetodosGUI.reproducirSonido("src/main/java/assets/sonidoClick2.wav");
        btnRegresar.setEnabled(true);

        new HiloAnimacion(pnlInput, 262, 750, 1.2).start();


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido("src/main/java/assets/sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnRegresar);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnRegresar);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido("src/main/java/assets/sonidoClick.wav");

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
    private javax.swing.JPanel pnlInput;
    // End of variables declaration//GEN-END:variables
}
