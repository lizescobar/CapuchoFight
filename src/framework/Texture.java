package framework;

import java.awt.image.BufferedImage;
import window.BufferedImageLoader;

// areglo de imagenes
public class Texture {
    
    SpriteSheet bs,ps,vs,es,ens,ts,ks,gs;
    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;
    private BufferedImage bombSheet = null;
    private BufferedImage explosionSheet = null;
    private BufferedImage enemySheet = null;
    private BufferedImage treasureSheet = null;
    private BufferedImage keySheet = null;
    private BufferedImage backSheet = null;
    private BufferedImage mainFondo = null;
    
    public BufferedImage[] block = new BufferedImage[10];
    public BufferedImage[] player = new BufferedImage[15];       
    public BufferedImage[] bomb = new BufferedImage[10];
    public BufferedImage[] enemy = new BufferedImage[10];
    public BufferedImage[] treasure = new BufferedImage[2];
    public BufferedImage[] key = new BufferedImage[1];
    public BufferedImage[] background = new BufferedImage[1];

    
    public Texture(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            blockSheet = loader.LoadImage("/Blocks.png");
            playerSheet = loader.LoadImage("/Player.png");
            bombSheet = loader.LoadImage("/blueBomb.png");
            explosionSheet = loader.LoadImage("/Explosion.png");
            enemySheet = loader.LoadImage("/Enemy.png");
            treasureSheet = loader.LoadImage("/Treasure.png");
            keySheet = loader.LoadImage("/key.png");
            backSheet = loader.LoadImage("/ice.png");
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        bs = new SpriteSheet(blockSheet); 
        ps = new SpriteSheet(playerSheet);
        vs = new SpriteSheet(bombSheet);
        es = new SpriteSheet(explosionSheet);
        ens = new SpriteSheet(enemySheet);
        ts = new SpriteSheet(treasureSheet);   
        ks = new SpriteSheet(keySheet);
        gs = new SpriteSheet(backSheet);
        getTextures();
        
    }
    
    private void getTextures(){
        block[0] = bs.grabImage(0,0,32,32);
        block[1] = bs.grabImage(2,0,32,32);
        block[2] = bs.grabImage(4,0,32,32);
        block[3] = bs.grabImage(6,0,32,32);
        block[4] = bs.grabImage(8,0,32,32);
        block[5] = bs.grabImage(10,0,32,32);
        
        player[0] = ps.grabImage(0,0,32,32);
        player[2] = ps.grabImage(0,2,32,32);
        player[3] = ps.grabImage(0,4,32,32);
        player[1] = ps.grabImage(0,6,32,32);
        
        bomb[0] = vs.grabImage(0, 0, 32, 32);
                
        bomb[2] = es.grabImage(2, 2, 32, 32);
        bomb[3] = es.grabImage(0, 2, 32, 32);
        bomb[4] = es.grabImage(2, 0, 32, 32);
        bomb[5] = es.grabImage(4, 2, 32, 32);
        bomb[6] = es.grabImage(2, 4, 32, 32);
        
        enemy[0] = ens.grabImage(0, 0, 32, 32);
        key[0] = ks.grabImage(0, 0, 32, 32);
        treasure[0] = ts.grabImage(0, 0, 32, 32);
        
        background[0] = gs.grabImage(0, 0,32,32);
        
        
    }
}
