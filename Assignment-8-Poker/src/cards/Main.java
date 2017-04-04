package cards;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Nickolas Komarnitsky and Porter Anderson
 * u0717854
 * 3/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky and Porter Anderson
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Poker");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller canvas = new Controller(800,600);
        root.getChildren().add(canvas);
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        canvas.start();
//        primaryStage.show();

        Odds odds = new Odds();
        double[] exhaustive = odds.percentage_per_hand_category_exhaustive(5);
        double[] stochastic = odds.percentage_per_hand_category_stochastic(5,10000000);
        double odds_to_win = odds.odds_to_win(2,5,10,40,100);
        System.out.println(odds_to_win);

        Stage percentagesExhaustive = new Stage();
        percentagesExhaustive.setTitle("Exhaustive");
        VBox vBoxExhaustive = new VBox();
        Scene sceneExhaustive = new Scene(vBoxExhaustive);

        Label royal_flush_exhaustive = new Label("Royal Flush: " + exhaustive[0]);
        Label straight_flush_exhaustive = new Label("Straight Flush: " + exhaustive[1]);
        Label four_of_a_kind_exhaustive = new Label("Four of a kind: " + exhaustive[2]);
        Label full_house_exhaustive = new Label("Full House: " + exhaustive[3]);
        Label flush_exhaustive = new Label("Flush: " + exhaustive[4]);
        Label straight_exhaustive = new Label("Straight: " + exhaustive[5]);
        Label three_of_a_kind_exhaustive = new Label("Three of a Kind: " + exhaustive[6]);
        Label two_pair_exhaustive = new Label("Two Pair: " + exhaustive[7]);
        Label pair_exhaustive = new Label("Pair: " + exhaustive[8]);
        Label bust_exhaustive = new Label("High Card: " + exhaustive[9]);

        vBoxExhaustive.getChildren().addAll(royal_flush_exhaustive,straight_flush_exhaustive, four_of_a_kind_exhaustive,full_house_exhaustive,flush_exhaustive,straight_exhaustive,three_of_a_kind_exhaustive,two_pair_exhaustive,pair_exhaustive,bust_exhaustive);

        percentagesExhaustive.setScene(sceneExhaustive);
        percentagesExhaustive.show();

        Stage percentagesStochastic = new Stage();
        percentagesStochastic.setTitle("Stochastic");
        VBox vBoxStochastic = new VBox();
        Scene sceneStochastic = new Scene(vBoxStochastic);

        Label royal_flush_stochastic = new Label("Royal Flush: " + stochastic[0]);
        Label straight_flush_stochastic = new Label("Straight Flush: " + stochastic[1]);
        Label four_of_a_kind_stochastic = new Label("Four of a kind: " + stochastic[2]);
        Label full_house_stochastic = new Label("Full House: " + stochastic[3]);
        Label flush_stochastic = new Label("Flush: " + stochastic[4]);
        Label straight_stochastic = new Label("Straight: " + stochastic[5]);
        Label three_of_a_kind_stochastic = new Label("Three of a Kind: " + stochastic[6]);
        Label two_pair_stochastic = new Label("Two Pair: " + stochastic[7]);
        Label pair_stochastic = new Label("Pair: " + stochastic[8]);
        Label bust_stochastic = new Label("High Card: " + stochastic[9]);

        vBoxStochastic.getChildren().addAll(royal_flush_stochastic,straight_flush_stochastic, four_of_a_kind_stochastic,full_house_stochastic,flush_stochastic,straight_stochastic,three_of_a_kind_stochastic,two_pair_stochastic,pair_stochastic,bust_stochastic);

        percentagesStochastic.setScene(sceneStochastic);
        percentagesStochastic.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
