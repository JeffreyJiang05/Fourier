package org.jiang;

import org.jiang.math.complex.Complex;
import org.jiang.math.matrix.IntegerMatrix;
import org.jiang.math.matrix.IntegerVector;

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

        Complex c = new Complex(6, 2);
        System.out.println(c);
        System.out.println(c.divide(new Complex(3, 1)));
    }
}
