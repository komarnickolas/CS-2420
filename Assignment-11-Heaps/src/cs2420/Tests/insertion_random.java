package cs2420.Tests;

import cs2420.Heap;
import cs2420.Timing;
import javafx.scene.control.ProgressIndicator;

import java.io.IOException;
import java.util.Random;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/13/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class insertion_random extends Test{


    public insertion_random() throws IOException {
        super();
        setWriter("Heap Insertion Random.txt");
        println("Size;Time");
        this.updateTitle("insertion_random");
    }

    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        Random random = new Random();
        setNum_of_runs(5);
        for (int i = 0; i <= Timing.MAX; i+=Timing.COUNT) {
            updateProgress(i, Timing.MAX);
            this.updateMessage("Running... " + i +"/"+Timing.MAX);
            for(int k = 0; k<getNum_of_runs(); k++) {
                start();
                heap.add(random.nextInt(Timing.MAX));
                end();
            }
           println(i + ";" + getTotal());
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX, Timing.MAX);
        close();
        return null;
    }
}
