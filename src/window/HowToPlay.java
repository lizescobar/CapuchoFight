package window;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HowToPlay {
    
    
    public ArrayList<Button> buttonsArray= new ArrayList<Button>();
    public BufferedImage image;
    
    
    public HowToPlay (){        
        buttonsArray.add(new Button(2*32, 17*32, 5*32, 1*32, 3)); 
        buttonsArray.add(new Button(10*32, 17*32, 5*32, 1*32, 4));
        buttonsArray.add(new Button(18*32, 17*32, 5*32, 1*32, 5));        
    }
    
    public void render(Graphics g){
        
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        BufferedImage background = null;      
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            background = loader.LoadImage("/comoJugar.png");                 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        g.drawImage(background,0 ,0, null);
       
        //guide lines
        /*
        g.setColor(Color.WHITE);
        for (int i = 0; i < Game.WIDTH/32; i++) 
        {
            g.drawLine(i*32, 0, i*32, Game.HEIGHT);
        }
        for (int i = 0; i < Game.HEIGHT/32; i++) 
        {
            g.drawLine(0, i*32, Game.WIDTH, i*32);
        }*/                     
        for (int i = 0; i < buttonsArray.size(); i++){
          buttonsArray.get(i).render(g);
        }                
    }

    public class Button{
        public int x;
        public int y;
        public Rectangle boundary;       
        public int id;
        public int width;
        public int height;
        
        
        public Button (int x, int y , int width, int height, int id){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.id = id;            
            boundary = new Rectangle(x, y, width, height);
        }
        
        public void render(Graphics g){
            
            Graphics2D g2= (Graphics2D )g;
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
            Font font = new Font("Broadway", Font.PLAIN, 20);
            g2.setFont(font);
            g2.setColor(Color.black);
            g2.drawString("JUGAR",(3*32)+10, (17*32)+25);
            
            Font font1 = new Font("Broadway", Font.PLAIN, 20);
            g2.setFont(font1);
            g2.setColor(Color.black);
            g2.drawString("VOLVER",(10*32)+35, (17*32)+25);
           
           
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(15.0f));
            g2.draw(boundary);
            g2.setColor(Color.white);
            g2.fill(boundary);
            
            Font font2 = new Font("Broadway", Font.PLAIN, 20);
            g2.setFont(font2);
            g2.setColor(Color.black);            
            g2.drawString("SALIR",(19*32)+15, (17*32)+25);            
            
        }
    }    
   
}

    
