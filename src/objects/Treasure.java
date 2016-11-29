package objects;

import framework.Texture;
import framework.gameObject;
import framework.objectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import window.Game;
import window.Handler;


public class Treasure extends gameObject {
    
    Texture texture = Game.getInstance();
    private final Handler handler;
    private Game game;
    
    public Treasure(float x, float y, int type, Handler handler, objectID id,Game game){
    super (x, y, id);
    this.game=game;
    this.type = type;
    this.handler = handler;
    }
     
    
    
     @Override
    public void tick(LinkedList<gameObject> object) {
        Collision(object);
    }
     
    
    @Override
    public void render(Graphics g){
        
        if (type == 0){
            g.drawImage(texture.treasure[0],(int)x,(int)y, null);
        }
            
        /*Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.pink);
        g2d.draw(getBounds());*/  
    }
    
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x , (int)y , 32, 32);
    }
    
    private void Collision(LinkedList<gameObject> object){
    
      for(int i = 0; i < handler.object.size(); i++ ){
            gameObject tempObject = handler.object.get(i);
            
            if (tempObject.getID() == objectID.Player){
            
                if(getBounds().intersects(tempObject.getBounds())){
                   handler.removeObject(tempObject);
                   tempObject.setType(10);
                   game.levelCounter++;
                   if(game.levelCounter == 4){
                       game.gameState=3;                       
                   }
                   
                }
            }
      
      }
    
    }
}
