package assignment04;

import java.util.Arrays;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/3/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        AnagramUtil anagramUtil = new AnagramUtil();
        System.out.println(Arrays.toString(anagramUtil.getLargestAnagramGroupHashMap("words_english")));
        System.out.println(Arrays.toString(anagramUtil.getLargestAnagramGroupHashMap("words")));
        System.out.println(Arrays.toString(anagramUtil.getLargestAnagramGroupHashMap("moderate_word_list")));
        System.out.println(Arrays.toString(anagramUtil.getLargestAnagramGroupHashMap("corncob_lowercase.txt")));
        System.out.println(Arrays.toString(anagramUtil.getLargestAnagramGroupHashMap("all_words")));
    }
}
