
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PigTurnMath {

    public PigTurnMath() {}

    public static BigFraction expectedTurnScore(int holdAt) {
    	
        BigFraction[] e = new BigFraction[holdAt + 6];

        for (int t = holdAt; t <= holdAt + 5; t++) {
            e[t] = new BigFraction(BigInteger.valueOf(t), BigInteger.valueOf(1));
        }

        for (int i = holdAt - 1; i >= 0; i--) {
            BigFraction sum = BigFraction.ZERO;

              for (int roll = 2; roll <= 6; roll++) {
                sum = sum.add(e[i + roll]);
            }

            e[i] = sum.divide(new BigFraction(BigInteger.valueOf(6), BigInteger.ONE));
        }

        return e[0]; 
    }


    public static void main(String[] args) {
		int holdAt = 100;
		BigFraction frac = expectedTurnScore(holdAt);
		System.out.printf("The expected score of a Pig turn holding at or above %d is approximately %f and exactly %s.\n", holdAt, frac.asBigDecimal(6, RoundingMode.HALF_UP).doubleValue(), frac);
	}
}


