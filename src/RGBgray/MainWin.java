package RGBgray;

import javax.imageio.ImageIO;
import java.awt.event.ComponentEvent;
import java.awt.image.RescaleOp;
import java.lang.String;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainWin extends JFrame{
    static MyFrame f;
    MyPanel pic;
    static BufferedImage imag;
    boolean loading = false;
    String fileName;
    MyPanel pan;
    float br;
    static BufferedImage d3;

    public MainWin() {
        f = new MyFrame("Выберите изображение");
        f.setSize(480, 480);
        JMenuBar menuBar = new  JMenuBar();
        f.setJMenuBar(menuBar);
        menuBar.setBounds(0,0,350,30);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar toolbar = new  JToolBar("Toolbar", JToolBar.VERTICAL);
        toolbar.setBounds(0, 0, 30, 300);
        f.add(toolbar);
        JSlider bright = new JSlider(JSlider.VERTICAL,1,200,100);

        toolbar.add(bright);

        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {

                JSlider bright = (JSlider) event.getSource();
                event.getSource();
                br=bright.getValue();
                System.out.println(br);
                pic.repaint();
                GrayWin.pic1.repaint();
            }
        };
        bright.addChangeListener(listener);

        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        Action loadAction = new AbstractAction("Загрузить") {
            public void actionPerformed(ActionEvent event) {
                JFileChooser jf = new JFileChooser();
                int result = jf.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        fileName = jf.getSelectedFile().getAbsolutePath();
                        File iF = new File(fileName);
                        jf.addChoosableFileFilter(new TextFileFilter(".png"));
                        jf.addChoosableFileFilter(new TextFileFilter(".jpg"));
                        imag = ImageIO.read(iF);
                        loading = true;
                        f.setSize(imag.getWidth(), imag.getWidth());
                        pic.setSize(imag.getWidth(), imag.getWidth());
                        pic.repaint();

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(f, "Такого файла не существует");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(f, "Исключение ввода-вывода");
                    } catch (Exception ex) {
                    }
                }
            }
        };
        JMenuItem loadMenu = new JMenuItem(loadAction);
        fileMenu.add(loadMenu);

        Action grayAction = new AbstractAction("Сделать чернобелым") {
            public void actionPerformed(ActionEvent event) {
                if(true)
                {
                    new GrayWin();
                }
            }
        };
        JMenuItem grayMenu = new JMenuItem(grayAction);
        fileMenu.add(grayMenu);

        Action saveasAction = new AbstractAction("Сохранить как...") {
            public void actionPerformed(ActionEvent event) {
                try {
                    JFileChooser jf = new JFileChooser();
                    TextFileFilter pngFilter = new TextFileFilter(".png");
                    TextFileFilter jpgFilter = new TextFileFilter(".jpg");
                    jf.addChoosableFileFilter(pngFilter);
                    jf.addChoosableFileFilter(jpgFilter);
                    int result = jf.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        fileName = jf.getSelectedFile().getAbsolutePath();
                    }
                    // Смотрим какой фильтр выбран
                    if (jf.getFileFilter() == pngFilter) {
                        ImageIO.write(imag, "png", new File(fileName + ".png"));
                    } else {
                        ImageIO.write(imag, "jpeg", new File(fileName + ".jpg"));
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(f, "Ошибка ввода-вывода");
                }
            }
        };
        JMenuItem saveasMenu = new JMenuItem(saveasAction);
        fileMenu.add(saveasMenu);

        pic = new MyPanel();
        pic.setBackground(Color.white);
        pic.setOpaque(true);
        f.add(pic);

        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {

                if (loading == false) {
                    pic.setSize(f.getWidth(), f.getHeight());
                    BufferedImage tempImage = new BufferedImage(pic.getWidth(), pic.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D d2 = (Graphics2D) tempImage.createGraphics();
                    d2.setColor(Color.white);
                    d2.fillRect(0, 0, pic.getWidth(), pic.getHeight());
                    tempImage.setData(imag.getRaster());
                    imag = tempImage;
                    pic.repaint();
                }
                loading = false;
            }
        });
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWin();
            }
        });
    }

    class MyFrame extends JFrame {
        public void paint(Graphics g) {
            super.paint(g);
        }

        public MyFrame(String title) {
            super(title);
        }
    }

    class MyPanel extends JPanel {
        public MyPanel() {
        }

        public void paintComponent(Graphics g) {
            if (imag == null) {
                imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());

            }
            super.paintComponent(g);
            float x =br/100;
            RescaleOp rop = new RescaleOp(x, 0, null);

            d3 = rop.filter(imag, null);
            g.drawImage(d3, 0, 0, this);
        }
    }

    // Фильтр картинок
    class TextFileFilter extends FileFilter {
        private String ext;

        public TextFileFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(java.io.File file) {
            if (file.isDirectory()) return true;
            return (file.getName().endsWith(ext));
        }

        public String getDescription() {
            return "*" + ext;
        }
    }
}
