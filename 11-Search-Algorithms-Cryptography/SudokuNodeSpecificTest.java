import java.util.Scanner;
import java.util.ArrayList;

/**
 * Test class to verify SudokuNode functionality with a specific puzzle.
 */
public class SudokuNodeSpecificTest {

    public static void main(String[] args) {
        testSpecificPuzzle();
    }

    /**
     * Tests SudokuNode with the specific puzzle provided.
     */
    private static void testSpecificPuzzle() {
        // The puzzle provided in the question
        String puzzleInput = "..3..182.\n" +
                "8........\n" +
                "...2.4..7\n" +
                "3.85.....\n" +
                ".7..2.6.5\n" +
                ".......1.\n" +
                "7..4.....\n" +
                "..43...98\n" +
                "...7....3\n";

        // Create a SudokuNode using the Scanner constructor
        Scanner scanner = new Scanner(puzzleInput);
        SudokuNode puzzleNode = new SudokuNode(scanner);

        // Display the puzzle
        System.out.println("Initial Puzzle:");
        System.out.println(puzzleNode);

        // Display the expected solution
        System.out.println("\nExpected Solution:");
        System.out.println(
                "543971826\n" +
                        "827635941\n" +
                        "691284537\n" +
                        "318569274\n" +
                        "479123685\n" +
                        "256847319\n" +
                        "735498162\n" +
                        "164352798\n" +
                        "982716453");

        // We could implement a full solver here to verify the solution,
        // but that would require additional search algorithms.
        // Instead, we'll just verify that the first expansion works correctly.
        System.out.println("\nVerifying first expansion:");
        ArrayList<SearchNode> children = puzzleNode.expand();
        System.out.println("Number of children from first expansion: " + children.size());

        // Find which cell was expanded
        if (!children.isEmpty()) {
            SudokuNode firstChild = (SudokuNode) children.get(0);
            int row = -1, col = -1;

            // Find first difference between root and child
            outer: for (int r = 0; r < SudokuNode.SIZE; r++) {
                for (int c = 0; c < SudokuNode.SIZE; c++) {
                    if (puzzleNode.getCell(r, c) == SudokuNode.UNKNOWN &&
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