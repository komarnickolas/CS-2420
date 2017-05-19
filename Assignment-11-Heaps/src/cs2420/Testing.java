package cs2420;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/7/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Testing {
    // sample simple Test you can use
    @Test
    public void test_basic_insertion()
    {
        Heap<Integer> integerHeap = new Heap<>();

        integerHeap.add( 5 );
        integerHeap.add( 6 );
        integerHeap.add( 3 );
        integerHeap.add( 7 );
        integerHeap.add( 8 );
        integerHeap.add( 1 );

        assertEquals(6, integerHeap.size());

        Object[] temp = integerHeap.toArray();

        assertArrayEquals(new Integer[]{null, 1,6,3,7,8,5}, temp);

        // if you want to look at your heap, uncomment this line to generate a graph file,
        //
//               heap.generateDotFile("Documents/test_heap.dot");
        //
        // or uncomment this line, run the tests:
        //
        //       System.out.println(heap);
        //
        // and then paste the output of the console into: http://www.webgraphviz.com/

        Heap<String> stringHeap = new Heap<>();
        stringHeap.add("String 1");
        stringHeap.add("String 2");
        stringHeap.add("String 3");

        assertEquals(3,stringHeap.size());
    }


    // sample advanced Test you might want to implement
    @Test
    public void test_lots_of_insertions_deletions_peeks()
    {
        Heap<Integer> heap = new Heap<>();

        final int COUNT = 1000;
        Random generator = new Random();

        for(int i = 0; i< COUNT; i++){
            heap.add(generator.nextInt(COUNT));
        }

        assertEquals(COUNT, heap.size());

        heap.generateDotFile("Documents/large_heap.dot");

        int smallest = heap.dequeue();

        //           remove one, make sure it is larger than smallest, update smallest
        while (heap.size() != 0){
            int temp = heap.dequeue();
            assertTrue(temp >= smallest);
            smallest = temp;
        }
    }

    @Test
    public void test_build_from_array(){
        Heap<Integer> heap = new Heap<>();
        heap.build_heap_from_array(new Integer[]{1,6,3,7,8,5});
        heap.build_heap_from_array(new Integer[]{1,2,3});
        heap.generateDotFile("Documents/build_from_array.dot");
    }
}
