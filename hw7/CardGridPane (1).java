package application;

import java.awt.Insets;
import java.util.Stack;

public class CardGridPane extends GridPane {

	// Stacks for undo functionality
	private Stack<int[]> moves = new Stack<>();
	private Stack<String> cardStrings = new Stack<>();

	public final static int ROWS = 4;
	public final static int COLUMNS = 4;
	private StringProperty[][] cards = new StringProperty[ROWS][COLUMNS];

	// Constructor for CardGridPane
	public CardGridPane(StringProperty[][] cards) {
		this.cards = cards;
		for (int row = 0; row < cards.length; row++) {
			for (int column = 0; column < cards[row].length; column++) {
				final int currentRow = row;
				final int currentColumn = column;
				CardPane cardPane = new CardPane(cards[currentRow][currentColumn]);
				add(cardPane, currentColumn, currentRow);
				GridPane.setMargin(cardPane, new Insets(5));

				// Detect drag on the cardPane
				cardPane.setOnDragDetected(event -> {
					if (!cards[currentRow][currentColumn].get().isEmpty()) {
						ClipboardContent content = new ClipboardContent();
						content.putString(cards[currentRow][currentColumn].get()); // Add card value to clipboard
						cardPane.startDragAndDrop(TransferMode.MOVE).setContent(content);
						event.consume();
					}
				});

				// Allow drag over the cardPane
				cardPane.setOnDragOver(event -> {
					if (event.getGestureSource() != cardPane && event.getDragboard().hasString()) {
						event.acceptTransferModes(TransferMode.MOVE);
					}
					event.consume();
				});

				// Detect drag release on the cardPane
				cardPane.setOnDragDropped(event -> {
					CardPane source = (CardPane) event.getGestureSource();
					CardPane target = (CardPane) event.getSource();
					Integer srcRow = GridPane.getRowIndex(source);
					Integer srcCol = GridPane.getColumnIndex(source);
					Integer tgtRow = GridPane.getRowIndex(target);
					Integer tgtCol = GridPane.getColumnIndex(target);

					if (srcRow == null || srcCol == null || tgtRow == null || tgtCol == null) {
						return; // Invalid move
					}

					int sourceRow = srcRow;
					int sourceColumn = srcCol;
					int targetRow = tgtRow;
					int targetColumn = tgtCol;

					// Validate the move
					if (isValidMove(sourceRow, sourceColumn, targetRow, targetColumn)) {
						moves.push(new int[] { sourceRow, sourceColumn, targetRow, targetColumn });
						cardStrings.push(cards[targetRow][targetColumn].get());

						// Make the move
						cards[targetRow][targetColumn].set(cards[sourceRow][sourceColumn].get());
						cards[sourceRow][sourceColumn].set("");

						event.setDropCompleted(true);
					} else {
						event.setDropCompleted(false);
					}
					event.consume();
				});
			}
		}

		sceneProperty().addListener((obs, oldScene, newScene) -> {
			if (newScene != null) {
				newScene.setOnKeyPressed(event -> {
					// Check for Control-Z or U key presses
					if ((event.isControlDown() && event.getCode() == KeyCode.Z) ||
							event.getCode() == KeyCode.U) {
						undo();
					}
				});
			}
		});
	}

	// Method to validate the move
	public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

		if (sourceRow == targetRow && sourceColumn == targetColumn) {
			return false;
		}

		if (sourceRow != targetRow && sourceColumn != targetColumn) {
			return false;
		}

		if (cards[sourceRow][sourceColumn] == null || cards[targetRow][targetColumn] == null) {
			return false;
		}

		String sourceCard = cards[sourceRow][sourceColumn].get();
		String targetCard = cards[targetRow][targetColumn].get();

		if (sourceCard == null || sourceCard.isEmpty()) {
			return false;
		}

		if (targetCard == null || targetCard.isEmpty()) {
			return false;
		}

		if (sourceCard.length() != 2 || targetCard.length() != 2) {
			return false;
		}

		char sourceRank = Character.toUpperCase(sourceCard.charAt(0));
		char sourceSuit = Character.toUpperCase(sourceCard.charAt(1));
		char targetRank = Character.toUpperCase(targetCard.charAt(0));
		char targetSuit = Character.toUpperCase(targetCard.charAt(1));

		// Ensure both cards have valid ranks and suits
		String validRanks = "A23456789TJQK";
		String validSuits = "CHSD";
		if (validRanks.indexOf(sourceRank) == -1 || validRanks.indexOf(targetRank) == -1 ||
				validSuits.indexOf(sourceSuit) == -1 || validSuits.indexOf(targetSuit) == -1) {
			return false; // Invalid rank or suit
		}

		if (sourceSuit == targetSuit) {
			return true;
		}

		// Check if the cards have the same or adjacent ranks
		int sourceRankIndex = validRanks.indexOf(sourceRank);
		int targetRankIndex = validRanks.indexOf(targetRank);
		int rankDifference = Math.abs(sourceRankIndex - targetRankIndex);

		if (rankDifference <= 1) {
			return true;
		}

		return false;
	}

	// Method to undo the last move
	public void undo() {
		if (!moves.isEmpty() && !cardStrings.isEmpty()) {
			int[] move = moves.pop();
			int sourceRow = move[0];
			int sourceColumn = move[1];
			int targetRow = move[2];
			int targetColumn = move[3];

			// Set source card to current value of target card
			cards[sourceRow][sourceColumn].set(cards[targetRow][targetColumn].get());

			// Set target card back to its previous value
			cards[targetRow][targetColumn].set(cardStrings.pop());
		}
	}
}