
public class Foundation extends CardStack {

	public Foundation() {
		super();
	}

	public boolean canPlayFrom() {
		return false;
	}

	public boolean playTo(Card card) {
		if (stack.isEmpty() && card.getRank() == 0) {
			super.addCard(card);

		}

		Card topCard = getTopCard();

		if (card.getSuits() == topCard.getSuits() && card.getRank() == topCard.getRank() + 1) {
			super.addCard(card);
			return true;
		}

		return false;
	}

}
