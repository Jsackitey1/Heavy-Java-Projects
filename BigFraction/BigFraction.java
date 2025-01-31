import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigFraction {

    public static final BigFraction ONE = new BigFraction(BigInteger.ONE, BigInteger.ONE);
    public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE);

    private BigInteger num;
    private BigInteger den;

    public BigFraction(BigInteger num, BigInteger den) {
        if (den.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        BigInteger gcd = num.gcd(den);
        this.num = num.divide(gcd);
        this.den = den.divide(gcd);

        if (this.den.compareTo(BigInteger.ZERO) < 0) {
            this.num = this.num.negate();
            this.den = this.den.negate();
        }
    }

    public BigFraction(long num, long den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }
	BigFraction(BigFraction f){
			this.num= f.getNum();
			this.den=f.getDen();
			
			
		}
	
    public BigFraction(String s) {
        String[] parts = s.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Input string must be in the format 'num/den'.");
        }
        this.num = new BigInteger(parts[0]);
        this.den = new BigInteger(parts[1]);

        if (this.den.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        BigInteger gcd = this.num.gcd(this.den);
        this.num = this.num.divide(gcd);
        this.den = this.den.divide(gcd);

        if (this.den.compareTo(BigInteger.ZERO) < 0) {
            this.num = this.num.negate();
            this.den = this.den.negate();
        }
    }

    
	public BigFraction add(BigFraction b) {
        BigInteger commonDen = this.den.multiply(b.getDen());
        BigInteger newNum = this.num.multiply(b.getDen()).add(b.getNum().multiply(this.den));
        return new BigFraction(newNum, commonDen);
    }

    public BigFraction subtract(BigFraction b) {
        BigInteger commonDen = this.den.multiply(b.getDen());
        BigInteger newNum = this.num.multiply(b.getDen()).subtract(b.getNum().multiply(this.den));
        return new BigFraction(newNum, commonDen);
    }

    public BigFraction multiply(BigFraction b) {
        BigInteger newNum = this.num.multiply(b.getNum());
        BigInteger newDen = this.den.multiply(b.getDen());
        return new BigFraction(newNum, newDen);
    }

    public BigFraction divide(BigFraction b) {
        if (b.getNum().equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        BigInteger newNum = this.num.multiply(b.getDen());
        BigInteger newDen = this.den.multiply(b.getNum());
        return new BigFraction(newNum, newDen);
    }

    public BigFraction negate() {
        return new BigFraction(this.num.negate(), this.den);
    }

    public BigDecimal asBigDecimal(int scale, RoundingMode roundingMode) {
        BigDecimal num = new BigDecimal(this.num);
        BigDecimal den = new BigDecimal(this.den);
        return num.divide(den, scale, roundingMode);
    }

    public BigInteger getNum() {
        return this.num;
    }

    public BigInteger getDen() {
        return this.den;
    }

    @Override
    public String toString() {
        return this.num + "/" + this.den;
    }
}