public class PigRollSequences {
	private static int[] dp;

	public PigRollSequences() {
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println(getNumSequences(10));
//
//	}

	public static int getNumSequences(int turnTotal) {
		if (turnTotal < 0) {
			return 0;
		}
		// Initialize dp array
		dp = new int[turnTotal + 1];
		
		// Fill with -1 to indicate uncalculated values
		for (int i = 0; i <= turnTotal; i++) {
			dp[i] = -1;
		}
		return getNumSequencesHelper(turnTotal);
	}

	private static int getNumSequencesHelper(int turnTotal) {
		// Base cases
		if (turnTotal < 0) {
			return 0;
		}
		if (turnTotal == 0) {
			return 1;
		}

		if (dp[turnTotal] != -1) {
			return dp[turnTotal];
		}

		// Calculate result for current turnTotal
		int result = 0;
		for (int dice = 2; dice <= 6; dice++) {
			result += getNumSequencesHelper(turnTotal - dice);
		}

		// Store result in dp array
		dp[turnTotal] = result;
		return result;
	}

}
