import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigFractionRunner {
    public static void main(String[] args) {
        // Testing constructors
        BigFraction f1 = new BigFraction(1, -2);
        BigFraction f2 = new BigFraction("3/-4");
        BigFraction f3 = new BigFraction(new BigInteger("5"), new BigInteger("8"));
        BigFraction f4 = new BigFraction(f1);

        System.out.println("Constructors:");
        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println("f3: " + f3);
        System.out.println("f4 (copy of f1): " + f4);

        // Testing addition
        BigFraction sum = f1.add(f2);
        System.out.println("\nAddition: " + f1 + " + " + f2 + " = " + sum);

        // Testing subtraction
        BigFraction diff = f1.subtract(f2);
        System.out.println("Subtraction: " + f1 + " - " + f2 + " = " + diff);

        // Testing multiplication
        BigFraction prod = f1.multiply(f2);
        System.out.println("Multiplication: " + f1 + " * " + f2 + " = " + prod);

        // Testing division
        BigFraction quotient = f1.divide(f2);
        System.out.println("Division: " + f1 + " / " + f2 + " = " + quotient);

        // Testing negation
        BigFraction neg = f1.negate();
        System.out.println("Negation: -" + f1 + " = " + neg);

        // Testing conversion to BigDecimal
        BigDecimal decimal = f1.asBigDecimal(5, RoundingMode.HALF_UP);
        System.out.println("BigDecimal representation of " + f1 + " = " + decimal);

        // Testing getters
        System.out.println("\nGetters:");
        System.out.println("Numerator of " + f1 + " = " + f1.getNum());
        System.out.println("Denominator of " + f1 + " = " + f1.getDen());

        // Edge case: division by zero
        try {
            BigFraction invalid = new BigFraction(1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("\nException: " + e.getMessage());
        }
    }
}
