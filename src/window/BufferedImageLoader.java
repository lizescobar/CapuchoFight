package window;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
    public BufferedImage image;
    
    public BufferedImage LoadImage(String path){
        try {
          image = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            System.out.println("No existe esa imagen");
        }
        return image;
    }
    
}
