//import java.awt.{Dimension, Label}
import scala.swing._

object MainGUI {
  def main(args: Array[String]): Unit = {
    val label = new Label("Hello world")
    val panel = new FlowPanel(label)
    val frame = new MainFrame {
      title = "My Scala Swing Window"
      contents = panel
      preferredSize = new Dimension(400, 300)
      centerOnScreen()
      open()
    }
  }
}