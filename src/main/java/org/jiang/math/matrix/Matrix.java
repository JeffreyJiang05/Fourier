package org.jiang.math.matrix;

/**
 * An interface representing a Matrix object containing number values
 *
 * @param <T> The type of the matrix
 */
public interface Matrix<T> {

    /**
     * A method returning the size of the matrix.
     *
     * @return An int array of length two containing the dimensions of the matrix in [row, col]
     */
    int[] getSize();

    /**
     * A method returning the number of rows in the matrix.
     *
     * @return The number of rows in the matrix
     */
    int getRows();

    /**
     * A method returning the number of columns in the matrix.
     *
     * @return The number of columns in the matrix
     */
    int getCols();

    /**
     * A method returning the value located at row m and column n of the matrix
     *
     * @param m row of the matrix
     * @param n col of the matrix
     * @return The value located at row m and col n of the matrix
     */
    T get(int m, int n);

    /**
     * Creates a new matrix that is the transposed of the matrix instance.
     *
     * @return A transposed matrix
     */
    Matrix<T> transpose();

    /**
     * Scales a matrix by the scalar given.
     *
     * @param scalar The value to scale the matrix by
     * @return The scaled matrix instance.
     */
    Matrix<T> scale(T scalar);

    /**
     * Adds a given matrix to the instance matrix.
     *
     * @param addMatrix The matrix to add to the instance
     * @return The instance after adding the addMatrix
     * @throws MatrixSizeException thrown when the addMatrix is not the same size as the instance
     */
    Matrix<T> add(Matrix<T> addMatrix) throws MatrixSizeException;

    /**
     * Subtracts the instance matrix by the given matrix.
     *
     * @param subMatrix The matrix to subtract from the instance
     * @return The instance after subtracting the subMatrix
     * @throws MatrixSizeException thrown when the addMatrix is not the same size as the instance
     */
    Matrix<T> subtract(Matrix<T> subMatrix) throws MatrixSizeException;

    /**
     * Multiplies a given matrix to the instance matrix. The order of the multiplication are as follows:
     * multMatrix * instance as matrix multiplication is read right to left.
     *
     * @param multMatrix The matrix to multiply to the instance
     * @return The instance after multiplying the multMatrix
     * @throws MatrixSizeException thrown when the addMatrix is not the same size as the instance
     */
    Matrix<T> multiply(Matrix<T> multMatrix) throws MatrixSizeException;

}
