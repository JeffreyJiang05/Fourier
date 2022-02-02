package org.jiang;

import org.jiang.math.complex.Complex;
import org.jiang.math.fourier.Fourier;

import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        /*Complex c = new Complex(3 * Math.PI / 2, 1, Complex.Mode.POLAR);
        System.out.println(c);
        System.out.println(c.getArgument());
        // System.out.println(5 * Math.PI / 3);
        System.out.println(c.modulus());*/

        /*IntegerMatrix matrix = new IntegerMatrix(new int[]{1, 2, 3});
        IntegerMatrix orig = new IntegerMatrix(new int[]{4}, new int[]{5}, new int[]{6});
        System.out.println(matrix.multiply(orig));*/

        // System.out.println(Complex.exp(new Complex(1, Math.PI / 6, Complex.Mode.COORDINATE)));
        /*timeIt(Fourier::discreteFourierTransform, new Complex[]{
                new Complex(1, 0, Complex.Mode.COORDINATE),
                new Complex(2, 0, Complex.Mode.COORDINATE),
                new Complex(3, 0, Complex.Mode.COORDINATE),
                new Complex(4, 0, Complex.Mode.COORDINATE)
        });*/

        /*System.out.println(Arrays.toString(Fourier.discreteFourierTransform(new Complex[]{
                new Complex(1, 0, Complex.Mode.RECTANGULAR),
                new Complex(2, 0, Complex.Mode.RECTANGULAR),
                new Complex(3, 0, Complex.Mode.RECTANGULAR),
                new Complex(4, 0, Complex.Mode.RECTANGULAR)
        })));*/

        // System.out.println(Arrays.toString(Fourier.discreteFourierTransform(new double[]{1,2,3,4})));

        // System.out.println(Arrays.toString(Fourier.discreteCosineTransform(new double[]{1,2,3,4,5,6})));

        System.out.println(Arrays.toString(Fourier.discreteFourierTransform(new double[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(Fourier.discreteFourierTransform(new double[]{1,2,3,4,5,0,0,0})));

        System.out.println(Arrays.toString(Fourier.cooleyFastFourierTransform(new double[]{1,2,3,4,5})));
    }

/*    public static void timeIt(Function<Complex[], Complex[]> func, Complex[] args) {
        double start = System.currentTimeMillis();
        System.out.println(Arrays.toString(args));
        System.out.println(Arrays.toString(func.apply(args)));
        double end = System.currentTimeMillis();
        System.out.println("Took " + (end - start) + " ms.");
    }*/
}
