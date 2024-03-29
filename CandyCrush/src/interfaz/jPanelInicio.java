/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package interfaz;

import logicaScala.Matrix;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

/**
 *
 * @author César
 */
public class jPanelInicio extends JPanel {

    /**
     * Creates new form jPanelInicio
     */
    Main frame;
    private HiloAnimacion hiloAnimacion;

//    private String ruta = "src/main/java";

    private String ruta = "src";

    private int dificultad = 2;

    private int filas = 9;
    private int columnas = 9;

    private PanelTablero panelTablero;

    private JPanelPartida panelPartida;

    AtomicBoolean panelesListos = new AtomicBoolean(false);



    public jPanelInicio(Main frame) {
        initComponents();
        MetodosGUI.ponerImagenLabel(lblLogo, new ImageIcon(ruta+"/assets/portada.png"));
        lblGif.setIcon(new ImageIcon(ruta+"/assets/gif.gif"));

        pnlColor.setBackground(Color.decode("#438eff"));
        pnlCover.setBackground(Color.decode("#438eff"));
        pnlFondo.setBackground(Color.decode("#ffffff"));
        pnlTransicion.setBackground(Color.decode("#292930"));
        btnIniciar.setBackground(Color.decode("#438eff"));
        btnVerVideo.setBackground(Color.decode("#438eff"));
        btnMemoria.setBackground(Color.decode("#438eff"));
        inicializarMatrizPanelesPartida();

        this.frame = frame;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnContinuar = new javax.swing.JButton();
        lblGif = new javax.swing.JLabel();
        pnlTransicion = new JPanel();
        pnlCover = new JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogo.setToolTipText(null);
        pnlColor = new JPanel();
        pnlEjercicios = new JPanel();
        btnRegresar = new javax.swing.JButton();
        btnCambiarModo = new javax.swing.JButton();
        btnAjustesDeTablero = new javax.swing.JButton();
        btnIniciarPartida = new javax.swing.JButton();
        btnVerVideo = new javax.swing.JButton();
        btnMemoria = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        pnlFondo = new JPanel();

        setMinimumSize(new java.awt.Dimension(1183, 750));
        setLayout(null);

        btnContinuar.setBorderPainted(false);
        btnContinuar.setContentAreaFilled(false);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        add(btnContinuar);
        btnContinuar.setBounds(0, 0, 1183, 750);
        add(lblGif);
        lblGif.setBounds(265, 486, 654, 103);
        add(pnlTransicion);
        pnlTransicion.setBounds(0, -750, 1183, 750);
        add(pnlCover);
        pnlCover.setBounds(80, 470, 1210, 140);
        add(lblLogo);
        lblLogo.setBounds(308, 0, 568, 750);
        add(pnlColor);
        pnlColor.setBounds(0, 0, 1183, 750);

        pnlEjercicios.setBackground(Color.decode("#242424")
        );
        pnlEjercicios.setLayout(null);

        btnRegresar.setBackground(new Color(255, 255, 255));
        btnRegresar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnRegresar.setForeground(new Color(0, 153, 255));
        btnRegresar.setText("▶");
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        pnlEjercicios.add(btnRegresar);
        btnRegresar.setBounds(540, 20, 50, 50);

        btnCambiarModo.setBackground(new Color(255, 255, 255));
        btnCambiarModo.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        btnCambiarModo.setForeground(new Color(51, 153, 255));
        btnCambiarModo.setText("MODO DIFÍCIL");
        btnCambiarModo.setBorder(null);
        btnCambiarModo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarModo.setFocusPainted(false);
        btnCambiarModo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCambiarModoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCambiarModoMouseExited(evt);
            }
        });
        btnCambiarModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarModoActionPerformed(evt);
            }
        });
        pnlEjercicios.add(btnCambiarModo);
        btnCambiarModo.setBounds(63, 520, 490, 121);

        btnAjustesDeTablero.setBackground(new Color(255, 255, 255));
        btnAjustesDeTablero.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        btnAjustesDeTablero.setForeground(new Color(51, 153, 255));
        btnAjustesDeTablero.setText("CAMBIAR DIMENSIONES");
        btnAjustesDeTablero.setBorder(null);
        btnAjustesDeTablero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAjustesDeTablero.setFocusPainted(false);
        btnAjustesDeTablero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAjustesDeTableroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAjustesDeTableroMouseExited(evt);
            }
        });
        btnAjustesDeTablero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustesDeTableroActionPerformed(evt);
            }
        });
        pnlEjercicios.add(btnAjustesDeTablero);
        btnAjustesDeTablero.setBounds(63, 315, 490, 121);

        btnIniciarPartida.setBackground(new Color(255, 255, 255));
        btnIniciarPartida.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        btnIniciarPartida.setForeground(new Color(0, 153, 255));
        btnIniciarPartida.setText("INICIAR PARTIDA");
        btnIniciarPartida.setBorder(null);
        btnIniciarPartida.setBorderPainted(false);
        btnIniciarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarPartida.setFocusPainted(false);
        btnIniciarPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarPartidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarPartidaMouseExited(evt);
            }
        });
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });
        pnlEjercicios.add(btnIniciarPartida);
        btnIniciarPartida.setBounds(63, 109, 490, 121);

        add(pnlEjercicios);
        pnlEjercicios.setBounds(0, 0, 615, 750);

        btnVerVideo.setBackground(new Color(151, 250, 99));
        btnVerVideo.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        btnVerVideo.setForeground(new Color(255, 255, 255));
        btnVerVideo.setText("VER PUNTUACIONES");
        btnVerVideo.setBorder(null);
        btnVerVideo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerVideo.setFocusPainted(false);
        btnVerVideo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerVideoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerVideoMouseExited(evt);
            }
        });
        btnVerVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVideoActionPerformed(evt);
            }
        });
        add(btnVerVideo);
        btnVerVideo.setBounds(631, 520, 490, 121);

        btnMemoria.setBackground(new Color(151, 250, 99));
        btnMemoria.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        btnMemoria.setForeground(new Color(255, 255, 255));
        btnMemoria.setText("VER MEMORIA");
        btnMemoria.setBorder(null);
        btnMemoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMemoria.setFocusPainted(false);
        btnMemoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMemoriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMemoriaMouseExited(evt);
            }
        });
        btnMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemoriaActionPerformed(evt);
            }
        });
        add(btnMemoria);
        btnMemoria.setBounds(631, 315, 490, 121);

        btnIniciar.setBackground(new Color(151, 250, 99));
        btnIniciar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        btnIniciar.setForeground(new Color(255, 255, 255));
        btnIniciar.setText("JUGAR");
        btnIniciar.setBorder(null);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciar.setFocusPainted(false);
        btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarMouseExited(evt);
            }
        });
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        add(btnIniciar);
        btnIniciar.setBounds(631, 109, 490, 121);

        pnlFondo.setPreferredSize(new java.awt.Dimension(1183, 750));
        add(pnlFondo);
        pnlFondo.setBounds(0, 0, 1183, 750);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        //Botón que lanza la ejecución del 'Javamento'
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");

        if (hiloAnimacion != null) {
            hiloAnimacion.interrupt();
        }
        this.hiloAnimacion = new HiloAnimacion(pnlEjercicios, 568, 0, 1.17);
        hiloAnimacion.start();

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnIniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");

        MetodosGUI.agrandarBoton(btnIniciar);
    }//GEN-LAST:event_btnIniciarMouseEntered

    private void btnIniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnIniciar);

    }//GEN-LAST:event_btnIniciarMouseExited

    private void btnMemoriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMemoriaMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");

        MetodosGUI.agrandarBoton(btnMemoria);

    }//GEN-LAST:event_btnMemoriaMouseEntered

    private void btnMemoriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMemoriaMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnMemoria);

    }//GEN-LAST:event_btnMemoriaMouseExited

    private void btnMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoriaActionPerformed
        //Botón que permite ver la memoria del trabajo.
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");

        try {
            File file = new File("../Memoria.pdf");

//            File file = new File(ruta+"/assets/Memoria PL1 Martin Guijarro Cesar.pdf");
            if (!Desktop.isDesktopSupported())//revisamos si Desktop es permitido por el sistema
            {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo porque la plataforma no soporta Desktop");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) //se revisa si existe o no el archivo
            {
                desktop.open(file);              //se abre dicho archivo
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo PDF.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnMemoriaActionPerformed

    private void btnVerVideoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerVideoMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");

        MetodosGUI.agrandarBoton(btnVerVideo);

    }//GEN-LAST:event_btnVerVideoMouseEntered

    private void btnVerVideoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerVideoMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnVerVideo);

    }//GEN-LAST:event_btnVerVideoMouseExited

    private void btnVerVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVideoActionPerformed
        //este botón abre la defensa del trabajo

        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");
        GameOver.mostrarPuntajes();
