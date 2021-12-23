package org.jiang;

import org.jiang.math.complex.Complex;
import org.jiang.math.matrix.IntegerMatrix;
import org.jiang.math.matrix.IntegerVector;

public class Main {
    public static void main(String[] args) {
        Complex c = new Complex(0, 1, Complex.Mode.POLAR);
        System.out.println(c);
        System.out.println(c.getArgument());
        // System.out.println(5 * Math.PI / 3);
        System.out.println(c.modulus());
    }
}
