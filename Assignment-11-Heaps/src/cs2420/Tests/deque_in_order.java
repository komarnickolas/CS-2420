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
public class deque_in_order extends Task<Integer> {

    public deque_in_order() throws IOException {
        Timing.heap_deque_in_order_fw = new FileWriter("Heap Deque In Order.txt");
        Timing.heap_deque_in_order_pw = new PrintWriter( Timing.heap_deque_in_order_fw);
        Timing.heap_deque_in_order_pw.println("Size;Time");
        this.updateTitle("deque_in_order");
    }
    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        for(int k = 0; k<Timing.MAX; k++){
            heap.add(k);
        }
        int progress = 0;
        for (int i = Timing.MAX; i > 0 ; i-=Timing.COUNT) {
            updateProgress(progress, Timing.MAX);
            this.updateMessage("Running... " + i +"/"+Timing.MAX);
            Long start = System.nanoTime();
            try {
                heap.dequeue();
            }catch (Exception e){
                e.printStackTrace();
            }
            Long end = System.nanoTime() - start;
            Timing.heap_deque_in_order_pw.println(i+";"+end);
            progress++;
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX, Timing.MAX);
        Timing.heap_deque_in_order_pw.close();
        return null;
    }
}
