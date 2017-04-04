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
public class Matrix {
    int numRows;
    int numColumns;
    int data[][];

    // Constructor with data for new matrix (automatically determines dimensions)
    public Matrix(int data[][]) {
        numRows = data.length; // d.length is the number of 1D arrays in the 2D array
        if(numRows == 0) {
            numColumns = 0;
        } else {
            numColumns = data[0].length; // d[0] is the first 1D array
        }
        this.data = new int[numRows][numColumns]; // create a new matrix to hold the data

        // copy the data over
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                this.data[i][j] = data[i][j];
            }
        }

    }


    @Override
    public String toString() {
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this
		 * 			matrix, as specified on the quiz question.
		 */
        String output = "";
        for (int j = 0; j < this.numRows; j++) {
            output += "";
            for (int i = 0; i < this.numColumns; i++) {
                output += this.data[j][i] + " ";
            }
            output += "\n";
        }
        return output; // placeholder
    }


    public Matrix plus(Matrix matrix) {
		/*
		 * TODO: replace the below return statement with the correct code,
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
        if (this.numColumns != matrix.numColumns || this.numRows != matrix.numRows) {
            return null;
        }
        int[][] answer = new int[this.numRows][this.numColumns];
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                answer[i][j] = this.data[i][j] + matrix.data[i][j];
            }
        }
        Matrix output = new Matrix(answer);
        return output; // placeholder
    }


    /**
     * ********************
     * BONUS ROUND! ONLY CONTINUE BELOW IF YOU ARE SATISFIED WITH YOUR SOLUTION FOR
     * THE ABOVE CODE:
     * {@link #Matrix(int[][])}, {@link #plus(Matrix)}, AND {@link #toString()}
     * *********************
     * */

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
            return false;
        }

		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
        Matrix matrix = (Matrix) other;
        for (int i = 0; i < matrix.numRows; i++) {
            for (int j = 0; j < matrix.numColumns; j++) {
                if (matrix.data[i][j] != this.data[i][j]) {
                    return false;
                }
            }
        }
        return true;// placeholder
    }
}