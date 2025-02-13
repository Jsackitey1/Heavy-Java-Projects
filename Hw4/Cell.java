
public class Cell extends CardStack{
	
	public Cell() {
		super();
	}
	
	
	//incomplete
	
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
