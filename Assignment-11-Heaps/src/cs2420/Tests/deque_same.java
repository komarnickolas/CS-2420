package cs2420.Tests;

import cs2420.Heap;
import cs2420.Timing;
import javafx.scene.control.ProgressIndicator;

import java.io.IOException;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/14/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class deque_same extends Test {

    public deque_same() throws IOException {
        super();
        setWriter("Heap Deque Same.txt");
        println("Size;Time");
        this.updateTitle("deque_same");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        for(int k = 0; k<Timing.MAX; k++){
            updateValue(k);
            heap.add(Timing.COUNT);
        }
        int progress = 0;
        for (int i = Timing.MAX; i >0; i-=Timing.COUNT) {
            updateProgress(progress, Timing.MAX);
            this.updateMessage("Running... " + i +"/"+Timing.MAX);
            start();
            try {
                heap.dequeue();
            }catch (Exception e){
                e.printStackTrace();
            }
            end();
            println(i+";"+getTotal());
            progress++;
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX, Timing.MAX);
        close();
        return null;
    }
}
