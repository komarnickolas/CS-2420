package cards;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
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
public class Timing extends Application{

<<<<<<< HEAD
    private ProgressIndicator s5progressindicator, s7progressindicator, oddsindicator;
    private ProgressBar s5progressbar, s7progressbar;
=======
    private static Odds odds = new Odds();
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105

    private Label e5label, e7label, s5label, s7label, oddslabel;

    FileWriter stochastic_5_file_writer, stochastic_7_file_writer, stochastic_5_percents_file_writer, stocahstic_7_percents_file_writer, exhaustive_5_file_writer, exhausitve_7_file_writer, odds_file_writer;
    PrintWriter stochastic_5_print_writer, stochastic_7_print_writer, stochastic_5_percents_print_writer, stochastic_7_percents_print_writer, exhaustive5_print_writer, exhaustive7_print_writer, odds_print_writer;

    public static void main(String[] args){
        launch(args);
    }

    Task<Void> stochastic5 = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
<<<<<<< HEAD
            Odds odds = new Odds();
            updateProgress(0,10000000);
=======
            System.out.println("Running Stochastic 5");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            stochastic_5_print_writer.println("Stochastic 5 cards");
            stochastic_5_print_writer.println("Sample Size;Time");
            stochastic_5_percents_print_writer.println("Stochastic 5 percentages");
            stochastic_5_percents_print_writer.println("Sample Size;Royal Flush;Straight Flush;Four of a Kind;Full House;Flush;Straight;Three of a Kind;Two Pair;Pair;High Card");
            for (int i = 1000; i <= 10000000; i += 100000) {
                double[] percents = null;
                double Royal_Flush = 0;
                double Straight_Flush = 0;
                double Four_of_a_kind = 0;
                double Full_House = 0;
                double Flush = 0;
                double Straight = 0;
                double Three_of_a_Kind = 0;
                double Two_Pair = 0;
                double Pair = 0;
                double High_Card = 0;
                Long start = System.nanoTime();
                percents = odds.percentage_per_hand_category_stochastic(5, i);
                Long end = System.nanoTime();
                for(int k = 0; k<100; k++) {
                    percents = odds.percentage_per_hand_category_stochastic(5, i);
                    Royal_Flush += percents[0];
                    Straight_Flush += percents[1];
                    Four_of_a_kind += percents[2];
                    Full_House += percents[3];
                    Flush += percents[4];
                    Straight += percents[5];
                    Three_of_a_Kind += percents[6];
                    Two_Pair += percents[7];
                    Pair += percents[8];
                    High_Card += percents[9];
                }
                Royal_Flush /= 100;
                Straight_Flush /= 100;
                Four_of_a_kind /= 100;
                Full_House /= 100;
                Flush /= 100;
                Straight /= 100;
                Three_of_a_Kind /= 100;
                Two_Pair /= 100;
                Pair /= 100;
                High_Card /= 100;
                stochastic_5_print_writer.println(i + ";" + (end - start));
                stochastic_7_percents_print_writer.println(i +";" + Royal_Flush+";" +Straight_Flush+ ";" + Four_of_a_kind + ";" + Full_House+ ";" + Flush+ i + ";" + Straight + ";" + Three_of_a_Kind+ ";" + Two_Pair+ ";" + Pair+ ";" + High_Card);
                updateProgress(i,10000000);
            }
            stochastic_5_print_writer.close();
            stochastic_5_percents_print_writer.close();
<<<<<<< HEAD
            updateProgress(10000000,10000000);
=======
            System.out.println("Stochastic 5 Done");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            return null;
        }
    };
     Task<Void> stochastic7 = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
<<<<<<< HEAD
            Odds odds = new Odds();
            updateProgress(0,10000000);
