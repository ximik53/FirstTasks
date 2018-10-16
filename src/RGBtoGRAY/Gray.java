package RGBtoGRAY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Gray {
    static RGBtoGRAY.Gray.GrayFrame f;
    // поверхность рисования
    BufferedImage grey;


    public Gray() {
        f = new RGBtoGRAY.Gray.GrayFrame("RGB to Gray");
        f.setSize(480, 480);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        f.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        f.setVisible(true);
    }

    class GrayFrame extends JFrame {
        public void paint(Graphics g) {
            super.paint(g);
        }

        public GrayFrame(String title) {
            super(title);
        }
    }

    class GrayPanel extends JPanel {
        public GrayPanel() {
        }

        public void paintComponent(Graphics g) {
            grey = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D out = (Graphics2D) grey.getGraphics();
            out.drawImage(grey, 0,0, null);
        }
    }
}