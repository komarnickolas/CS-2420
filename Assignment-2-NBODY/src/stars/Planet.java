package stars;

import javafx.scene.control.*;

import java.awt.*;
import java.awt.Label;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/19/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Planet extends Satellite {

    double distance;

    /**
     *
     * @param distance - distance from the sun
     * @param i - velocity
     * @param i1 - velocity
     * @param speed - speed planet is moving
     * @param mass - mass of the planet
     * @param radius - radius of the planet
     * @param name - name of the planet
     *
     * Creates a new planet
     */
    public Planet(double distance, int i, int i1, double speed, double mass, double radius, String name) {
        super();
        setName(name);
        this.distance = distance;
        this.mass = mass;
        this.radius = radius;
        velocity = new Geometry_Vector(i, i1);
        position = new Geometry_Vector(this.distance, this.distance);
        setSize((int) this.radius, (int) this.radius);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
