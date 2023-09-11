package interfaz;
import javax.swing.*;
import java.awt.*;

public class VidasPanel extends JFrame {
    private int vidas;
    private JPanel panel;

    public VidasPanel(int vidas) {
        this.vidas = vidas;

        setTitle("Vidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(1, vidas));

        actualizarVidas(); // Actualizar el panel inicialmente

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public void actualizarVidas() {
        panel.removeAll(); // Eliminar los componentes anteriores

        for (int i = 0; i < vidas; i++) {
            //JLabel corazon = new JLabel("\u2764"); // Representación del corazón
            JLabel corazon = new JLabel();
            //corazon.setFont(new Font("Arial", Font.PLAIN, 36)); // Tamaño y fuente del corazón
            corazon.setBounds(0, 0, 100, 100);
            MetodosGUI.ponerImagenLabel(corazon, new ImageIcon("src/assets/corazon.png"));
            panel.add(corazon);
        }

        revalidate(); // Volver a validar el panel para que se actualice en la interfaz
    }

    public static void main(String[] args) {
        int vidas = 5; // Número de vidas, puedes cambiarlo según tus necesidades
        VidasPanel vidasPanel = new VidasPanel(vidas);
        vidasPanel.setVisible(true);

        // Ejemplo de cambio dinámico de las vidas
        while (vidasPanel.vidas > 0) {
            try {
                Thread.sleep(3000); // Esperar 3 segundos
                vidasPanel.vidas -=1; // Cambiar el número de vidas
                vidasPanel.actualizarVidas(); // Actualizar el panel de vidas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
