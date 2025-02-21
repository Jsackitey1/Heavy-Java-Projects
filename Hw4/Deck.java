import java.util.Random;

public class Deck extends CardStack {

	public Deck(long seed) {

		Random random = new Random(seed);
		for (Card c : Card.allCards) {
			addCard(c);
		}
		// knuth shuffle

		for (int i = stack.size() - 1; i > 0; i--) {

			int j = random.nextInt(i + 1);

			Card c = stack.get(i);
			stack.set(i, stack.get(j));
			stack.set(j, c);

		}

	}

	public boolean canPlayFrom() throws IllegalPlayException {
		throw new IllegalPlayException("That card stack cannot be played from.");
	}

	public boolean playTo(Card card) throws NullPointerException, IllegalPlayException {
		if (card == null) {
			throw new NullPointerException();
		}

		throw new IllegalPlayException("Illegal stack number. Stacks are numbered 1-16");

	}

}
