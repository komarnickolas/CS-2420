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
public class Star extends Satellite {


    /**
     *
     * @param x - location
     * @param y - location
     * @param i - velocity
     * @param i1 - velocity
     * @param sun_mass - size of the sun
     * @param sun_radius - radius of the sun
     * @param name - name of the sun
     *
     *  Creates a new star that stays in place
     */
    public Star(double x, double y, int i, int i1, double sun_mass, double sun_radius, String name) {
        super();
        this.position = new Geometry_Vector(x,y);
        this.velocity = new Geometry_Vector(i,i1);
        this.mass = sun_mass;
        this.radius = sun_radius;
        setName(name);
    }

    @Override
    public void update_position(double dt){}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(0,0,getWidth(),getHeight());
    }

    @Override
    public void update_velocity(Geometry_Vector acceleration, double dt) {}

    @Override
    public void update_display_size(double system_radius){
        setSize(50,50);
    }
}
