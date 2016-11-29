package window;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;

public class MainMenu{

    public ArrayList<Button> buttonsArray= new ArrayList<Button>( );
    public BufferedImage image;    
    
    public MainMenu (){        
        buttonsArray.add(new Button(15*32, 4*32, 8*32, 3*32, 0)); 
        buttonsArray.add(new Button(15*32, 9*32, 8*32, 3*32, 1));
        buttonsArray.add(new Button(15*32, 14*32, 8*32, 3*32, 2));          
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.red);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        BufferedImage background = null;
        BufferedImage bomber = null;
        BufferedImage jail = null;
        BufferedImage label = null;
         
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            background = loader.LoadImage("/fondo.png");
            bomber = loader.LoadImage("/Bomberman_2.png");
            jail = loader.LoadImage("/jail.png");
            label = loader.LoadImage("/b.png");            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        g.drawImage(background,-150 ,-200, null);
        g.drawImage(bomber,-70 ,130, null);
        g.drawImage(jail,0 ,0, null);
        g.drawImage(label,0 ,0, null);
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
        }
        
       */
        
        for (int i = 0; i < buttonsArray.size(); i++) 
        {
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
            
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.id = id;            
            boundary = new Rectangle(x, y, width, height);
        }
        
        public void render(Graphics g){
            
            Graphics2D g2= (Graphics2D )g;
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
            Font font = new Font("Broadway", Font.PLAIN, 50);
            g2.setFont(font);
            g2.setColor(Color.black);
            g2.drawString("JUGAR",(15*32)+40, (4*32)+60);
            
            Font font1 = new Font("Broadway", Font.PLAIN, 30);
            g2.setFont(font1);
            g2.setColor(Color.black);
            g2.drawString("¿CÓMO JUGAR?",(15*32)+5, (9*32)+60);
           
           
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(15.0f));
            g2.draw(boundary);
            g2.setColor(Color.white);
            g2.fill(boundary);
            
            Font font2 = new Font("Broadway", Font.PLAIN, 50);
            g2.setFont(font2);
            g2.setColor(Color.black);            
            g2.drawString("SALIR",(15*32)+50, (15*32)+30);            
            
        }
    }    
   
}
