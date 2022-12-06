import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image shyguyPic;
    public Image toadPic;
    public Image bananaPic;
    public Image backgroundPic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public MarioKart shyguy;
    public MarioKart toad;
    public MarioKart banana;

    public int growthfactor=10;
    public int angle=1;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        shyguyPic = Toolkit.getDefaultToolkit().getImage("shyguy2.png"); //load the picture
        shyguy = new MarioKart("shyguy",100,100); //construct

        toadPic = Toolkit.getDefaultToolkit().getImage("toad.png");
        toad = new MarioKart("toad",150,600);

        bananaPic = Toolkit.getDefaultToolkit().getImage("banana2.png");
        banana = new MarioKart("banana",100,200);

        backgroundPic = Toolkit.getDefaultToolkit().getImage("marioKartbackground.png");

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            crash();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //calls the move( ) code in the objects
        shyguy.move();
        toad.move();
        banana.move();
        shyguy.bounce();
        toad.bounce();
        banana.wrap();
    }

    public void crash(){
        //if shyguy and toad intersect
        if(shyguy.rec.intersects(toad.rec)&& shyguy.isCrashing == false){
            System.out.println("CRASH");
            shyguy.isCrashing = true;
            shyguy.width = shyguy.width+growthfactor;
            shyguy.height = shyguy.height+growthfactor;
            shyguy.dx = -shyguy.dx;
            toad.dx = -toad.dx;
            if(shyguy.width>=100){
                growthfactor=growthfactor*-1;
            }
            if(shyguy.width<=50){
                growthfactor=growthfactor*-1;
            }
        }
        if(!shyguy.rec.intersects(toad.rec)){
            shyguy.isCrashing = false;
        }

        if (shyguy.rec.intersects(toad.rec)){
            shyguy.dy =  -shyguy.dy;
            toad.dy = -toad.dy;
        }

      //  if(toad.rec.intersects(banana.rec)){
            //toad and shyguy spin when it crashes with banana
    //}


       // if(shyguy.rec.intersects(toad.rec)){
         //   shyguy.grow()
      //  }
    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

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

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the astronaut

        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it

        g.drawImage(backgroundPic,0,0,WIDTH,HEIGHT,null);

        g.drawImage(shyguyPic, shyguy.xpos, shyguy.ypos, shyguy.width, shyguy.height, null);
        g.setColor(new Color(20,100,200));
   //     g.drawRect(shyguy.rec.x, shyguy.rec.y, shyguy.rec.width, shyguy.rec.height);

        g.drawImage(toadPic, toad.xpos, toad.ypos,toad.width, toad.height, null);

        angle++;

     //   g.drawRect(toad.rec.x, toad.rec.y, toad.rec.width, toad.rec.height);

        g.drawImage(bananaPic, banana.xpos, banana.ypos, banana.width,banana.height, null);
       // g.drawRect(banana.rec.x, banana.rec.y, banana.rec.width, banana.rec.height);


        g.dispose();
        bufferStrategy.show();
        }
    }