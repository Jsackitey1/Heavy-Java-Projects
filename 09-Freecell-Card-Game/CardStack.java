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

		stack.add(card);
		return true;
	}

	public boolean playTo(CardStack otherStack) throws IllegalPlayException {
		if (!otherStack.canPlayFrom()) {
			throw new IllegalPlayException("That card stack cannot be played from.");
		}

		boolean legalPlay = playTo(otherStack.getTopCard());

		// System.out.println("The other stack is " + otherStack.toString());

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
		if (stack.isEmpty()) {
			return " ";
		}
		return stack.toString();
	}

	public Card[] toArray() {
		return stack.toArray(new Card[stack.size()]);
	}

}
