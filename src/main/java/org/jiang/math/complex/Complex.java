package org.jiang.math.complex;

/**
 * A class representing a complex number
 */
public class Complex {

    public enum Mode {COORDINATE, POLAR}

    private double real;
    private double imaginary;

    /**
     * TODO: POLAR
     * Constructs a complex number from either Polar values or coordinates
     *
     * @param val argument in radians or real part of a complex number
     * @param val2 scalar or coefficient of the imaginary part of a complex number
     * @param mode mode to construct values
     */
    public Complex(double val, double val2, Mode mode) {
        switch (mode) {
            case COORDINATE: {
                this.real = real;
                this.imaginary = imaginary;
                break;
            }
            case POLAR: {
                this.real = Math.cos(val) * val2;
                this.imaginary = Math.sin(val) * val2;
                break;
            }
        }
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
        return new Complex(0, getImagCoefficient(), Mode.COORDINATE);
    }

    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    // TODO: Fix on the axis angles
    public double getArgument() {
        double arg = Math.atan(imaginary / real);
        return arg > 0
                ? (imaginary < 0 ? Math.PI + arg : arg)
                : (imaginary > 0 ? Math.PI + arg : 2 * Math.PI + arg);
    }

    /**
     * A method creating a conjugate of the instance
     *
     * @return the conjugate of the complex number
     */
    public Complex conjugate() {
        return new Complex(imaginary, real, Mode.COORDINATE);
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
