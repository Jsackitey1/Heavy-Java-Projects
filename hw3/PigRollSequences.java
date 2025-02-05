public class PigRollSequences {
	private static int[] e;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNumSequences(50));

	}

	public static int getNumSequences(int turnTotal) {
		
//		if(turnTotal==0) {
//	        return 1;
//	    }
//	    
//	    if(turnTotal<0) {
//	        return 0;
//	    }
//	    
//	    int total = 0;
//	    
//	    for (int i = 2; i <= 6; i++) {
//	        total += getNumSequences(turnTotal - i);
//	    }
//	    
//	    return total;
	
		if (turnTotal < 0) {
			
			return 0;
		}

		// Create array to store results 
		int[] dp = new int[turnTotal + 1];
		dp[0] = 1; 

		// Calculate sequences for each possible total from 1 to turnTotal
		for (int i = 1; i <= turnTotal; i++) {
			for (int dice = 2; dice <= 6; dice++) {
				if (i >= dice) {
					dp[i] += dp[i - dice];
				}
			}
		}

		return dp[turnTotal];
	}

}
