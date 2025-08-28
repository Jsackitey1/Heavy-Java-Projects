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
						System.out.println();
						throw new InputMismatchException();

					}

					from = input.nextInt();
					if (from == -1)
						return;

					if (!input.hasNextInt()) {
						System.out.println();
						throw new InputMismatchException();
					}

					to = input.nextInt();
					if (to == -1)
						return;

					try {
						play(from, to);
					} catch (IllegalPlayException e) {
						System.out.println();
						System.out.println("Illegal play: " + e.getMessage());
						continue;
					}

					break;
				} catch (InputMismatchException e) {
					System.out.println();
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

	public void play(int from, int to) throws IllegalPlayException {
		// Check for valid stack numbers
		if (from < 1 || from > 16 || to < 1 || to > 16) {
			throw new IllegalPlayException("Illegal stack number. Stacks are numbered 1-16.");
		}

		// Check if source and destination are the same
		if (from == to) {
			throw new IllegalPlayException("Source and destination stacks must be different.");
		}

		// Check for foundation source
		if (from >= 5 && from <= 8) {
			throw new IllegalPlayException("That card stack cannot be played from.");
		}

		try {
			game.play(from, to);
		} catch (IllegalPlayException e) {
			throw e; 
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
			System.out.printf("%2d: ", i);
			printArray(game.getStack(i));
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
