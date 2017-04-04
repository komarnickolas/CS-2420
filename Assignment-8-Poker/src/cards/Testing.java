package cards;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Testing {

    Deck deck;

    @Before
    public void reset(){
        deck = new Deck();
        deck.shuffle();
    }
    @Test
    public void testRank(){
        Card[] royal_flush = {
                new Card(14,3),
                new Card(13,3),
                new Card(12,3),
                new Card(11,3),
                new Card(10,3),
                new Card(14,0),
                new Card(2,0)};

        Card[] straight_flush = {
                new Card(4,0),
                new Card(5,0),
                new Card(6,0),
                new Card(7,0),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] four_of_a_kind = {
                new Card(4,0),
                new Card(4,1),
                new Card(4,2),
                new Card(4,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] full_house = {
                new Card(4,0),
                new Card(4,1),
                new Card(4,2),
                new Card(8,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] flush = {
                new Card(4,0),
                new Card(9,0),
                new Card(4,2),
                new Card(8,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] straight = {
                new Card(4,0),
                new Card(5,1),
                new Card(6,2),
                new Card(7,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] three_of_a_kind = {
                new Card(4,0),
                new Card(5,1),
                new Card(4,2),
                new Card(4,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] two_pair = {
                new Card(4,0),
                new Card(5,1),
                new Card(4,2),
                new Card(8,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] pair = {
                new Card(4,0),
                new Card(5,1),
                new Card(4,2),
                new Card(6,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Card[] bust = {
                new Card(4,0),
                new Card(5,1),
                new Card(9,2),
                new Card(6,3),
                new Card(8,0),
                new Card(14,0),
                new Card(2,0)};

        Hand hand1 = new Hand(deck, royal_flush);
        Hand hand2 = new Hand(deck, straight_flush);
        Hand hand3 = new Hand(deck, four_of_a_kind);
        Hand hand4 = new Hand(deck, full_house);
        Hand hand5 = new Hand(deck, flush);
        Hand hand6 = new Hand(deck, straight);
        Hand hand7 = new Hand(deck, three_of_a_kind);
        Hand hand8 = new Hand(deck, two_pair);
        Hand hand9 = new Hand(deck, pair);
        Hand hand10 = new Hand(deck, bust);

        assertEquals(Hand.Rank.Royal_Flush, hand1.rank());
        assertEquals(Hand.Rank.Straight_Flush, hand2.rank());
        assertEquals(Hand.Rank.Four_Of_A_Kind, hand3.rank());
        assertEquals(Hand.Rank.Full_House, hand4.rank());
        assertEquals(Hand.Rank.Flush, hand5.rank());
        assertEquals(Hand.Rank.Straight, hand6.rank());
        assertEquals(Hand.Rank.Three_Of_A_Kind, hand7.rank());
        assertEquals(Hand.Rank.Two_Pair, hand8.rank());
        assertEquals(Hand.Rank.Pair, hand9.rank());
        assertEquals(Hand.Rank.High_Card, hand10.rank());
    }
}
