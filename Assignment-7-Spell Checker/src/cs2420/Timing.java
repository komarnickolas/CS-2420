package cs2420;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/24/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Timing {
    public static void main(String[] args){
        try{
            testHeight();
//            test();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static int max = 1000000000;

    public static void testHeight() throws IOException{
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        FileWriter fw1 = new FileWriter("Binary Search Tree.txt");
        PrintWriter pr1 = new PrintWriter(fw1);
        pr1.println("Size;Height Random;Height LogN");
        for(int i = 0; i<max; i+= 100){
            ArrayList<Integer> items = new ArrayList<>();
            for(int j = 0; j<i; j++){
                items.add(j);
            }
            Collections.shuffle(items);
            for (int j = 0; j < items.size(); j++) {
                bst.add(items.get(j));
            }
            pr1.println(""+i+";"+bst.root.height() + ";" + Math.log(i)/Math.log(2)+";");
            bst.clear();
            System.out.println(i);
        }
        pr1.close();
    }

    /**
     * Test and record timing data for our Binary Search Tree
     * @throws IOException
     */
    public static void test() throws IOException{
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        FileWriter fw1 = new FileWriter("Binary Search Tree.txt");
        FileWriter fw2 = new FileWriter("BST.txt");
        FileWriter fw3 = new FileWriter("TreeSet.txt");
        PrintWriter pr1 = new PrintWriter(fw1);
        PrintWriter pr2 = new PrintWriter(fw2);
        PrintWriter pr3 = new PrintWriter(fw3);
        pr1.println("Binary Search Tree");
        pr1.println("Size;Ordered;Random;Ordered_Contains;Random_Contains");
        pr2.println("BST");
        pr2.println("Size;Add_Ordered;Add_Random;Remove_Ordered;Remove_Random;Contains_Ordered;Conrains_Random");
        pr3.println("TreeSet");
        pr3.println("Size;Add_Ordered;Add_Random;Remove_Ordered;Remove_Random;Contains_Ordered;Conrains_Random");
        for(int i = 0; i<max; i++){
            ArrayList<Integer> items = new ArrayList<>();
            for(int j = 0; j<i; j++){
                items.add(j);
            }

            // BST TESTS

            //Ordred Tests
            //Add Ordered
            Long start = System.nanoTime();
            for(int j = 0; j<items.size(); j++){
                bst.add(items.get(j));
            }
            long end_ordered = System.nanoTime() - start;

            //Contains Ordered
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                bst.contains(j);
            }
            long end_ordered_contains = System.nanoTime() - start;

            //Ordered Remove
            start = System.nanoTime();
            for(int j = i-1; j>=0; j--){
                bst.remove(j);
            }
            long end_ordered_remove = System.nanoTime() - start;

            bst.clear();
            //Random Tests
            //Add Random
            long end_random = 0;
            for(int run_times = 0; run_times<5; run_times++) {
                Collections.shuffle(items);
                start = System.nanoTime();
                for (int j = 0; j < items.size(); j++) {
                    bst.add(items.get(j));
                }
                long end = System.nanoTime() - start;
                end_random += end;
                bst.clear();
            }
            end_random /= 4;

            //Contains Random
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                bst.contains(j);
            }
            long end_random_contains = System.nanoTime() - start;

            //Remove Random
            start = System.nanoTime();
            for(int j = i-1; j>=0; j--){
                bst.remove(j);
            }
            long end_random_remove = System.nanoTime() - start;

            bst.clear();


            //TREE SET TESTS

            //Ordred Tests
            //Add Ordered
            start = System.nanoTime();
            for(int j = 0; j<items.size(); j++){
                treeSet.add(items.get(j));
            }
            long end_ordered_tree = System.nanoTime() - start;

            //Contains Ordered
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                treeSet.contains(j);
            }
            long end_ordered_contains_tree = System.nanoTime() - start;

            //Remove Ordered
            start = System.nanoTime();
            for(int j = i-1; j>=0; j--){
                treeSet.remove(j);
            }
            long end_ordered_remove_tree = System.nanoTime() - start;

            treeSet.clear();

            //Random Tests
            //Add Random
            long end_random_tree = 0;
            for(int run_times = 0; run_times<5; run_times++) {
                Collections.shuffle(items);
                start = System.nanoTime();
                for (int j = 0; j < items.size(); j++) {
                    treeSet.add(items.get(j));
                }
                long end = System.nanoTime() - start;
                end_random_tree += end;
                treeSet.clear();
            }
            end_random_tree /= 4;

            //Contains Random
            start = System.nanoTime();
            for(int j = 0; j<i; j++){
                treeSet.contains(j);
            }
            long end_random_contains_tree = System.nanoTime() - start;

            //Remove Random
            start = System.nanoTime();
            for(int j = i-1; j>=0; j--){
                treeSet.remove(j);
            }
            long end_random_remove_tree = System.nanoTime() - start;

            treeSet.clear();

            if(i % 10 == 0) {
                pr1.println("" + i + ";" + end_ordered + ";" + end_random + ";" + end_ordered_contains + ";" + end_random_contains);
                pr2.println("" + i + ";" + end_ordered + ";" + end_random + ";" + end_ordered_remove+ ";" + end_random_remove + ";" + end_ordered_contains + ";" + end_random_contains);
                pr3.println("" + i + ";" + end_ordered_tree + ";" + end_random_tree + ";" + end_ordered_remove_tree+ ";" + end_random_remove_tree + ";" + end_ordered_contains_tree + ";" + end_random_contains_tree);
                System.out.println(i);
            }
        }
        pr1.close();
        pr2.close();
        pr3.close();
    }

}
