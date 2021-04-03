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
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**This class sets up a jframe, on which a jpanel is later placed to show the game
 *
 * @author matt
*/ 
public class BomberMan extends JFrame{
// these fields are used in the GamePanel class instead
    public Graphics2D graphics;
    private boolean gameRunning = true;
    /**
     * The list of all the entities that exist in the game
     */
    private ArrayList entities = new ArrayList();
    /**
     * The list of entities that need to be removed from the grid, for resetting
     * after a game over
     */
    private ArrayList removeList = new ArrayList();
    /**
     * The speed at which the player should move (pixels/sec)
     */
    private double moveSpeed = 300;

    /**
     * The time at which the last bomb is dropped for one player
     */
    private long lastBomb = 0;
    /**
     * The minimum interval before each bomb drop for each player (ms)
     */
    private long bombPlaceInterval = 500;

    private boolean waitingForKeyPress = true;

    private boolean leftPressed = false;

    private boolean rightPressed = false;
    /**
     * bombPressed becomes true when one player drops a bomb
     */
    private boolean bombPressed = false;
    /**
     * The number of key presses after pressing any key to begin the game
     */
    private int pressCount = 1;

  // private BufferedImage image = BufferedImage(800, 800, BufferedImage.TYPE_INT_BGR);

    
    public BomberMan(){
        // create a frame to contain our game
        //JFrame container = new JFrame("Bomber Man");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setContentPane(new GamePanel(800,800));
      
     
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        // get hold the content of the frame and set up the resolution of the game
       // JPanel panel = (JPanel) container.getContentPane();
       // panel.setPreferredSize(new Dimension(800, 600));
      //  panel.setLayout(null);

        // setup our canvas size and put it into the content of the frame
       
      //  setBounds(0, 0, 800, 800);
       // panel.add(container);

        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        setIgnoreRepaint(true);
        
    }
}
        // finally make the window visible 
      //  container.pack();
       // container.setResizable(false);
       // container.setVisible(true);

        // add a listener to respond to the user closing the window. If they
        // do we'd like to exit the game
     //   container.addWindowListener(new WindowAdapter() {
         //   @Override
        //    public void windowClosing(WindowEvent e) {
                //System.exit(0);
            
     //   });

      //  addKeyListener(new KeyInputHandler());

        // create the buffering strategy which will allow AWT
        // to manage our accelerated graphics
    

    