package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import objects.Bomb;
import window.Handler;

public class keyInput extends KeyAdapter {
    
    Handler handler;
    int visible = 0;
    int contadorP;
    
    public keyInput(Handler handler){
        this.handler = handler;  
       
    }
            
            
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){            
            gameObject tempObject = handler.object.get(i);
           
            if (tempObject.getID() == objectID.Player){
                if(key == KeyEvent.VK_RIGHT){ 
                    tempObject.setVelX(3);
                    tempObject.setAnimation(2);
                }
                if(key == KeyEvent.VK_LEFT){ 
                    tempObject.setVelX(-3);
                    tempObject.setAnimation(3);
                    
                }               
                if(key == KeyEvent.VK_UP){ 
                    tempObject.setVelY(-3);
                    tempObject.setAnimation(1);
                }
                if(key == KeyEvent.VK_DOWN){ 
                    tempObject.setVelY(3);
                    tempObject.setAnimation(0);
                }
                if(key == KeyEvent.VK_SPACE){                                       
                   handler.addObject(new Bomb(tempObject.getX(), tempObject.getY(), 0,handler,objectID.Bomb));                
                }                    
            }
            
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        
        
    }
    
    
    
    @Override
    public void keyReleased(KeyEvent e){
    
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            gameObject tempObject = handler.object.get(i);
            
            if (tempObject.getID() == objectID.Player){
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);         
               
            }
        }
        
    }
  
    
    
}
