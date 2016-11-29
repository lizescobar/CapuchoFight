package window;

import framework.SpriteSheet;
import framework.keyInput;
import framework.objectID;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import objects.Block;
import framework.*;
import java.awt.image.BufferedImage;
import objects.Enemy;
import objects.Key;
import objects.Player;
import objects.Treasure;

public class Game extends Canvas implements Runnable {
    
    private boolean running = false;
    private Thread thread;
    public static int WIDTH,HEIGHT;
    public static SpriteSheet graphics;
    public BufferedImage level = null;
    public BufferedImage level1 = null;
    public BufferedImage level2 = null;
    private BufferedImage ice = null;
    private BufferedImage grass = null;
    private BufferedImage soil = null;
    private MainMenu mainMenu;
    private HowToPlay howToPlay;
    private Win win;
    private Lose lose;
    public int levelCounter = 1;
    
    
    public int gameState=0;
    
    /*game state 
    0 main menu
    1 level 1
    2 como jugar
    3 ganar
    4 perder
    */
    
    
    
    Handler handler;
    static Texture texture;
    
  //  Random rand = new Random();
    
    private void init(){
        WIDTH  = getWidth();
        HEIGHT = getHeight();
  // añande el nivel
        texture = new Texture();
        BufferedImageLoader loader = new BufferedImageLoader();
        ice = loader.LoadImage("/ice.png");
        grass = loader.LoadImage("/grass.png");
        soil = loader.LoadImage("/soil.png");
        level = loader.LoadImage("/Level_2.png");
        level1 = loader.LoadImage("/Level_3.png");
        level2 = loader.LoadImage("/Level_4.png");
        handler = new Handler(this);
        mainMenu = new MainMenu();
        howToPlay = new HowToPlay();
        win = new Win();
        lose = new Lose();
     ///
        
       this.addKeyListener(new keyInput(handler));
       this.addMouseListener(new MouseInput (mainMenu,howToPlay,this, win,lose));
      
        
  ///esanea la imagen
        LoadImageLevel(level);
       ///
        this.addKeyListener(new keyInput(handler));
        
    }
   
    
   public synchronized void start(){
         if(running) //Siempre es falso
             return;
         
         running = true;
         thread = new Thread(this);
         thread.start();
   }
   
   
    
   @Override
   public void run(){
       //Volver a correr el juego tan rapido como el computador puede
       init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){ 
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
		tick();
		updates++;
		delta--;
            }
            render();
            frames++;
	
            
            if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames + " TICKS: " + updates);
                    frames = 0;
                    updates = 0;
            }
        }
        
        
   }
   
   private void tick(){
       
       handler.tick();
     
       
   }
   
   private void render(){
       BufferStrategy bs = this.getBufferStrategy(); //se refiere al canvas
       if (bs == null){
           this.createBufferStrategy(3);
           return;
       }
       
       Graphics g = bs.getDrawGraphics();
       
       
       ////////////////////////////////
       //Dibujar aqui 
   
              
       switch(gameState){
           case 0:
               //mainMenu will be rendered
               mainMenu.render(g);
               break;
           case 1:
               //first level will be rendered
               
               if(levelCounter == 1){
               g.drawImage(ice, 0, 0, this);
               }
               if(levelCounter == 2){
               g.drawImage(grass, 0, 0, this);
               }
               if(levelCounter == 3){
               g.drawImage(soil, 0, 0, this);
               }
               handler.render(g);
            break;
            case 2:
               //comoJugar will be rendered
               howToPlay.render(g);
            break;
            case 3:
               win.render(g); 
               handler.object.clear();               
               init();
            break;
            case 4:
               //comoJugar will be rendered                
               lose.render(g);
               handler.object.clear();
               init();
            break;
       }
       ///////////////////////////////  
       
       g.dispose();//libera recursos
       bs.show();
   }
   // escanea la imagen de entrada
    public void LoadImageLevel(BufferedImage image){
       int w = image.getWidth();
       int h = image.getHeight();
              
        for (int xx = 0; xx < h; xx++){
            for(int yy= 0 ; yy < w ; yy++){
               
            int pixel = image.getRGB(xx,yy);
            int red = ((pixel >> 16) & 255);
            int green = ((pixel >> 8) & 0xff);
            int blue = ((pixel) & 0xff);
               
            if (red == 0 & green == 0 & blue == 255){ handler.addObject(new Player(xx*32,yy*32,0,handler,objectID.Player,this));}
            if (red == 0 & green == 0 & blue == 0) {handler.addObject(new Block(xx*32,yy*32,0,handler,objectID.Block));}
            if (red == 64 & green == 64 & blue == 64){ handler.addObject(new Block(xx*32,yy*32,1,handler,objectID.Block));} 
            if (red == 178 & green == 0 & blue == 255){ handler.addObject(new Block(xx*32,yy*32,2,handler,objectID.Block));}
            if (red == 255 & green == 106 & blue == 0){ handler.addObject(new Block(xx*32,yy*32,3,handler,objectID.Block));}
            if (red == 0 & green == 255 & blue == 0){ handler.addObject(new Block(xx*32,yy*32,4,handler,objectID.Block));}
            if (red == 0 & green == 255 & blue == 255){ handler.addObject(new Block(xx*32,yy*32,5,handler,objectID.Block));}
            if (red == 255 & green == 0 & blue == 0){ handler.addObject(new Enemy(xx*32,yy*32,0,handler,objectID.Enemy));}
            if (red == 38 & green == 127 & blue == 0){ handler.addObject(new Enemy(xx*32,yy*32,1,handler,objectID.Enemy));}
            if (red == 255 & green == 216 & blue == 0){ handler.addObject(new Treasure(xx*32,yy*32, 0,handler,objectID.Treasure,this));}
            if (red == 240 & green == 255 & blue == 38){ handler.addObject(new Key(xx*32,yy*32, 0,handler,objectID.Key,this));}
            //if (red == 255 & green == 255 & blue == 255){ handler.addObject(new Background(xx*32,yy*32,0,handler,objectID.Background,this));}                             
            }
        }
   }
    
        
   public static Texture getInstance(){
       return texture;
   }
   
   public static void main(String args[]){       
        Window window = new Window(791, 599, "Capucho fight", new Game());
   }
    
}