=======
            System.out.println("Running Stochastic 7");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            stochastic_7_print_writer.println("Stochastic 7 cards");
            stochastic_7_print_writer.println("Sample Size;Time");
            stochastic_7_percents_print_writer.println("Stochastic 7 percentages");
            stochastic_7_percents_print_writer.println("Sample Size;Royal Flush;Straight Flush;Four of a Kind;Full House;Flush;Straight;Three of a Kind;Two Pair;Pair;High Card");
            for (int i = 1000; i <= 10000000; i += 100000) {
                double[] percents = null;
                double Royal_Flush = 0;
                double Straight_Flush = 0;
                double Four_of_a_kind = 0;
                double Full_House = 0;
                double Flush = 0;
                double Straight = 0;
                double Three_of_a_Kind = 0;
                double Two_Pair = 0;
                double Pair = 0;
                double High_Card = 0;
                Long start = System.nanoTime();
                percents = odds.percentage_per_hand_category_stochastic(7, i);
                Long end = System.nanoTime();
                for(int k = 0; k<100; k++) {
                    percents = odds.percentage_per_hand_category_stochastic(7, i);
                    Royal_Flush += percents[0];
                    Straight_Flush += percents[1];
                    Four_of_a_kind += percents[2];
                    Full_House += percents[3];
                    Flush += percents[4];
                    Straight += percents[5];
                    Three_of_a_Kind += percents[6];
                    Two_Pair += percents[7];
                    Pair += percents[8];
                    High_Card += percents[9];
                }
                Royal_Flush /= 100;
                Straight_Flush /= 100;
                Four_of_a_kind /= 100;
                Full_House /= 100;
                Flush /= 100;
                Straight /= 100;
                Three_of_a_Kind /= 100;
                Two_Pair /= 100;
                Pair /= 100;
                High_Card /= 100;
                stochastic_7_print_writer.println(i + ";" + (end - start));
                stochastic_7_percents_print_writer.println(i +";" + Royal_Flush+";" +Straight_Flush+ ";" + Four_of_a_kind + ";" + Full_House+ ";" + Flush+ i + ";" + Straight + ";" + Three_of_a_Kind+ ";" + Two_Pair+ ";" + Pair+ ";" + High_Card);
                updateProgress(i,10000000);
            }
            stochastic_7_print_writer.close();
            stochastic_7_percents_print_writer.close();
<<<<<<< HEAD
            updateProgress(10000000,10000000);
=======
            System.out.println("Stochastic 7 Done");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            return null;
        }
    };
    Task<Void> exhaustive5 = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
<<<<<<< HEAD
            Odds odds = new Odds();
            updateMessage("Exhaustive 5 running");
            double[] percents_5 = null;
            Long start = System.nanoTime();
            percents_5 = odds.percentage_per_hand_category_exhaustive(5);
=======
            System.out.println("Running Exhaustive 5");
            double[] percents = null;
            Long start = System.nanoTime();
            percents = odds.percentage_per_hand_category_exhaustive(5);
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            Long end = System.nanoTime();
            exhaustive5_print_writer.println("Exhaustive 5 cards Time: " + (end - start));
            exhaustive5_print_writer.println("-----------------------");
            exhaustive5_print_writer.println("Exhaustive 5 percentages");
            exhaustive5_print_writer.println("Rank;Percentage");
<<<<<<< HEAD
            exhaustive5_print_writer.println("Royal Flush;" + percents_5[0]);
            exhaustive5_print_writer.println("Straight Flush;" + percents_5[1]);
            exhaustive5_print_writer.println("Four of a Kind;" + percents_5[2]);
            exhaustive5_print_writer.println("Full House;" + percents_5[3]);
            exhaustive5_print_writer.println("Flush;" + percents_5[4]);
            exhaustive5_print_writer.println("Straight;" + percents_5[5]);
            exhaustive5_print_writer.println("Three of a Kind;" + percents_5[6]);
            exhaustive5_print_writer.println("Two Pair;" + percents_5[7]);
            exhaustive5_print_writer.println("Pair;" + percents_5[8]);
            exhaustive5_print_writer.println("High Card;" + percents_5[9]);
            exhaustive5_print_writer.println("-----------------------");
            exhaustive5_print_writer.close();
            updateMessage("Exhaustive 5 Done");
