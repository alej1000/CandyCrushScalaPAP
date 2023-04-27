package interfaz

import java.awt.Font
import java.awt.Image
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JOptionPane

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author César
 */
object MetodosGUI {
  def ponerImagenbutton(jButtonImg: JButton, imagen: ImageIcon): Unit = {
    try {
      //Se redimensiona
      val imgRedimensionada = new ImageIcon(imagen.getImage.getScaledInstance(jButtonImg.getWidth, jButtonImg.getHeight, Image.SCALE_SMOOTH))
      jButtonImg.setIcon(imgRedimensionada)
    } catch {
      case e: Exception =>
        System.out.println("Error: error al colocar la imagen." + e.toString)
    }
  }

  def ponerImagenLabel(jLabelImg: JLabel, imagen: ImageIcon): Unit = {
    try {
      //Se redimensiona
      val imgRedimensionada = new ImageIcon(imagen.getImage.getScaledInstance(jLabelImg.getWidth, jLabelImg.getHeight, Image.SCALE_SMOOTH))
      jLabelImg.setIcon(imgRedimensionada)
    } catch {
      case e: Exception =>
        System.out.println("Error: error al colocar la imagen." + e.toString)
    }
  }

  def encogerBoton(btnElegido: JButton, imagen: ImageIcon): Unit = {
    val posx = btnElegido.getX
    val posy = btnElegido.getY
    val longitudHorizontalOriginal = btnElegido.getWidth
    val longitudVerticalOriginal = btnElegido.getHeight
    val longitudHorizontal = (longitudHorizontalOriginal / 1.03).toInt
    val longitudVertical = (btnElegido.getHeight / 1.03).toInt
    val nuevaPosx = posx + (longitudHorizontalOriginal - longitudHorizontal) / 2
    val nuevaPosy = posy + (longitudVerticalOriginal - longitudVertical) / 2
    btnElegido.setSize(longitudHorizontal, longitudVertical)
    btnElegido.setLocation(nuevaPosx, nuevaPosy)
    ponerImagenbutton(btnElegido, imagen)
  }

  def agrandarBoton(btnElegido: JButton, imagen: ImageIcon): Unit = {
    val posx = btnElegido.getX
    val posy = btnElegido.getY
    val longitudHorizontalOriginal = btnElegido.getWidth
    val longitudVerticalOriginal = btnElegido.getHeight
    val longitudHorizontal = (longitudHorizontalOriginal * 1.03).toInt + 1 //para un correcto redondeo sumamos 1

    val longitudVertical = (btnElegido.getHeight * 1.03).toInt + 1 //para un correcto redondeo sumamos 1

    val nuevaPosx = posx - (longitudHorizontal - longitudHorizontalOriginal) / 2
    val nuevaPosy = posy - (longitudVertical - longitudVerticalOriginal) / 2
    btnElegido.setSize(longitudHorizontal, longitudVertical)
    btnElegido.setLocation(nuevaPosx, nuevaPosy)
    ponerImagenbutton(btnElegido, imagen)
  }

  def agrandarBoton(btnElegido: JButton): Unit = {
    val posx = btnElegido.getX
    val posy = btnElegido.getY
    val longitudHorizontalOriginal = btnElegido.getWidth
    val longitudVerticalOriginal = btnElegido.getHeight
    val fuente = btnElegido.getFont
    val longitudHorizontal = (longitudHorizontalOriginal * 1.03).toInt + 1 //para un correcto redondeo sumamos 1

    val longitudVertical = (btnElegido.getHeight * 1.03).toInt + 1 //para un correcto redondeo sumamos 1

    val nuevaPosx = posx - (longitudHorizontal - longitudHorizontalOriginal) / 2
    val nuevaPosy = posy - (longitudVertical - longitudVerticalOriginal) / 2
    btnElegido.setSize(longitudHorizontal, longitudVertical)
    btnElegido.setFont(new Font(fuente.getName, fuente.getStyle, (fuente.getSize * 1.03).toInt + 1))
    btnElegido.setLocation(nuevaPosx, nuevaPosy)
  }

  def encogerBoton(btnElegido: JButton): Unit = {
    val posx = btnElegido.getX
    val posy = btnElegido.getY
    val longitudHorizontalOriginal = btnElegido.getWidth
    val longitudVerticalOriginal = btnElegido.getHeight
    val fuente = btnElegido.getFont
    val longitudHorizontal = (longitudHorizontalOriginal / 1.03).toInt
    val longitudVertical = (btnElegido.getHeight / 1.03).toInt
    val nuevaPosx = posx + (longitudHorizontalOriginal - longitudHorizontal) / 2
    val nuevaPosy = posy + (longitudVerticalOriginal - longitudVertical) / 2
    btnElegido.setSize(longitudHorizontal, longitudVertical)
    btnElegido.setFont(new Font(fuente.getName, fuente.getStyle, (fuente.getSize / 1.03).toInt))
    btnElegido.setLocation(nuevaPosx, nuevaPosy)
  }

  def reproducirSonido(localizacion: String): Unit = {
    try {
      val archivoSonido = new File(localizacion) //Leemos un archivo

      val audio = AudioSystem.getAudioInputStream(archivoSonido) //creamos el objeto que traerá el sonido al programa

      val clip = AudioSystem.getClip
      clip.open(audio)
      clip.start()
    } catch {
      case ex: Exception =>
        JOptionPane.showMessageDialog(null, "Error al reproducir el archivo de sonido.", "Error:", JOptionPane.INFORMATION_MESSAGE)
        ex.printStackTrace()
    }
  }

  def ajustarTexto(jLabel: JLabel): Unit = {
    //fuente: https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size/2715279#2715279
    val labelFont = jLabel.getFont
    val labelText = jLabel.getText
    val stringWidth = jLabel.getFontMetrics(labelFont).stringWidth(labelText)
    val componentWidth = jLabel.getWidth
    // Find out how much the font can grow in width.
    val widthRatio = componentWidth.toDouble / stringWidth.toDouble
    val newFontSize = (labelFont.getSize * widthRatio).toInt
    val componentHeight = jLabel.getHeight
    // Pick a new font size so it will not be larger than the height of label.
    val fontSizeToUse = Math.min(newFontSize, componentHeight)
    // Set the label's font size to the newly determined size.
    jLabel.setFont(new Font(labelFont.getName, Font.PLAIN, fontSizeToUse))
  }
}
