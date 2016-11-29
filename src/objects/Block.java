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

public class Block extends gameObject  {
    
    Texture texture = Game.getInstance();
    private final Handler handler;
       
    public Block(float x, float y, int type, Handler handler, objectID id){
    super (x, y, id);
    this.type = type;
    this.handler = handler;
    }

    @Override
    public void tick(LinkedList<gameObject> object) {         
    }
    
    private void Collision(LinkedList<gameObject> object){        
    }

  
    @Override
    public void render(Graphics g){
        
            if (type == 0){
                g.drawImage(texture.block[0],(int)x,(int)y, null);
            }
            if (type == 1){
                g.drawImage(texture.block[1],(int)x,(int)y, null);
            }
            if (type == 2){
                g.drawImage(texture.block[2],(int)x,(int)y, null);
            }
            if (type == 3){
                g.drawImage(texture.block[3],(int)x,(int)y, null);
            }
            if (type == 4){
                g.drawImage(texture.block[4],(int)x,(int)y, null);
            }
            if (type == 5){
                g.drawImage(texture.block[5],(int)x,(int)y, null);
            }
        /*Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.red);
        g2d.draw(getBounds());*/  
    }
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x , (int)y , 32, 32);
    }
}
