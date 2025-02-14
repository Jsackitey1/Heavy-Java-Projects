
public class Cascade extends CardStack{
	
	public Cascade() {
		super();
		
	}

	public boolean playTo(Card card) {
		
		if (stack.isEmpty()) {
			super.addCard(card);
			return true;
		}

		Card topCard = getTopCard();
		

		if (Card.suitIsRed[topCard.getSuit()] !=  Card.suitIsRed[card.getSuit()] && card.getRank() == topCard.getRank() - 1) {
			super.addCard(card);
			return true;
		}

		return false;
	
	}
	

}
