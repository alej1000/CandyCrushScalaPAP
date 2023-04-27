package swing

import javax.swing.{JButton, JFrame}

object SwingExample {
  def main(args: Array[String]): Unit = {
    val frame = new JFrame("Hello, Scala Swing!")
    val button = new JButton("Click me!")
    frame.getContentPane.add(button)
    frame.pack()
    frame.setVisible(true)
  }
}
