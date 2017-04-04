package cards;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Controller extends Canvas {

    private Odds odds;
    private GraphicsContext gc;
    private Image cards;
    public Controller(double width, double height) throws IOException {
        odds = new Odds();
        odds.generateHands(7);
        gc = getGraphicsContext2D();
        setWidth(width);
        setHeight(height);
        cards = new Image("/cards.png");
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
        for(Hand hand : odds.getHands()){
            System.out.println(hand.toString());
        }
    }
    public void start(){
            gc.clearRect(0,0,getWidth(),getHeight());
            draw();
    }

    private void draw(){
        Hand hand1 = odds.getHands().get(0);
        Hand hand2 = odds.getHands().get(1);
        Hand hand3 = odds.getHands().get(2);
        Hand hand4 = odds.getHands().get(3);

        drawHand(hand1, getWidth()/2, 0, true);
        drawHand(hand2, getWidth()-100, getHeight()/2, false);
        drawHand(hand3, getWidth()/2, getHeight()-150, true);
        drawHand(hand4, 0, getHeight()/2, false);

        gc.fillText(""+hand1.rank(), getWidth()/2, 110);
        gc.fillText(""+hand2.rank(), getWidth()-150, getHeight()/2);
        gc.fillText(""+hand3.rank(), getWidth()/2, getHeight() - 160);
        gc.fillText(""+hand4.rank(), 80, getHeight()/2);
    }

    private void drawHand(Hand hand, double x, double y, boolean top_or_bottom){
        if(top_or_bottom) {
            int increment = -(74*(hand.getCards().size()/2));
            for(Card card : hand.getCards()) {
                gc.drawImage(
                        cards,
                        parseValue(card.getValue()),
                        parseSuit(card.getSuit()),
                        74,
                        98,
                        x + increment,
                        y,
                        74,
                        98);
                increment += 74;
            }
        } else{
            int increment = -(100 * (hand.getCards().size()/2));
            for(Card card : hand.getCards()) {
                gc.drawImage(
                        cards,
                        parseValue(card.getValue()),
                        parseSuit(card.getSuit()),
                        74,
                        98,
                        x,
                        y + increment,
                        74,
                        98);
                increment += 100;
            }
        }
    }

    private int parseValue(int value){
       switch(value){
           case 2:
               return 73;
           case 3:
               return 146;
           case 4:
               return 219;
           case 5:
               return 292;
           case 6:
               return 365;
           case 7:
               return 438;
           case 8:
               return 511;
           case 9:
               return 584;
           case 10:
               return 657;
           case 11:
               return 730;
           case 12:
               return 803;
           case 13:
               return 876;
           case 14:
               return 0;
       }
       return 0;
    }

    private int parseSuit(Card.Suit suit){
        switch (suit){
            case Clubs:
                return 0;
            case Spades:
                return 97;
            case Hearts:
                return 195;
            case Diamonds:
                return 293;
        }
        return 0;
    }

}
