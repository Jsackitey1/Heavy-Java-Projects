public class Foundation extends CardStack {

	public Foundation() {
		super();
	}

	public boolean canPlayFrom() {
		return false;
	}

	public boolean playTo(Card card) {
		if (card == null) {
			return false;
		}

		if (stack.isEmpty()) {

			if (card.getRank() == 0) {
				super.addCard(card);
				return true;
			}
			return false;
		}
		
		

		Card topCard = getTopCard();

		if (card.getSuit() == topCard.getSuit() && card.getRank() == topCard.getRank() + 1) {
			super.addCard(card);
			return true;
		}

		return false;
	}

}
