package org.jiang.matrix;

import java.util.Iterator;

/**
 * Class representing a vector object only containing integer values
 */
public class IntegerVector implements Matrix<Integer>, Iterable<Integer>{

    private final int[] vector;

    /**
     * Constructs an integer vector from a provided size.
     *
     * @param size Size of the vector
     */
    public IntegerVector(int size) {
        vector = new int[size];
    }

    /**
     * Constructs an integer vector from provided values.
     *
     * @param values values of the vector
     */
    public IntegerVector(int... values) {
        vector = values;
    }

    @Override
    public int[] getSize() {
        return new int[]{vector.length};
    }

    @Override
    public int getRows() {
        return vector.length;
    }

    /**
     * Method that returns the number of columns in a vector which is 0
     *
     * @return 0 as a vector does not contain any columns
     */
    @Override
    public int getCols() {
        return 0;
    }

    /**
     * A method returning the value located at a row of a matrix.
     *
     * @param index index of the vector
     * @return The integer value found at the index of the vector
     */
    public int get(int index) {
        return vector[index];
    }

    /**
     * Method to return the value located at the row and column of a matrix. You should use {@link #get(int)} instead.
     *
     * @param m row of the matrix
     * @param n redundant value
     * @return The integer value found at the m row of the vector
     */
    @Override
    public Integer get(int m, int n) {
        return get(m);
    }

    @Override
    public Matrix<Integer> transpose() {
        int[][] transposed = new int[1][getRows()];
        for (int i = 0; i < getRows(); i++) {
            transposed[0][i] = get(i);
        }
        return new IntegerMatrix(transposed);
    }

    @Override
    public Matrix<Integer> scale(double scalar) {
        for (int i = 0; i < getRows(); i++) {
            vector[i] *= scalar;
        }
        return this;
    }

    @Override
    public Matrix<Integer> add(Matrix<Integer> addMatrix) throws MatrixSizeException {
        return null;
    }

    @Override
    public Matrix<Integer> subtract(Matrix<Integer> subMatrix) throws MatrixSizeException {
        return null;
    }

    @Override
    public Matrix<Integer> multiply(Matrix<Integer> multMatrix) throws MatrixSizeException {
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return i < getRows();
            }

            @Override
            public Integer next() {
                return get(i++);
            }
        };
    }
}
