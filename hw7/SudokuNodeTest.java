import java.util.ArrayList;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Test class for SudokuNode to verify constructor functionality, expansion, and
 * deep cloning.
 */
public class SudokuNodeTest {

    public static void main(String[] args) {
        // Test the constructors
        testGridConstructor();
        testScannerConstructor();

        // Uncomment to test with System.in
        // testSystemInConstructor();

        // Test expansion and cloning
        testExpansionAndCloning();
    }

    /**
     * Tests the constructor that takes a 2D array.
     */
    private static void testGridConstructor() {
        // Create a Sudoku grid as specified
        int[][] grid = new int[][] {
                { 0, 2, 6, 5, 0, 4, 0, 0, 0 },
                { 0, 8, 0, 0, 0, 9, 0, 0, 0 },
                { 0, 0, 7, 0, 8, 0, 0, 0, 5 },
                { 0, 0, 0, 4, 0, 0, 0, 7, 0 },
                { 3, 0, 9, 0, 0, 0, 4, 0, 6 },
                { 0, 6, 0, 0, 0, 3, 0, 0, 0 },
                { 6, 0, 0, 0, 7, 0, 1, 0, 0 },
                { 0, 0, 0, 9, 0, 0, 0, 3, 0 },
                { 0, 0, 0, 2, 0, 8, 6, 5, 0 }
        };

        // Create a SudokuNode using the grid constructor
        SearchNode root = new SudokuNode(grid);

        // Verify construction is correct
        System.out.println("Grid Constructor Result:");
        System.out.println(root.toString());
    }

    /**
     * Tests the constructor that takes a Scanner with predefined input.
     */
    private static void testScannerConstructor() {
        // Create a Scanner with the specified input
        String input = ".265.4...\n.8...9...\n..7.8...5\n...4...7.\n3.9...4.6\n.6...3...\n6...7.1..\n...9...3.\n...2.865.\n";
        Scanner scanner = new Scanner(input);

        // Create a SudokuNode using the Scanner constructor
        SearchNode root = new SudokuNode(scanner);

        // Verify construction is correct
        System.out.println("\nScanner Constructor Result:");
        System.out.println(root.toString());
    }

    /**
     * Tests the constructor that takes System.in.
     * Note: This requires manual input and will pause execution waiting for input.
     */
    private static void testSystemInConstructor() {
        // Simulate System.in with our test data
        String input = ".265.4...\n.8...9...\n..7.8...5\n...4...7.\n3.9...4.6\n.6...3...\n6...7.1..\n...9...3.\n...2.865.\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create a SudokuNode using the System.in constructor
        System.out.println("\nSystem.in Constructor:");
        System.out.println("Please enter the Sudoku puzzle (9 lines of 9 characters each):");
        SearchNode root = new SudokuNode(new Scanner(System.in));

        // Restore original System.in
        System.setIn(originalIn);

        // Verify construction is correct
        System.out.println("\nSystem.in Constructor Result:");
        System.out.println(root.toString());
    }

    /**
     * Tests expansion and cloning functionality.
     */
    private static void testExpansionAndCloning() {
        // Create a Sudoku puzzle
        int[][] grid = new int[][] {
                { 0, 2, 6, 5, 0, 4, 0, 0, 0 },
                { 0, 8, 0, 0, 0, 9, 0, 0, 0 },
                { 0, 0, 7, 0, 8, 0, 0, 0, 5 },
                { 0, 0, 0, 4, 0, 0, 0, 7, 0 },
                { 3, 0, 9, 0, 0, 0, 4, 0, 6 },
                { 0, 6, 0, 0, 0, 3, 0, 0, 0 },
                { 6, 0, 0, 0, 7, 0, 1, 0, 0 },
                { 0, 0, 0, 9, 0, 0, 0, 3, 0 },
                { 0, 0, 0, 2, 0, 8, 6, 5, 0 }
        };

        SudokuNode rootNode = new SudokuNode(grid);

        // Save the string representation of root before expansion
        String rootBeforeExpansion = rootNode.toString();

        // Expand the root node
        ArrayList<SearchNode> children = rootNode.expand();

        // Verify root node hasn't changed during expansion
        String rootAfterExpansion = rootNode.toString();
        boolean rootUnchanged = rootBeforeExpansion.equals(rootAfterExpansion);

        // Print only the essential results
        System.out.println("\nExpansion Test Results:");
        System.out.println("Number of children: " + children.size());
        System.out.println("Root node unchanged after expansion: " + rootUnchanged);

        // Find which cell was expanded
        if (!children.isEmpty()) {
            SudokuNode firstChild = (SudokuNode) children.get(0);
            int row = -1, col = -1;

            // Find first difference between root and child
            outer: for (int r = 0; r < SudokuNode.SIZE; r++) {
                for (int c = 0; c < SudokuNode.SIZE; c++) {
                    if (rootNode.getCell(r, c) == SudokuNode.UNKNOWN &&
                            firstChild.getCell(r, c) != SudokuNode.UNKNOWN) {
                        row = r;
                        col = c;
                        break outer;
                    }
                }
            }

            if (row != -1) {
                System.out.println("First empty cell filled: [" + row + "][" + col + "]");
                System.out.println("Value placed in child: " + firstChild.getCell(row, col));
            }
        }
    }
}