import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import java.util.Stack;

public class CardGridPane extends GridPane {

    private static final double GRID_PADDING = 10.0;
    private static final double GRID_GAP = 10.0;
    private static final double CARD_MARGIN = 4.0;
    private static final String RANKS = "A23456789TJQK";
    private StringProperty[][] cardGrid; // Store reference to card grid

    // Inner class to hold move information
    private static class MoveInfo {
        int sourceRow;
        int sourceCol;
        int targetRow;
        int targetCol;
        String coveredCard; // The card that was at the destination before the move

        MoveInfo(int sr, int sc, int tr, int tc, String cc) {
            sourceRow = sr;
            sourceCol = sc;
            targetRow = tr;
            targetCol = tc;
            coveredCard = cc;
        }
    }

    private Stack<MoveInfo> undoStack;

    public CardGridPane(StringProperty[][] cardGrid) {
        this.cardGrid = cardGrid; // Store reference to card grid
        undoStack = new Stack<>();
        setPadding(new Insets(GRID_PADDING));
        setHgap(GRID_GAP);
        setVgap(GRID_GAP);
        setAlignment(Pos.CENTER);

        // Set up scene property listener for key events
        sceneProperty().addListener((o, oldVal, newVal) -> {
            if (getScene() != null) {
                getScene().setOnKeyPressed(ev -> {
                    // Check for Control-z or 'u' key press
                    if ((ev.isControlDown() && ev.getCode().toString().equals("Z")) ||
                            ev.getCode().toString().equals("U")) {
                        handleUndo();
                    }
                });
            }
        });

        // Populate grid with CardPane objects
        for (int row = 0; row < cardGrid.length; row++) {
            for (int col = 0; col < cardGrid[row].length; col++) {
                CardPane cardPane = new CardPane(cardGrid[row][col]);
                setMargin(cardPane, new Insets(CARD_MARGIN));

                // Set up drag detection
                cardPane.setOnDragDetected(event -> {
                    cardPane.startFullDrag();
                });

                // Set up drag release handling
                cardPane.setOnMouseDragReleased(event -> {
                    CardPane source = (CardPane) event.getGestureSource();
                    CardPane target = (CardPane) event.getSource();

                    int sourceRow = GridPane.getRowIndex(source);
                    int sourceCol = GridPane.getColumnIndex(source);
                    int targetRow = GridPane.getRowIndex(target);
                    int targetCol = GridPane.getColumnIndex(target);

                    // Get the card values
                    String sourceCard = cardGrid[sourceRow][sourceCol].get();
                    String targetCard = cardGrid[targetRow][targetCol].get();

                    // Check if move is legal
                    if (isLegalMove(sourceRow, sourceCol, targetRow, targetCol, sourceCard, targetCard)) {
                        // Store move information before making the move
                        undoStack.push(new MoveInfo(sourceRow, sourceCol, targetRow, targetCol, targetCard));

                        // Execute the move
                        cardGrid[targetRow][targetCol].set(sourceCard);
                        cardGrid[sourceRow][sourceCol].set("");

                        // Debug print to verify stack contents
                        System.out.println("Move stored in stack:");
                        System.out.printf("Source: (%d, %d), Target: (%d, %d), Covered: %s%n",
                                sourceRow, sourceCol, targetRow, targetCol, targetCard);
                        System.out.println("Current stack size: " + undoStack.size());
                    }
                });

                add(cardPane, col, row);
            }
        }
    }

    private void handleUndo() {
        if (!undoStack.isEmpty()) {
            MoveInfo move = undoStack.pop();

            // Get the current card at the target position
            String currentTargetCard = cardGrid[move.targetRow][move.targetCol].get();

            // Restore the cards
            cardGrid[move.sourceRow][move.sourceCol].set(currentTargetCard);
            cardGrid[move.targetRow][move.targetCol].set(move.coveredCard);
        }
    }

    private boolean isLegalMove(int sourceRow, int sourceCol, int targetRow, int targetCol,
            String sourceCard, String targetCard) {
        // Check if positions are different
        if (sourceRow == targetRow && sourceCol == targetCol) {
            return false;
        }

        // Check if positions share same row or column
        if (sourceRow != targetRow && sourceCol != targetCol) {
            return false;
        }

        // Check if both cards are valid 2-character card names
        if (sourceCard == null || targetCard == null ||
                sourceCard.length() != 2 || targetCard.length() != 2) {
            return false;
        }

        // Check if cards share same suit or adjacent ranks
        char sourceSuit = sourceCard.charAt(1);
        char targetSuit = targetCard.charAt(1);
        char sourceRank = sourceCard.charAt(0);
        char targetRank = targetCard.charAt(0);

        // Check if suits match
        if (sourceSuit == targetSuit) {
            return true;
        }

        // Check if ranks are adjacent
        int sourceRankIndex = RANKS.indexOf(sourceRank);
        int targetRankIndex = RANKS.indexOf(targetRank);
        if (sourceRankIndex == -1 || targetRankIndex == -1) {
            return false;
        }
        return Math.abs(sourceRankIndex - targetRankIndex) <= 1;
    }
}
