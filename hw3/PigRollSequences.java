
public class PigRollSequences {
	private static int [] e;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println(getNumSequences(50));

	}
	
	public static int getNumSequences(int turnTotal){
		
		if(turnTotal==0) {
			return 1;
		}
		
		if(turnTotal<0) {
			return 0;
		}
		
		int total = 0;
		
        for (int i = 2; i <= 6; i++) {
        	
            total += getNumSequences(turnTotal - i);
        }
        
        return total;
	}

}
