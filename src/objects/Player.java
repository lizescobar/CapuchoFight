package objects;

import framework.Texture;
import framework.gameObject;
import framework.objectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import window.Animation;
import window.Game;
import window.Handler;

public class Player extends gameObject {
    
    public float width = 32, height = 32;
    private final Handler handler;
    private Game game;
    
    
    
    Texture texture = Game.getInstance();
    
    private final Animation playerWalkFront;
    private final Animation playerWalkBack;
    private final Animation playerWalkLeft;
    private final Animation playerWalkRigth;
        
    public Player(float x, float y, int type, Handler handler, objectID id, Game game) {
        super(x, y, id);
        
        this.handler = handler;
        this.type = type;
        this.game = game;
        playerWalkFront = new Animation(1, texture.player[0]);
        playerWalkBack = new Animation(1, texture.player[1]);
        playerWalkLeft = new Animation(1, texture.player[2]);
        playerWalkRigth = new Animation(1, texture.player[3]);
        animation = 0;
    }
    
  
    
    @Override
    public void tick(LinkedList<gameObject> object) {
        
        x += velX;
        y += velY;
       
        Collision(object);
        
        playerWalkFront.runAnimation();
        playerWalkBack.runAnimation();
        playerWalkLeft.runAnimation();
        playerWalkRigth.runAnimation();
    }
    
    private void Collision(LinkedList<gameObject> object){
        
        
        for(int i = 0; i < handler.object.size(); i++ ){
            gameObject tempObject = handler.object.get(i);
            
            if (tempObject.getID() == objectID.Block){      
               
                if (getBoundsTop().intersects(tempObject.getBounds())){
                   y = tempObject.getY() + 34;
                }
                
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                } 
                                
                if (getBoundsRight().intersects(tempObject.getBounds())){
                   x = tempObject.getX() - width;
                }
                
                if (getBoundsLeft().intersects(tempObject.getBounds())){
                   x = tempObject.getX() + 34;
                }
                
            }
            
            if (tempObject.getID() == objectID.Enemy){      
               
                if (getBoundsTop().intersects(tempObject.getBounds())){
                    this.setType(1);  
                    System.out.println(this.getType());
                    handler.removeObject(this);
                }
                
                if(getBounds().intersects(tempObject.getBounds())){
                    this.setType(1);  
                    System.out.println(this.getType());
                    handler.removeObject(this);
                } 
                                
                if (getBoundsRight().intersects(tempObject.getBounds())){
                    this.setType(1);  
                    System.out.println(this.getType());
                    handler.removeObject(this);
                }
                
                if (getBoundsLeft().intersects(tempObject.getBounds())){
                    this.setType(1);  
                    System.out.println(this.getType());
                    handler.removeObject(this);
                }
                
            }
            
            if (tempObject.getID() == objectID.Bomb){  
             
                if (tempObject.getType() == 1){
                    
                    if (getBounds().intersects(new Rectangle((int)tempObject.getBounds().getX(),(int)tempObject.getBounds().getY()-26,(int)tempObject.getBounds().getWidth(),(int)tempObject.getBounds().getHeight()))
                            || getBounds().intersects(tempObject.getBounds())){
                        this.setType(1);  
                        System.out.println(this.getType());
                        handler.removeObject(this);
                    } 

                    if (getBoundsTop().intersects(new Rectangle((int)tempObject.getBounds().getX(),(int)tempObject.getBounds().getY()+26,(int)tempObject.getBounds().getWidth(),(int)tempObject.getBounds().getHeight()))
                            || getBoundsTop().intersects(tempObject.getBounds())){
                        this.setType(1);  
                        System.out.println(this.getType());
                        handler.removeObject(this);
                    } 

                    if (getBoundsRight().intersects(new Rectangle((int)tempObject.getBounds().getX()-26,(int)tempObject.getBounds().getY(),(int)tempObject.getBounds().getWidth(),(int)tempObject.getBounds().getHeight()))
                            || getBoundsRight().intersects(tempObject.getBounds())){
                        this.setType(1);  
                        System.out.println(this.getType());
                        handler.removeObject(this);
                    }

                    if (getBoundsLeft().intersects(new Rectangle((int)tempObject.getBounds().getX()+26,(int)tempObject.getBounds().getY(),(int)tempObject.getBounds().getWidth(),(int)tempObject.getBounds().getHeight()))
                            || getBoundsLeft().intersects(tempObject.getBounds())){
                        this.setType(1);  
                        System.out.println(this.getType());
                        handler.removeObject(this);
                    }
                        
                }
                         
            }
                         
        }
                
}

    @Override
    public void render(Graphics g) {
        
        
        if(animation == 0){
            playerWalkFront.drawAnimation(g, (int)x,(int) y,32,32);
        }
        if (animation == 1 ){
            playerWalkBack.drawAnimation(g, (int)x,(int) y,32,32);
        } 
        if (animation == 2){
            playerWalkRigth.drawAnimation(g, (int)x,(int) y,32,32);
        } 
        if (animation == 3){
            playerWalkLeft.drawAnimation(g, (int)x,(int) y,32,32);
        } 
        
        /*Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());*/                 
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2-((width/2)/2))), (int) ((int)y+(height/2)), (int)width/2,(int)height/2 );
    }
    
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2-((width/2)/2))), (int)y, (int)width/2,(int)height/2 );
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5,(int)height-10 );
    }
    
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+5, (int)5,(int)height-10 );
    }       
    
}