//        try {
//            URI uri = new URI("https://bit.ly/3fUVowh");
//                    Desktop.getDesktop().browse(uri);
//            if (!Desktop.isDesktopSupported())//revisamos si Desktop es permitido por el sistema
//            {
//                JOptionPane.showMessageDialog(null, "No se ha podido abrir el vídeo porque la plataforma no soporta Desktop");
//                return;
//            }
//            Desktop desktop = Desktop.getDesktop();
//            try {
//                desktop.browse(uri);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "No se ha podido abrir el vídeo por un error con la clase URI");
//
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnVerVideoActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        //EL botón invisible se desactiva y lanza la animación de deslizamiento
        lblGif.setVisible(false);
        pnlCover.setVisible(false);
        btnContinuar.setVisible(false);
        btnContinuar.setEnabled(false);
        pnlEjercicios.setLocation(1500, 0);
        new HiloAnimacion(pnlColor,-pnlColor.getWidth(),0,1.14).start();
        new HiloAnimacion(lblLogo,0,0,1.14).start();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");
        if (hiloAnimacion != null) {
            hiloAnimacion.interrupt();
        }
        this.hiloAnimacion = new HiloAnimacion(pnlEjercicios, 1183, 0, 1.14);
        hiloAnimacion.start();


    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCambiarModoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarModoMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnCambiarModo);

    }//GEN-LAST:event_btnCambiarModoMouseEntered

    private void btnCambiarModoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarModoMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnCambiarModo);

    }//GEN-LAST:event_btnCambiarModoMouseExited

    private void btnCambiarModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarModoActionPerformed
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");
        dificultad = dificultad % 2+1;
        btnCambiarModo.setText(dificultad == 1 ? "MODO FÁCIL" : "MODO DIFÍCIL");
        inicializarMatrizPanelesPartida();

    }//GEN-LAST:event_btnCambiarModoActionPerformed

    private void btnAjustesDeTableroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesDeTableroMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnAjustesDeTablero);


    }//GEN-LAST:event_btnAjustesDeTableroMouseEntered

    private void btnAjustesDeTableroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesDeTableroMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnAjustesDeTablero);

    }//GEN-LAST:event_btnAjustesDeTableroMouseExited

    private void btnAjustesDeTableroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustesDeTableroActionPerformed
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");
//        new HiloAnimacion(pnlTransicion, 0, 0, 1.4).start();
//        jPanelInicio estePanel = this;
//        new HiloTransicion(frame, this).start();
        filas = pedirEntero("filas");
        columnas = pedirEntero("columnas");
        inicializarMatrizPanelesPartida();


    }//GEN-LAST:event_btnAjustesDeTableroActionPerformed

    private int pedirEntero(String mensaje){
        String number = JOptionPane.showInputDialog(null, "Introduczca el número de "+mensaje+":");
        //while number was not a number greater than 0
        try {
            int num = Integer.parseInt(number);
            if (num > 0) {
                return num;
            } else {
                return pedirEntero(mensaje);
            }
        } catch (Exception e) {
            return pedirEntero(mensaje);
        }
    }
    private void btnIniciarPartidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarPartidaMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnIniciarPartida);

    }//GEN-LAST:event_btnIniciarPartidaMouseEntered

    private void btnIniciarPartidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarPartidaMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnIniciarPartida);

    }//GEN-LAST:event_btnIniciarPartidaMouseExited

    private void inicializarMatrizPanelesPartida(){
        jPanelInicio estePanel = this;
        panelesListos.set(false);


        class HiloInicializador extends Thread{
            PanelTablero panelTablero;
            JPanelPartida panelPartida;

            @Override
            public void run() {
                Matrix matrix = new Matrix(filas, columnas, dificultad);
                panelTablero = new PanelTablero(columnas, filas, columnas * 20, filas * 20, matrix, null, null,null);
                panelPartida = new JPanelPartida(estePanel, panelTablero, frame);
                estePanel.setPanelTablero(panelTablero);
                estePanel.setPanelPartida(panelPartida);
                panelesListos.set(true);
                System.out.println("hilo inicializador terminado");
            }

            public PanelTablero getPanelTablero() {
                return panelTablero;
            }

            public JPanelPartida getPanelPartida() {
                return panelPartida;
            }

        }

        HiloInicializador hiloCargaSegundoPlano = new HiloInicializador();
        hiloCargaSegundoPlano.start();
    }

    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
        // TODO add your handling code here:
