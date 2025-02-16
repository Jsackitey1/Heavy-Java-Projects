import java.util.Scanner;
import java.util.Random;

public class Freecell {
	private Random random=new Random();
	static Scanner input=new Scanner(System.in);
	
	public Freecell() {
		
	}
	

	public void play() {
		play(random.nextInt((int)(Math.random()*100)));
	}
	
	
	
	public void play(long seed) {
	    FreecellGame game = new FreecellGame(seed);

	    while (true) {
	        System.out.println("Cells:");
	        for (int i = 1; i <= 4; i++) {
	            System.out.printf("%2d: %s%n", i, game.getStack(i).length == 0 ? "" : game.getStack(i)[0]);
	        }

	        System.out.println("Foundations:");
	        for (int i = 5; i <= 8; i++) {
	            System.out.printf("%2d: %s%n", i, game.getStack(i).length == 0 ? "" : game.getStack(i)[game.getStack(i).length - 1]);
	        }

	        System.out.println("Cascades:");
	        for (int i = 9; i <= 16; i++) {
	            System.out.printf("%2d: ", i);
	            printArray(game.getStack(i));
	        }

	        System.out.println();
	        
	        int from = -2, to = -2;
	        while (true) {
	            System.out.print("Please enter source and destination card stacks, or \"-1\" to quit: ");
	            if (input.hasNextInt()) {
	                from = input.nextInt();
	                if (from == -1) return; 
	            } else {
	                System.out.println("Invalid input. Please enter a valid integer.");
	                input.next(); 
	                continue;
	            }

	            if (input.hasNextInt()) {
	                to = input.nextInt();
	                if (to == -1) return; 
	                break;
	            } 
	            else {
	                System.out.println("Invalid input. Please enter a valid integer.");
	                input.next(); 
	            }
	        }

	        game.play(from, to);

	        if (game.isGameOver()) {
	            System.out.println("You Win!");
	            break;
	        }
	    }
	}

	
	private void printArray(Card[] cards) {
		StringBuilder sb = new StringBuilder();
		if (cards.length==0) {
			sb.append("");
		}
		else {
		for(int i = 0 ; i < cards.length - 1 ; ++i) {
			sb.append(cards[i] + ", ");
		}
		
		sb.append(cards[cards.length - 1]);
		}
		System.out.println(sb.toString());
	}

	
	public static void main(String [] args) {
		Freecell freecell = new Freecell();
        freecell.play(0);
        //freecell.play();
	}
	
	
}
