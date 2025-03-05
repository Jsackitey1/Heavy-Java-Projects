import java.util.Scanner;

/**
 * SearchTest - apply a search algorithm to a root search node
 */
public class SearcherTest {

	/**
	 * <code>main</code> - apply a search algorithm to a root search node
	 * @param args (unused) */
	public static void main(String[] args){

		// Create the searcher object (uncomment desired code):
		Searcher searcher = new BreadthFirstSearcher();
		//	Searcher searcher = new DepthFirstSearcher();

		// Create the root search node (uncomment desired code):
		SearchNode root = new BucketsNode();
		//	SearchNode root = new SudokuNode(new Scanner(System.in));
		
		// Note how we can now test an abstract Searcher searching with an abstract SearchNode:
		if (searcher.search(root)) {
			// successful search
			System.out.println("Goal node found in " + searcher.getNodeCount() 
			+ " nodes.");
			System.out.println("Goal path:");
			searcher.printGoalPath();
		} else {
			// unsuccessful search
			System.out.println("Goal node not found in " 
					+ searcher.getNodeCount() + " nodes.");
		}
	}
} // SearchTest