//        jPanelInicio estePanel = this;
//        AtomicBoolean panelesListos = new AtomicBoolean(false);
//
//        class HiloInicializador extends Thread{
//            PanelTablero panelTablero;
//            JPanelPartida panelPartida;
//
//            @Override
//            public void run() {
//                Matrix matrix = new Matrix(filas, columnas, dificultad);
//                panelTablero = new PanelTablero(columnas, filas, columnas * 20, filas * 20, matrix, null, null);
//                panelPartida = new JPanelPartida(estePanel, panelTablero, frame);
//                panelesListos.set(true);
//                System.out.println("hilo inicializador terminado");
//            }
//
//            public PanelTablero getPanelTablero() {
//                return panelTablero;
//            }
//
//            public JPanelPartida getPanelPartida() {
//                return panelPartida;
//            }
//
//        }
//        HiloInicializador hiloCargaSegundoPlano = new HiloInicializador();
//        hiloCargaSegundoPlano.start();
//        inicializarMatrizPanelesPartida();
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick2.wav");   //reproducimos el sonido
        new HiloAnimacion(pnlTransicion, 0, 0, 1.4).start();        //iniciamos la animación de cortinilla




        //nuevo hilo que inicializa matrix
//        Runnable miRunnable = new Runnable() {
//
//            MiPanel miPanel;
//            JPanelPartida panelPartida;
//            @Override
//            public void run() {
//                Matrix matrix = new Matrix(columnas, filas, dificultad);
//                miPanel = new MiPanel(columnas, filas, columnas * 20, filas * 20, matrix, null, null);
//                panelPartida = new JPanelPartida(estePanel, miPanel, frame);
//            }
//
//            public MiPanel getMiPanel() {
//                return miPanel;
//            }
//
//            public JPanelPartida getPanelPartida() {
//                return panelPartida;
//            }
//        };
//
//        Thread hiloCargaSegundoPlano = new Thread(miRunnable);
//        hiloCargaSegundoPlano.start();






