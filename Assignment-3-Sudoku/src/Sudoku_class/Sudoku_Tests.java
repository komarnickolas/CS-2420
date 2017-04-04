package Sudoku_class;

import org.junit.Test;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/27/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Sudoku_Tests {
    @Test
    public void isValidTest(){
        Sudoku sudoku = new Sudoku("Sudoku_class.txt");
        sudoku.solve_sudoku();
    }
}
