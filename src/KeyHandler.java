/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Keyhandler takes key presses and toggles the move method, or changes the game
 * state to pause etc
 *
 * @author Matt
 */
public class KeyHandler implements KeyListener {

    public static ArrayList<Key> keys = new ArrayList<Key>();

    public class Key {

        public int presses, ignore;
        public boolean down, clicked;

        //adds all keys used to an array list
        public Key() {
            keys.add(this);
        }

        //helps toggle a key press
        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }

        }
        //helps toggle a key press
        public void tick() {
            if (ignore < presses) {
                ignore++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key dropBomb = new Key();
    public Key start = new Key();
    public Key pause = new Key();

    //GamePanel begins listening for key presses
    public KeyHandler(GamePanel panel) {
        panel.addKeyListener(this);
    }

    //when the paused game state is toggled, all keys are released, even if they're being physically pressed
    public void releaseAll() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).down = false;
        }
    }
    
    //tick helps update keys being pressed
    public void tick() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent k, boolean pressed) {
        if (k.getKeyCode() == KeyEvent.VK_W) {
            up.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_A) {
            down.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_S) {
            left.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_D) {
            right.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            dropBomb.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_T) {
            start.toggle(pressed);
        }
        if (k.getKeyCode() == KeyEvent.VK_P) {
            pause.toggle(pressed);
        }

    }

    @Override
    public void keyTyped(KeyEvent k) {
        //nothing is done
    }

    @Override
    public void keyPressed(KeyEvent k) {
        toggle(k, true);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        toggle(k, false);
    }

}
