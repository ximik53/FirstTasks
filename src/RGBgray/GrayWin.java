package RGBgray;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static RGBgray.MainWin.d3;
import static RGBgray.MainWin.imag;

public class GrayWin
{
    static MyFrame f1;
    static MyPanel pic1;
    boolean loading=false;
    String fileName;
    public GrayWin()
    {
        f1=new MyFrame("RGB to Gray");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(imag.getWidth(),imag.getHeight());
        JMenuBar menuBar = new  JMenuBar();
        f1.setJMenuBar(menuBar);
        JMenu fileMenu = new  JMenu("Файл");
        menuBar.add(fileMenu);
        final BufferedImage[] buff = {new BufferedImage(d3.getWidth(), d3.getHeight(), BufferedImage.TYPE_BYTE_GRAY)};

        Action saveasAction = new  AbstractAction("Сохранить как...")
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    JFileChooser jf= new  JFileChooser();
                    TextFileFilter pngFilter = new  TextFileFilter(".png");
                    TextFileFilter jpgFilter = new  TextFileFilter(".jpg");
                    jf.addChoosableFileFilter(pngFilter);
                    jf.addChoosableFileFilter(jpgFilter);
                    int  result = jf.showSaveDialog(null);
                    if(result==JFileChooser.APPROVE_OPTION)
                    {
                        fileName = jf.getSelectedFile().getAbsolutePath();
                    }
                    if(jf.getFileFilter()==pngFilter)
                    {
                        ImageIO.write(d3, "png", new  File(fileName+".png"));
                    }
                    else
                    {
                        ImageIO.write(d3, "jpeg", new  File(fileName+".jpg"));
                    }
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(f1, "Ошибка ввода-вывода");
                }
            }
        };
        JMenuItem saveasMenu = new  JMenuItem(saveasAction);
        fileMenu.add(saveasMenu);

        pic1 = new  MyPanel();
        pic1.setBounds(30,0,260,260);
        pic1.setOpaque(true);
        f1.add(pic1);


        f1.addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if(loading==false)
                {
                    BufferedImage tempImage = new  BufferedImage(imag.getWidth(), imag.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                    pic1.setSize(f1.getWidth(), f1.getHeight());
                    Graphics2D out = (Graphics2D) tempImage.getGraphics();
                    out.drawImage(d3, 0,0, null);
                    d3=tempImage;
                    buff[0] =tempImage;
                    pic1.repaint();
                }
                loading=false;
            }
        });
        f1.setLayout(null);
        f1.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  GrayWin();
            }
        });
    }




    class MyFrame extends JFrame
    {
        public void paint(Graphics g)
        {
            super.paint(g);
        }
        public MyFrame(String title)
        {
            super(title);
        }
    }

    class MyPanel extends JPanel
    {
        public MyPanel()
        { }
        public void paintComponent (Graphics g)
        {
            if(imag==null)
            {
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            super.paintComponent(g);
            BufferedImage tempImage =new BufferedImage(d3.getWidth(),d3.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D out = (Graphics2D) tempImage.getGraphics();
            out.drawImage(d3, 0,0, null);
            d3=tempImage;
            g.drawImage(tempImage, 0, 0,this);
        }
    }
    class TextFileFilter extends FileFilter
    {
        private String ext;
        public TextFileFilter(String ext)
        {
            this.ext=ext;
        }
        public boolean accept(java.io.File file)
        {
            if (file.isDirectory()) return true;
            return (file.getName().endsWith(ext));
        }
        public String getDescription()
        {
            return "*"+ext;
        }
    }
}