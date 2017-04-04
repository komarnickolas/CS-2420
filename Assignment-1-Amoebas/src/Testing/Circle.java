package Testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 01/12/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Circle extends JComponent implements MouseMotionListener,MouseListener{

    private boolean selected,fill;
    private int x,y,width,height;
    private Color color;
    private Point offset;
    private Ellipse2D ellipse;

    public Circle(double x,double y, double width, double height){
        this.x = (int) x;
        this.y = (int) y;
        this.width = (int) width;
        this.height = (int) height;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setBounds(0,0,this.width,this.height);
        setLocation(this.x,this.y);
        setSize(this.width,this.height);
        int fill = (int) (Math.random()*2)+1;
        switch (fill){
            case 1:
                this.fill = true;
                break;
            case 2:
                this.fill = false;
                break;
        }
        int color = (int) (Math.random()*2)+1;
        switch (color){
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.BLUE;
                break;
        }
    }

    public boolean overlaping(Circle circle){
        return getBounds().intersects(circle.getBounds());
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(color);
        ellipse = new Ellipse2D.Double(0,0,width,height);
        if(fill){
            graphics.fill(ellipse);
        }else {
            graphics.draw(ellipse);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (contains(e.getPoint())) {
            selected = true;
        }
        offset = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selected){
            Point mouse = e.getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(mouse,getParent());
            double newX = mouse.getX() - offset.getX();
            double newY = mouse.getY()-offset.getY();
            setLocation((int) newX + getParent().getInsets().left,(int) newY+ getParent().getInsets().top);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int getXValue(){
        return x;
    }
    public int getYValue(){
        return y;
    }
    public void setXValue(int x){this.x = x;}
    public void setYValue(int y){this.y = y;}
}
