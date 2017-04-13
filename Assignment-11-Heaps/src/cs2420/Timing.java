package cs2420;


import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
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

    private PrintWriter heap_insertion_pw, heap_deque_pw;
    private FileWriter heap_insertion_fw, heap_deque_fw;
    private Heap<Integer> heap;
    private final int MAX = 1000000;
    private final int COUNT = 1;
    private Random rng = new Random();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        heap_insertion_fw = new FileWriter("Heap Insertion.txt");
        heap_insertion_pw = new PrintWriter(heap_insertion_fw);
        heap_insertion_pw.println("Size;Time");

        heap_deque_fw = new FileWriter("Heap Deque.txt");
        heap_deque_pw = new PrintWriter(heap_deque_fw);
        heap_deque_pw.println("Size;Time");

        TableView<Task> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getItems().add(insertion);
        table.getItems().add(deque);

        TableColumn<Task, String> titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<Task,String>("title"));
        titleCol.setPrefWidth(75);


        TableColumn<Task, String> statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        statusCol.setPrefWidth(75);

        TableColumn<Task, Double> progressCol = new TableColumn("Progress");
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        progressCol.setCellFactory(ProgressBarTableCell.forTableColumn());

        table.getColumns().addAll(titleCol, statusCol, progressCol);

        BorderPane root = new BorderPane();
        root.setCenter(table);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        ExecutorService executor = Executors.newFixedThreadPool(table.getItems().size(), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        for (Task task : table.getItems()) {
            executor.execute(task);
        }
    }

    Task<Void> insertion = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            this.updateTitle("insertion");
            this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
            this.updateMessage("Waiting...");
            Thread.sleep(rng.nextInt(3000) + 2000);
            this.updateMessage("Running...");
            heap = new Heap<>();
            for (int i = 0; i <= MAX; i+=COUNT) {
                updateProgress(i, MAX);
                this.updateMessage("Running... " + i +"/"+MAX);
                Long start = System.nanoTime();
                heap.add(i);
                Long end = System.nanoTime() - start;
                heap_insertion_pw.println(i+";"+end);
            }
            this.updateMessage("Done");
            this.updateProgress(1, 1);
            heap_insertion_pw.close();
            return null;
        }
    };

    Task<Void> deque = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            this.updateTitle("deque");
            this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
            this.updateMessage("Waiting...");
            Thread.sleep(rng.nextInt(3000) + 2000);
            this.updateMessage("Running...");
            heap = new Heap<>();
            for (int i = 1; i <= MAX; i+=COUNT) {
                updateProgress(i, MAX);
                this.updateMessage("Running... " + i +"/"+MAX);
                heap.clear();
                for(int k = 0; k<i; k++){
                    heap.add(k);
                }
                Long start = System.nanoTime();
                try {
                    heap.dequeue();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Long end = System.nanoTime() - start;
                heap_deque_pw.println(i+";"+end);
            }
            this.updateMessage("Done");
            this.updateProgress(MAX, MAX);
            heap_deque_pw.close();
            return null;
        }
    };


}
