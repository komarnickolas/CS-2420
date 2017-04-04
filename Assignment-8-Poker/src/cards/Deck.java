package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Deck {

    private static final Card[] deck = new Card[52];
    {
        int value = 2;
        int suit = 0;
        for(int i = 0; i < 52; i++) {
            deck[i] = new Card(value, suit);
            value++;
            if(value == 15){
                value = 2;
                suit++;
            }
        }
    }
    private Random_Generator random_generator;
    private int top = 51;

    public Deck(){
        random_generator = new Javas_Random_Generator();
    }

    public void shuffle(){
        top = 51;
        for(int i = 0; i< 51; i++){
            int randomPosition = random_generator.next_int(deck.length);
            Card temp = deck[i];
            deck[i] = deck[randomPosition];
            deck[randomPosition] = temp;
        }
    }

    public Card deal(){
        Card card = deck[top];
        top--;
        return card;
    }

    public String toString(){
        return Arrays.toString(deck);
    }

    public Card[] getDeck(){
        return this.deck;
    }
}
