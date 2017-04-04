import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 01/12/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Jiggler extends Thread{

    private boolean spread_out;
    private JLabel fps;
    private JPanel panel;
    private int frames;

    public Jiggler(JLabel fps){
        spread_out = true;
        this.fps = fps;

    }

    public void checkOverlapping(Component component, Component[] components){
            Circle circle = (Circle) component;
            for(Component c : components){
                Circle temp = (Circle) c;
                if(circle.overlaping(temp) && !circle.equals(temp)){
                    if(spread_out) {
                        move(circle);
                        panel.updateUI();
                    }
                }
            }
    }

    public void move(Circle circle){
        int x = circle.getXValue();
        int y = circle.getYValue();
        int direction = (int) ((Math.random() * 100) + 1);
        switch (direction % 2) {
            case 0:
                if(x+1 <= 800) {
                    if (y + 1 <= 550) {
                        circle.setLocation(x+1, y + 1);
                    }else{
                        circle.setLocation(x + 1, y);
                    }
                }
                break;
            case 1:
                if(x-1 >= 0) {
                    if (y - 1 >= 0) {
                        circle.setLocation(x-1, y - 1);
                    }else{
                        circle.setLocation(x - 1, y);
                    }
                }
                break;
        }
    }

    public void createCircles(int numberOfCircles, JPanel panel){
        this.panel = panel;
        while(numberOfCircles != 0){
            double x = Math.random() * 750;
            double y = Math.random() * 550 + 50;
            double width = Math.random() * 60 + 10;
            double height = Math.random() * 50 + 10;
            Circle circle = new Circle(x +panel.getInsets().left, y + panel.getInsets().top, width,height);
            panel.add(circle);
            panel.revalidate();
            numberOfCircles--;
        }
        panel.updateUI();
    }

    @Override
    public void run(){
        frames = 0;
        long startTime = System.nanoTime();
        float currentTime;
        while(true) {
            if(spread_out) {
                for (Component component : panel.getComponents()) {
                    checkOverlapping(component, panel.getComponents());
                    frames++;
                }
                currentTime = (System.nanoTime() - startTime) / 1000000000f;
                if (currentTime >= 1) {
                    fps.setText("FPS: " + frames);
                    frames = 0;
                    startTime = System.nanoTime();
                }
            }
        }
    }

    public void stopSpreadingOut(){
        spread_out = false;
    }

    public void startSpreadingOut(){
        spread_out = true;
    }

    public int getFrames(){
        return frames;
    }

}
