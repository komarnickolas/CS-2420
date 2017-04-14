package cs2420.Tests;

import cs2420.Heap;
import cs2420.Timing;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/13/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class insertion_in_order extends Task<Integer> {

    public insertion_in_order() throws IOException {
        Timing.heap_insertion_in_order_fw = new FileWriter("Heap Insertion In Order.txt");
        Timing.heap_insertion_in_order_pw = new PrintWriter(Timing.heap_insertion_in_order_fw);
        Timing.heap_insertion_in_order_pw.println("Size;Time");
        this.updateTitle("insertion_in_order");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        for (int i = 0; i <= Timing.MAX; i+=Timing.COUNT) {
            updateProgress(i, Timing.MAX);
            this.updateMessage("Running... " + i +"/"+Timing.MAX);
            Long start = System.nanoTime();
            heap.add(i);
            Long end = System.nanoTime() - start;
            Timing.heap_insertion_in_order_pw.println(i+";"+end);
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX,Timing.MAX);
        Timing.heap_insertion_in_order_pw.close();
        return null;
    }
}
