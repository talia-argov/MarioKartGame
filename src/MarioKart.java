import java.awt.*;

public class MarioKart {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx = 1;                    //the speed of the hero in the x direction
    public int dy = 1;                   //the speed of the hero in the y direction
    public int width;                   //the width of the hero image
    public int height;                 //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rec;


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public MarioKart(String pName, int pXpos, int pYpos) { // MarioKart constructor
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        xpos = (int)(Math.random()*400+100);
        ypos = (int)(Math.random()*150+50);
        dx = 1;
        dy = -1;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

        System.out.println(xpos + " " + ypos);

    } // end MarioKart constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move

    public void bounce() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        // if MarioKart hits the right side, reverse dx direction
        if (xpos >= 1000 - width || xpos <= 0) { //right and left wall
            dx = -dx;
        }
        if (ypos <= 0 - height || ypos >= 700 - height) {
            dy = -dy;
        }
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void wrap() {

        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000 && dx > 0) { // right wall
            xpos = 0;
        }
        if (xpos <= -width && dx < 0) { //left wall
            xpos = 1000;
        }
        if (ypos >= 700 && dy > 0) { //bottom wall
            System.out.println(-height);
            ypos = -height;
        }
        if (ypos <= -height && dy < 0) { //top wall
            System.out.println("high");
            ypos = 700;
        }

        System.out.println(ypos);

        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void grow(){
        width = (int)(width*1.1);
        height = (int)(height*1.1);
    }
    }




