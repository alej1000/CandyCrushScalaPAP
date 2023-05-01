/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author César
 */
public class MetodosGUI {

    public static void ponerImagenbutton(JButton jButtonImg, ImageIcon imagen) {
        try {
            //Se redimensiona
            ImageIcon imgRedimensionada = new ImageIcon(imagen.getImage().getScaledInstance(jButtonImg.getWidth(), jButtonImg.getHeight(), Image.SCALE_SMOOTH));
            jButtonImg.setIcon(imgRedimensionada);
        } catch (Exception e) {
            System.out.println("Error: error al colocar la imagen." + e.toString());
        }
    }

    public static void ponerImagenLabel(JLabel jLabelImg, ImageIcon imagen) {
        try {
            //Se redimensiona
            ImageIcon imgRedimensionada = new ImageIcon(imagen.getImage().getScaledInstance(jLabelImg.getWidth(), jLabelImg.getHeight(), Image.SCALE_SMOOTH));
            jLabelImg.setIcon(imgRedimensionada);
        } catch (Exception e) {
            System.out.println("Error: error al colocar la imagen." + e.toString());
        }
    }

    public static ImageIcon reescalarImagen(ImageIcon imagen, int resX, int resY) {
        try {
            //Se redimensiona
            ImageIcon imgRedimensionada = new ImageIcon(imagen.getImage().getScaledInstance(resX, resY, Image.SCALE_SMOOTH));
            return imgRedimensionada;
        } catch (Exception e) {
            System.out.println("Error: error al colocar la imagen." + e.toString());
            return imagen;
        }
    }

    public static void encogerBoton(JButton btnElegido, ImageIcon imagen) {
        int posx = btnElegido.getX();
        int posy = btnElegido.getY();
        int longitudHorizontalOriginal = btnElegido.getWidth();
        int longitudVerticalOriginal = btnElegido.getHeight();
        int longitudHorizontal = (int) (longitudHorizontalOriginal / 1.03);
        int longitudVertical = (int) (btnElegido.getHeight() / 1.03);
        int nuevaPosx = posx + (longitudHorizontalOriginal - longitudHorizontal) / 2;
        int nuevaPosy = posy + (longitudVerticalOriginal - longitudVertical) / 2;
        btnElegido.setSize(longitudHorizontal, longitudVertical);
        btnElegido.setLocation(nuevaPosx, nuevaPosy);
        ponerImagenbutton(btnElegido, imagen);
    }

    public static void agrandarBoton(JButton btnElegido, ImageIcon imagen) {
        int posx = btnElegido.getX();
        int posy = btnElegido.getY();
        int longitudHorizontalOriginal = btnElegido.getWidth();
        int longitudVerticalOriginal = btnElegido.getHeight();
        int longitudHorizontal = (int) (longitudHorizontalOriginal * 1.03) + 1; //para un correcto redondeo sumamos 1
        int longitudVertical = (int) (btnElegido.getHeight() * 1.03) + 1;       //para un correcto redondeo sumamos 1
        int nuevaPosx = posx - (longitudHorizontal - longitudHorizontalOriginal) / 2;
        int nuevaPosy = posy - (longitudVertical - longitudVerticalOriginal) / 2;
        btnElegido.setSize(longitudHorizontal, longitudVertical);
        btnElegido.setLocation(nuevaPosx, nuevaPosy);
        ponerImagenbutton(btnElegido, imagen);

    }

    public static void agrandarBoton(JButton btnElegido) {
        int posx = btnElegido.getX();
        int posy = btnElegido.getY();
        int longitudHorizontalOriginal = btnElegido.getWidth();
        int longitudVerticalOriginal = btnElegido.getHeight();
        Font fuente = btnElegido.getFont();
        int longitudHorizontal = (int) (longitudHorizontalOriginal * 1.03) + 1; //para un correcto redondeo sumamos 1
        int longitudVertical = (int) (btnElegido.getHeight() * 1.03) + 1;       //para un correcto redondeo sumamos 1
        int nuevaPosx = posx - (longitudHorizontal - longitudHorizontalOriginal) / 2;
        int nuevaPosy = posy - (longitudVertical - longitudVerticalOriginal) / 2;
        btnElegido.setSize(longitudHorizontal, longitudVertical);
        btnElegido.setFont(new Font(fuente.getName(), fuente.getStyle(), (int) (fuente.getSize() * 1.03) + 1));
        btnElegido.setLocation(nuevaPosx, nuevaPosy);

    }

    public static void encogerBoton(JButton btnElegido) {
        int posx = btnElegido.getX();
        int posy = btnElegido.getY();
        int longitudHorizontalOriginal = btnElegido.getWidth();
        int longitudVerticalOriginal = btnElegido.getHeight();
        Font fuente = btnElegido.getFont();

        int longitudHorizontal = (int) (longitudHorizontalOriginal / 1.03);
        int longitudVertical = (int) (btnElegido.getHeight() / 1.03);
        int nuevaPosx = posx + (longitudHorizontalOriginal - longitudHorizontal) / 2;
        int nuevaPosy = posy + (longitudVerticalOriginal - longitudVertical) / 2;
        btnElegido.setSize(longitudHorizontal, longitudVertical);
        btnElegido.setFont(new Font(fuente.getName(), fuente.getStyle(), (int) (fuente.getSize() / 1.03)));

        btnElegido.setLocation(nuevaPosx, nuevaPosy);
    }

    public static void reproducirSonido(String localizacion) {
        try {
            File archivoSonido = new File(localizacion);    //Leemos un archivo
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivoSonido); //creamos el objeto que traerá el sonido al programa
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al reproducir el archivo de sonido.", "Error:", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void ajustarTexto(JLabel jLabel) {
        //fuente: https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size/2715279#2715279

        Font labelFont = jLabel.getFont();
        String labelText = jLabel.getText();

        int stringWidth = jLabel.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = jLabel.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = jLabel.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        jLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }
}
