package lab03;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/30/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Main {
    public static void main(String[] args){
        int[][] data = new int[3][4];
        int[][] toAdd = new int[3][4];
        for(int row = 0; row < data.length; row++){
            for(int col = 0; col<data[0].length; col++){
                data[row][col] = row;
                toAdd[row][col] = col;
            }
        }
        Matrix matrix = new Matrix(data);
        Matrix matrixToAdd = new Matrix(toAdd);
        System.out.println(matrix.toString());
        System.out.println(matrixToAdd.toString());
        System.out.println(matrix.plus(matrixToAdd).toString());
        System.out.println(matrix.equals(matrix));
        System.out.println(matrix.equals(matrixToAdd));

    }
}