//        Thread hiloCargaSegundoPlano = new Thread(new Runnable() {
//
//            MiPanel miPanel;
//            JPanelPartida panelPartida;
//            @Override
//            public void run(){
//                Matrix matrix = new Matrix(columnas, filas, dificultad);
//                miPanel = new MiPanel(columnas, filas,columnas*20,filas*20,matrix,null,null);
//                panelPartida= new JPanelPartida(estePanel, miPanel, frame);
//            }
//
//            public MiPanel getMiPanel(){
//                return miPanel;
//            }
//
//            public JPanelPartida getPanelPartida(){
//                return panelPartida;
//            }
//
//        });
//        hiloCargaSegundoPlano.start();



//        Thread hiloCargaSegundoPlano = new Thread(new Runnable() {
//            MiPanel miPanel;
//            JPanelPartida panelPartida;
//
//            @Override
//            public void run() {
//                Matrix matrix = new Matrix(columnas, filas, dificultad);
//                miPanel = new MiPanel(columnas, filas, columnas * 20, filas * 20, matrix, null, null);
//                panelPartida = new JPanelPartida(estePanel, miPanel, frame);
//            }
//
//            public MiPanel getMiPanel() {
//                return miPanel;
//            }
//
//            public JPanelPartida getPanelPartida() {
//                return panelPartida;
//            }
//        });
//
//// Iniciar el hilo
//        hiloCargaSegundoPlano.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    //esperamos a que termine la animación del cortinilla
                    Thread.sleep(300);
                    while (!panelesListos.get()) {
                        Thread.sleep(10);
                    }
//                    Thread.sleep(500);
                    //si estamos en el hilo principal
                    if (SwingUtilities.isEventDispatchThread()) {
                        frame.mostrarPanel(panelPartida, "");    //cambiamos de panel
                    } else {
                        //si no estamos en el hilo principal
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                frame.mostrarPanel(panelPartida, "");    //cambiamos de panel

                            }
                        });
                    }
                    panelPartida.iniciarMusica();   //iniciamos la musica de la partida
                    panelPartida.iniciarCortinillaTransicion();
                    panelTablero.animacionCarga();   //iniciamos la animacion de carga del tablero
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

//


    }//GEN-LAST:event_btnIniciarPartidaActionPerformed

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        MetodosGUI.reproducirSonido(ruta+"/assets/sonidoClick.wav");
        MetodosGUI.agrandarBoton(btnRegresar);

    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        MetodosGUI.encogerBoton(btnRegresar);

    }//GEN-LAST:event_btnRegresarMouseExited

    public void regresar() {
        if (SwingUtilities.isEventDispatchThread()) {
            frame.mostrarPanel(this, TOOL_TIP_TEXT_KEY);

        } else {
            jPanelInicio estePanel = this;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.mostrarPanel(estePanel, TOOL_TIP_TEXT_KEY);
//                    panelPartida = null;
//                    panelTablero = null;
                    inicializarMatrizPanelesPartida();

                }
            });
        }


        new HiloAnimacion(pnlTransicion, 0, -750, 1.4).start();
        JPanel panelActual = this;

    }

    public void setPanelTablero(PanelTablero panelTablero) {
        this.panelTablero = panelTablero;
    }

    public void setPanelPartida(JPanelPartida panelPartida) {
        this.panelPartida = panelPartida;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjustesDeTablero;
    private javax.swing.JButton btnCambiarModo;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.JButton btnMemoria;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVerVideo;
    private javax.swing.JLabel lblGif;
    private javax.swing.JLabel lblLogo;
    private JPanel pnlColor;
    private JPanel pnlCover;
    private JPanel pnlEjercicios;
    private JPanel pnlFondo;
    private JPanel pnlTransicion;
    // End of variables declaration//GEN-END:variables
}
