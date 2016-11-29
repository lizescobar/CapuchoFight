package objects;

import framework.Texture;
import framework.gameObject;
import framework.objectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import window.Animation;
import window.Game;
import window.Handler;

public class Bomb extends gameObject {
    
    Texture texture = Game.getInstance();  
    private final Handler handler;
    private final Animation bomb;
    private final Animation bombExplosion;
    private Animation bombExplosion1;
    private Animation bombExplosion2;
    private Animation bombExplosion3;
    private Animation bombExplosion4;
    private final timeExplosion thread;
    private boolean boundsTop = false;
    private boolean boundsGround = false;
    private boolean boundsLeft = false;
    private boolean boundsRight = false;
    

    public Bomb(float x, float y, int type, Handler handler, objectID id) {
        super(x, y, id);
        bomb = new Animation(1, texture.bomb[0]);
        bombExplosion = new Animation(1, texture.bomb[2]);
        bombExplosion1 = new Animation(1, texture.bomb[3]);
        bombExplosion2 = new Animation(1, texture.bomb[4]);
        bombExplosion3 = new Animation(1, texture.bomb[5]);
        bombExplosion4 = new Animation(1, texture.bomb[6]);
        animation = 0;
        thread = new timeExplosion();
        this.type = type;
        this.handler = handler;
        type = 0;
        thread.start();
    }    
    
        
    @Override
    public void tick(LinkedList<gameObject> object) {
        
        bomb.runAnimation();
        bombExplosion.runAnimation();

        if(bombExplosion1 != null){
            bombExplosion1.runAnimation();
        }
        if( bombExplosion2 != null){
            bombExplosion2.runAnimation();
        }
        if(bombExplosion3 != null){
            bombExplosion3.runAnimation();
        }
        if(bombExplosion4 != null){
            bombExplosion4.runAnimation();
        }

        Collision(object);
           
    }

    @Override
    public void render(Graphics g) { 
        
        if(type == 0){
            bomb.drawAnimation(g, (int)x,(int) y, 32, 32);
        }

        if(type == 1){
            bombExplosion.drawAnimation(g, (int)x,(int) y, 32, 32);

            if (boundsTop == true){
                bombExplosion2 = null;
            }

            if (boundsLeft == true){
                bombExplosion1 = null;
            }

            if (boundsGround == true){
                bombExplosion4 = null;
            }
            
            if (boundsRight == true){
                bombExplosion3 = null;
            }

            if(bombExplosion2 != null){
                bombExplosion2.drawAnimation(g, (int)x,(int) y-32, 32, 32);
            }
            
            if ( bombExplosion1 != null){
                bombExplosion1.drawAnimation(g, (int)x-32,(int) y, 32, 32);
            }

            if ( bombExplosion4 != null){
                bombExplosion4.drawAnimation(g, (int)x,(int) y+32, 32, 32);
            }
            
            if ( bombExplosion3 != null){
                bombExplosion3.drawAnimation(g, (int)x+32,(int) y, 32, 32);
            }

        } 
        /*Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.blue);
        g2d.draw(getBounds());
        g.setColor(Color.green);
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsGround());*/
    }
    
    private void Collision(LinkedList<gameObject> object){
   
        for(int i = 0; i < handler.object.size(); i++ ){    
            gameObject tempObject = handler.object.get(i);   

            if(type == 3){
                if(tempObject.getType()==5||tempObject.getType()==2||tempObject.getType()==1){              
                               
                    if (getBoundsTop().intersects(tempObject.getBounds())){handler.removeObject(tempObject);}   
                    if (getBoundsLeft().intersects(tempObject.getBounds())){handler.removeObject(tempObject);} 
                    if (getBoundsGround().intersects(tempObject.getBounds())){handler.removeObject(tempObject);} 
                    if (getBoundsRight().intersects(tempObject.getBounds())){handler.removeObject(tempObject);}
                    handler.removeObject(this);
                }
                    
            }

            if (tempObject.getID() == objectID.Block){ 
                if(tempObject.getType() == 0 || tempObject.getType() == 3|| tempObject.getType() == 4){
                    if (getBoundsTop().intersects(tempObject.getBounds())){
                        boundsTop = true;  
                    }
                    if(getBoundsLeft().intersects(tempObject.getBounds())){
                        boundsLeft = true;
                    } 
                    if (getBoundsGround().intersects(tempObject.getBounds())){
                        boundsGround = true;
                    }
                    if (getBoundsRight().intersects(tempObject.getBounds())){
                        boundsRight = true;
                    }  
                }                                                    
            }           
            
        }
    }
        
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
      
    public Rectangle getBoundsTop() {
        return new Rectangle((int)x+10, (int)y-25,10,10);
    }
   
    public Rectangle getBoundsRight() {
        return new Rectangle((int)x+45,(int)y+10,10,10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x-25, (int)y+10 ,10,10);
    }
    
    public Rectangle getBoundsGround() {
        return new Rectangle((int)x+10,(int)y+45,10,10);
    }
 
        
    private class timeExplosion extends Thread{    
        
        @Override
        public void run(){
            try {                
                Thread.sleep(2000);
                type = 1;
                Thread.sleep(500);
                type = 3;                
            } catch (InterruptedException ex) {
                Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    } 
     
}
