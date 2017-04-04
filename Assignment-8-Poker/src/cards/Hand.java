package cards;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Hand {
    private Card[] cards;
    private Deck deck;
    private Card.Suit flush_suit;
    private Card lowest_straight;
    private Card highest_flush;
    private int four_and_three_of_a_kind;
    private int full_house_three, full_house_two;
    private int two_pair;
    private int pair;
    private CardValueComparator cardValueComparator = new CardValueComparator();
    private CardSuitComparator cardSuitComparator = new CardSuitComparator();

    public enum Rank{
        Royal_Flush, Straight_Flush, Four_Of_A_Kind, Full_House, Flush, Straight, Three_Of_A_Kind, Two_Pair, Pair, High_Card;
    }

    public Hand(Deck deck){
        this.deck = deck;
    }

    public Hand(Deck deck, Card[] cards){
        this.deck = deck;
        this.cards = cards;
    }

    public int compareTo(Hand hand){
        switch (hand.rank()){
            case Flush:
                if(suitValue(flush_suit) > suitValue(hand.flush_suit)){
                    return 1;
                }else if(suitValue(flush_suit) == suitValue(hand.flush_suit)) {
                    if(highest_flush.getValue() > hand.highest_flush.getValue()){
                        return 1;
                    }else{
                        return -1;
                    }
                }else{
                    return -1;
                }
            case Straight_Flush:case Royal_Flush:
                if(suitValue(flush_suit) > suitValue(hand.flush_suit)){
                    return 1;
                }else if(suitValue(flush_suit) == suitValue(hand.flush_suit)) {
                    if(lowest_straight.getValue() > hand.lowest_straight.getValue()){
                        return 1;
                    }else{
                        return -1;
                    }
                }else{
                    return -1;
                }
            case Straight:
                if(lowest_straight.getValue() > hand.lowest_straight.getValue()){
                    return 1;
                }else{
                    return -1;
                }
            case Four_Of_A_Kind:case Three_Of_A_Kind:
                if(four_and_three_of_a_kind > hand.four_and_three_of_a_kind){
                    return 1;
                }else{
                    return -1;
                }
            case Full_House:
                if(full_house_three > hand.full_house_two){
                    return 1;
                }else{
                    return -1;
                }
            case Two_Pair:
                if(two_pair > hand.two_pair){
                    return 1;
                }else{
                    return -1;
                }
            case Pair:
                if(pair > hand.pair){
                    return 1;
                }else{
                    return -1;
                }
            case High_Card:
                Arrays.sort(cards, cardValueComparator);
                if(cards[cards.length - 1].getValue() > hand.getCards().get(hand.getCards().size()-1).getValue()) {
                    return 1;
                }else {
                    return -1;
                }
        }
        return 0;
    }


    private int suitValue(Card.Suit suit){
        switch (suit){
            case Clubs:
                return 0;
            case Diamonds:
                return 1;
            case Hearts:
                return 2;
            case Spades:
                return 3;
        }
        return 0;
    }

    public int getRank(){
        switch (rank()){
            case Royal_Flush:
                return 0;
            case Straight_Flush:
                return 1;
            case Four_Of_A_Kind:
                return 2;
            case Full_House:
                return 3;
            case Flush:
                return 4;
            case Straight:
                return 5;
            case Three_Of_A_Kind:
                return 6;
            case Two_Pair:
                return 7;
            case Pair:
                return 8;
            case High_Card:
                return 9;
        }
        return 0;
    }

    public Rank rank(){
        if(is_royal_flush()){
            return Rank.Royal_Flush;

        }else if(is_straight_flush()){
            return Rank.Straight_Flush;

        }else if(is_four_of_a_kind()){
            return Rank.Four_Of_A_Kind;

        }else if(is_full_house()){
            return Rank.Full_House;

        }else if(is_flush()){
            return Rank.Flush;

        }else if(is_straight()){
            return Rank.Straight;

        }else if(is_three_of_a_kind()){
            return Rank.Three_Of_A_Kind;

        }else if(is_two_pair()){
            return Rank.Two_Pair;

        }else if(is_pair()){
            return Rank.Pair;
        }else{
            return Rank.High_Card;
        }
    }

    public void getRandomHand(int size){
        cards = new Card[size];
        for(int j = 0; j < size; j++){
            Card card = deck.deal();
            cards[j] = card;
        }
    }

    public void getTwoRandomHands(Hand hand1, Hand hand2){
        hand1.getRandomHand(7);
        hand2.getRandomHand(7);
    }

    public String toString(){
        StringBuilder hand = new StringBuilder();
        for(Card card : cards){
            hand.append(card.toString());
            hand.append(", ");
        }
        hand.deleteCharAt(hand.length()-2);
        return hand.toString();
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for(Card card :cards){
            cardArrayList.add(card);
        }
        return cardArrayList;
    }

    public boolean is_royal_flush(){
        Arrays.sort(cards, cardValueComparator);
        Arrays.sort(cards, cardSuitComparator);
        for(int j = 0; j < cards.length; j ++) {
            if(cards[j].getValue() == 10){
                int count = 0;
                for(int i = j; i < cards.length-1; i ++) {
                    count++;
                    if (cards[i].getValue() + 1 != cards[i + 1].getValue()) {
                        count = 0;
                    } else {
                        if (cards[i].getSuit() != cards[i + 1].getSuit()) {
                            count = 0;
                        }
                    }
                    if(count == 4){
                        flush_suit = cards[j].getSuit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean is_straight_flush(){
        Arrays.sort(cards, cardValueComparator);
        Arrays.sort(cards, cardSuitComparator);
        int count = 0;
        lowest_straight = cards[0];
        for(int i = 0; i < cards.length-1; i ++) {
            count++;
            if (cards[i].getValue() + 1 != cards[i + 1].getValue()) {
                lowest_straight = cards[i+1];
                count = 0;
            } else {
                if (cards[i].getSuit() != cards[i + 1].getSuit()) {
                    lowest_straight = cards[i+1];
                    count = 0;
                }
            }
            if(count == 4){
                flush_suit = cards[i].getSuit();
                return true;
            }
        }
        return false;
    }
    public boolean is_four_of_a_kind(){
        Arrays.sort(cards, cardValueComparator);
        int count = 1;
        for(int i = 0; i < cards.length-1; i++){
            if(cards[i].getValue() == cards[i+1].getValue()){
                count++;
                if(count == 4){
                    four_and_three_of_a_kind = cards[i].getValue();
                    return true;
                }
            }else {
                count = 1;
            }
        }
        return false;
    }
    public boolean is_full_house(){
        Arrays.sort(cards, cardValueComparator);
        boolean three = false,two = false;
        int count = 0;
        ArrayList<Integer> spots = new ArrayList<>();
        for(int i = 0; i < cards.length-1; i++){
            if(count == 2){
                full_house_three = cards[i].getValue();
                spots.add(i);
                three = true;
                break;
            }
            if(cards[i].getValue() != cards[i+1].getValue()){
                count = 0;
                spots = new ArrayList<>();

            }else {
                spots.add(i);
                count++;
            }
        }
        for(int i = 0; i < cards.length-1; i++){
            if(!spots.contains(i)) {
                if (cards[i].getValue() == cards[i + 1].getValue()) {
                        full_house_two = cards[i].getValue();
                        two = true;
                }
            }
        }
        if(three && two){
            return true;
        }else{
            return false;
        }
    }
    public boolean is_flush(){
        Arrays.sort(cards,cardValueComparator);
        Arrays.sort(cards,cardSuitComparator);
        int count = 1;
        for(int i = 0; i < cards.length-1; i++){
            if(cards[i].getSuit() == cards[i+1].getSuit()){
                count++;
                if(count == 5){
                    highest_flush = cards[i];
                    flush_suit = cards[i].getSuit();
                    return true;
                }
            }else{
                count = 1;
            }
        }
        return false;

    }
    public boolean is_straight(){
        Arrays.sort(cards, cardValueComparator);
        int count = 0;
        lowest_straight = cards[0];
        for(int i = 0; i < cards.length-1; i ++){
            if(cards[i].getValue() + 1 != cards[i+1].getValue()){
                lowest_straight = cards[i+1];
                count = 0;
            }
            count++;
            if(count == 4){
                break;
            }
        }
        if(count == 4){
            return true;
        }else{
            return false;
        }
    }
    public boolean is_three_of_a_kind(){
        Arrays.sort(cards, cardValueComparator);
        int count = 1;
        for(int i = 0; i < cards.length-1; i++){
            if(cards[i].getValue() == cards[i+1].getValue()){
                count++;
                if(count == 3){
                    four_and_three_of_a_kind = cards[i].getValue();
                    return true;
                }
            }else {
                count = 1;
            }
        }
        return false;
    }
    public boolean is_two_pair(){
        Arrays.sort(cards, cardValueComparator);
        int count = 0;
        for(int i = 0; i < cards.length-1; i++){
            if(cards[i].getValue() == cards[i+1].getValue()){
                count++;
                if(count == 2){
                    two_pair = cards[i].getValue();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean is_pair(){
        Arrays.sort(cards, cardValueComparator);
        for(int i = 0; i < cards.length-1; i++){
            if(cards[i].getValue() == cards[i+1].getValue()){
                pair = cards[i].getValue();
                return true;
            }
        }
        return false;
    }
}
