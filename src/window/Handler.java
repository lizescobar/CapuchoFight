package window;

import framework.gameObject;
import framework.objectID;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    
    public LinkedList<gameObject> object = new LinkedList<gameObject>();
    private gameObject tempObject;
    private Game game;
    
    public Handler(Game game)
    {
        this.game= game;
    }
    
    public void tick(){
    for(int i = 0; i <object.size(); i++){
        tempObject = object.get(i);
        tempObject.tick(object);
         if (tempObject.getID()==objectID.Player)
            {
                if(tempObject.getType()==1)
                    game.gameState=4;
            }
        
    }
}
    public void render(Graphics  g){
        for(int i = 0; i <object.size(); i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }    
        
    public void addObject(gameObject object){
    this.object.add(object);
         
    }
    
    public void removeObject(gameObject object){
    this.object.remove(object);
         
    }
    
 
}