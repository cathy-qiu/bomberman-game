/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private boolean running = false;
private BufferedImage img;
private Graphics2D g;
public KeyHandler key;
private GameStateManager gsm;

//just in case we figure out how to make the game resizable
    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();  
        
    }
  

     public void init() {
   running = true;
   img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   g = (Graphics2D) img.getGraphics();
   
   
   gsm = new GameStateManager();
    }
    

    public void run() {
        init();
        //game updates 30 hertz per second
        final double UPDATE_SPEED = 30.0;
        //Time before update
        final double BEFORE_UPDATE =  1000000000/UPDATE_SPEED;
        final double TARGET_FPS = 60;
        //Total time before render
        final double TTBR = 1000000000/TARGET_FPS;
        //Must update before render
        final int MUBR = 5;
        //Time of last update
        double lastUpdateTime = System.nanoTime();
        
        double lastRenderTime;
        
        int oldFrameCount = 0;
        int frameCount = 0;
        int lastTimeSeconds;
        
        
        
        while (running) {
            
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > BEFORE_UPDATE) && (updateCount < MUBR)) {
                update();
                input(key);
                lastUpdateTime += BEFORE_UPDATE;
                updateCount ++;
            }
            //update has occured when if statement is true
            if(now - lastUpdateTime > BEFORE_UPDATE) {
                lastUpdateTime = now - BEFORE_UPDATE;
            }
            input(key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;
            
            int thisSecond = (int) (lastUpdateTime /  1000000000);
            if(thisSecond > lastUpdateTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("New time" + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastTimeSeconds= thisSecond;
            }
            

        }
    }
   
    private final int x = 0;
    
    public void update() {
        gsm.update();
    }
    
    public void input(KeyHandler key){
        gsm.input(key);

    }
    public void render(){
        g.fillRect(0,0,width,height);
        gsm.render(g);
    }
    public void draw() {
        
        Graphics g = (Graphics) this.getGraphics();
        g.drawImage(img, 0,0,width,height,null);
        g.dispose();
        
    }
}
