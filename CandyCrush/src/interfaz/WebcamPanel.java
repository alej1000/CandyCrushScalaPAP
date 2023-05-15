package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;

public class WebcamPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private Webcam webcam;

    public WebcamPanel() {
        setLayout(new FlowLayout());

        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        JButton captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> {
            image = webcam.getImage();
            image = cropToSquare(image);
            image = resize(image, 500, 500);
            String base64Image = getBase64Image(image);
            String dataUrl = "data:image/jpeg;base64," + base64Image;
            System.out.println("Data URL:\n" + dataUrl);

            webcam.close();

            // Cerrar el panel al capturar la imagen
            JFrame frame = (JFrame) getTopLevelAncestor();
            frame.dispose();
        });

        add(captureButton);

        new Thread(() -> {
            while (true) {
                image = webcam.getImage();
                repaint();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private BufferedImage cropToSquare(BufferedImage source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        return source.getSubimage(x, y, size, size);
    }

    private BufferedImage resize(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = resizedImage.createGraphics();
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();
        return resizedImage;
    }

    private String getBase64Image(BufferedImage image) {
        String base64Image = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Webcam Capture");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 520);
        frame.setResizable(false);

        WebcamPanel panel = new WebcamPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
