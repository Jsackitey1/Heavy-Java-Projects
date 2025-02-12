
public class Freecell {
	public Freecell() {
		
	}
	
	public void play() {
		play((long)(Math.random()*100));
		
		
	}
	
	public void play(long seed) {
		FreecellGame game=new FreecellGame(seed);
		
		
	}
	
	public static void main(String [] args) {
		Freecell freecell = new Freecell();
        freecell.play(0);
		
		
	}

}
