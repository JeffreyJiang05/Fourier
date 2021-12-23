package org.jiang.math.complex;

/**
 * A class representing a complex number
 */
public class Complex {

    /**
     * An enum representing the modes of a Complex number argument
     */
    public enum Mode {
        /**Arguments uses the standard coordinate system a + bi*/
        COORDINATE,
        /**Arguments uses the polar system with an argument and modulus*/
        POLAR
    }

    private double real;
    private double imaginary;

    /**
     * Constructs a complex number from either Polar values or coordinates
     *
     * @param val argument in radians or real part of a complex number
     * @param val2 modulus or coefficient of the imaginary part of a complex number
     * @param mode mode to construct values
     */
    public Complex(double val, double val2, Mode mode) {
        switch (mode) {
            case COORDINATE: {
                this.real = val;
                this.imaginary = val2;
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

    /**
     * Returns the modulus of the complex number
     *
     * @return The modulus of a complex number
     */
    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Returns the argument of the complex number
     *
     * @return The argument of a complex number
     */
    public double getArgument() {
        double arg = Math.atan(imaginary / real);

        return arg > 0
                ? (imaginary < 0 ? Math.PI + arg : arg)
                : (arg == 0 ? real > 0 ? 0 : Math.PI : (imaginary > 0 ? Math.PI + arg : 2 * Math.PI + arg));
    }

    /**
     * A method creating a conjugate of the instance
     *
     * @return the conjugate of the complex number
     */
    public Complex conjugate() {
        return new Complex(imaginary, real, Mode.COORDINATE);
    }

    /**
     * Adds a complex number ot the instance.
     *
     * @param c complex number to add to the instance
     * @return the instance after adding the complex number. For chaining.
     */
    public Complex add(Complex c) {
        real += c.getReal();
        imaginary += c.getImagCoefficient();
        return this;
    }

    /**
     * Adds a real number to the complex number instance
     *
     * @param val real number to add to the complex number
     * @return the instance after adding the real number. For chaining.
     */
    public Complex add(double val) {
        real += val;
        return this;
    }

    /**
     * Subtracts a complex number from the instance
     *
     * @param c Complex number to subtract from the instance
     * @return the instance after the difference. For chaining.
     */
    public Complex subtract(Complex c) {
        real -= c.getReal();
        imaginary -= c.getImagCoefficient();
        return this;
    }

    /**
     * Subtracts a double from the instance
     *
     * @param val value to remove from the complex number
     * @return the instance after the difference. For chaining.
     */
    public Complex subtract(double val) {
        real -= val;
        return this;
    }

    /**
     * Multiplies the instance by a complex number
     *
     * @param c Complex number to multiply
     * @return the product of the complex number. For chaining.
     */
    public Complex multiply(Complex c) {
        real = getReal() * c.getReal() - getImagCoefficient() * c.getImagCoefficient();
        imaginary = getReal() * c.getImagCoefficient() + getImagCoefficient() * c.getReal();
        return this;
    }

    /**
     * Multiplies the instance by a value
     *
     * @param val value to multiply by
     * @return the scaled complex number. For chaining
     */
    public Complex multiply(double val) {
        real *= val;
        imaginary *= val;
        return this;
    }

    /**
     * Divides the instance by a complex number
     *
     * @param c divisor
     * @return the quotient. For chaining
     */
    public Complex divide(Complex c) {
        double sto = real;
        real = (getReal() * c.getReal() + getImagCoefficient() * c.getImagCoefficient())
                / (c.getReal() * c.getReal() + c.getImagCoefficient() * c.getImagCoefficient());
        imaginary = (getImagCoefficient() * c.getReal() - sto * c.getImagCoefficient())
                / (c.getReal() * c.getReal() + c.getImagCoefficient() * c.getImagCoefficient());
        return this;
    }

    /**
     * Divides the instance by a value
     *
     * @param val value to divide
     * @return the quotient. For chaining
     */
    public Complex divide(double val) {
        real /= val;
        imaginary /= val;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%f + %fi", real, imaginary);
    }

    /**
     * Raises e to a complex power. Uses Euler's theorem that e^(θi) = cis θ = cos θ + sin θ * i
     *
     * @param c Complex power
     * @return A new complex number from raising e to the complex power
     */
    public static Complex exp(Complex c) {
        double expReal = Math.exp(c.getReal());
        Complex expImag = new Complex(Math.cos(c.getImagCoefficient()), Math.sin(c.getImagCoefficient()), Mode.COORDINATE);
        return expImag.add(expReal);
    }
}
