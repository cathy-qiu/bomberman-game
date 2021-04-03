/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class GameStateManager {
    
public ArrayList<GameState> states;
public GameStateManager() {
states = new ArrayList<GameState> ();
states.add(new PlayState(this));
}


public void update() {
for(int i = 0; i < states.size(); i++){
    states.get(i).update();
}
}

public void input(KeyHandler key) {
for(int i = 0; i < states.size(); i++){
   states.get(i).input(key);
}
}

public void render(Graphics2D g) {
for(int i = 0; i < states.size(); i++){
    states.get(i).render(g);
}
}
}