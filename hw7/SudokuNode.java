import java.util.ArrayList;

public class SudokuNode extends SearchNode implements java.lang.Cloneable {
	public static final int SIZE = 9;
	public static final int UNKNOWN = 0;
	private int[][] grid;

	public SudokuNode(int[][] grid) {

		this.grid = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				this.grid[row][col] = grid[row][col];
			}
		}
	}

	public SudokuNode(java.util.Scanner scanner) {

		this.grid = new int[SIZE][SIZE];

		// Read 9 lines from the scanner
		for (int row = 0; row < SIZE; row++) {
			if (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				// Process each character in the line
				for (int col = 0; col < SIZE && col < line.length(); col++) {
					char c = line.charAt(col);
					if (c == '.') {
						grid[row][col] = UNKNOWN;
					}

					else if (c >= '1' && c <= '9') {
						grid[row][col] = Character.getNumericValue(c);
					}

					else {
						grid[row][col] = UNKNOWN;
					}
				}

				// If line is shorter than SIZE, fill remaining cells with UNKNOWN
				for (int col = line.length(); col < SIZE; col++) {
					grid[row][col] = UNKNOWN;
				}
			}

			else {
				// If fewer than SIZE lines, fill remaining rows with UNKNOWN
				for (int col = 0; col < SIZE; col++) {
					grid[row][col] = UNKNOWN;
				}
			}
		}
	}

	@Override
	public boolean isGoal() {
		// Check if any cell is UNKNOWN
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (grid[row][col] == UNKNOWN) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<SearchNode> expand() {
		ArrayList<SearchNode> children = new ArrayList<>();

		// Find first UNKNOWN cell
		int emptyRow = -1;
		int emptyCol = -1;

		// Search row by row, column by column
		outerLoop: for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (grid[row][col] == UNKNOWN) {
					emptyRow = row;
					emptyCol = col;
					break outerLoop;
				}
			}
		}

		// return empty list
		if (emptyRow == -1) {
			return children;
		}

		// For each possible value 1-9, check if it's valid for this cell
		for (int value = 1; value <= 9; value++) {
			if (isValidPlacement(emptyRow, emptyCol, value)) {
				// Create a new node with the value filled in
				SudokuNode child = (SudokuNode) this.childClone();
				child.grid[emptyRow][emptyCol] = value;
				children.add(child);
			}
		}

		return children;
	}

	private boolean isValidPlacement(int row, int col, int value) {
		// Check row
		for (int c = 0; c < SIZE; c++) {
			if (grid[row][c] == value) {
				// Value already exists in this row
				return false;
			}
		}

		// Check column
		for (int r = 0; r < SIZE; r++) {
			if (grid[r][col] == value) {
				return false;
			}
		}

		int blockRow = (row / 3) * 3;
		int blockCol = (col / 3) * 3;

		for (int r = blockRow; r < blockRow + 3; r++) {
			for (int c = blockCol; c < blockCol + 3; c++) {
				if (grid[r][c] == value) {
					return false;
				}
			}
		}

		// the value can be placed in the cell
		return true;
	}

	public int getCell(int row, int column) {
		return grid[row][column];

	}

	public java.lang.String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				sb.append(grid[row][col] == UNKNOWN ? '.' : grid[row][col]);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	public java.lang.Object clone() {
		// shallow clone from the parent
		SudokuNode copy = (SudokuNode) super.clone();

		// deep copy of the grid
		int[][] gridCopy = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				gridCopy[row][col] = this.grid[row][col];
			}
		}

		// Update the copy's grid with our deep copy
		copy.grid = gridCopy;

		return copy;
	}

}
