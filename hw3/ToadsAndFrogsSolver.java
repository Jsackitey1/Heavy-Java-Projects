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
		
	    char opponent = (currentPlayer == TOAD) ? FROG : TOAD;
	    
	    //for each board position:
	    
	    for (int i = 0; i < board.length; i++) {
	    	//if the current player is 'T' and a 'T' is at this position:
	        if (board[i] == currentPlayer) {
	            if (currentPlayer == TOAD) {
	            	//if a move is not out of bounds and there is an empty space to the right:
	                if (i + 1 < board.length && board[i + 1] == EMPTY) {
	                    board[i] = EMPTY;
	                    board[i + 1] = TOAD;
//	                    make the move in the board
//	                    char winner = getWinner(board, 'F')
	                    
	                    char winner = getWinner(board, opponent);
	                    
	                    board[i] = TOAD;
	                    board[i + 1] = EMPTY;
//	                    if winner == current player, return current player
	                    if (winner == currentPlayer) {
	                        return currentPlayer;
	                    }
	                }
//	                if a hop is not out of bounds and there is a 'F' to the right and a '-' to the right of that:
//	                ... (same structure as with move)
	                if (i + 2 < board.length && board[i + 1] == FROG && board[i + 2] == EMPTY) {
	                    board[i] = EMPTY;
	                    board[i + 2] = TOAD;
	                    
	                    char winner = getWinner(board, opponent);
	                    
	                    board[i] = TOAD;
	                    board[i + 2] = EMPTY;
	                    
	                    if (winner == currentPlayer) {
	                        return currentPlayer;
	                    }
	                }
	            }
//	            else (the current player is 'F' and 'F' is at this position):
//	        		... (same structure as with current player 'T')
	            else {
	                if (i - 1 >= 0 && board[i - 1] == EMPTY) {
	                    board[i] = EMPTY;
	                    board[i - 1] = FROG;
	                    
	                    char winner = getWinner(board, opponent);
	                    
	                    board[i] = FROG;
	                    board[i - 1] = EMPTY;
	                    
	                    if (winner == currentPlayer) {
	                        return currentPlayer;
	                    }
	                }
	                
	                // Check hop left over opponent
	                if (i - 2 >= 0 && board[i - 1] == TOAD && board[i - 2] == EMPTY) {
	                    // Make hop
	                    board[i] = EMPTY;
	                    board[i - 2] = FROG;
	                    
	                    // Recursive check
	                    char winner = getWinner(board, opponent);
	                    
	                    // Unmake hop
	                    board[i] = FROG;
	                    board[i - 2] = EMPTY;
	                    
	                    if (winner == currentPlayer) {
	                        return currentPlayer;
	                    }
	                }
	            }
	        }
	    }
	    
	    // If no winning moves found, return opponent
	    return board[0];
	}

}
