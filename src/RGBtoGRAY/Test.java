package RGBtoGRAY;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedImage img=ImageIO.read(new File( "C:\\Users\\ximik\\Pictures\\Saved Pictures\\unnamed.png"));
        BufferedImage grey = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D out = (Graphics2D) grey.getGraphics();
        out.drawImage(img, 0,0, null);
        File output = new File("C:\\Users\\ximik\\Pictures\\Saved Pictures\\Greyunnamed.png");
        ImageIO.write(grey, "png", output);
    }
}