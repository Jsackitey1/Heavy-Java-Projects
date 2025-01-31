import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigFraction {
	
	public static final BigFraction ONE = new BigFraction(BigInteger.ONE, BigInteger.ONE);
	public static final BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
	private BigInteger num;
	private BigInteger den;
	
	BigFraction(BigInteger num, BigInteger den){
		
		BigInteger GCD= num.gcd(den);
		num=num.divide(GCD);
		den=den.divide(GCD);
		
		if (den.signum()<0) {
			den=den.negate();
			num=num.negate();
		}
		this.num = num;
        this.den = den;
		
	}
	
	
	BigFraction(BigFraction f){
		this.num=num;
		this.den=den;
	}
	

	BigFraction(long numerator, long denominator){
		BigInteger num=BigInteger.valueOf(numerator);
		BigInteger den=BigInteger.valueOf(denominator);
		
		this.num=num;
		this.den=den;
		
	  new BigFraction(num,den);

		
	}
	
	BigFraction(String s){
		String[] parts=s.split("/");

		this.num=new BigInteger(parts[0]);
		this.den=new BigInteger(parts[1]);
		
		new BigFraction(num,den);
		
	}
	
	
	public BigFraction add(BigFraction b) {
		BigFraction fraction = new  BigFraction(b);
		
		 BigInteger otherNum = fraction.getNum();
		 BigInteger otherDen = fraction.getDen();
		
		 BigInteger commonDen = this.den.multiply(otherDen);
		
		 BigInteger newNum = this.num.multiply(otherDen).add(otherNum.multiply(this.den));
		
		   
		return new BigFraction(newNum, commonDen);
	}
	
	

	public BigDecimal asBigDecimal(int scale, RoundingMode roundingMode){
		BigDecimal num= new BigDecimal(this.num);
		BigDecimal den= new BigDecimal (this.den);
		BigDecimal results= num.divide(den, scale, roundingMode);
		return results;
	} 
	
	
	public BigFraction divide(BigFraction b) {
		BigFraction fraction = new  BigFraction(b);

	    BigInteger newNum = fraction.getNum();
	    BigInteger newDen = fraction.getDen();

	   
	    BigInteger resultNum = this.num.multiply(newDen);
	    BigInteger resultDen = this.den.multiply(newNum);

	    return new BigFraction(resultNum, resultDen);
	}

	
	public BigInteger getDen() {
		return this.den;
		
	}
	
	public BigInteger getNum() {
		return this.num;
		
	}
	
	public BigFraction multiply(BigFraction b) {
		BigFraction fraction = new  BigFraction(b);
		BigInteger newNum= fraction.getNum();
		BigInteger newDen =fraction.getDen();
	
	    BigInteger resultNum = this.num.multiply(newNum);
	    BigInteger resultDen = this.den.multiply(newDen);

	    return new BigFraction(resultNum, resultDen);
		
	}
	
	public BigFraction negate() {
		return new BigFraction(this.num.negate(), this.den);
	}
	
	public BigFraction  subtract(BigFraction b) {
		BigFraction fraction = new  BigFraction(b);
		
		 BigInteger otherNum = fraction.getNum();
		 BigInteger otherDen = fraction.getDen();
		
		 BigInteger commonDen = this.den.multiply(otherDen);
		
		 BigInteger newNum = this.num.multiply(otherDen).subtract(otherNum.multiply(this.den));
		
		   
		return new BigFraction(newNum, commonDen);
		
	}
	
	public java.lang.String toString(){
		
		return this.num+"/"+this.den;
		
	}

}
