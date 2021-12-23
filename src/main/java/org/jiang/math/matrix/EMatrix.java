package org.jiang.math.matrix;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A class representing a mathematical EMatrix object of a Number type.
 *
 * @param <E> A Number type within the EMatrix
 */
public class EMatrix<E extends Number> implements Iterable<E[]> {

    private final Number[][] matrix;

    /**
     * Constructor for an empty matrix with size m by n
     *
     * @param m number of rows in the matrix
     * @param n number of columns in the matrix
     */
    public EMatrix(int m, int n) {
        matrix = new Number[m][n];
    }

    /**
     * Constructor for a matrix from an array
     *
     * @param matrix Rectangular array to become a matrix
     * @throws IllegalArgumentException Thrown if the argument is a jagged array
     */
    @SafeVarargs
    public EMatrix(E[]... matrix) throws IllegalArgumentException {
        if (isJaggedArr(matrix)) throw new IllegalArgumentException("EMatrix must be rectangular!");
        this.matrix = matrix;
    }

    private static boolean isJaggedArr(Object[][] data) {
        for (int len = data[0].length, i = 1; i < data.length; i++) {
            if (data[i].length != len) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method to get a value located at [m][n] of the matrix
     *
     * @param m row
     * @param n column
     * @return The value located at the position [m][n]
     */
    @SuppressWarnings("unchecked")
    public E get(int m, int n) {
        return (E) matrix[m][n];
    }

    /**
     * A method to set a value located at [m][n] of the matrix
     *
     * @param value value to set at matrix[m][n]
     * @param m row
     * @param n column
     */
    public void set(E value, int m, int n) {
        matrix[m][n] = value;
    }

    public int[] getSize() {
        return new int[]{matrix.length, matrix[0].length};
    }

    @SuppressWarnings("unchecked")
    public EMatrix<E> transpose() {
        Number[][] arr = new Number[matrix[0].length][matrix.length];
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix[0].length; m++) {
                arr[m][n] = get(n, m);
            }
        }
        return new EMatrix<>((E[][]) arr);
    }

    public EMatrix<E> scale(double scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // matrix[i][j] = (E) (matrix[i][j].doubleValue() * scalar);
            }
        }
        return this;
    }

    @Override
    public Iterator<E[]> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return matrix.length > i;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E[] next() {
                return (E[]) matrix[i++];
            }
        };
    }

    @Override
    public String toString() {
        if (matrix.length == 1) return Arrays.toString(matrix[0]);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) str.append("┌ ");
            else if (i == matrix.length - 1) str.append("└ ");
            else str.append("│ ");

            for (int j = 0; j < matrix[i].length; j++) {
                str.append(matrix[i][j]).append(" ");
            }

            if (i == 0) str.append("┐\n");
            else if (i == matrix.length - 1) str.append("┘\n");
            else str.append("│\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}
