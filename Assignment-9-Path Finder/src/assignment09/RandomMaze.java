package assignment09;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 3/28/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class RandomMaze {
    private int rows;
    private int columns;
    private Node[][] nodes;
    private Node start;
    private Node goal;
    private Random rng;

    public RandomMaze(int rows, int columns) {
        rng = new Random();
        setSize(rows, columns);
    }

    /**
     * randomize places the nodes in the correct spots and initializes them
     */
    public void randomize() {
        this.nodes = new Node[this.rows][this.columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (row == 0 || row == rows - 1 || column == 0 || column == columns - 1) {
                    nodes[row][column] = new Node(-1);
                    nodes[row][column].fileSymbol = "X";
                }
            }
        }

        int randStartRow = (rng.nextInt(rows - 2) + 1);
        int randStartColumn = (rng.nextInt(columns - 2) + 1);

        int randGoalRow = (rng.nextInt(rows - 2) + 1);
        int randGoalColumn = (rng.nextInt(columns - 2) + 1);

        nodes[randStartRow][randStartColumn] = new Node(1);
        nodes[randStartRow][randStartColumn].fileSymbol = "S";
        while (randGoalRow == randStartRow && randGoalColumn == randStartColumn) {
            randGoalRow = (rng.nextInt(rows - 2) + 1);
            randGoalColumn = (rng.nextInt(columns - 2) + 1);
        }
        nodes[randGoalRow][randGoalColumn] = new Node(2);
        nodes[randGoalRow][randGoalColumn].fileSymbol = "G";

        start = nodes[randStartRow][randStartColumn];
        goal = nodes[randGoalRow][randGoalColumn];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (nodes[row][column] == null) {
                    int randomNum = rng.nextInt(1000);
                    if (randomNum < 300) {
                        nodes[row][column] = new Node(-1);
                        nodes[row][column].fileSymbol = "X";
                    }
                    else {
                        nodes[row][column] = new Node(0);
                        nodes[row][column].fileSymbol = " ";
                    }
                }
            }
        }
    }

    public boolean writeFile(String outputFile) throws IOException {
        try {
            FileWriter fw = new FileWriter(outputFile);
            PrintWriter pr = new PrintWriter(fw);
            pr.println(nodes.length + " " + nodes[0].length);
            for (int row = 0; row < nodes.length; row++) {
                for (int col = 0; col < nodes[0].length; col++) {
                    pr.write(nodes[row][col].fileSymbol);
                }
                pr.println();
            }
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setSize(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        if (rows <= 3) {
            this.rows = 4;
        }
        if (columns <= 3) {
            this.columns = 4;
        }
        nodes = new Node[this.rows][this.columns];
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }
}
