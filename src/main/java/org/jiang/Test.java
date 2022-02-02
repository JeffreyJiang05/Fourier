package org.jiang;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.jiang.math.complex.Complex;
import org.jiang.math.fourier.Fourier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static final String RANGE = " range";
    public static final String RANDOM = " random";
    public static final String DOUBLE = " double";
    public static final String COMPLEX = " complex";
    public static final String DISCRETE_COSINE_TRANSFORM = "discreteCosineTransform";
    public static final String DISCRETE_SINE_TRANSFORM = "discreteSineTransform";

    public static void main(String[] args) {

        // timeDoubleFunction(DISCRETE_COSINE_TRANSFORM + RANGE, Fourier::discreteCosineTransform, generateDoubleArray(10000));
        // timeDoubleFunction(DISCRETE_COSINE_TRANSFORM + RANDOM, Fourier::discreteCosineTransform, generateRandomArray(10000, -10000, 10000));

        // timeDoubleFunction(DISCRETE_SINE_TRANSFORM + RANGE, Fourier::discreteFourierTransform, generateDoubleArray(10000));
        // timeDoubleFunction(DISCRETE_SINE_TRANSFORM + RANDOM, Fourier::discreteFourierTransform, generateRandomArray(10000, -10000, 10000));

        /*timeItThread(Fourier::discreteFourierTransform, generateDoubleArray(2<<14), DISCRETE_COSINE_TRANSFORM + DOUBLE + RANGE);
        timeItThread(Fourier::discreteFourierTransform, generateRandomArray(2<<14, -10000, 10000), DISCRETE_COSINE_TRANSFORM + DOUBLE + RANDOM);*/

        // timeItThread(Fourier::discreteFourierTransform, generateDoubleArray(10000), DISCRETE_SINE_TRANSFORM + DOUBLE + RANGE);
        // timeItThread(Fourier::discreteFourierTransform, generateRandomArray(10000, -10000, 10000), DISCRETE_SINE_TRANSFORM + DOUBLE + RANDOM);

        // timeItThread(Fourier::discreteFourierTransform, generateComplexArray(2<<14), DISCRETE_SINE_TRANSFORM + COMPLEX + RANGE);
        timeItThread(Fourier::discreteFourierTransform, generateComplexArray(10000), DISCRETE_SINE_TRANSFORM + COMPLEX + RANGE);
    }

    private static <T> void timeItThread(Consumer<T[]> func, final T[] args, String message) {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            func.accept(args);
            long end = System.currentTimeMillis();
            logger.info("[{}] Executing on a {} sized array took {} milliseconds.", message, args.length, end-start);
        }).start();
    }

    private static void timeItThread(Consumer<double[]> func, final double[] args, String message) {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            func.accept(args);
            long end = System.currentTimeMillis();
            logger.info("[{}] Executing on a {} sized array took {} milliseconds.", message, args.length, end-start);
        }).start();
    }


    public static double[] generateDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static double[] generateDoubleArray(int size, int val) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = val;
        }
        return arr;
    }

    public static Complex[] generateComplexArray(int size) {
        Complex[] arr = new Complex[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Complex(i, i, Complex.Mode.RECTANGULAR);
        }
        return arr;
    }

    public static Complex[] generateComplexArray(int size, Complex val) {
        Complex[] arr = new Complex[size];
        for (int i = 0; i < size; i++) {
            arr[i] = val;
        }
        return arr;
    }

    public static double[] generateRandomArray(int size, int lower, int upper) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ((int) (Math.random() * (upper - lower)) + lower);
        }
        return arr;
    }

}
