package cs2420;

import Tests.TestPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by komar on 4/28/2017.
 */
public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        TestPane root = new TestPane();
        root.addTests(new HuffmanTest("Resources/alphabet",100,1),
                new HuffmanTest("Resources/alphabetplus",100,1),
                new HuffmanTest("Resources/a_few_letters",100,1),
                new HuffmanTest("Resources/simple",100,1),
                new HuffmanTest("Resources/decl_of_ind",100,1),
                new HuffmanTest("Resources/constitution",100,1),
                new HuffmanTest("Resources/two_cities",100,1),
                new HuffmanTest("Resources/green_eggs_and_ham",100,1));
        root.setExec();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
