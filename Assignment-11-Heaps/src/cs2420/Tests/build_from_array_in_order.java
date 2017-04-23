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
public class build_from_array_in_order extends Test {
    public build_from_array_in_order() throws IOException {
        super();
        setWriter("Build From Array In Order.txt");
        println("Size;Time");
        this.updateTitle("build_from_array_in_order");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        for(int i = 1; i< Timing.MAX; i+=Timing.COUNT){
            updateProgress(i,Timing.MAX);
            this.updateMessage(i +"/"+Timing.MAX);
            Integer[] array = new Integer[i];
            for(int j = 0; j<i; j++){
                array[j] = j;
            }
            start();
            heap.build_heap_from_array(array);
            end();
            println(i+";"+getTotal());
        }
        updateMessage("Done");
        updateProgress(Timing.MAX, Timing.MAX);
        close();
        return null;
    }
}
