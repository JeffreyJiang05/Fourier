package org.jiang.math.matrix;

import java.util.Arrays;

/**
 * A class containing utility Matrix operations
 */
public class MatrixUtil {

    public static boolean isJaggedArray(int[][] arr) {
        for (int len = arr[0].length, i = 1; i < arr.length; i++) {
            if (arr[i].length != len) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJaggedArray(double[][] arr) {
        for (int len = arr[0].length, i = 1; i < arr.length; i++) {
            if (arr[i].length != len) {
                return true;
            }
        }
        return false;
    }

    public static <T, R> boolean isEqualSize(Matrix<T> m1, Matrix<R> m2) {
        return Arrays.equals(m1.getSize(), m2.getSize());
    }

}
