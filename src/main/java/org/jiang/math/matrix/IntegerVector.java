package org.jiang.math.matrix;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class representing a vector object only containing integer values
 */
public class IntegerVector implements Matrix<Integer>, Iterable<Integer> {

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
    public Matrix<Integer> scale(Integer scalar) {
        for (int i = 0; i < getRows(); i++) {
            vector[i] *= scalar;
        }
        return this;
    }

    /**
     * Scales the vector by a double converting it into a DoubleVector
     *
     * @param scalar amount to scale the matrix
     * @return A new DoubleVector
     */
    public Matrix<Double> scale(double scalar) {
        double[] result = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            result[i] = vector[i] * scalar;
        }
        return new DoubleVector(result);
    }

    @Override
    public Matrix<Integer> add(Matrix<Integer> addMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] += addMatrix.get(i, 0);
        }
        return this;
    }

    public Matrix<Double> add(DoubleMatrix addMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        double[] sum = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            sum[i] = vector[i] + addMatrix.get(i, 0);
        }
        return new DoubleVector(sum);
    }

    @Override
    public Matrix<Integer> subtract(Matrix<Integer> subMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, subMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] -= subMatrix.get(i, 0);
        }
        return this;
    }

    @Override
    public Matrix<Integer> multiply(Matrix<Integer> multMatrix) throws MatrixSizeException {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        int product = 0;
        for (int k = 0; k < getRows(); k++) {
            product += multMatrix.get(0, k) * get(k, 0);
        }
        return new IntegerVector(product);
    }

    /**
     * Returns a clone of the vectors content as an array. Data remain encapsulated.
     *
     * @return A clone of the vector
     */
    public int[] toArray() {
        return vector.clone();
    }

    /**
     * Checks the equality of the vectors
     *
     * @param obj vector to compare
     * @return A boolean if the vectors' content are equal
     */
    public boolean equals(IntegerVector obj) {
        return Arrays.equals(obj.vector, vector);
    }

    /**
     * Checks the equality of the vectors
     *
     * @param obj vector to compare
     * @return A boolean if the vectors' content are equal
     */
    public boolean equals(DoubleVector obj) {
        double[] copy = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            copy[i] = vector[i];
        }
        return Arrays.equals(obj.toArray(), copy);
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

    /**
     * Converts the current object into a double vector
     *
     * @return A double vector
     */
    public DoubleVector toDoubleVector() {
        double[] copy = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            copy[i] = vector[i];
        }
        return new DoubleVector(copy);
    }
    
}
