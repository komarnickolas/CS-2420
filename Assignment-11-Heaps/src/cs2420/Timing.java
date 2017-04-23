package cs2420;


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
    private ArrayList<Test> tasks = new ArrayList<>();
    private ExecutorService exec;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        TableView<Test> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getItems().add(new insertion_same());
        table.getItems().add(new insertion_in_order());
        table.getItems().add(new insertion_random());
        table.getItems().add(new deque_same());
        table.getItems().add(new deque_in_order());
        table.getItems().add(new deque_random());
        table.getItems().add(new build_from_array_same());
        table.getItems().add(new build_from_array_in_order());
        table.getItems().add(new build_from_array_random());

        MenuBar menuBar = new MenuBar();
        Menu control = new Menu("Control");
        Menu variables = new Menu("Max: " + MAX + " Count: " + COUNT);
        MenuItem count = new MenuItem("Set Count");
        MenuItem max = new MenuItem("Set Max");
        variables.getItems().addAll(max,count);
        MenuItem launch = new MenuItem("Launch Tasks");
        MenuItem stop = new MenuItem("Stop");
        control.getItems().addAll(launch,stop);
        menuBar.getMenus().addAll(control, variables);

        TableColumn<Test, String> titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(75);

        TableColumn<Test, String> statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        statusCol.setPrefWidth(75);

        TableColumn<Test, Double> progressCol = new TableColumn("Progress");
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        progressCol.setCellFactory(ProgressBarTableCell.forTableColumn());

        table.getColumns().addAll(titleCol, statusCol, progressCol);

        VBox root = new VBox(10,menuBar, table);

        exec = Executors.newFixedThreadPool(table.getItems().size(), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
        launch.setOnAction(e ->table.getItems().forEach(exec::execute));
        stop.setOnAction(e -> stop());
        max.setOnAction(e ->{
            MAX = Integer.parseInt(JOptionPane.showInputDialog("Max: "));
            variables.setText("Max: " + MAX + " Count: " + COUNT);
        });
        count.setOnAction(e ->{
            COUNT = Integer.parseInt(JOptionPane.showInputDialog("Count: "));
            variables.setText("Max: " + MAX + " Count: " + COUNT);
        });
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    @Override
    public void stop() {
        exec.shutdownNow() ;
    }
}
