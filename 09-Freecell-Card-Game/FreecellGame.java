public class FreecellGame {

	private CardStack[] stacks = new CardStack[17];
	// 0:deck ; 1-4:cells ; 5-8:foundations , 9-16:cascades

	public FreecellGame(long seed) {
		// Initialize deck (index 0)
		stacks[0] = new Deck(seed);

		// Initialize cells (indices 1-4)
		for (int i = 1; i <= 4; i++) {
			stacks[i] = new Cell();
		}

		// Initialize foundations (indices 5-8)
		for (int i = 5; i <= 8; i++) {
			stacks[i] = new Foundation();
		}

		// Initialize cascades (indices 9-16)
		for (int i = 9; i <= 16; i++) {
			stacks[i] = new Cascade();
		}

		int i = 9;
		while (!stacks[0].isEmpty()) {
			stacks[i++].addCard(stacks[0].removeTopCard());
			if (i > 16) {
				i = 9;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Cells:\n");
		for (int i = 1; i <= 4; i++) {
			sb.append(String.format("%2d: %s\n", i,
					stacks[i].isEmpty() ? " " : stacks[i].getTopCard()));
		}

		sb.append("Foundations:\n");
		for (int i = 5; i <= 8; i++) {
			sb.append(String.format("%2d: ", i));

			if (!stacks[i].isEmpty()) {
				Card[] cards = stacks[i].toArray();
				for (int j = 0; j < cards.length; j++) {
					if (j > 0)
						sb.append(", ");
					sb.append(cards[j]);
				}
			}
			sb.append("\n");
		}

		// Display Cascades (9-16)
		sb.append("Cascades:\n");
		for (int i = 9; i <= 16; i++) {
			sb.append(String.format("%2d: ", i));
			Card[] cards = stacks[i].toArray();
			for (int j = 0; j < cards.length; j++) {
				if (j > 0)
					sb.append(", ");
				sb.append(cards[j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public boolean play(int srcStackNum, int destStackNum) throws IllegalPlayException {
		if (srcStackNum < 1 || srcStackNum > 16 ||
				destStackNum < 1 || destStackNum > 16) {
			throw new IllegalPlayException("Illegal stack number. Stacks are numbered 1-16.");
		}

		if (srcStackNum == destStackNum) {
			throw new IllegalPlayException("Source and destination stacks must be different.");
		}

		if (stacks[srcStackNum].isEmpty()) {
			throw new IllegalPlayException("You cannot play from an empty stack.");
		}

		Card cardToMove = stacks[srcStackNum].getTopCard();

		// Try to play the card to destination stack
		if (stacks[destStackNum].playTo(cardToMove)) {
			// If successful, remove the card from source stack
			stacks[srcStackNum].removeTopCard();
			return true;
		}

		return false;
	}

	public Card[] getStack(int stackNum) {
		return stacks[stackNum].toArray();

	}

	// completed when all 52 cards all in the foundation
	public boolean isGameOver() {
		int totalCards = 0;
		// there are 4 foundations
		for (int i = 5; i <= 8; i++) {
			totalCards += stacks[i].size();
		}
		return totalCards == 52;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new FreecellGame(0));

	}

}
