package org.jiang.matrix;

public class DoubleMatrix implements Matrix<Double> {

    public double[][] matrix;

    @Override
    public int[] getSize() {
        return new int[0];
    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getCols() {
        return 0;
    }

    @Override
    public Matrix<Double> transpose() {
        return null;
    }

    @Override
    public Matrix<Double> scale(double scalar) {
        return null;
    }

    @Override
    public Matrix<Double> add(Matrix<Double> addMatrix) {
        return null;
    }

    @Override
    public Matrix<Double> subtract(Matrix<Double> subMatrix) {
        return null;
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> multMatrix) {
        return null;
    }
}
