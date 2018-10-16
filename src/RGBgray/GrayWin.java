package RGBgray;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static RGBgray.MainWin.imag;

public class GrayWin
{
    static MyFrame f;
    MyPanel pic;
    boolean loading=false;
    String fileName;
    public GrayWin()
    {
        f=new MyFrame("RGB to Gray");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(imag.getWidth(),imag.getHeight());
        JMenuBar menuBar = new  JMenuBar();
        f.setJMenuBar(menuBar);
        JMenu fileMenu = new  JMenu("Файл");
        menuBar.add(fileMenu);

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
                        ImageIO.write(imag, "png", new  File(fileName+".png"));
                    }
                    else
                    {
                        ImageIO.write(imag, "jpeg", new  File(fileName+".jpg"));
                    }
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(f, "Ошибка ввода-вывода");
                }
            }
        };
        JMenuItem saveasMenu = new  JMenuItem(saveasAction);
        fileMenu.add(saveasMenu);

        pic = new  MyPanel();
        pic.setOpaque(true);
        f.add(pic);


        f.addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if(loading==false)
                {
                    pic.setSize(f.getWidth(), f.getHeight());
                    BufferedImage tempImage = new  BufferedImage(imag.getWidth(), imag.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                    Graphics2D out = (Graphics2D) tempImage.getGraphics();
                    out.drawImage(imag, 0,0, null);
                    imag=tempImage;
                    pic.repaint();
                }
                loading=false;
            }
        });
        f.setLayout(null);
        f.setVisible(true);
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
            g.drawImage(imag, 0, 0,this);
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