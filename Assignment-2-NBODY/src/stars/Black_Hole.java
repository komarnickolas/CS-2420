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
public class Black_Hole extends Satellite {


    /**
     *
     * @param v
     * @param distance
     * @param v1
     * @param s
     */
    public Black_Hole(double v, double distance, double v1, String s) {
        this.position = new Geometry_Vector(distance,distance);
        this.velocity = new Geometry_Vector(v,v1);
        setName(s);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(0,0,getWidth(),getHeight());
    }

}
