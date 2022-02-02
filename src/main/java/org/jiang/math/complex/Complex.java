package org.jiang.math.complex;

/**
 * A class representing a complex number
 */
public class Complex {

    /**
     * An enum representing the different representations of a complex number
     */
    public enum Mode {
        /**
         * Uses the form a+bi. There is only one way to represent one point.
         */
        RECTANGULAR,
        /**
         * Uses the arg * e^(modulus * i). There are an infinite way of representing each point.
         */
        POLAR
    }

    private double real;
    private double imaginary;

    /**
     * Constructs a complex number from either Polar values or coordinates
     *
     * @param val1 argument in radians or real part of a complex number
     * @param val2 modulus or coefficient of the imaginary part of a complex number
     * @param mode the representation used to create the complex number
     */
    public Complex(double val1, double val2, Mode mode) {
        switch (mode) {
            case RECTANGULAR: {
                real = val1;
                imaginary = val2;
                break;
            }
            case POLAR: {
                real = val1 * Math.cos(val2);
                imaginary = val1 * Math.sin(val2);
                break;
            }
        }
    }

    /**
     * A copy constructor
     *
     * @param c The complex number to copy
     */
    public Complex(Complex c) {
        this(c.getRealCoefficient(), c.getImagCoefficient(), Mode.RECTANGULAR);
    }

    /**
     * Default constructor that makes a complex number centered at the origin
     */
    public Complex() {
        this(0, 0, Mode.RECTANGULAR);
    }

    /**
     * A method returning the value of the real coefficient of the complex number
     *
     * @return The real coefficient of the complex number
     */
    public double getRealCoefficient() {
        return real;
    }

    /**
     * A method returning the real part of the complex number as a Complex object
     *
     * @return The real part of the complex number
     */
    public Complex getReal() {
        return new Complex(getRealCoefficient(), 0, Mode.RECTANGULAR);
    }

    /**
     * A method returning the value of the imaginary coefficient of the complex number
     *
     * @return The complex coefficient of the complex number
     */
    public double getImagCoefficient() {
        return imaginary;
    }

    /**
     * A method returning the imaginary part of the complex number as a Complex object
     *
     * @return The imaginary part of the complex number
     */
    public Complex getImag() {
        return new Complex(0, getImagCoefficient(), Mode.RECTANGULAR);
    }

    /**
     * A method returning the angle in which the Complex angle forms from the positive real axis.
     *
     * @return The argument of the complex number
     */
    public double getArgument() {
        double arg = Math.atan(getImagCoefficient() / getRealCoefficient());
        return arg > 0
                ? (imaginary < 0 ? Math.PI + arg : arg)
                : (arg == 0 ? real > 0 ? 0 : Math.PI : (imaginary > 0 ? Math.PI + arg : 2 * Math.PI + arg));
    }

    /**
     * A method returning the distance in which the Complex number is from the origin.
     *
     * @return The modulus of the complex number
     */
    public double getModulus() {
        return Math.sqrt(getRealCoefficient() * getRealCoefficient() + getImagCoefficient() * getImagCoefficient());
    }

    /**
     * A method returning the conjugate of the Complex number. The conjugate of a complex number is the complex number
     * reflected over the real axis. The conjugate of a+bi is a-bi.
     *
     * @return The conjugate of the complex number
     */
    public Complex conjugate() {
        return new Complex(getRealCoefficient(), -getImagCoefficient(), Mode.RECTANGULAR);
    }

    /**
     * Adds a complex number to the current instance. This serves as a horizontal and vertical translation.
     *
     * @param c The complex number to add to the instance
     * @return The sum of the complex number
     */
    public Complex add(Complex c) {
        real += c.getRealCoefficient();
        imaginary += c.getImagCoefficient();
        return this;
    }

    /**
     * Adds a real number to the complex number instance. This serves as a horizontal translation.
     *
     * @param val The real number to add to the instance
     * @return The sum of the instance and the complex number
     */
    public Complex add(double val) {
        real += val;
        return this;
    }

    /**
     * Subtracts a complex number from the current instance. This serves as a horizontal and vertical translation.
     *
     * @param c The complex number to remove from the instance
     * @return The difference between the instance and the complex number
     */
    public Complex subtract(Complex c) {
        real -= c.getRealCoefficient();
        imaginary -= c.getImagCoefficient();
        return this;
    }

    /**
     * Subtracts a real number from the current instance. This serves as a horizontal translation.
     *
     * @param val The real number to remove from the instance
     * @return The difference between the instance and the real number
     */
    public Complex subtract(double val) {
        real -= val;
        return this;
    }

    /**
     * Multiplies a complex number to the current instance. This serves as a rotation and a scalar.
     *
     * @param c The complex number to multiply to the instance
     * @return The product of the instance and the complex number
     */
    public Complex multiply(Complex c) {
        double theta = c.getArgument() + getArgument();
        double modulus = c.getModulus() * getModulus();

        real = modulus * Math.cos(theta);
        imaginary = modulus * Math.sin(theta);

        return this;
    }

    /**
     * Multiplies a real number to the current instance. This serves as a scalar.
     *
     * @param val The real number to scale the instance by
     * @return The product of the instance and the real number
     */
    public Complex multiply(double val) {
        real *= val;
        imaginary *= val;
        return this;
    }

    /**
     * Divides the current instance by a complex number. This serves as a rotation and a scalar.
     *
     * @param c The complex number to divide the instance by
     * @return The quotient of the instance and the complex number
     */
    public Complex divide(Complex c) {
        double theta = getArgument() - c.getArgument();
        double modulus = getModulus() / c.getModulus();

        real = modulus * Math.cos(theta);
        imaginary = modulus * Math.sin(theta);

        return this;
    }

    /**
     * Divides the current instance by a real number. This serves as a scalar.
     *
     * @param val The real number to divide the instance by
     * @return The quotient of the complex number and the real number
     */
    public Complex divide(double val) {
        real /= val;
        imaginary /= val;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%f + %fi", getRealCoefficient(), getImagCoefficient());
    }

    /**
     * Raises e to a complex number. e<sup>a+bi</sup> = e<sup>a</sup>e<sup>bi</sup> = e<sup>a</sup>[cos(b)+ i sin(b)]
     *
     * @param c The complex power
     * @return A new complex number from raising e to the complex power
     */
    public static Complex exp(Complex c) {
        double scale = Math.exp(c.getRealCoefficient());
        Complex baseComplex = new Complex(Math.cos(c.getImagCoefficient()), Math.sin(c.getImagCoefficient()), Mode.RECTANGULAR);
        return baseComplex.multiply(scale);
    }
}
