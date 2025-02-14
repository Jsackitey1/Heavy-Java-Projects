public class FreecellGame {
	private CardStack[] stacks = new CardStack[17];
	// 0:deck ; 1-4:cells ; 5-8:foundations , 9-16:cascades

	public FreecellGame(long seed) {

		stacks[0] = new Deck(seed);

		for (int i = 1; i < stacks.length; i++) {
			stacks[i] = new CardStack();
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
		for (int i = 1; i < stacks.length; i++) {
			sb.append(String.format("%2d: %s\n", i, stacks[i]));
		}
		return sb.toString();
	}

	public boolean play(int srcStackNum, int destStackNum) {
		// Validate stack numbers
		if (srcStackNum < 0 || srcStackNum >= stacks.length ||
				destStackNum < 0 || destStackNum >= stacks.length) {
			return false;
		}

		if (stacks[srcStackNum].isEmpty()) {
			return false;
		}
		return stacks[destStackNum].playTo(stacks[srcStackNum]);
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