=======
            exhaustive5_print_writer.println("Royal Flush;" + percents[0]);
            exhaustive5_print_writer.println("Straight Flush;" + percents[1]);
            exhaustive5_print_writer.println("Four of a Kind;" + percents[2]);
            exhaustive5_print_writer.println("Full House;" + percents[3]);
            exhaustive5_print_writer.println("Flush;" + percents[4]);
            exhaustive5_print_writer.println("Straight;" + percents[5]);
            exhaustive5_print_writer.println("Three of a Kind;" + percents[6]);
            exhaustive5_print_writer.println("Two Pair;" + percents[7]);
            exhaustive5_print_writer.println("Pair;" + percents[8]);
            exhaustive5_print_writer.println("High Card;" + percents[9]);
            exhaustive5_print_writer.println("-----------------------");
            exhaustive5_print_writer.close();
            System.out.println("Exhaustive 5 Done");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            return null;
        }
    };
    Task<Void> exhaustive7 = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
<<<<<<< HEAD
            Odds odds = new Odds();
            updateMessage("Exhaustive 7 running");
            double[] percents_7 = null;
            Long start = System.nanoTime();
            percents_7 = odds.percentage_per_hand_category_exhaustive(7);
=======
            System.out.println("Running Exhaustive 7");
            double[] percents = null;
            Long start = System.nanoTime();
            percents = odds.percentage_per_hand_category_exhaustive(7);
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            Long end = System.nanoTime();
            exhaustive7_print_writer.println("Exhaustive 7 cards Time: " + (end-start));
            exhaustive7_print_writer.println("-----------------------");
            exhaustive7_print_writer.println("Exhaustive 7 percentages");
            exhaustive7_print_writer.println("Rank;Percentage");
<<<<<<< HEAD
            exhaustive7_print_writer.println("Royal Flush;" + percents_7[0]);
            exhaustive7_print_writer.println("Straight Flush;" + percents_7[1]);
            exhaustive7_print_writer.println("Four of a Kind;" + percents_7[2]);
            exhaustive7_print_writer.println("Full House;" + percents_7[3]);
            exhaustive7_print_writer.println("Flush;" + percents_7[4]);
            exhaustive7_print_writer.println("Straight;" + percents_7[5]);
            exhaustive7_print_writer.println("Three of a Kind;" + percents_7[6]);
            exhaustive7_print_writer.println("Two Pair;" + percents_7[7]);
            exhaustive7_print_writer.println("Pair;" + percents_7[8]);
            exhaustive7_print_writer.println("High Card;" + percents_7[9]);
            exhaustive7_print_writer.println("-----------------------");
            exhaustive7_print_writer.close();
            updateMessage("Exhaustive 7 Done");
=======
            exhaustive7_print_writer.println("Royal Flush;" + percents[0]);
            exhaustive7_print_writer.println("Straight Flush;" + percents[1]);
            exhaustive7_print_writer.println("Four of a Kind;" + percents[2]);
            exhaustive7_print_writer.println("Full House;" + percents[3]);
            exhaustive7_print_writer.println("Flush;" + percents[4]);
            exhaustive7_print_writer.println("Straight;" + percents[5]);
            exhaustive7_print_writer.println("Three of a Kind;" + percents[6]);
            exhaustive7_print_writer.println("Two Pair;" + percents[7]);
            exhaustive7_print_writer.println("Pair;" + percents[8]);
            exhaustive7_print_writer.println("High Card;" + percents[9]);
            exhaustive7_print_writer.println("-----------------------");
            exhaustive7_print_writer.close();
            System.out.println("Exhaustive 7 Done");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            return null;
        }
    };
    Task<Void> texas_holdum = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
<<<<<<< HEAD
            Odds odds = new Odds();
            updateMessage("Odds running");
=======
            System.out.println("Running Odds to Win");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            odds_print_writer.println("Texas Holdum");
            odds_print_writer.println("Cards;Chance");
            updateProgress(0,48);
            for (int c1 = 0; c1 < 48; c1++) {
                for (int c2 = c1 + 1; c2 < 48; c2++) {
                    for (int c3 = 0; c3 < 48; c3++) {
                        for (int c4 = c3 + 1; c4 < 48; c4++) {
                            double oddstowin = odds.odds_to_win(c1, c2, c3, c4, 100);
                            Card[] cards1 = {odds.getP1c1(), odds.getP1c2(), odds.getP2c1(), odds.getP2c2()};
                            odds_print_writer.println(Arrays.toString(cards1) + oddstowin);
                            updateMessage("Odds of:" + Arrays.toString(cards1) + " are: "+ oddstowin);
                        }
                    }
                }
                updateProgress(c1,48);
            }
            updateProgress(48,48);
            odds_print_writer.close();
