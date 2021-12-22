package org.jiang;

import org.jiang.matrix.IntegerMatrix;
import org.jiang.matrix.IntegerVector;

public class Main {
    public static void main(String[] args) {
        IntegerMatrix m1 = new IntegerMatrix(new int[]{1, 2}, new int[]{3, 4});
        IntegerMatrix m2 = new IntegerMatrix(m1);

        System.out.println(m1.equals(m2));

        IntegerVector vector = new IntegerVector(1, 2, 3);
        for (int val : vector) {
            System.out.println(val);
        }
        System.out.println(vector.transpose());
    }
}
