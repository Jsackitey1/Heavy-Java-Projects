public class Cell extends CardStack {

	public Cell() {
		super();
	}

	// add null check

	public boolean playTo(Card card) throws NullPointerException,
			IllegalPlayException {

		if (card == null) {
			throw new NullPointerException();
		}
		if (!stack.isEmpty()) {
			throw new IllegalPlayException("Cells may only contain a single card.");
		}

		stack.push(card);
		return true;
	}

}
