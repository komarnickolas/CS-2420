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
public class build_from_array_random extends Test {
    public build_from_array_random() throws IOException {
        super();
        setWriter("Build From Array Random.txt");
        println("Size;Time");
        this.updateTitle("build_from_array_random");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        Random rng = new Random();
        for(int i = 1; i< Timing.MAX; i+=Timing.COUNT){
            updateProgress(i,Timing.MAX);
            this.updateMessage(i +"/"+Timing.MAX);
            setNum_of_runs(5);
            for(int k = 0; k<getNum_of_runs(); k++) {
                Integer[] array = new Integer[i];
                for (int j = 0; j < i; j++) {
                    array[j] = rng.nextInt(Timing.MAX);
                }
                start();
                heap.build_heap_from_array(array);
                end();
            }
            println(i+";"+getTotal());
        }
        updateMessage("Done");
        updateProgress(Timing.MAX, Timing.MAX);
        close();
        return null;
    }
}
