package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class gameObject {
    
    protected float x, y;
    protected objectID id;
    protected int animation;
    protected int type;
    protected float velX = 0, velY = 0;
        
    public gameObject(float x, float y, objectID id){
        
        this.x = x;
        this.y = y;
        this.id = id;
    
    }
    
    public abstract void tick(LinkedList<gameObject> object); //colisiones
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public  float getX(){
        return x;
    }
    
    public  float getY(){
        return y;
    }
    
    public  void setX(float x){
        this.x = x;
    }
    
    public  void setY(float y){
        this.y = y ;
    }
    
    public  float getVelX(){
        return velX;
    }
    
    public  float getVelY(){
        return velY;
    }
    
    public  void setVelX(float velX){
        this.velX = velX;
    }
    
    public  void setVelY(float velY){
       this.velY = velY;
    }
   
    public  objectID getID(){
      return id;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
                    
}
