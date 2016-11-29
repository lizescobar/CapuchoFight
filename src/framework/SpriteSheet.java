package framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet 
{
    private  BufferedImage image;
    public BufferedImage level;
    
    public SpriteSheet(BufferedImage image){
        this.image = image;
        
    }
    
    public BufferedImage grabImage(int col, int row, int w, int h){
        BufferedImage img = image.getSubimage(16*col+col, 16*row+row,w,h); 
        return img;
    }
    
    public SpriteSheet (String path, String levelPath)
    {
        try {
			image= ImageIO.read(new File(path));
                        level= ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public BufferedImage getGraphics (int row, int col, int width, int heigth) 
    {
        return image.getSubimage(32*col, 32*row,width,heigth);
    }
    
    
}
