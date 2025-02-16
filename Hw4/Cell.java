
public class Cell extends CardStack{
	
	public Cell() {
		super();
	}
	
	
	//null check
	
	public boolean playTo(Card card) {
		
		if (card == null) {
			return false;
		}
		if (!stack.isEmpty()) {
			return false;
		}
		
		else {
			
		super.addCard(card);
		}
		
		return true;
	}

}
