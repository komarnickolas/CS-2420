package Sudoku;

import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;

public class Sudoku extends Application{

	private static int[][] board = new int[9][9];
	private static int[][] original_board = new int[9][9];
	private static TextField[][] sudoku = new TextField[9][9];

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = new Stage();
		VBox root = new VBox();
		GridPane gridPane = new GridPane();
		PseudoClass right = PseudoClass.getPseudoClass("right");
		PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Sudoku");
		MenuItem solve = new MenuItem("Solve");
		MenuItem clear = new MenuItem("Clear");
		menu.getItems().addAll(solve, clear);
		menuBar.getMenus().addAll(menu);
		clear.setOnAction(e -> {
			gridPane.getChildren().removeAll();
			for (int col = 0; col < 9; col++) {
				for (int row = 1; row < 10; row++) {
					StackPane cell = new StackPane();
					cell.getStyleClass().add("cell");
					cell.pseudoClassStateChanged(right, col == 2 || col == 5);
					cell.pseudoClassStateChanged(bottom, row == 3 || row == 6);
					TextField textField = new TextField();
					sudoku[row-1][col] = textField;
					cell.getChildren().add(textField);
					gridPane.add(cell, col, row);
				}
			}
		});
		solve.setOnAction(e -> {
			if(getBoard()) {
				solve();
				gridPane.getChildren().removeAll();
				for (int col = 0; col < 9; col++) {
					for (int row = 1; row < 10; row++) {
						StackPane cell = new StackPane();
						cell.getStyleClass().add("cell");
						cell.pseudoClassStateChanged(right, col == 2 || col == 5);
						cell.pseudoClassStateChanged(bottom, row == 3 || row == 6);
						TextField textField = createTextField(board[row - 1][col]);
						sudoku[row - 1][col] = textField;
						if (board[row - 1][col] == original_board[row - 1][col]) {
							textField.setStyle("-fx-text-inner-color: red;");
						}
						cell.getChildren().add(textField);
						gridPane.add(cell, col, row);
					}
				}
			}else{

			}
		});
		for (int col = 0; col < 9; col++) {
			for (int row = 1; row < 10; row++) {
				StackPane cell = new StackPane();
				cell.getStyleClass().add("cell");
				cell.pseudoClassStateChanged(right, col == 2 || col == 5);
				cell.pseudoClassStateChanged(bottom, row == 3 || row == 6);
				TextField textField = new TextField();
				sudoku[row-1][col] = textField;
				cell.getChildren().add(textField);
				gridPane.add(cell, col, row);
			}
		}
		root.getChildren().addAll(menuBar,gridPane);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("Sudoku/stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static boolean getBoard() {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[0].length; j++) {
				TextField current = sudoku[i][j];
				if(current.getText().equals("")){
					board[i][j] = 0;
					original_board[i][j] = 0;
				}else {
					board[i][j] = Integer.parseInt(current.getText());
					original_board[i][j] = Integer.parseInt(current.getText());
//					if(!isValid(i,j)){
//						current.setStyle("-fx-text-inner-color: red;");
//						return false;
//					}
				}
			}
		}
		return true;
	}

	private TextField createTextField(int number) {
		TextField textField = new TextField(""+number);
		return textField;
	}

	public static boolean solve(){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(board[i][j]!=0)
					continue;

				for(int k=1; k<=9; k++){
					board[i][j] = k;
					if(isValid(i, j) && solve())
						return true;
					board[i][j] = 0;
				}

				return false;
			}
		}

		return true; // does not matter
	}

	public static boolean isValid(int i, int j){
		HashSet<Integer> set = new HashSet<Integer>();

		for(int k=0; k<9; k++){
			if(set.contains(board[i][k]))
				return false;

			if(board[i][k]!=0 ){
				set.add(board[i][k]);
			}
		}

		set.clear();

		for(int k=0; k<9; k++){
			if(set.contains(board[k][j]))
				return false;

			if(board[k][j]!=0 ){
				set.add(board[k][j]);
			}
		}

		set.clear();

		for(int m=0; m<3; m++){
			for(int n=0; n<3; n++){
				int x=i/3*3+m;
				int y=j/3*3+n;
				if(set.contains(board[x][y]))
					return false;

				if(board[x][y]!=0){
					set.add(board[x][y]);
				}
			}
		}

		return true;
	}
}
