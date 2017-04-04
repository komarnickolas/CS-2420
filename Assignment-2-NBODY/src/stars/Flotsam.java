package stars;

import java.awt.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/19/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Flotsam extends Satellite {

    /**
     *
     * @param x - location
     * @param y - location
     * @param i - velocity
     * @param i1 - velocity
     *
     * creates a new flotsam object
     */
    public Flotsam(int x, int y, int i, int i1) {
        super();
        setName("Flotsam");
        setForeground(Color.GRAY);
        position = new Geometry_Vector(x,y);
        velocity = new Geometry_Vector(i,i1);
        this.radius = 2000;
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(0,0,getWidth(),getHeight());
    }
}
