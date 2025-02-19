public class Foundation extends CardStack {

	public Foundation() {
		super();
	}

	public boolean canPlayFrom() throws IllegalPlayException {
		return false;
	}

	public boolean playTo(Card card) throws NullPointerException, IllegalPlayException {
		if (card == null) {
			throw new NullPointerException();
		}

		if (stack.isEmpty()) {
			if (card.getRank() != 0) {
				throw new IllegalPlayException("The first foundation card must be an Ace.");
			}
			super.addCard(card);
			return true;
		}

		Card topCard = getTopCard();

		if (card.getSuit() != topCard.getSuit()) {
			throw new IllegalPlayException("Plays to a foundation must match suit.");
		}

		if (card.getRank() != topCard.getRank() + 1) {
			throw new IllegalPlayException("Plays to a foundation must have the next increasing rank.");
		}

		super.addCard(card);
		return true;
	}

}
