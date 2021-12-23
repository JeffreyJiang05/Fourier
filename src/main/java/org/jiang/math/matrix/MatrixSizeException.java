package org.jiang.math.matrix;

public class MatrixSizeException extends RuntimeException {

    private String msg;

    public MatrixSizeException(String msg) {
        super(msg);
    }

}
