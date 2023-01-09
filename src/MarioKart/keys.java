package MarioKart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class keys { //K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

    import java.awt.Graphics2D;
        import java.awt.image.BufferStrategy;
        import java.awt . *;
        import javax.swing . *;

/***
 * Step 0 for keyboard control - Import
 */
        import java.awt.event . *;

    /***
     * Step 1 for keyboard control - implements KeyListener
     */
    public class CheeseWorld implements Runnable, KeyListener {

        //Variable Definition Section

        //Sets the width and height of the program window
        final int WIDTH = 1000;
        final int HEIGHT = 700;

        //Declare the variables needed for the graphics
        public JFrame frame;
        public Canvas canvas;
        public JPanel panel;
        public BufferStrategy bufferStrategy;

        //Declare the variables needed for images
        public Image shyguyPic;
        public Image toadPic;
        public Image bananaPic;

        //Declare the character objects
        public Image shyguy1;
        public Image toad1;
        public Image banana1;

        // Main method definition
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            MarioKart myApp = new MarioKart();   //creates a new instance of the game
            new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
        }

        // Constructor Method - setup portion of the program
        // Initialize your variables and construct your program objects here.
        public MarioKeys() {

            setUpGraphics();

            /***
             * Step 3 for keyboard control - addKeyListener(this) to the canvas
             */
            canvas.addKeyListener(this);

            //load images
            shyguyPic = Toolkit.getDefaultToolkit().getImage("shyguy2.png");
            toadPic = Toolkit.getDefaultToolkit().getImage("toad.png");
            bananaPic = Toolkit.getDefaultToolkit().getImage("banana2.png");

            //create (construct) the objects needed for the game
            shyguy1 = new Shyguy(200, 300, 4, 4, shyguyPic);
            toad1 = new Toad(400, 300, 3, -4, toadPic);
            banana1 = new Banana(250, 250, 5, 5, bananaPic);

        } // CheeseWorld()
//*******************************************************************************
//User Method Section

        // main thread
        // this is the code that plays the game after you set things up
        public void moveThings() {
            shyguy1.move();
            toad1.move();
            banana1.move();
        }

        public void checkIntersections() {

        }

        public void run() {
            while (true) {
                moveThings();           //move all the game objects
                checkIntersections();   // check character crashes
                render();               // paint the graphics
                pause(20);         // sleep for 20 ms
            }
        }

        //paints things on the screen using bufferStrategy
        public void render() {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);

            //draw characters to the screen
            g.drawImage(shyguy1.pic, shyguy1.xpos, shyguy1.ypos, shyguy1.width, shyguy1.height, null);
            g.drawImage(toad1.pic, toad1.xpos, toad1.ypos, toad1.width, toad1.height, null);
            g.drawImage(banana1.pic, banana1.xpos, banana1.ypos, banana1.width, banana1.height, null);

            g.dispose();
            bufferStrategy.show();
        }

        /***
         * Step 4 for keyboard control - add required methods
         * You need to have all 3 even if you aren't going to use them all
         */
        public void keyPressed(KeyEvent event) {
            //This method will do something whenever any key is pressed down.
            //Put if( ) statements here
            char key = event.getKeyChar();     //gets the character of the key pressed
            int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
            System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

            if (keyCode == 68) { //d
                banana1.right = true;
            }
            if (keyCode == 88) { //x
                banana1.down = true;
            }
            if (keyCode == 83) {//s
                banana1.left = true;
            }
            if (keyCode == 69) { //e
                banana1.up = true;
            }
            //keyPressed()
        }

        public void keyReleased(KeyEvent event) {
            char key = event.getKeyChar();
            int keyCode = event.getKeyCode();
            //This method will do something when a key is released
            if (keyCode == 68) {
                banana1.right = false;
            }
            if (keyCode == 88) {
                banana1.down = false;
            }
            if (keyCode == 83) {//s
                banana1.left = false;
            }
            if (keyCode == 69) { //e
                banana1.up = false;
            }

        }//keyReleased()

        public void keyTyped(KeyEvent event) {
            // handles a press of a character key (any key that can be printed but not keys like SHIFT)
            // we won't be using this method, but it still needs to be in your program
        }//keyTyped()


        //Graphics setup method
        public void setUpGraphics() {
            frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");

        }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time) {
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }

    }//class
}
