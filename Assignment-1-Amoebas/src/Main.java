import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 01/12/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Main {
    private static Jiggler jiggler;
    private static JPanel canvas;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        Insets insets = frame.getInsets();
        canvas = new JPanel(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu view = new JMenu("View");
        JMenuItem size = new JMenuItem("Number of circles");
        JButton stop = new JButton("Stop");
        JButton start = new JButton("Start");
        JLabel fps = new JLabel("FPS: 0");
        jiggler = new Jiggler(fps);
        size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noc = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Circles"));
                canvas.removeAll();
                jiggler.createCircles(noc, canvas);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jiggler.stopSpreadingOut();
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jiggler.startSpreadingOut();
            }
        });
        menuBar.add(fps);
        menuBar.add(view);
        view.add(size);
        menuBar.add(start);
        menuBar.add(stop);
        frame.add(menuBar);
        Dimension dimension = menuBar.getPreferredSize();
        menuBar.setBounds(insets.left, insets.top, 800, dimension.height);
        frame.add(canvas);
        canvas.setBounds(insets.left,dimension.height+insets.top, 900,700);
        frame.setSize(800 + insets.left + insets.right, 600 + insets.top + insets.bottom);
        jiggler.createCircles(50, canvas);
        frame.setVisible(true);
        jiggler.run();
    }
}