<<<<<<< HEAD
            updateMessage("Odds Done");
=======
            System.out.println("Odds Done");
>>>>>>> e98cb3ccce05646740fe8898be2a9a9b4526f105
            return null;
        }
    };


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        GridPane root = new GridPane();
        e5label = new Label("Exhaustive 5");
        e7label = new Label("Exhaustive 7");
        s5label = new Label("Stochastic 5: ");
        s7label = new Label("Stochastic 7: ");
        oddslabel = new Label("Odds of: ");
        s5progressindicator = new ProgressIndicator();
        s5progressindicator.progressProperty().bind(stochastic5.progressProperty());
        s7progressindicator = new ProgressIndicator();
        s7progressindicator.progressProperty().bind(stochastic7.progressProperty());
        s5progressbar = new ProgressBar();
        s5progressbar.progressProperty().bind(stochastic5.progressProperty());
        s7progressbar = new ProgressBar();
        s7progressbar.progressProperty().bind(stochastic7.progressProperty());
        oddsindicator = new ProgressIndicator();
        oddsindicator.progressProperty().bind(texas_holdum.progressProperty());
        oddslabel.textProperty().bind(texas_holdum.messageProperty());
        e5label.textProperty().bind(exhaustive5.messageProperty());
        e7label.textProperty().bind(exhaustive7.messageProperty());
        root.add(e5label, 0,0);
        root.add(e7label, 0,1);

        HBox s5 = new HBox();
        s5.getChildren().addAll(s5label,s5progressbar,s5progressindicator);
        root.add(s5,0,2);

        HBox s7 = new HBox();
        s7.getChildren().addAll(s7label,s7progressbar,s7progressindicator);
        root.add(s7,0,3);

        HBox odds = new HBox();
        odds.getChildren().addAll(oddslabel,oddsindicator);
        root.add(odds,0,4);

        Scene scene = new Scene(root,500,100);
        primaryStage.setScene(scene);
        stochastic_5_file_writer = new FileWriter("Stochastic 5.txt");
        stochastic_5_print_writer = new PrintWriter(stochastic_5_file_writer);

        stochastic_5_percents_file_writer = new FileWriter("Stochastic 5 percents.txt");
        stochastic_5_percents_print_writer = new PrintWriter(stochastic_5_percents_file_writer);

        stochastic_7_file_writer = new FileWriter("Stochastic 7.txt");
        stochastic_7_print_writer = new PrintWriter(stochastic_7_file_writer);

        stocahstic_7_percents_file_writer = new FileWriter("Stochastic 7 percents.txt");
        stochastic_7_percents_print_writer = new PrintWriter(stocahstic_7_percents_file_writer);

        exhaustive_5_file_writer = new FileWriter("Exhaustive 5.txt");
        exhaustive5_print_writer = new PrintWriter(exhaustive_5_file_writer);

        exhausitve_7_file_writer = new FileWriter("Exhaustive 7.txt");
        exhaustive7_print_writer = new PrintWriter(exhausitve_7_file_writer);

        odds_file_writer = new FileWriter("Texas.txt");
        odds_print_writer = new PrintWriter(odds_file_writer);

        Thread stochastic5thread = new Thread(stochastic5);
        stochastic5thread.setDaemon(true);
        Thread stochastic7thread = new Thread(stochastic7);
        stochastic7thread.setDaemon(true);
        Thread exhaustive5thread = new Thread(exhaustive5);
        exhaustive5thread.setDaemon(true);
        Thread exhaustive7thread = new Thread(exhaustive7);
        exhaustive7thread.setDaemon(true);
        Thread oddsThread = new Thread(texas_holdum);
        oddsThread.setDaemon(true);
        stochastic5thread.start();
        stochastic7thread.start();
        exhaustive5thread.start();
        exhaustive7thread.start();
        oddsThread.start();

        primaryStage.show();

    }
}
