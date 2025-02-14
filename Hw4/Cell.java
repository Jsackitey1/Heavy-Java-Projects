
public class Cell extends CardStack{
	
	public Cell() {
		super();
	}
	
	
	
	
	public boolean playTo(Card card) {
		
		if (!stack.isEmpty()) {
			return false;
		}
		
		else {
			
		super.addCard(card);
		}
		
		return true;
	}

}
