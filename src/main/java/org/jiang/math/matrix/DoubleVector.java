package org.jiang.math.matrix;

import java.util.Arrays;
import java.util.Iterator;

public class DoubleVector implements Matrix<Double>, Iterable<Double>{

    private final double[] vector;

    /**
     * Constructs a double vector from a provided size.
     *
     * @param size Size of the vector
     */
    public DoubleVector(int size) {
        vector = new double[size];
    }

    /**
     * Constructs a double vector from provided values.
     *
     * @param values values of the vector
     */
    public DoubleVector(double... values) {
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
     * @return The double value found at the index of the vector
     */
    public double get(int index) {
        return vector[index];
    }

    /**
     * Method to return the value located at the row and column of a matrix. You should use {@link #get(int)} instead.
     *
     * @param m row of the matrix
     * @param n redundant argument
     * @return The double value found at the m row of the vector
     */
    @Override
    public Double get(int m, int n) {
        return get(m);
    }

    @Override
    public Matrix<Double> transpose() {
        double[][] transposed = new double[1][getRows()];
        for (int i = 0; i < getRows(); i++) {
            transposed[0][i] = get(i);
        }
        return new DoubleMatrix(transposed);
    }

    @Override
    public Matrix<Double> scale(Double scalar) {
        for (int i = 0; i < getRows(); i++) {
            vector[i] *= scalar;
        }
        return this;
    }

    /**
     * Scales the vector by an integer scalar
     *
     * @param scalar value to scale the vector by
     * @return the scaled vector instance
     */
    public Matrix<Double> scale(int scalar) {
        for (int i = 0; i < getRows(); i++) {
            vector[i] *= scalar;
        }
        return this;
    }

    @Override
    public Matrix<Double> add(Matrix<Double> addMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] += addMatrix.get(i, 0);
        }
        return this;
    }

    /**
     * Adds an integer vector to the instance
     *
     * @param addMatrix an integer vector
     * @return the instance after adding the vector
     */
    public Matrix<Double> add(IntegerVector addMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] += addMatrix.get(i, 0);
        }
        return this;
    }

    @Override
    public Matrix<Double> subtract(Matrix<Double> subMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, subMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] -= subMatrix.get(i, 0);
        }
        return this;
    }

    /**
     * Subtracts an integer matrix from the instance
     *
     * @param subMatrix an integer matrix
     * @return the instance after subtracting the matrix
     */
    public Matrix<Double> subtract(IntegerVector subMatrix) throws MatrixSizeException {
        if (!MatrixUtil.isEqualSize(this, subMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < getRows(); i++) {
            vector[i] -= subMatrix.get(i, 0);
        }
        return this;
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> multMatrix) throws MatrixSizeException {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        double product = 0;
        for (int k = 0; k < getRows(); k++) {
            product += multMatrix.get(0, k) * get(k, 0);
        }
        return new DoubleVector(product);
    }

    /**
     * Multiplies a given matrix to the instance matrix. The order of the multiplication are as follows:
     * multMatrix * instance as matrix multiplication is read right to left.
     *
     * @param multMatrix The matrix to multiply to the instance
     * @return The instance after multiplying the multMatrix
     * @throws MatrixSizeException thrown when the addMatrix is not the same size as the instance
     */
    public Matrix<Double> multiply(IntegerMatrix multMatrix) throws MatrixSizeException {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        double product = 0;
        for (int k = 0; k < getRows(); k++) {
            product += multMatrix.get(0, k) * get(k, 0);
        }
        return new DoubleVector(product);
    }

    /**
     * Returns a clone of the vectors content as an array. Data remain encapsulated.
     *
     * @return A clone of the vector
     */
    public double[] toArray() {
        return vector.clone();
    }

    /**
     * Checks the equality of the vectors
     *
     * @param obj vector to compare
     * @return A boolean if the vectors' content are equal
     */
    public boolean equals(DoubleVector obj) {
        return Arrays.equals(obj.vector, vector);
    }

    /**
     * Checks the equality of the vectors
     *
     * @param obj vector to compare
     * @return A boolean if the vectors' content are equal
     */
    public boolean equals(IntegerVector obj) {
        double[] copy = new double[getRows()];
        for (int i = 0; i < obj.getRows(); i++) {
            copy[i] = obj.get(i);
        }
        return Arrays.equals(vector, copy);
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return i < getRows();
            }

            @Override
            public Double next() {
                return get(i++);
            }
        };
    }
}
