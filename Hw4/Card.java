
/**
 * @author sackjo02
 *
 */

public class Card {
	
	private int rank,suits;
	
	public static String[] rankNames= {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	public static String[] suitNames= {"C","d","h","S"};
	public static boolean[] suitIsRed= {false,true,true,false};
	public static Card[] allCards;
	
	public Card(int rank, int suits) {
		this.rank = rank;
		this.suits = suits;
	}
	
	static {
		
		allCards=new Card[rankNames.length*suitNames.length];
		
		int i=0;
		
		for(int suit=0;suit<suitNames.length;suit++) {
			for (int rank=0;rank<rankNames.length;rank++) {
				allCards[i++]=new Card(rank,suit);
				
			}
		}
	}
	
	public int getRank() {
		return rank;
	}
	
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuits() {
		return suits;
	}

	public void setSuits(int suits) {
		this.suits = suits;
	}

	
	public String toString() {
		return rankNames[rank]+ suitNames[suits];
	}
	
}
