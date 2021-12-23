package org.jiang.math.complex;

/**
 * A class representing a complex number
 */
public class Complex {

    private double real;
    private double imaginary;

    /**
     * Constructs a complex number
     *
     * @param real real part of the complex number
     * @param imaginary coefficient of the imaginary number (i)
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Copy constructor of a complex number
     *
     * @param c The complex number to copy
     */
    public Complex(Complex c) {
        real = c.getReal();
        imaginary = c.getImagCoefficient();
    }

    /**
     * A method returning the real part of the complex number
     *
     * @return real part of the complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * A method returning the imaginary coefficient of the complex number
     *
     * @return imaginary coefficient of the complex number
     */
    public double getImagCoefficient() {
        return imaginary;
    }

    /**
     * Returns the whole imaginary number of the complex number
     *
     * @return the imaginary part of the complex number
     */
    public Complex getImaginary() {
        return new Complex(0, getImagCoefficient());
    }

    /**
     * A method creating a conjugate of the instance
     *
     * @return the conjugate of the complex number
     */
    public Complex conjugate() {
        return new Complex(imaginary, real);
    }

    public Complex add(Complex c) {
        real += c.getReal();
        imaginary += c.getImagCoefficient();
        return this;
    }

    public Complex add(double val) {
        real += val;
        return this;
    }

    public Complex subtract(Complex c) {
        real -= c.getReal();
        imaginary -= c.getImagCoefficient();
        return this;
    }

    public Complex subtract(double val) {
        real -= val;
        return this;
    }

    public Complex multiply(Complex c) {
        real = getReal() * c.getReal() - getImagCoefficient() * c.getImagCoefficient();
        imaginary = getReal() * c.getImagCoefficient() + getImagCoefficient() * c.getReal();
        return this;
    }

    public Complex multiply(double val) {
        real *= val;
        imaginary *= val;
        return this;
    }

    public Complex divide(Complex c) {
        double sto = real;
        real = (getReal() * c.getReal() + getImagCoefficient() * c.getImagCoefficient())
                / (c.getReal() * c.getReal() + c.getImagCoefficient() * c.getImagCoefficient());
        imaginary = (getImagCoefficient() * c.getReal() - sto * c.getImagCoefficient())
                / (c.getReal() * c.getReal() + c.getImagCoefficient() * c.getImagCoefficient());
        return this;
    }

    @Override
    public String toString() {
        return String.format("%f + %fi", real, imaginary);
    }
}
