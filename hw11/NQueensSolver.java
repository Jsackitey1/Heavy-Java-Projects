import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * NQueensSolver - a depth-first search solver of the <a href="https://en.wikipedia.org/wiki/Eight_queens_puzzle">n queens problem</a> that counts all solutions given n.
 * A non-attacking placement of queens is one in which no pair of queens shares the same row, same column, or same diagonal as another queen. 
 * @author Todd Neller
 */

public class NQueensSolver {

	/**
	 * boolean verbose - whether or not to print each solution found
	 */
	public boolean verbose = false;

	/**
	 * int n - the number of queens to place in a non-mutually attacking configuration on an n-by-n board
	 */
	private int n;	

	/**
	 * Construct a solver for finding and counting all solutions to the n queens problem.
	 * @param n
	 */
	public NQueensSolver(int n) {
		this.n = n;
	}

	/**
	 * Return the number of solutions to the n queens problem.
	 * @return the number of solutions to the n queens problem
	 */
	public long solve() {
		return solve(0, new int[n]); // begin by assigning a queen to row 0 given an empty array of assignments
	}

	/**
	 * Return the count of all solutions to the n queens problem given queen column assignments (cols) for rows less than parameter row.
	 * @param row the current 0-based row one in which one is seeking to place a non-attacking queen
	 * @param cols a length n array indexed by 0-based row, providing non-attacking 0-based column assignments for queens prior to the given row parameter. 
	 * @return the number of ways all remaining queens may be placed in a non-attacking configuration subject to the constraint of prior row queen placements
	 */
	private long solve(int row, int[] cols) {
		// TODO - implement
		
		// Algorithm: 
		// If the row is n, (solution base case) 
		// - if verbose is true, print the cols array for testing/debugging purposes
		// - return 1
		// Otherwise: (recursive case)
		// For each column c in the given row:
		//  - iterate through previous rows 0 through row - 1 searching for queen that attacks this row, column c (and stopping iteration if a conflict is found)
		//    Note: If the previous row is r, an expression to check for same column/diagonal conflicts is (cols[r] == c || Math.abs(cols[r] - c) == row - r)
		//  - if no conflicts were found with a queen placement in column c for this row:
		//  --- assign c to cols[row]
		//  --- recursively call solve(row + 1, cols), adding the return value to a _local_ sum variable (not field).
		// Return the local sum of all return values from recursive calls.
	}

	/**
	 * Return the number of solutions to the n queens problem, computed with n threads. Thread number i will count the number of solutions
	 * with the row 0 queen placed in column i.  The results of all n threads will then be accumulated to a total sum that is returned.
	 * @return the number of solutions to the n queens problem
	 */
	public long solveNThreads() {
		// TODO - implement

		return 0;
	}
	
	/**
	 * Obtain the problem size n from args or, if not given, from the standard input.  Then find and count all solutions and print the count.
	 * @param args optionally, a String array containing a String of a single positive integer n specifying the problem size.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		if (args.length == 0)
			System.out.print("Number of queens? ");
		String sizeStr = (args.length == 0) ? in.next() : args[0];
		int size = 8;
		try {
			size = Integer.parseInt(sizeStr);
		}
		catch (NumberFormatException ex) {
			System.err.println("Invalid NQueensSolver size: " + sizeStr);
			System.exit(1);
		}
		NQueensSolver solver = new NQueensSolver(size);

		// Uncomment to show solutions (for debugging purposes):
//		solver.verbose = true;
		
		long startTime = System.currentTimeMillis();
		System.out.println("Serial: " + solver.solve() + " solutions in " + (System.currentTimeMillis() - startTime) + " ms");
		startTime = System.currentTimeMillis();
		System.out.println("Parallel: " + solver.solveNThreads() + " solutions in " + (System.currentTimeMillis() - startTime) + " ms");
		in.close();
	}
}
public class NQueensSolver {

}
