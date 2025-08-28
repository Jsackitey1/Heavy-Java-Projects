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
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (grid[row][col] == UNKNOWN) {
					emptyRow = row;
					emptyCol = col;
					break;
				}
			}
			if (emptyRow != -1)
				break;
		}

		// If no empty cell found, return empty list
		if (emptyRow == -1)
			return children;

		// For each valid value (1-9)
		for (int value = 1; value <= 9; value++) {
			if (isValidPlacement(emptyRow, emptyCol, value)) {
				// Create a deeply-cloned child using childClone
				SudokuNode child = (SudokuNode) this.childClone();

				// Fill in the candidate value in the UNKNOWN cell
				child.grid[emptyRow][emptyCol] = value;

				// Add child to the list
				children.add(child);
			}
		}

		return children;
	}

	private boolean isValidPlacement(int row, int col, int num) {
		// Check row
		for (int c = 0; c < SIZE; c++) {
			if (grid[row][c] == num)
				return false;
		}

		// Check column
		for (int r = 0; r < SIZE; r++) {
			if (grid[r][col] == num)
				return false;
		}

		int subgridRowStart = (row / 3) * 3;
		int subgridColStart = (col / 3) * 3;
		for (int r = subgridRowStart; r < subgridRowStart + 3; r++) {
			for (int c = subgridColStart; c < subgridColStart + 3; c++) {
				if (grid[r][c] == num)
					return false;
			}
		}

		return true;
	}

	public int getCell(int row, int col) throws IndexOutOfBoundsException {
		if (row < 0 || row >= 9 || col < 0 || col >= 9) {
			throw new IndexOutOfBoundsException("Cell index out of bounds.");
		}
		return grid[row][col];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (grid[row][col] == UNKNOWN) {
					sb.append('.');
				} else {
					// Append the digit (1-9)
					sb.append(grid[row][col]);
				}
			}
			if (row < SIZE - 1) {
				sb.append('\n');
			}
		}

		return sb.toString();
	}

	
	
	
	@Override
	public java.lang.Object clone() {
		// Get shallow clone from the parent
		SudokuNode copy = (SudokuNode) super.clone();

		// Deep copy the grid
		int[][] gridCopy = new int[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				gridCopy[row][col] = this.grid[row][col];
			}
		}

		// Replace the grid with the deep copy
		copy.grid = gridCopy;

		return copy;
	}

}
