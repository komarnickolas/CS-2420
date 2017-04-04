package Sudoku_class;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/27/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Sudoku {
    /**
     * Declare fields here as needed.
     *
     * Remember - the puzzle should (for our purposes) be a one dimensional array
     */
    private int[][] puzzle;
    private int guess_count;

    /**
     *  Constructor
     */
    public Sudoku(){
        puzzle = new int[9][9];
        guess_count = 0;
    }

    /**
     * Create a new puzzle by reading a file
     *
     * the file should be 9 rows of 9 numbers separated by whitespace
     *
     */
    public Sudoku(String filename){
        puzzle = new int[9][9];
        guess_count = 0;
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            Object[] lines = stream.toArray();
            for (int row = 0; row < lines.length; row++) {
                String line = (String) lines[row];
                String[] numbers = line.split(" ");
                for(int col =0; col<numbers.length; col++){
                    puzzle[row][col] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return a copy of the puzzle as a 1D matrix
     */
    public int [] get_puzzle_1D(){
        int[] temp = new int[81];
        int spot = 0;
        for(int row = 0; row< puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length; col++) {
                temp[spot] = puzzle[row][col];
                spot++;
            }
            row++;
        }
        return temp;
    }
    /**
     * @return a copy of the puzzle as a 2D matrix
     */
    public int [][] get_puzzle_2D(){
        return  puzzle;
    }

    /**
     * @return how many guesses it took to recursively solve the problem.
     */
    public int get_guess_count(){
        return guess_count;
    }



    /**
     * Function: valid_for_row
     *
     * Description: Determine if the given number can be placed in the
     * given row without violating the rules of sudoku
     *
     * Inputs:
     * @input row : which row to see if the number can go into
     * @input number: the number of interest
     *
     * Outputs:
     *
     * true if it is possible to place that number in the row without
     * violating the rule of 1 unique number per row.
     *
     * In other words, if the given number is already present in the row,
     * it is not possible to place it again (return false), otherwise
     * it is possible to place it (return true);
     *
     * WARNING: call this function before placing the number in the puzzle...
     *
     */
    private boolean valid_for_row( int row, int number ){
        for(int i : puzzle[row]){
            if(i==number){
                return false;
            }
        }
        return true;
    }


    /**
     * Function: valid_for_col (see above)
     */
    private boolean valid_for_column( int col, int number ){
        for(int row = 0; row<puzzle.length; row++){
            if(puzzle[row][col] == number){
                return false;
            }
        }
        return true;
    }

    /**
     * Function: valid_for_box (see above)
     *
     * The sudoku boxes are:
     *
     * 0 | 1 | 2
     * ---+---+---
     * 3 | 4 | 5
     * ---+---+---
     * 6 | 7 | 8
     *
     * where each box represents a 3x3 square in the game.
     *
     */
    private boolean valid_for_box( int box, int number ){
        int[][] selectedBox = getBox(box);
        for(int row = 0; row<selectedBox.length; row++) {
            for(int col = 0; col<selectedBox[0].length; col++) {
                if (selectedBox[row][col] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * Function is_valid( position, value )
     *
     * Determine if the given value is valid in the puzzle at that position.
     *
     * Inputs:
     *
     * @param  position - which bucket in the puzzle to check for validity - should be empty
     * @param possible_value - the value to check (1-9)
     *
     * @return true if valid
     */
    private boolean is_valid( int position, int possible_value ){
        int col = position % 9;
        int row = position/9;
        int box = box(row,col);
        if(valid_for_column(col,possible_value) && valid_for_row(row,possible_value) && valid_for_box(box,possible_value)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param row
     * @param col
     * @return - box that the position is located (0-8)
     */
    private int box(int row, int col){
        if(row < 3 && col <3){
            return 0;
        }
        else if(row < 3 && col > 3 && col < 6){
            return 1;
        }
        else if(row < 3 && col >6){
            return 2;
        }
        else if(row > 3 && row < 6 && col <3){
            return 3;
        }
        else if(row > 3 && row < 6 && col > 3 && col < 6){
            return 4;
        }
        else if(row > 3 && row < 6 && col > 6){
            return 5;
        }
        else if(row > 6 && col < 3){
            return 6;
        }
        else if(row > 6 && col > 3 && col < 6){
            return 7;
        }
        else if(row > 6 && col >6 ){
            return 8;
        }
        return 0;
    }

    /**
     * solve the sudoku problem
     *
     * @return true if successful
     */
    public boolean solve_sudoku( ){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(puzzle[i][j]!=0)
                    continue;

                for(int k=1; k<=9; k++){
                    puzzle[i][j] = k;
                    if(is_valid(i, j) && solve_sudoku())
                        return true;
                    puzzle[i][j] = 0;
                }

                return false;
            }
        }

        return true;
    }

    /**
     *
     * Function solve_sudoku( puzzle, position )
     *
     * Recursively check to see if the puzzle can be solved following class algorithm
     *
     * Inputs:
     * position - the index of the "current" box in the array (should be set to 0 by initial call)
     *
     */
    public boolean solve_sudoku( int position){
        int row = position/9;
        int col = position%9;
        if (filled()){
            return verify();
        }
        if(!empty(position)){
           return solve_sudoku(position +1);
        }else {
            for (int i = 1; i <= 9; i++) {
                if(is_valid(position,i)) {
                    puzzle[row][col] = i;
                    if (solve_sudoku(position + 1)) {
                        return true;
                    }else{
                        puzzle[row][col] = 0;
                    }
                }
            }
            return false;
        }
    }

    private boolean empty(int position){
        if(puzzle[position/9][position%9] == 0){
            return true;
        }
        return false;
    }

    /**
     * Function: toString(  )
     *
     * @return a string showing the state of the board
     *
     */
    @Override
    public String toString(){
        String state = "";
        for(int row = 0; row<puzzle.length; row++){
            for(int col = 0; col<puzzle[0].length; col++){
                state += puzzle[row][col];
                state += " ";
                if(col == 2 || col == 5){
                    state += "|";
                }
            }
            if(row == 2 || row == 5){
                state += "\n-------------------\n";
            }else {
                state += "\n";
            }
        }
        return state;
    }

    /**
     * Given a puzzle (filled or partial), verify that every element does not repeat in row, col, or box.
     *
     * @return true if a validly solved puzzle
     */
    public boolean verify(){
        return filled() && verify_cols() && verify_boxes() && verify_rows() ;
    }

    private boolean filled(){
        for(int r = 0; r<puzzle.length; r++){
            for(int c = 0; c<puzzle[0].length; c++){
                if(puzzle[r][c] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @return true if all rows have only 1-9
     */
    private boolean verify_rows(){
        HashSet<Integer> numbers = new HashSet<>();
        for(int row = 0; row<puzzle.length; row++) {
            for (int num : puzzle[row]) {
                if(num == 0){
                    return false;
                }
                else {
                    if (numbers.contains(num) && num != 0) {
                        return false;
                    }
                    numbers.add(num);
                }
            }
            numbers.clear();
        }
        return true;
    }

    /**
     *
     * @return true if all cols have only 1-9
     */
    private boolean verify_cols(){
        HashSet<Integer> numbers = new HashSet<>();
        for (int col = 0; col < puzzle[0].length; col++) {
            for(int row = 0; row<puzzle.length; row++) {
                if(puzzle[row][col] == 0){
                    return false;
                }else {
                    if (numbers.contains(puzzle[row][col]) && puzzle[row][col] != 0) {
                        return false;
                    }
                    numbers.add(puzzle[row][col]);
                }
            }
            numbers.clear();
        }
        return true;
    }

    /**
     *
     * @return true if all boxes have only 1-9
     */
    private boolean verify_boxes(){
        HashSet<Integer> numbers = new HashSet<>();
        for(int i =0; i<9; i++){
            int[][] box = getBox(i);
            for(int row = 0; row < box.length; row++){
                for(int col = 0; col<box[0].length; col++){
                    if(box[row][col] == 0){
                        return false;
                    }else{
                        if (numbers.contains(box[row][col])) {
                            return false;
                        }
                        numbers.add(box[row][col]);
                    }
                }
                numbers.clear();
            }
        }
        return true;
    }

    /**
     * Attempt to solve a sudoku by eliminating obviously wrong values
     * Algorithm
     *
     *  1) build a 81 (representing 9x9) array of sets
     *  2) put a set of 1-9 in each of the 81 spots
     *  3) do initial elimination
     *      for each known value, eliminate nubmers from sets in same row, col, box
     *      eliminate all values in the given square
     *  4)  while progress is being made (initially true)
     *         find a non-processed square with one possible answer and  eliminate this number from row, col, box
     */
    public void solve_by_elimination() {
    }


    /**
     * print a grid showing all possible valid answers
     * This should be a 27x27 matrix.
     *
     * I would suggest you do this first to get good debugging help
     *
     * @param possibilities - array list of all the sets of 1-9s
     */
    private static void print_possibilities( ArrayList<HashSet<Integer>> possibilities ){
        ArrayList<Integer> all = new ArrayList<>();
        for(HashSet<Integer> set : possibilities){
            for(int i : set){
                all.add(i);
            }
        }

    }

    /**
     * Given a possibility constraint matrix (81 sets of [1-9]) remove the given number
     * from every matrix in the given box
     *
     * @param possibilities - 81 sets of [1-9]
     * @param position      - where the value exists (transform to row,col)
     * @param value         - the value to prune
     */
    private static void prune_box( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ){

    }
    private static void prune_column( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ){

    }
    private static void prune_row( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ){

    }

    /**
     *
     * @param boxNumber - the box to return (1-9)
     *                  |1|2|3|
     *                  |4|5|6|
     *                  |7|8|9|
     * @return - array of numbers in Box
     */
    private int[][] getBox(int boxNumber){
        int[][] box = new int[3][3];
        int boxRow = 0;
        int boxCol = 0;
        switch (boxNumber){
            case 0:
                for(int row = 0; row<3; row++){
                    for(int col = 0; col<3;col++) {
                        box[row][col] = puzzle[row][col];
                    }
                }
                break;
            case 1:
                for(int row = 0; row<3; row++){
                    for(int col = 3; col<6;col++) {
                        box[row][boxCol] = puzzle[row][col];
                        boxCol++;
                    }
                    boxCol = 0;
                }
                break;
            case 2:
                for(int row = 0; row<3; row++){
                    for(int col = 6; col<9;col++) {
                        box[row][boxCol] = puzzle[row][col];
                        boxCol++;
                    }
                    boxCol = 0;
                }
                break;
            case 3:
                for(int row = 3; row<6; row++){
                    for(int col = 0; col<3;col++) {
                        box[boxRow][col] = puzzle[row][col];
                        boxRow++;
                    }
                    boxRow = 0;
                }
                break;
            case 4:
                for(int row = 3; row<6; row++){
                    for(int col = 3; col<6;col++) {
                        box[boxRow][boxCol] = puzzle[row][col];
                        boxRow++;
                    }
                    boxCol = 0;
                    boxRow = 0;
                }
                break;
            case 5:
                for(int row = 3; row<6; row++){
                    for(int col = 6; col<9;col++) {
                        box[boxRow][boxCol] = puzzle[row][col];
                        boxCol++;
                        boxRow++;
                    }
                    boxCol = 0;
                    boxRow = 0;
                }
                break;
            case 6:
                for(int row = 6; row<9; row++){
                    for(int col = 0; col<3;col++) {
                        box[boxRow][col] = puzzle[row][col];
                        boxRow++;
                    }
                    boxRow = 0;
                }
                break;
            case 7:
                for(int row = 6; row<9; row++){
                    for(int col = 3; col<6;col++) {
                        box[boxRow][boxCol] = puzzle[row][col];
                        boxCol++;
                        boxRow++;
                    }
                    boxCol = 0;
                    boxRow = 0;
                }
                break;
            case 8:
                for(int row = 6; row<9; row++){
                    for(int col = 6; col<9;col++) {
                        box[boxRow][boxCol] = puzzle[row][col];
                        boxCol++;
                        boxRow++;
                    }
                    boxCol = 0;
                    boxRow = 0;
                }
                break;
        }
        return box;
    }
}