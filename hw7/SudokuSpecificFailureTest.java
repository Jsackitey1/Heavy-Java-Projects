import java.util.Scanner;

/**
 * Test class to verify the specific failing test case.
 */
public class SudokuSpecificFailureTest {

    public static void main(String[] args) {
        // Test with the failing test case
        testSpecificFailureCase();
    }

    /**
     * Tests the specific failure case from the JUnit test.
     */
    private static void testSpecificFailureCase() {
        // The puzzle from the failing test
        String puzzleInput = "43..8....\n" +
                "..82.....\n" +
                "..5....3.\n" +
                ".....5..7\n" +
                "...4.6.5.\n" +
                "..632...8\n" +
                ".2..4....\n" +
                "1..6...29\n" +
                ".4.9....5\n";

        // Create a SudokuNode with the puzzle
        Scanner scanner = new Scanner(puzzleInput);
        SudokuNode puzzleNode = new SudokuNode(scanner);

        // Display the puzzle
        System.out.println("Initial Puzzle:");
        System.out.println(puzzleNode);

        // Create the solution grid
        int[][] solutionGrid = {
                { 4, 3, 1, 5, 8, 7, 6, 9, 2 },
                { 9, 6, 8, 2, 3, 4, 5, 7, 1 },
                { 2, 7, 5, 1, 6, 9, 8, 3, 4 },
                { 3, 1, 4, 8, 9, 5, 2, 6, 7 },
                { 8, 9, 2, 4, 7, 6, 1, 5, 3 },
                { 7, 5, 6, 3, 2, 1, 9, 4, 8 },
                { 5, 2, 9, 7, 4, 8, 3, 1, 6 },
                { 1, 8, 7, 6, 5, 3, 4, 2, 9 },
                { 6, 4, 3, 9, 1, 2, 7, 8, 5 }
        };

        // Create a SudokuNode with the solution
        SudokuNode solutionNode = new SudokuNode(solutionGrid);

        // Check if isGoal() correctly identifies this as a solution
        boolean isGoal = solutionNode.isGoal();

        System.out.println("\nComplete solution grid:");
        System.out.println(solutionNode);
        System.out.println("isGoal() returns: " + isGoal);
        System.out.println("Expected: true");

        // Also test with a direct comparison to the expected output
        String expected = "431587692\n" +
                "968234571\n" +
                "275169834\n" +
                "314895267\n" +
                "892476153\n" +
                "756321948\n" +
                "529748316\n" +
                "187653429\n" +
                "643912785";

        System.out.println("\nExpected solution:");
        System.out.println(expected);

        String actual = solutionNode.toString();
        System.out.println("\nActual solution (via toString()):");
        System.out.println(actual);

        System.out.println("\nSolutions match: " + expected.equals(actual));

        // If they don't match, print character by character comparison
        if (!expected.equals(actual)) {
            System.out.println("\nDetailed comparison:");
            if (expected.length() != actual.length()) {
                System.out.println("Length mismatch! Expected: " + expected.length() + ", Actual: " + actual.length());
            }

            int minLength = Math.min(expected.length(), actual.length());
            for (int i = 0; i < minLength; i++) {
                char expChar = expected.charAt(i);
                char actChar = actual.charAt(i);
                if (expChar != actChar) {
                    System.out.println("Mismatch at position " + i + ": Expected '" +
                            (expChar == '\n' ? "\\n" : expChar) +
                            "', Actual '" +
                            (actChar == '\n' ? "\\n" : actChar) + "'");
                }
            }
        }
    }
}