package Testing;

import javafx.concurrent.Task;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by komar on 12/30/2016.
 */
public class AmoebasTest {
    HashMap<String, HashMap<Integer, Integer>> series;
    private Jiggler jiggler;

    public AmoebasTest(ProgressForm progressForm) {
        series = new HashMap<>();
        jiggler = new Jiggler(new JLabel("FPS: 0"));
        progressForm.activateProgressBar(task1);
        progressForm.getDialogStage().show();
        Thread thread = new Thread(task1);
        thread.run();
    }

    private Task<HashMap<String, HashMap<Integer, Integer>>> task1 = new Task<HashMap<String, HashMap<Integer, Integer>>>() {
        @Override
        public HashMap<String, HashMap<Integer, Integer>> call() throws InterruptedException, IOException {
            int total = 1000;
            updateProgress(0, total);
            HashMap<Integer, Integer> temp = new HashMap<>();
            JFrame frame = new JFrame();
            JPanel panel = new JPanel(null);
            frame.add(panel);
            for (int i = 100; i <= total; i+=100) {
                jiggler.createCircles(i,panel);
                frame.setVisible(true);
                jiggler.run();
                temp.put(i, jiggler.getFrames());
                frame.setVisible(false);
                frame = new JFrame();
                panel = new JPanel(null);
                frame.add(panel);
                updateProgress(i, total);
            }
            updateProgress(total,total);
            series.put("Series1", temp);
            return series;
        }
    };
}
