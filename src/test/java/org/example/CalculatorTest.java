package org.example;
import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    // Delta for comparing floating-point values
    double DELTA = 0.0002;

    @Test
    public void testSquareRoot_PositiveNumber() {
        assertEquals(2.0, CalculatorMain.squareRoot(4.0), DELTA);
        assertEquals(3.0, CalculatorMain.squareRoot(9.0), DELTA);
        assertEquals(1.4142, CalculatorMain.squareRoot(2.0), DELTA);
        assertEquals(0.0, CalculatorMain.squareRoot(0.0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareRoot_NegativeNumber() {
        CalculatorMain.squareRoot(-1.0);
    }

    @Test
    public void testFactorial_PositiveNumber() {
        assertEquals(1, CalculatorMain.factorial(0));
        assertEquals(1, CalculatorMain.factorial(1));
        assertEquals(2, CalculatorMain.factorial(2));
        assertEquals(6, CalculatorMain.factorial(3));
        assertEquals(24, CalculatorMain.factorial(4));
        assertEquals(120, CalculatorMain.factorial(5));
        assertEquals(3628800, CalculatorMain.factorial(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorial_NegativeNumber() {
        CalculatorMain.factorial(-1);
    }

    @Test
    public void testNaturalLogarithm_PositiveNumber() {
        assertEquals(0.0, CalculatorMain.naturalLogarithm(1.0), DELTA);
        assertEquals(1.0, CalculatorMain.naturalLogarithm(Math.E), DELTA);
        assertEquals(2.3026, CalculatorMain.naturalLogarithm(10.0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaturalLogarithm_ZeroNumber() {
        CalculatorMain.naturalLogarithm(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaturalLogarithm_NegativeNumber() {
        CalculatorMain.naturalLogarithm(-1.0);
    }

    @Test
    public void testPower_PositiveBase() {
        assertEquals(4.0, CalculatorMain.power(2.0, 2.0), DELTA);
        assertEquals(8.0, CalculatorMain.power(2.0, 3.0), DELTA);
        assertEquals(1.0, CalculatorMain.power(5.0, 0.0), DELTA);
        assertEquals(0.5, CalculatorMain.power(2.0, -1.0), DELTA);
    }

    @Test
    public void testPower_NegativeBase() {
        assertEquals(4.0, CalculatorMain.power(-2.0, 2.0), DELTA);
        assertEquals(-8.0, CalculatorMain.power(-2.0, 3.0), DELTA);
    }

    @Test
    public void testPower_ZeroBase() {
        assertEquals(0.0, CalculatorMain.power(0.0, 2.0), DELTA);
        assertEquals(1.0, CalculatorMain.power(0.0, 0.0), DELTA); // Math.pow(0.0, 0.0) is defined as 1.0 in Java
    }

    @Test
    public void testPower_FractionalExponent() {
        assertEquals(2.0, CalculatorMain.power(4.0, 0.5), DELTA);
        assertEquals(3.0, CalculatorMain.power(27.0, 1.0/3.0), DELTA);
    }

    // Additional edge case tests
    @Test
    public void testFactorial_LargeNumber() {
        // Testing for a reasonably large number without overflow
        assertEquals(2432902008176640000L, CalculatorMain.factorial(20));
    }

    @Test
    public void testNaturalLogarithm_VerySmallNumber() {
        // Testing for a very small positive number
        assertTrue(CalculatorMain.naturalLogarithm(0.0001) < 0);
    }

    @Test
    public void testPower_NegativeBaseWithFractionalExponent() {
        // This would result in a complex number in mathematics
        // Java's Math.pow returns NaN for this case
        assertTrue(Double.isNaN(CalculatorMain.power(-2.0, 0.5)));
    }
}
