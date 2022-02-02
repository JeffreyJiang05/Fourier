package org.jiang.math.fourier;

import org.jiang.math.complex.Complex;

import java.util.Arrays;

/**
 * Class containing the fourier transformation algorithms
 */
public class Fourier {

    /**
     * A method calculating the discrete fourier transform (DFT) from a given array of complex numbers.
     * Order of the algorithm is O(n<sup>2</sup>)
     *
     * @param data An array of complex numbers to perform the DFT on
     * @return The transformed array after performing the DFT
     */
    public static Complex[] discreteFourierTransform(Complex[] data) {
        Complex[] transformArr = new Complex[data.length];

        for (int k = 0; k < transformArr.length; k++) {
            Complex sum = new Complex(0, 0, Complex.Mode.RECTANGULAR);
            for (int n = 0; n < data.length; n++) {
                sum.add(
                        new Complex(data[n])
                                .multiply(Complex.exp(new Complex(0, (-2 * Math.PI * k * n) / data.length, Complex.Mode.RECTANGULAR)))
                );
            }
            transformArr[k] = sum;
        }
        return transformArr;
    }

    /**
     * A method calculating the discrete fourier transform from a given array of real numbers.
     * The order of the algorithm is O(n<sup>2</sup>)
     *
     * @param data An array of real numbers to perform the DFT on
     * @return The transformed array after performing the DFT
     */
    public static Complex[] discreteFourierTransform(double[] data) {
        Complex[] transformArr = new Complex[data.length];

        for (int k = 0; k < transformArr.length; k++) {
            Complex sum = new Complex();
            for (int n = 0; n < data.length; n++) {
                sum.add(
                        Complex.exp(new Complex(0, (-2 * Math.PI * k * n) / data.length, Complex.Mode.RECTANGULAR))
                                .multiply(data[n])
                );
            }
            transformArr[k] = sum;
        }
        return transformArr;
    }

    /**
     * A method calculating the discrete cosine transform (DCT) from a given array of real numbers.
     * The order of the algorithm is O(n<sup>2</sup>)
     *
     * @param data An array of real numbers to perform the DCT on
     * @return The transformed array after performing the DCT
     */
    public static double[] discreteCosineTransform(double[] data) {
        double[] transformed = new double[data.length];
        for (int k = 0; k < transformed.length; k++) {
            double sum = 0;
            for (int n = 0; n < data.length; n++) {
                sum += data[n] * Math.cos(Math.PI / data.length * (n + 0.5) * k );
            }
            transformed[k] = sum;
        }
        return transformed;
    }

    private static Complex[] cooleyFastFourierTransform(Complex[] data, Complex[] arr, int start, int size, int stride) {
        if (size == 1) {
            arr[0] = data[0];
        } else {
            System.arraycopy(cooleyFastFourierTransform(data, arr, 0, size / 2, 2 * stride), 0, data, 0, size / 2);
            System.arraycopy(cooleyFastFourierTransform(data, arr, start + stride, size / 2, 2 * stride), 0, data, size / 2, size / 2);
            // System.arraycopy(data, 0, cooleyFastFourierTransform(data, arr, 0, size / 2, 2 * stride),0, size / 2);
            // System.arraycopy(data, size / 2, cooleyFastFourierTransform(data, arr, start + stride, size / 2, 2 * stride), size / 2, size);

            for (int k = 0; k < size / 2 + 1; k++) {
                Complex p = data[k];
                Complex q = Complex.exp(
                        new Complex(0, -2 * Math.PI * k / size, Complex.Mode.RECTANGULAR))
                        .multiply(data[k + size / 2]);
                arr[k] = p.add(q);
                arr[k + size / 2] = p.subtract(q);
            }
        }
        return arr;
    }

    private static void copyToComplex(Complex[] data, Complex[] copy, int start, int end) {
        // new Complex(data[i], 0, Complex.Mode.RECTANGULAR);
        if (end - start >= 0) System.arraycopy(data, start, copy, start, end - start);
    }

    private static Complex[] cooleyFastFourierTransform(double[] data, Complex[] arr, int start, int size, int stride) {
        if (size == 1) {
            arr[0] = new Complex(data[0], 0, Complex.Mode.RECTANGULAR);
        } else {
            copyToComplex(cooleyFastFourierTransform(data, arr, 0, size / 2, size * 2), arr, 0, size / 2);
            copyToComplex(cooleyFastFourierTransform(data, arr, size / 2, size, stride * 2), arr, size / 2, size);

            for (int k = 0; k < size / 2 + 1; k++) {
                double p = data[k];
                Complex q = Complex.exp(
                                new Complex(0, -2 * Math.PI * k / size, Complex.Mode.RECTANGULAR))
                        .multiply(data[k + size / 2]);
                arr[k] = q.add(p);
                arr[k + size / 2] = q.subtract(p);
            }
        }
        return arr;
    }

    /**
     * A method calculating the discrete fourier transformation (DFT) using the Cooley-Tukey Fast Fourier Transformation
     * algorithm. The order of the algorithm is O(n log n)
     *
     * @param data An array of complex numbers to perform the FFT on. The size must be a power of two.
     * @return The transformed array after performing the Cooley-Tukey FFT
     * @throws IllegalArgumentException Thrown if the input array's size is not a power of two.
     */
    public static Complex[] cooleyFastFourierTransform(Complex[] data) throws IllegalArgumentException {
        if (data.length != 0 && (data.length & (data.length - 1)) == 0)
            throw new IllegalArgumentException("The size of the input array has to be a power of two.");
        return cooleyFastFourierTransform(data, new Complex[data.length], 0, data.length, 1);
    }

    /**
     * A method calculating the discrete fourier transformation (DFT) using the Cooley-Tukey Fast Fourier Transformation
     * algorithm. The order of the algorithm is O(n log n)
     *
     * @param data An array of doubles to perform the FFT on. The size must be a power of two.
     * @return The transformed array after performing the Cooley-Tukey FFT
     * @throws IllegalArgumentException Thrown if the input array's size is not a power of two.
     */
    public static Complex[] cooleyFastFourierTransform(double[] data) throws IllegalArgumentException {
        if (data.length != 0 && (data.length & (data.length - 1)) == 0)
            throw new IllegalArgumentException("The size of the input array has to be a power of two.");
        return cooleyFastFourierTransform(data, new Complex[data.length], 0, data.length, 1);
    }

}
