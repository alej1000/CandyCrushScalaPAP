package conexionDeScala

//package interfaz
//
//import java.lang.Math.sqrt
//import java.util.logging.Level
//import java.util.logging.Logger
//import javax.swing.JComponent
//import javax.swing.JLabel
//import javax.swing.JPanel
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//
///**
// *
// * @author César
// */
//class HiloAnimacion extends Thread {
//  private var coomponente: JComponent = null
//  private var label: JLabel = null
//  private var posFinal = 0
//  private var coordXFinal = 0
//  private var coordYFinal = 0
//  private var aceleracion = .0
//  private var interrumpido = false
//
//  def this(panel: JPanel, label: JLabel, posFinal: Int) {
//    this()
//    this.coomponente = panel
//    this.label = label
//    this.posFinal = posFinal
//  }
//
//  def this(componente: JComponent, coordXFinal: Int, coordYFinal: Int, aceleracion: Double) {
//    this()
//    this.coomponente = componente
//    this.label = label
//    this.coordXFinal = coordXFinal
//    this.coordYFinal = coordYFinal
//    this.aceleracion = aceleracion
//  }
//
//  override def run(): Unit = {
//    if (label != null && coomponente != null) {
//      val primeraPosLbl = label.getX //primera posición que tuvo el
//
//      //label a la hora de crearse el hilo
//      while (label.getX > posFinal) { //mientras que el label no haya llegado a su pos final
//        //se calcula la nueva posición del label usando
//        //MRUA para que quede fluido
//        val lblNuevaPos = (label.getX.toInt / 1.1).toInt
//        //se aplica el mismo movimiento relativo al label
//        val pnlNuevaPos = ((coomponente.getWidth - label.getWidth) * (lblNuevaPos.toFloat / primeraPosLbl) - (coomponente.getWidth - label.getWidth)).toInt
//        label.setLocation(lblNuevaPos, posFinal)
//        coomponente.setLocation(pnlNuevaPos, posFinal)
//        try Thread.sleep(14) //descanso de 14 ms para que no sea
//
//        catch {
//          case ex: InterruptedException =>
//            Logger.getLogger(classOf[HiloAnimacion].getName).log(Level.SEVERE, null, ex)
//            break //todo: break is not supported
//
//        }
//      }
//      if (!Thread.interrupted) coomponente.setVisible(false)
//    }
//    else {
//      var distancia = sqrt(((coordXFinal - coomponente.getX) * (coordXFinal - coomponente.getX)) + ((coordYFinal - coomponente.getY) * (coordYFinal - coomponente.getY)))
//      val coseno = ((coomponente.getX - coordXFinal) / distancia).toFloat
//      val seno = (coomponente.getY - coordYFinal) / distancia
//      while (distancia > 0.49) { //mientras que el label no haya llegado a su pos final
//        //se calcula la nueva posición del label usando
//        //MRUA para que quede fluido
//        distancia = distancia / aceleracion
//        val componenteNuevaPosX = (coseno * distancia).toInt + coordXFinal
//        val componenteNuevaPosY = (seno * distancia).toInt + coordYFinal
//        if (distancia > 0.49) {
//          coomponente.setLocation(componenteNuevaPosX, componenteNuevaPosY)
//          try Thread.sleep(14) //descanso de 14 ms para que no sea
//
//          catch {
//            case ex: InterruptedException =>
//
//              //                        Logger.getLogger(HiloAnimacion.class.getName()).log(Level.SEVERE, null, ex);
//              interrumpido = true
//              break //todo: break is not supported
//
//          }
//        }
//      }
//      if (!interrumpido) coomponente.setLocation(coordXFinal, coordYFinal)
//    }
//  }
//}
