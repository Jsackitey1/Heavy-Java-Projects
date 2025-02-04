import java.util.Scanner;
// https://en.wikipedia.org/wiki/Toads_and_Frogs

public class ToadsAndFrogsSolver {
	public static final char TOAD = 'T', FROG = 'F', EMPTY = '-';

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Board position (using TF-)? ");
		String board = in.nextLine().trim().toUpperCase();
		System.out.print("Current player (T/F)? ");
		char currentPlayer = in.nextLine().trim().toUpperCase().charAt(0);
		System.out.println("Winner: " + getWinner(board.toCharArray(), currentPlayer));
		in.close();
	}

	public static char getWinner(char[] board, char currentPlayer) {
		// TODO - implement
		
		// Algorithm: 
		// For each legal move for the current player,
		//   - if we call getWinner on the resulting game state (new board, other player)
		//     and find that this leads to a win for the current player, 
		//     then return the current player.
		// Otherwise, failing to find a winning play (or any legal play), return the opponent player.
		
		
		
		
		/*getWinner(board, current player):

		for each board position:
		if the current player is 'T' and a 'T' is at this position:
		if a move is not out of bounds and there is an empty space to the right:
		make the move in the board
		char winner = getWinner(board, 'F')
		unmake the move in the board
		if winner == current player, return current player
		if a hop is not out of bounds and there is a 'F' to the right and a '-' to the right of that:
		... (same structure as with move)
		else (the current player is 'F' and 'F' is at this position):
		... (same structure as with current player 'T')
		return opponent player  (Note: We only reach this point if either (BASE CASE) no legal moves were found, or (RECURSIVE CASE) all legal moves were wins for the opponent.)*/

		return board[0];
	}

}
