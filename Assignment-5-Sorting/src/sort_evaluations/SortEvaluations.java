package sort_evaluations;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * u0717854
 * 2/11/2017
 * 2420
 * Assignment 05
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 *
 * Creates a gui for viewing the output of Sort_Utils timing tests
 */
public class SortEvaluations extends Application {

    private Sort_Utils sort_utils = new Sort_Utils();
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        GridPane root = new GridPane();
        ArrayList<Sorter<Integer>> sort_methods = new ArrayList<>();
//        sort_methods.add(new Java_Sort<>());
//        sort_methods.add(new Merge_Sort<>());
//        sort_methods.add(new Shell_Sort<>());
//        sort_methods.add(new Insertion_Sort<>());
        sort_methods.add(new Quick_Sort_Naive<>());
        sort_methods.add(new Quick_Sort_Inplace_M3<>());
//        sort_methods.add(new Quick_Sort_Inplace_Random_Pivot<>());
//        sort_methods.add(new Quick_Sort_Inplace_First_Pivot<>());
        int col = 0;
        int row = 0;
        int i =4;
        for(Sorter<Integer> sorter : sort_methods){
            sort_utils.test_and_time(sorter,0,10,10000 ,Integer.MAX_VALUE);
            TableView current = createTable();
            root.add(sort_utils.current_sort_label,col,row);
            root.add(sort_utils.array_unsorted,col,row+1);
            root.add(sort_utils.array_sorted,col,row+2);
            root.add(current,col,row+3);
            writeFile("Timing"+i+".txt", sort_utils.data, sorter.name_of_sort());
            i++;
            sort_utils.data.clear();
            col++;
            if(col % 4 == 0){
                col = 0;
                row += 4;
            }
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static boolean writeFile(String outputFile, ObservableList<Timing_Data> data, String name) throws IOException {
        try {
            FileWriter fw = new FileWriter(outputFile);
            PrintWriter pr = new PrintWriter(fw);
            pr.println(name);
            pr.println("Count;Sorted;Reverse;Random;Same");
            for(Timing_Data td : data){
                pr.println(""+td.getCount_data()+";"+td.getSorted_data()+";"+td.getReverse_data()+";"+td.getRandom_data()+";"+td.getSame_data());
            }
            pr.println();
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     *
     * @return - Table containing columns containg the timing data from sort utils
     */
    public TableView createTable(){
        TableView<Timing_Data> tableView = new TableView<>();
        TableColumn count_collumn = new TableColumn("count");
        TableColumn sorted_collumn = new TableColumn("sorted");
        TableColumn reverse_collumn = new TableColumn("reverse");
        TableColumn random_collumn = new TableColumn("random");
        TableColumn same_collumn = new TableColumn("same");
        count_collumn.setCellValueFactory(new PropertyValueFactory<>("count_data"));
        sorted_collumn.setCellValueFactory(new PropertyValueFactory<>("sorted_data"));
        reverse_collumn.setCellValueFactory(new PropertyValueFactory<>("reverse_data"));
        random_collumn.setCellValueFactory(new PropertyValueFactory<>("random_data"));
        same_collumn.setCellValueFactory(new PropertyValueFactory<>("same_data"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().setAll(count_collumn,sorted_collumn, reverse_collumn,random_collumn,same_collumn);
        ObservableList<Timing_Data> data = FXCollections.observableArrayList();
        for(Timing_Data d: sort_utils.data) {
            data.add(d);
        }
        tableView.setItems(data);
        return tableView;
    }
}
