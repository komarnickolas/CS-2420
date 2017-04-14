package cs2420.Tests;

import cs2420.Heap;
import cs2420.Timing;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class insertion_random extends Task<Integer>{

    public insertion_random() throws IOException {
        Timing.heap_insertion_random_fw = new FileWriter("Heap Insertion Random.txt");
        Timing.heap_insertion_random_pw = new PrintWriter(Timing.heap_insertion_random_fw);
        Timing.heap_insertion_random_pw.println("Size;Time");
        this.updateTitle("insertion_random");
    }

    @Override
    protected Integer call() throws Exception {
        this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
        this.updateMessage("Running...");
        Heap<Integer> heap = new Heap<>();
        Random random = new Random();
        for (int i = 0; i <= Timing.MAX; i+=Timing.COUNT) {
            updateProgress(i, Timing.MAX);
            long total = 0;
            for(int j = 0; j<5; j++) {
                this.updateMessage("Running... " + i + "/" + Timing.MAX);
                Long start = System.nanoTime();
                heap.add(random.nextInt(Timing.MAX));
                Long end = System.nanoTime() - start;
                total += end;
            }
            total /= 5;
            Timing.heap_insertion_random_pw.println(i+";"+total);
        }
        this.updateMessage("Done");
        this.updateProgress(Timing.MAX, Timing.MAX);
        Timing.heap_insertion_random_pw.close();
        return null;
    }
}
