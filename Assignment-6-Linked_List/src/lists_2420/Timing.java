package lists_2420;


import javafx.beans.property.SimpleStringProperty;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Timing{
    public static void main(String[] args){
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int max = 1000000;

    public static void test() throws IOException {
        Linked_List_2420<Integer> linked_list_2420 = new Linked_List_2420<>();
        Array_List_2420 array_list_2420 = new Array_List_2420();
        FileWriter fw1 = new FileWriter("Linked List Timing.txt");
        FileWriter fw2 = new FileWriter("Array List Timing.txt");
        FileWriter fw3 = new FileWriter("Array vs Linked List Timing.txt");
        PrintWriter pr1 = new PrintWriter(fw1);
        PrintWriter pr2 = new PrintWriter(fw2);
        PrintWriter pr3 = new PrintWriter(fw3);
        pr1.println("Linked List");
        pr1.println("First;Middle;Last;Remove");
        pr2.println("Array List");
        pr2.println("First;Middle;Last;Remove");
        pr3.println("Array vs Linked");
        pr3.println("Size;Linked_Start;Array_Start;Linked_Last;Array_Last");
        for(int i = 10; i<max; i*=10){
            long start = System.nanoTime();
            for(int j = 0; j<i; j++) {
                linked_list_2420.add_first((int) Math.random() * 50);
            }
            long end_first_linked = System.nanoTime() - start;
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                array_list_2420.add_first((int) Math.random() * 50);
            }
            long end_first_array= System.nanoTime() - start;
            linked_list_2420.clear();
            array_list_2420.clear();
            start = System.nanoTime();
            for(int j = 0; j<i; j++) {
                linked_list_2420.add_last((int) Math.random() * 50);
            }
            long end_last_linked = System.nanoTime() - start;
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                array_list_2420.add_last((int) Math.random() * 50);
            }
            long end_last_array= System.nanoTime() - start;
            pr3.println(""+i+";" + end_first_linked +";" + end_first_array +";"+end_last_linked+";"+end_last_array );
            System.out.println(i);
        }
        pr3.close();
        linked_list_2420.clear();
        array_list_2420.clear();
        for(int i = 0; i<max; i++){
            long start = System.nanoTime();
            linked_list_2420.add_first(i);
            long end_first_linked = System.nanoTime() - start;

            start = System.nanoTime();
            linked_list_2420.add_middle((int) Math.random()*linked_list_2420.size(), i);
            long end_middle_linked = System.nanoTime() - start;

            start = System.nanoTime();
            linked_list_2420.add_last(i);
            long end_last_linked = System.nanoTime() - start;

            start = System.nanoTime();
            linked_list_2420.remove_first();
            long end_remove_linked = System.nanoTime() - start;
            linked_list_2420.add_first(i);

            start = System.nanoTime();
            array_list_2420.add_first(i);
            long end_first_array = System.nanoTime() - start;

            start = System.nanoTime();
            array_list_2420.add_middle((int) Math.random()*array_list_2420.size(), i);
            long end_middle_array = System.nanoTime() - start;

            start = System.nanoTime();
            array_list_2420.add_last(i);
            long end_last_array = System.nanoTime() - start;

            start = System.nanoTime();
            array_list_2420.remove_first();
            long end_remove_array = System.nanoTime() - start;
            array_list_2420.add_first(i);

            if(i % 10 == 0) {
                TimingData linked = new TimingData(end_first_linked, end_middle_linked, end_last_linked, end_remove_linked);
                pr1.println(linked.getFirst()+";"+linked.getMiddle()+";"+linked.getLast() + ";" + linked.getRemove());
                TimingData array = new TimingData(end_first_array, end_middle_array, end_last_array, end_remove_array);
                pr2.println(array.getFirst()+";"+array.getMiddle()+";"+array.getLast() + ";" + array.getRemove());
                System.out.println(i);
            }
        }
        pr1.close();
        pr2.close();
    }

    public static class TimingData{
        private String first, middle, last, remove;
        public TimingData(Long first, Long middle, Long last, Long remove){
            this.first = ""+first;
            this.middle = ""+middle;
            this.last = ""+last;
            this.remove = ""+remove;
        }

        public String getFirst() {
            return first;
        }

        public String getMiddle() {
            return middle;
        }


        public String getLast() {
            return last;
        }


        public String getRemove() {
            return remove;
        }
    }
}
