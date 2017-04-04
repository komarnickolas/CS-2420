package cards;

import java.util.ArrayList;

/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Odds {

    private static ArrayList<Hand> hands;
    private static Deck deck;
    private static Integer[] histogram;
    private static Card p1c1,p1c2,p2c1,p2c2;

    public Odds(){
        deck = new Deck();
        hands = new ArrayList<>();
    }

    static double odds_to_win(int h1c1, int h1c2, int h2c1, int h2c2, int samples){
        deck = new Deck();
        double odds_to_win = 0;
        p1c1 = deck.getDeck()[h1c1];
        p1c2 = deck.getDeck()[h1c2];
        p2c1 = deck.getDeck()[h2c1];
        p2c2 = deck.getDeck()[h2c2];
        for(int i = 0; i < samples; i++) {
            deck.shuffle();
            Hand flop = new Hand(deck);
            flop.getRandomHand(5);
            while(flop.getCards().contains(p1c1) || flop.getCards().contains(p1c2) || flop.getCards().contains(p2c1) || flop.getCards().contains(p2c2)){
                deck.shuffle();
                flop = new Hand(deck);
                flop.getRandomHand(5);
            }
            Card[] h1 = {
                    p1c1,
                    p1c2,
                    flop.getCards().get(0),
                    flop.getCards().get(1),
                    flop.getCards().get(2),
                    flop.getCards().get(3),
                    flop.getCards().get(4)};
            Card[] h2 = {
                    p2c1,
                    p2c2,
                    flop.getCards().get(0),
                    flop.getCards().get(1),
                    flop.getCards().get(2),
                    flop.getCards().get(3),
                    flop.getCards().get(4)};

            Hand p1 = new Hand(deck, h1);
            Hand p2 = new Hand(deck, h2);
            if (p1.getRank() < p2.getRank()) {
                odds_to_win++;
            }else if(p1.getRank() == p2.getRank()){
                if(p1.compareTo(p2) > 0){
                    odds_to_win++;
                }
            }
        }
        return (odds_to_win/samples)*100;
    }

    static double[] percentage_per_hand_category_exhaustive(int hand_size){
        deck = new Deck();
        Card[] cards = deck.getDeck();
        histogram = new Integer[10];
        for(int i = 0; i<histogram.length; i++){
            histogram[i] = 0;
        }
        double[] percenteges = new double[10];
        if(hand_size == 7) {
            for (int c1 = 0; c1 < 48; c1++) {
                for (int c2 = c1 + 1; c2 < 48; c2++) {
                    for (int c3 = c2 + 1; c3 < 48; c3++) {
                        for (int c4 = c3 + 1; c4 < 48; c4++) {
                            for (int c5 = c4 + 1; c5 < 48; c5++) {
                                for (int c6 = c5 + 1; c6 < 48; c6++) {
                                    for (int c7 = c6 + 1; c7 < 48; c7++) {
                                        Card[] flop = {cards[c1], cards[c2], cards[c3], cards[c4], cards[c5], cards[c6], cards[c7]};
                                        Hand hand = new Hand(deck, flop);
                                        int rank = hand.getRank();
                                        histogram[rank]++;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }else{
            for (int c1 = 0; c1 < 48; c1++) {
                for (int c2 = c1 + 1; c2 < 48; c2++) {
                    for (int c3 = c2 + 1; c3 < 48; c3++) {
                        for (int c4 = c3 + 1; c4 < 48; c4++) {
                            for (int c5 = c4 + 1; c5 < 48; c5++) {
                                Card[] flop = {cards[c1], cards[c2], cards[c3], cards[c4], cards[c5]};
                                Hand hand = new Hand(deck, flop);
                                int rank = hand.getRank();
                                histogram[rank]++;
                            }
                        }
                    }
                }
            }
        }
        int number_of_hands = 0;
        for(Integer i : histogram){
            number_of_hands += i;
        }
        for(int i = 0; i < percenteges.length; i++){
            float percent = (float) histogram[i]/number_of_hands;
            percenteges[i] = percent*100;
        }
        return percenteges;
    }

    public void generateHands(int hand_size){
        deck.shuffle();
        for(int i = 0; i < 4; i++) {
            Hand hand = new Hand(deck);
            hand.getRandomHand(hand_size);
            hands.add(hand);
        }
    }

    static double[] percentage_per_hand_category_stochastic(int hand_size, int random_samples){
        deck = new Deck();
        histogram = new Integer[10];
        double[] percenteges = new double[10];
        for(int i = 0; i<histogram.length; i++){
            histogram[i] = 0;
        }
        for(int i =1; i<random_samples; i++){
            deck.shuffle();
            Hand hand = new Hand(deck);
            hand.getRandomHand(hand_size);
            int rank = hand.getRank();
            histogram[rank]++;
        }
        int number_of_hands = 0;
        for(Integer i : histogram){
            number_of_hands += i;
        }
        for(int i = 0; i < percenteges.length; i++){
            float percent = (float) histogram[i]/number_of_hands;
            percenteges[i] = percent*100;
        }
        return percenteges;
    }

    public ArrayList<Hand> getHands(){
        return this.hands;
    }

    public static Card getP1c1() {
        return p1c1;
    }

    public static Card getP1c2() {
        return p1c2;
    }

    public static Card getP2c1() {
        return p2c1;
    }

    public static Card getP2c2() {
        return p2c2;
    }
}
