package Testing;

import Testing.AnagramUtil.AnagramUtil;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/6/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Test extends Thread {

    HashMap<String, ArrayList<String>> series;

    public Test(){
        series = new HashMap<>();
        series.put("Series 5", task2());
        System.out.println("Task 5 Done");
    }

    public ArrayList<String> task1() {
            int total = 1000;
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i <= total; i++) {
                String randomString1 = AnagramUtil.randomString((int) Math.random()*30);
                String randomString2 = AnagramUtil.randomString((int) Math.random()*30);
                long start = System.nanoTime();
                AnagramUtil.areAnagrams(randomString1,randomString2);
                long end = System.nanoTime() - start;
                if(i%10 == 0) {
                    temp.add(i + " 0." + end);
                }
            }
            return temp;
    }

    public ArrayList<String> task2() {
            int total = 10000;
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i <= total; i++) {
                String[] anagrams = new String[i];
                for(int j = 0; j<anagrams.length; j++){
                    anagrams[j] = AnagramUtil.randomString((int) Math.random()*15);
                }
                long start = System.nanoTime();
                AnagramUtil.getLargestAnagramGroup(anagrams);
                long end = System.nanoTime() - start;
                if(i%10 == 0) {
                    temp.add(i + " 0." + end);
                }
            }
            return temp;
    }
    public ArrayList<String> task3() {
        int total = 10000;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i <= total; i++) {
            String[] anagrams = new String[i];
            for(int j = 0; j<anagrams.length; j++){
                anagrams[j] = AnagramUtil.randomString((int) Math.random()*15);
            }
            long start = System.nanoTime();
            AnagramUtil.insertionSort(anagrams,null);
            long end = System.nanoTime() - start;
            if(i%10 == 0) {
                temp.add(i + " 0." + end);
            }
        }
        return temp;
    }
    public ArrayList<String> task4() {
        int total = 10000;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i <= total; i++) {
            Integer[] integers = new Integer[i];
            for(int j = 0; j<integers.length; j++){
                integers[j] = (int) Math.random()*100;
            }
            long start = System.nanoTime();
            AnagramUtil.insertionSort(integers, null);
            long end = System.nanoTime() - start;
            if(i%10 == 0) {
                temp.add(i + " 0." + end);
            }
        }
        return temp;
    }
}
