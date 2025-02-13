import java.util.Stack;

public class CardStack {
	
	protected Stack<Card> stack= new Stack<>();
	
	public void addCard(Card card) {
		stack.push(card);
	}
	
	public boolean canPlayFrom() {
		return !stack.isEmpty();
	}
	
	public Card getTopCard() {
		return stack.isEmpty()? null:stack.peek();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public boolean playTo(Card card) {
		
		if(card==null) {
			return false;
		}
		addCard(card);
		return true;
	}
	
	public boolean playTo(CardStack otherStack) {
		if(!otherStack.canPlayFrom()) {
			return false;
		}
		
		boolean legalPlay= playTo(otherStack.getTopCard());
		
		if (legalPlay) {
			otherStack.removeTopCard();
			
		}
		
		return legalPlay;
	}

	public Card removeTopCard() {
		// TODO Auto-generated method stub
		return stack.isEmpty() ? null:stack.pop();
		
	}
	
	public int size() {
		return stack.size();
	}
	
	public String toString() {
		String s=stack.toString();
		return s.substring(1,s.length()-1);
	}
	
	public Card[] toArray() {
		return stack.toArray(new Card[stack.size()]);
	}

}
