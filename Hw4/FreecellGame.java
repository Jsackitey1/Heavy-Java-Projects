
public class FreecellGame {
	private CardStack [] stacks=new CardStack[17];
	//0:deck ; 1-4:cells ; 5-8:foundations , 9-16:cascades
	
	public FreecellGame(long seed) {
		
		stacks[0]= new Deck(seed);
		
		for (int i=1;i<stacks.length;i++) {
			stacks[i]=new CardStack();
		}
		
		int i=9;
		
		while(!stacks[0].isEmpty()) {
			stacks[i++].addCard(stacks[0].removeTopCard());
			
			if(i>16) {
				i=9;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<stacks.length;i++) {
			sb.append(String.format("%2d: %s\n",i,stacks[i]));
		}
		return sb.toString();
	}
	
	public boolean play(int srcStackNum,int destStackNum) {
		return true;
		
	}
	
	public Card[] getStack(int stackNum) {
		Card[] results=new Card[2];
		return results;
		
	}
	
	
	public boolean isGameOver() {
		return true;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new FreecellGame(0));

	}

}
