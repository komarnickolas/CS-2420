package cs2420.Tests;

import cs2420.Heap;
import cs2420.Timing;
import javafx.scene.control.ProgressIndicator;

import java.io.IOException;
import java.util.Random;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/14/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class deque_random extends Test {

    public deque_random() throws IOException {
        super();
        setWriter("Heap Deque Random.txt");
        println("Size;Time");
        this.updateTitle("deque_with_random");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Random random = new Random();
        for (int i = 1; i <= Timing.MAX; i+=Timing.COUNT) {
            updateProgress(i, Timing.MAX);
            this.updateMessage("Running... " + i +"/"+Timing.MAX);
            Heap<Integer> heap = new Heap<>();
            setNum_of_runs(5);
            for(int j = 0; j<getNum_of_runs(); j++) {
                updateValue(j);
                for (int k = 0; k < i; k++) {
                    heap.add(random.nextInt(Timing.MAX));
                }
                start();
                try {
                    heap.dequeue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                end();
            }
            println(i+";"+getTotal());
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX, Timing.MAX);
        close();
        return null;
    }
}
