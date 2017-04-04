package AStar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/24/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Main extends Application{

    private FileChooser fileChooser = new FileChooser();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        VBox root = new VBox();
        PathFinder pathFinder = new PathFinder();
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem open = new MenuItem("Open");
        open.setOnAction(event -> {
            File maze = fileChooser.showOpenDialog(null);
            pathFinder.solveMaze(maze.getAbsolutePath(),"Resources/mazeSolutions/"+maze.getName().substring(0,maze.getName().length()-4) +"Sol.txt");
        });
        file.getItems().add(open);
        menuBar.getMenus().add(file);
        root.getChildren().addAll(menuBar,pathFinder);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
