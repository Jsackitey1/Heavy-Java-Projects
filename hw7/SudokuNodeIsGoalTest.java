import java.util.Scanner;

/**
 * Test class to specifically verify the isGoal() method of SudokuNode.
 */
public class SudokuNodeIsGoalTest {

    public static void main(String[] args) {
        // Test with the solution node
        testSolutionNode();

        // Test with a partially filled node
        testPartialNode();

        // Test with an invalid solution (contains a repeated value)
        testInvalidSolution();
    }

    /**
     * Tests the isGoal() method with a complete solution
     */
    private static void testSolutionNode() {
        // Create the complete solution grid
        int[][] solutionGrid = {
                { 5, 4, 3, 9, 7, 1, 8, 2, 6 },
                { 8, 2, 7, 6, 3, 5, 9, 4, 1 },
                { 6, 9, 1, 2, 8, 4, 5, 3, 7 },
                { 3, 1, 8, 5, 6, 9, 2, 7, 4 },
                { 4, 7, 9, 1, 2, 3, 6, 8, 5 },
                { 2, 5, 6, 8, 4, 7, 3, 1, 9 },
                { 7, 3, 5, 4, 9, 8, 1, 6, 2 },
                { 1, 6, 4, 3, 5, 2, 7, 9, 8 },
                { 9, 8, 2, 7, 1, 6, 4, 5, 3 }
        };

        // Create a SudokuNode with the solution
        SudokuNode solutionNode = new SudokuNode(solutionGrid);

        // Check if isGoal() correctly identifies this as a solution
        boolean isGoal = solutionNode.isGoal();

        System.out.println("Complete solution grid:");
        System.out.println(solutionNode);
        System.out.println("isGoal() returns: " + isGoal);
        System.out.println("Expected: true");
        System.out.println();
    }

    /**
     * Tests the isGoal() method with a partially filled grid
     */
    private static void testPartialNode() {
        // Create the partial puzzle grid
        String puzzleInput = "..3..182.\n" +
                "8........\n" +
                "...2.4..7\n" +
                "3.85.....\n" +
                ".7..2.6.5\n" +
                ".......1.\n" +
                "7..4.....\n" +
                "..43...98\n" +
                "...7....3\n";

        // Create a SudokuNode with the partial puzzle
        Scanner scanner = new Scanner(puzzleInput);
        SudokuNode partialNode = new SudokuNode(scanner);

        // Check if isGoal() correctly identifies this as not a solution
        boolean isGoal = partialNode.isGoal();

        System.out.println("Partial puzzle grid:");
        System.out.println(partialNode);
        System.out.println("isGoal() returns: " + isGoal);
        System.out.println("Expected: false");
        System.out.println();
    }

    /**
     * Tests the isGoal() method with an invalid solution (contains repeated values)
     */
    private static void testInvalidSolution() {
        // Create a grid that's filled but has repeated values in a row
        int[][] invalidGrid = {
                { 5, 4, 3, 9, 7, 1, 8, 2, 6 },
                { 8, 2, 7, 6, 3, 5, 9, 4, 1 },
                { 6, 9, 1, 2, 8, 4, 5, 3, 7 },
                { 3, 1, 8, 5, 6, 9, 2, 7, 4 },
                { 4, 7, 9, 1, 2, 3, 6, 8, 5 },
                { 2, 5, 6, 8, 4, 7, 3, 1, 9 },
                { 7, 3, 5, 4, 9, 8, 1, 6, 2 },
                // This row has a repeated 6 (instead of 7)
                { 1, 6, 4, 3, 5, 2, 6, 9, 8 },
                { 9, 8, 2, 7, 1, 6, 4, 5, 3 }
        };

        // Create a SudokuNode with the invalid solution
        SudokuNode invalidNode = new SudokuNode(invalidGrid);

        // Check if isGoal() correctly identifies this as not a solution
        boolean isGoal = invalidNode.isGoal();

        System.out.println("Invalid solution grid (duplicate value in row 8):");
        System.out.println(invalidNode);
        System.out.println("isGoal() returns: " + isGoal);
        System.out.println("Expected: false");
        System.out.println();

        // Also check a specific test for whether the node detects values are filled
        boolean allFilled = true;
        for (int r = 0; r < SudokuNode.SIZE; r++) {
            for (int c = 0; c < SudokuNode.SIZE; c++) {
                if (invalidNode.getCell(r, c) == SudokuNode.UNKNOWN) {
                    allFilled = false;
                    break;
                }
            }
        }
        System.out.println("All cells filled: " + allFilled);
        System.out.println("Expected: true");
    }
}