package org.jiang.math.matrix;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A matrix containing only of double values.
 */
public class DoubleMatrix implements Matrix<Double>, Iterable<DoubleVector> {

    public double[][] matrix;

    /**
     * Constructs a double matrix containing of only zeros that are of size m rows by n columns.
     *
     * @param m rows of the matrix
     * @param n columns of the matrix
     */
    public DoubleMatrix(int m, int n) {
        matrix = new double[m][n];
    }

    /**
     * Constructs a double matrix containing of the values given.
     *
     * @param matrix The matrix values
     * @throws IllegalArgumentException Thrown if the matrix given is not rectangular
     */
    public DoubleMatrix(double[]... matrix) throws IllegalArgumentException {
        if (MatrixUtil.isJaggedArray(matrix)) throw new IllegalArgumentException("Matrix has to be Rectangular!");
        this.matrix = matrix;
    }

    /**
     * A copy constructor for a DoubleMatrix
     *
     * @param matrix Another matrix object to copy
     */
    public DoubleMatrix(DoubleMatrix matrix) {
        this.matrix = matrix.matrix.clone();
    }

    /**
     * A copy constructor for a IntegerMatrix
     *
     * @param m Another matrix object to copy
     */
    public DoubleMatrix(IntegerMatrix m) {
        this.matrix = new double[m.getRows()][m.getCols()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = m.get(i, j);
            }
        }
    }

    @Override
    public int[] getSize() {
        return new int[]{matrix.length, matrix[0].length};
    }

    @Override
    public int getRows() {
        return matrix.length;
    }

    @Override
    public int getCols() {
        return matrix[0].length;
    }

    @Override
    public Double get(int m, int n) {
        return matrix[m][n];
    }

    /**
     * A method that converts a row into a vector
     *
     * @param row row to convert to a vector
     * @return A vector object of the row's values
     */
    public Matrix<Double> getVector(int row) {
        return null; // TODO
    }

    @Override
    public Matrix<Double> transpose() {
        double[][] arr = new double[matrix[0].length][matrix.length];
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix[0].length; m++) {
                arr[m][n] = matrix[n][m];
            }
        }
        return new DoubleMatrix(arr);
    }

    @Override
    public Matrix<Double> scale(Double scalar) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                matrix[i][j] *= scalar;
            }
        }
        return this;
    }

    /**
     * Scales the matrix by an integer scalar
     *
     * @param scalar value to scale the matrix by
     * @return the scaled matrix instance
     */
    public Matrix<Double> scale(int scalar) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                matrix[i][j] *= scalar;
            }
        }
        return this;
    }

    @Override
    public Matrix<Double> add(Matrix<Double> addMatrix) {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] += addMatrix.get(i, j);
            }
        }
        return this;
    }

    /**
     * Adds an integer matrix to the instance
     *
     * @param addMatrix an integer matrix
     * @return the instance after adding the matrix
     */
    public Matrix<Double> add(IntegerMatrix addMatrix) {
        if (!MatrixUtil.isEqualSize(this, addMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] += addMatrix.get(i, j);
            }
        }
        return this;
    }

    @Override
    public Matrix<Double> subtract(Matrix<Double> subMatrix) {
        if (!MatrixUtil.isEqualSize(this, subMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] -= subMatrix.get(i, j);
            }
        }
        return this;
    }

    /**
     * Subtracts an integer matrix from the instance
     *
     * @param subMatrix an integer matrix
     * @return the instance after subtracting the matrix
     */
    public Matrix<Double> subtract(IntegerMatrix subMatrix) {
        if (!MatrixUtil.isEqualSize(this, subMatrix))
            throw new MatrixSizeException("The matrices being added together have to be the same size!");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] -= subMatrix.get(i, j);
            }
        }
        return this;
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> multMatrix) {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        double[][] product = new double[multMatrix.getRows()][getCols()];

        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[i].length; j++) {
                for (int k = 0; k < getRows(); k++) {
                    product[i][j] += multMatrix.get(i, k) * get(k, j);
                }
            }
        }

        return new DoubleMatrix(product);
    }

    /**
     * Multiply a matrix to the instance
     *
     * @param multMatrix an integer matrix
     * @return the instance after multiplying the matrix to it
     */
    public Matrix<Double> multiply(IntegerMatrix multMatrix) {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        double[][] product = new double[multMatrix.getRows()][getCols()];

        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[i].length; j++) {
                for (int k = 0; k < getRows(); k++) {
                    product[i][j] += multMatrix.get(i, k) * get(k, j);
                }
            }
        }

        return new DoubleMatrix(product);
    }

    public boolean equals(DoubleMatrix obj) {
        return Arrays.deepEquals(obj.matrix, matrix);
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

    @Override
    public Iterator<DoubleVector> iterator() {
        return null;
    }
}
