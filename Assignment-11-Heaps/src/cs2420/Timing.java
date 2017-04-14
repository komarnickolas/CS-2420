package cs2420;


import cs2420.Tests.*;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

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

    public static PrintWriter heap_insertion_same_pw,heap_insertion_in_order_pw, heap_insertion_random_pw, heap_deque_same_pw,heap_deque_in_order_pw,heap_deque_random_pw;
    public static FileWriter heap_insertion_same_fw,heap_insertion_in_order_fw, heap_insertion_random_fw,heap_deque_same_fw,heap_deque_in_order_fw,heap_deque_random_fw ;
    public static int MAX = 100;
    public static int COUNT = 1;
    private HashMap<Task, Label> tasks = new HashMap<>();
    private final ExecutorService exec = Executors.newFixedThreadPool(8, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t ;
    });

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        tasks.put(new insertion_same(), new Label());
        tasks.put(new insertion_in_order(), new Label());
        tasks.put(new insertion_random(), new Label());
        tasks.put(new deque_same(), new Label());
        tasks.put(new deque_in_order(), new Label());
        tasks.put(new deque_random(), new Label());
        Label pendingTasksLabel = new Label();
        IntegerProperty pendingTasks = new SimpleIntegerProperty(0);
        pendingTasksLabel.textProperty().bind(pendingTasks.asString("Pending Tasks: %d"));
        DoubleProperty progress = new SimpleDoubleProperty(ProgressIndicator.INDETERMINATE_PROGRESS);
        ProgressBar progressBar = new ProgressBar();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.progressProperty().bind(progress);
        progressBar.progressProperty().bind(progress);
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
        VBox root = new VBox(10,menuBar,new HBox(pendingTasksLabel, progressBar, progressIndicator));
        launch.setOnAction(e -> {
            progress.unbind();
            progress.bind( new DoubleBinding() {
                {
                    tasks.forEach((task,l) ->{
                        bind(task.progressProperty());
                    });
                }

                @Override
                public double computeValue() {
                    return MAX * tasks.size();
                }
            });
            pendingTasks.set(tasks.size());
            tasks.forEach(((task, label) -> root.getChildren().add(label)));
            tasks.forEach((task, label) ->
                    task.stateProperty().addListener((obs, oldState, newState) -> {
                        label.setText(task.getTitle() + " " + newState);

                        // update pendingTasks if task moves out of running state:
                        if (oldState == Worker.State.RUNNING) {
                            pendingTasks.set(pendingTasks.get() - 1);
                        }
                    }));
            tasks.forEach((task,label) -> exec.execute(task));
        });
        stop.setOnAction(e -> stop());
        max.setOnAction(e ->{
            MAX = Integer.parseInt(JOptionPane.showInputDialog("Max: "));
            variables.setText("Max: " + MAX + " Count: " + COUNT);
        });
        count.setOnAction(e ->{
            COUNT = Integer.parseInt(JOptionPane.showInputDialog("Count: "));
            variables.setText("Max: " + MAX + " Count: " + COUNT);
        });
        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();
    }

    @Override
    public void stop() {
        exec.shutdownNow() ;
    }
}
