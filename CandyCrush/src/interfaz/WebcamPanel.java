package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;

public class WebcamPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private Webcam webcam;

    private String base64Image  ="";

    public WebcamPanel() {
        setLayout(new FlowLayout());

        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        JButton captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> {
            image = webcam.getImage();
            image = cropToSquare(image);
            image = resize(image, 300, 300);
            image = compressJPEG(image, 0.18f); // Comprimir la imagen con calidad 60%
            String base64Image = convertToBase64Image(image);
            String dataUrl = "data:image/jpeg;base64," + base64Image;
//            System.out.println("Data URL:\n" + dataUrl);

            this.base64Image = dataUrl;
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

    private BufferedImage compressJPEG(BufferedImage input, float quality) {
        ByteArrayOutputStream compressed = new ByteArrayOutputStream();

        try (ImageOutputStream outputStream = new MemoryCacheImageOutputStream(compressed)) {
            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("JPEG").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(quality);

            jpgWriter.setOutput(outputStream);
            jpgWriter.write(null, new IIOImage(input, null, null), jpgWriteParam);
            jpgWriter.dispose();

            // Convert the compressed byte array back to a BufferedImage
            byte[] jpegData = compressed.toByteArray();
            ByteArrayInputStream inputBytes = new ByteArrayInputStream(jpegData);
            return ImageIO.read(inputBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return input; // Return the original image in case of an error
        }
    }


    private String convertToBase64Image(BufferedImage image) {
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

    public String getBase64Image() {
        return base64Image;
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
