
public class Cascade extends CardStack{
	
	public Cascade() {
		super();
		
	}
	//incomplete
	public boolean playTo(Card card) {
		
		if (stack.isEmpty()) {
			super.addCard(card);
			return true;
		}

		Card topCard = getTopCard();
		

		if (Card.suitIsRed[topCard.getSuits()] !=  Card.suitIsRed[card.getSuits()] && card.getRank() == topCard.getRank() - 1) {
			super.addCard(card);
			return true;
		}

		return false;
	
	}
	

}
