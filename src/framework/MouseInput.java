package framework;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import window.HowToPlay;
import window.Win;
import window.MainMenu;
import window.Lose;
import window.Game;

public class MouseInput implements MouseListener{
    
    public MainMenu mainMenu;
    public HowToPlay howToPlay;
    public Game game;
    public Win win;
    public Lose lose;
    
    public MouseInput (MainMenu mainMenu,HowToPlay howToPlay, Game game, Win win, Lose lose){
        this.mainMenu= mainMenu;
        this.howToPlay = howToPlay;
        this.win = win;
        this.game = game;
        this.lose = lose;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){       
    }

    @Override
    public void mousePressed(MouseEvent e) {       
        int x1=e.getX();
            int y1=e.getY();
            
            for (int i = 0; i < mainMenu.buttonsArray.size(); i++) 
            {
                if (mainMenu.buttonsArray.get(i).boundary.contains(new Point(x1, y1)))
                {
                    if(mainMenu.buttonsArray.get(i).id == 0){
                    game.gameState=1;
                    }
                    if(mainMenu.buttonsArray.get(i).id == 1){
                    game.gameState=2;
                    }
                    if(mainMenu.buttonsArray.get(i).id == 2 ){
                        System.exit(0);
                    }
                }
            }
            
             for (int i = 0; i < howToPlay.buttonsArray.size(); i++) 
            {
                if (howToPlay.buttonsArray.get(i).boundary.contains(new Point(x1, y1)))
                {
                    if(howToPlay.buttonsArray.get(i).id == 3){
                    game.gameState = 1;
                    }
                    if(howToPlay.buttonsArray.get(i).id == 4){
                    game.gameState=0;
                    }
                    if(howToPlay.buttonsArray.get(i).id == 5 ){
                        System.exit(0);
                    }
                }
            }
              for (int i = 0; i < win.buttonsArray.size(); i++) 
            {
                if (win.buttonsArray.get(i).boundary.contains(new Point(x1, y1)))
                {
                    
                    if(win.buttonsArray.get(i).id == 6){
                    game.gameState=0;
                    game.levelCounter = 1;
                    }
                    if(win.buttonsArray.get(i).id == 7 ){
                        System.exit(0);
                    }
                }
            }

    }

    @Override
    public void mouseReleased(MouseEvent e) {        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
