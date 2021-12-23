package org.jiang;

import org.jiang.math.complex.Complex;
import org.jiang.math.matrix.IntegerMatrix;
import org.jiang.math.matrix.IntegerVector;

public class Main {
    public static void main(String[] args) {
        /*Complex c = new Complex(3 * Math.PI / 2, 1, Complex.Mode.POLAR);
        System.out.println(c);
        System.out.println(c.getArgument());
        // System.out.println(5 * Math.PI / 3);
        System.out.println(c.modulus());*/

        IntegerMatrix matrix = new IntegerMatrix(new int[]{1, 2, 3});
        IntegerMatrix orig = new IntegerMatrix(new int[]{4}, new int[]{5}, new int[]{6});
        System.out.println(matrix.multiply(orig));
    }
}
