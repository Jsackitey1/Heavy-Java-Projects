import java.util.EmptyStackException;
import java.util.Stack;

public class CardStack {

	protected Stack<Card> stack = new Stack<>();

	public void addCard(Card card) {
		stack.push(card);
	}

	public boolean canPlayFrom() throws IllegalPlayException {
		if (!stack.isEmpty()) {
			return true;
		} else {
			throw new IllegalPlayException("You cannot play from an empty stack.");
		}
	}

	public Card getTopCard() throws EmptyStackException {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.peek();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public boolean playTo(Card card) throws IllegalPlayException, NullPointerException {

		if (card == null) {
			throw new NullPointerException();
		}

		if (!stack.isEmpty()) {
			Card topCard = stack.peek();
			if (card.getSuit() != topCard.getSuit() && card.getRank() != topCard.getRank()) {
				throw new IllegalPlayException("You cannot make the play.");
			}
		}

		addCard(card);
		
		return true;
	}

	public boolean playTo(CardStack otherStack) throws IllegalPlayException {
		if (!otherStack.canPlayFrom()) {
			throw new IllegalPlayException("You cannot make the play.");
		}

		boolean legalPlay = playTo(otherStack.getTopCard());

		if (legalPlay) {
			otherStack.removeTopCard();
		}

		return legalPlay;
	}

	public Card removeTopCard() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.pop();
	}

	public int size() {
		return stack.size();
	}

	public String toString() {
		String s = stack.toString();
		return s.substring(1, s.length() - 1);
	}

	public Card[] toArray() {
		return stack.toArray(new Card[stack.size()]);
	}

}
