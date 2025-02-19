import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Freecell {
	private Random random = new Random();
	static Scanner input = new Scanner(System.in);
	
	static FreecellGame game;

	public Freecell() {

	}

	public void play() throws IllegalPlayException {
		play(random.nextInt((int) (Math.random() * 100)));
	}

	public void play(long seed) throws IllegalPlayException {
		
		game = new FreecellGame(seed);
		
		while (true) {
			
			
			int from = -2, to = -2;

			while (true) {
				try {
					
					printBoard(game);
					
					System.out.print("Please enter source and destination card stacks, or \"-1\" to quit: ");
					

					if (!input.hasNextInt()) {
						throw new InputMismatchException();
						
					}

					from = input.nextInt();
					if (from == -1)
						return;

					if (!input.hasNextInt()) {
						throw new InputMismatchException();
					}

					to = input.nextInt();
					if (to == -1)
						return;

					try {
						game.play(from, to);
					} catch (IllegalPlayException e) {
						System.out.println("Illegal play: " + e.getMessage());
						continue;
					}

					break; 
				} catch (InputMismatchException e) {
					System.out.println("Source and destination card stacks must be entered as integers (1-16).");
					input.nextLine(); 
					continue;
				}
			}

			if (game.isGameOver()) {
				System.out.println("You Win!");
				break;
			}
		}
	}

	private void printArray(Card[] cards) {
		StringBuilder sb = new StringBuilder();
		if (cards.length == 0) {
			sb.append("");
		} else {
			for (int i = 0; i < cards.length - 1; ++i) {
				sb.append(cards[i] + ", ");
			}

			sb.append(cards[cards.length - 1]);
		}
		System.out.println(sb.toString());
	}
	
	
	public void printBoard(FreecellGame game) {
		System.out.println();
		System.out.println("Cells:");
		for (int i = 1; i <= 4; i++) {
			System.out.printf("%2d: %s%n", i, game.getStack(i).length == 0 ? "" : game.getStack(i)[0]);
		}

		System.out.println("Foundations:");
		for (int i = 5; i <= 8; i++) {
			System.out.printf("%2d: %s%n", i,
					game.getStack(i).length == 0 ? "" : game.getStack(i)[game.getStack(i).length - 1]);
		}

		System.out.println("Cascades:");
		for (int i = 9; i <= 16; i++) {
			System.out.printf("%2d: ", i);
			printArray(game.getStack(i));
		}

		System.out.println();
		
	}

	public static void main(String[] args) throws IllegalPlayException {
		Freecell freecell = new Freecell();
		freecell.play(2);
		// freecell.play();
	}

}
