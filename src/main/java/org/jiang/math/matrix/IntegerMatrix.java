package org.jiang.math.matrix;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 * A matrix containing only of integer values.
 */
public class IntegerMatrix implements Matrix<Integer>, Iterable<IntegerVector>{

    private final int[][] matrix;

    /**
     * Constructs an integer matrix containing of only zeros that are of size m rows by n columns.
     *
     * @param m rows of the matrix
     * @param n columns of the matrix
     */
    public IntegerMatrix(int m, int n) {
        matrix = new int[m][n];
    }

    /**
     * Constructs an integer matrix containing of the values given.
     *
     * @param matrix The matrix values
     * @throws IllegalArgumentException Thrown if the matrix given is not rectangular
     */
    public IntegerMatrix(int[]... matrix) throws IllegalArgumentException {
        if (MatrixUtil.isJaggedArray(matrix)) throw new IllegalArgumentException("Matrix has to be Rectangular!");
        this.matrix = matrix;
    }

    /**
     * A copy constructor for an IntegerMatrix
     *
     * @param matrix Another matrix object to copy
     */
    public IntegerMatrix(IntegerMatrix matrix) {
        this.matrix = matrix.matrix.clone();
    }

    /**
     * A copy constructor for a DoubleMatrix
     *
     * @param matrix Another matrix object to copy
     */
    public IntegerMatrix(DoubleMatrix matrix) {
        this.matrix = new int[matrix.getRows()][matrix.getCols()];
        // TODO: Finish this constructor
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
    public Integer get(int m, int n) {
        return matrix[m][n];
    }

    /**
     * A method that converts a row into a vector
     *
     * @param row row to convert to a vector
     * @return A vector object of the row's values
     */
    public Matrix<Integer> getVector(int row) {
        return new IntegerVector(matrix[row]);
    }

    @Override
    public Matrix<Integer> transpose() {
        int[][] arr = new int[matrix[0].length][matrix.length];
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix[0].length; m++) {
                arr[m][n] = matrix[n][m];
            }
        }
        return new IntegerMatrix(arr);
    }

    @Override
    public Matrix<Integer> scale(Integer scalar) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                matrix[i][j] *= scalar;
            }
        }
        return this;
    }

    /**
     * Scales the matrix by a double converting it into a DoubleMatrix
     *
     * @param scalar amount to scale the matrix
     * @return A new DoubleMatrix
     */
    public Matrix<Double> scale(double scalar) {
        double[][] result = new double[getRows()][getCols()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return new DoubleMatrix(result);
    }

    @Override
    public Matrix<Integer> add(Matrix<Integer> addMatrix) throws MatrixSizeException {
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
    public Matrix<Integer> subtract(Matrix<Integer> subMatrix) throws MatrixSizeException {
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
    public Matrix<Integer> multiply(Matrix<Integer> multMatrix) throws MatrixSizeException {
        if (getRows() != multMatrix.getCols()) {
            throw new MatrixSizeException(String.format("Can not multiply matrix of size %dx%d with matrix of size %dx%d", multMatrix.getRows(), multMatrix.getCols(), getRows(), getCols()));
        }
        int[][] product = new int[multMatrix.getRows()][getCols()];

        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[i].length; j++) {
                for (int k = 0; k < getRows(); k++) {
                    product[i][j] += multMatrix.get(i, k) * get(k, j);
                }
            }
        }

        return new IntegerMatrix(product);
    }

    /**
     * Checks the equality of the matrices.
     *
     * @param obj Matrix to check
     * @return A boolean if the matrices' content are equal
     */
    public boolean equals(IntegerMatrix obj) {
        return Arrays.deepEquals(obj.matrix, matrix);
    }

    @Override
    public String toString() {
        if (matrix.length == 1) return Arrays.toString(matrix[0]);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) str.append("??? ");
            else if (i == matrix.length - 1) str.append("??? ");
            else str.append("??? ");

            for (int j = 0; j < matrix[i].length; j++) {
                str.append(matrix[i][j]).append(" ");
            }

            if (i == 0) str.append("???\n");
            else if (i == matrix.length - 1) str.append("???\n");
            else str.append("???\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    @Override
    public Iterator<IntegerVector> iterator() {
        return new Iterator<>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return i < getRows();
            }

            @Override
            public IntegerVector next() {
                return new IntegerVector(matrix[i++]);
            }
        };
    }

    /**
     * Converts the current object into a double matrix
     *
     * @return A double matrix copy
     */
    public DoubleMatrix toDoubleMatrix() {
        double[][] copy = new double[getRows()][getCols()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        return new DoubleMatrix(copy);
    }
}
