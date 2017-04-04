package cards;

import java.util.Comparator;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class CardSuitComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if(o1.suitValue() < o2.suitValue()){
            return -1;
        }else if(o1.suitValue() < o2.suitValue()){
            return 1;
        }else {
            return 0;
        }
    }
}
