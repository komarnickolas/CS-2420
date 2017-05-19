package cs2420.Tests;

import Tests.Test;
import cs2420.Heap;
import cs2420.Timing;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressIndicator;

import java.io.IOException;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/13/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class deque_in_order extends Test {

    public deque_in_order() throws IOException {
        super();
        setWriter("Heap Deque In Order.txt");
        println("Size;Time");
        this.updateTitle("deque_in_order");
    }
    @Override
    protected Integer call() throws Exception {
        Heap<Integer> heap = new Heap<>();
        for(int k = 0; k<Timing.MAX; k++){
            heap.add(k);
        }
        int progress = 0;
        for (int i = Timing.MAX; i > 0 ; i-=Timing.COUNT) {
            updateProgress(progress);
            start();
            try {
                heap.dequeue();
            }catch (Exception e){
                e.printStackTrace();
            }
            end();
            println(i+";"+getTotal());
            updateData(new XYChart.Data(i,getTotal()/5), "Time");
            progress++;
        }
        return null;
    }
}
