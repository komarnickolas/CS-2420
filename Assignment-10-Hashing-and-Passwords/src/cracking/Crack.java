package cracking;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/31/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Crack{

    private static String aToz = "abcdefghijklmnopqrstuvwxyz";
    static public ArrayList<String> read_file_into_array(String file_name ){
        try(Stream<String> stream = Files.lines(Paths.get(file_name))){
            Object[] lines = stream.toArray();
            ArrayList<String> fileArray = new ArrayList<>();
            for(Object s : lines){
                fileArray.add((String)s);
            }
            return fileArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static public HashSet<String> read_file_into_hash_set(String file_name ){
        try(Stream<String> stream = Files.lines(Paths.get(file_name))){
            Object[] lines = stream.toArray();
            HashSet<String> fileSet = new HashSet<>();
            for(Object s : lines){
                fileSet.add((String)s);
            }
            return fileSet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * This method compares (hashes of) all permutations of up to "Max_Length" characters vs
     * the given list of hashes (the password file)
     *
     * @param hashes
     *            - hashes that you are seeing if you can find matches to
     * @param max_length
     *            - how many characters the passwords can be (in length)
     * @return the list of found passwords and their corresponding md5 hashes (e.g., [ "cat :
     *         d077f244def8a70e5ea758bd8352fcd8AB3293292CEF2342ACD32342" ])
     */
    static public ArrayList<String> brute_force_attack(Collection<String> hashes, int max_length ) {
        ArrayList<String> matches = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.setLength(max_length);
        brute_force_attack(hashes,matches,stringBuilder,0,max_length);
        return matches;
    }


    // recommended but not required recursive helper
    static public void brute_force_attack(Collection<String> hashes, ArrayList<String> successes, StringBuilder so_far, int depth, int max_length ) {
        String current = so_far.toString();
        String hash = null;
        try {
            hash = hash(current);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(hashes.contains(hash)){
            successes.add(current +" : "+ hash);
        }
        char[] perm = so_far.toString().toCharArray();
        if(depth == max_length){
            return;
        }else {
            for (int i = 0 ; i < aToz.length() ; i++) {
                perm[depth] = aToz.charAt(i);
                brute_force_attack(hashes,successes,new StringBuilder(new String(perm)), depth+1, max_length);
            }
        }
    }

    /**
     * compare all words in the given list (dictionary) to the password collection we wish to crack
     *
     * @param dictionary
     *            - The list of likely passwords
     * @param hashes
     *            - Collection of the hashwords we are trying to break
     * @return the list of found passwords and their corresponding md5 hashes (e.g., "cat :
     *         d077f244def8a70e5ea758bd8352fcd8AB3293292CEF2342ACD32342")
     */
    static public ArrayList<String> dictionary_attack( ArrayList<String> dictionary, Collection<String> hashes ) throws NoSuchAlgorithmException {
        ArrayList<String> matches = new ArrayList<>();
        for(String string : dictionary){
            if(hashes.contains(hash(string))){
                matches.add(string+" : "+hash(string));
            }
        }
        return matches;
    }

    /**
     * Generates a hash code of the permutation
     * @param perm
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String hash(String perm) throws NoSuchAlgorithmException {
        MessageDigest hash_generator = java.security.MessageDigest.getInstance("MD5");

        // build MD5 hash of a permutation
        hash_generator.update(perm.toString().getBytes());
        byte[] digest = hash_generator.digest();

        StringBuffer hashword_hex_code = new StringBuffer();
        for (byte b : digest) {
            hashword_hex_code.append(String.format("%02x", b & 0xff));
        }

        // use hashword_hex_code to compare to already encrypted/hashed words
        return hashword_hex_code.toString();
    }

    /**
     * Begin a brute for attack on the password hashfile
     *
     * Use up to 8 threads
     *
     * compute all permutations of strings and compare them to a list of already hashed passwords
     * to see if there is a match
     *
     * @param max_permutation_length
     *            - how long of passwords to attempt (suggest 6 or less)
     * @return a list of successfully cracked passwords
     */
    public static ArrayList<ArrayList<String>> multi_thread_brute_force_attack(int max_permutation_length, Collection<String> hashes) {
        long start_time = System.nanoTime();
        System.out.println("starting computation");

        ArrayList<Thread> threads = new ArrayList<>();

        int count = 0;
        int AVAILABLE_THREADS = 8;
        ExecutorService thread_pool = Executors.newFixedThreadPool(AVAILABLE_THREADS);
        ArrayList<ArrayList<String>> successes = new ArrayList<>();

        for (int i=0; i<26; i++) {
            successes.add( new ArrayList<>() );
        }

        for (int i=0; i<26; i++) {
            int temp = i;
            thread_pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("working on permutation " + temp);
                    StringBuilder stringBuilder = new StringBuilder("" + (char) ('a' + temp));
                    stringBuilder.setLength(max_permutation_length);
                    brute_force_attack(hashes, successes.get(temp),stringBuilder, 1, max_permutation_length);
                }
            });
        }

        try
        {
            thread_pool.shutdown();
            thread_pool.awaitTermination(1, TimeUnit.DAYS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        long total_time = System.nanoTime() - start_time;
        System.out.println("done: ( " + (total_time / 1000000000.0) + " seconds )");

        return successes;

    }

    public static String getaToz() {
        return aToz;
    }
}