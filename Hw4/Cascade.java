public class Cascade extends CardStack {

	public Cascade() {
		super();

	}

	// add null case
	public boolean playTo(Card card) throws NullPointerException, IllegalPlayException {

		if (card == null) {
			throw new NullPointerException();
		}

		if (stack.isEmpty()) {
			super.addCard(card);
			return true;
		}

		Card topCard = getTopCard();

		if (Card.suitIsRed[topCard.getSuit()] == Card.suitIsRed[card.getSuit()]) {
			throw new IllegalPlayException("Plays to a cascade must alternate in suit color.");
		}
		if (card.getRank() != topCard.getRank() - 1) {
			throw new IllegalPlayException("Plays to a cascade must have the next decreasing rank.");
		}

		super.addCard(card);
		return true;

	}

}
