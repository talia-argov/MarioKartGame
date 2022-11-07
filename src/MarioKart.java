public class MarioKart {

        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public String name;               //name of the hero
        public int xpos;                  //the x position
        public int ypos;                  //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;                 //the width of the hero image
        public int height;                //the height of the hero image
        public boolean isAlive;           //a boolean to denote if the hero is alive or dead


        //This is a constructor that takes 3 parameters.
        // This allows us to specify the hero's name and position when we build it.
        public MarioKart (String pName, int pXpos, int pYpos) { // MarioKart constructor
            name = pName;
            xpos = pXpos;
            ypos = pYpos;
            dx = 1;
            dy = 1;
            width = 60;
            height = 60;
            isAlive = true;

        } // end MarioKart constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() { // move
            xpos = xpos + dx;
            ypos = ypos + dy;

        } // end move

        public void bounce(){
            xpos = xpos + dx;
            ypos = ypos + dy;

            // if MarioKart hits the right side, reverse dx direction
            if (xpos == 1000-width) {
                dx = -dx;

                if (xpos==0-width){
                    dx = -dx;
                }

                }

            }

    public void wrap() {

        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos == 1000){
            xpos = 0;
        }
    }
}



