package cs2420;


import Tests.TestPane;
import Tests.TestTableView;
import cs2420.Tests.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/13/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Timing extends Application{

    public static int MAX = 100;
    public static int COUNT = 1;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        TestPane root = new TestPane();
        root.addTests(new build_from_array_in_order(), new build_from_array_random(), new build_from_array_same(), new deque_in_order(), new deque_random(), new deque_same(), new insertion_random(), new insertion_same());
        root.setExec();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
