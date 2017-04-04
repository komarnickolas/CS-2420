package assignment04;

import java.util.Comparator;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/3/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class CustomStringComparator implements Comparator<String> {
    @Override
    /**
     * returns an int based on comparison values.
     */
    public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
    }
}
