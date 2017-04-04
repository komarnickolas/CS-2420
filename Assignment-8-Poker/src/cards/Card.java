package cards;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Card{

    private Suit suit;
    private int value;
    private boolean dealt;

    public enum Suit{
        Spades, Hearts, Diamonds, Clubs
    }

    /**
     * Creates a new Card
     * @param value
     * @param suit
     */
    public Card(int value, int suit) {
        switch (suit){
            case 0:
                this.suit = Suit.Clubs;
                break;
            case 1:
                this.suit = Suit.Diamonds;
                break;
            case 2:
                this.suit = Suit.Hearts;
                break;
            case 3:
                this.suit = Suit.Spades;
                break;
        }
        this.value = value;
        this.dealt = false;
    }

    /**
     * Compares value of card to another
     * @param card
     * @return
     */
    public int compareValue(Card card){
        if(this.value < card.getValue()){
            return -1;
        }else if(this.value > card.getValue()){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Suit as an int
     * @return
     */
    public int suitValue(){
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

    public Suit getSuit(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }


    public String toString(){
        switch (value) {
            case 11:
                return "Jack of " + suit;
            case 12:
                return "Queen of " + suit;
            case 13:
                return "King of " + suit;
            case 14:
                return "Ace of " + suit;
            default:
                return value + " of " + suit;
        }
    }

    public boolean isDealt() {
        return dealt;
    }

    public void setDealt(){
        this.dealt = true;
    }
}
