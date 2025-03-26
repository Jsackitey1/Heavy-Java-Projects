import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class CardGridPane extends GridPane {

    private static final double GRID_PADDING = 10.0;
    private static final double GRID_GAP = 10.0;
    private static final double CARD_MARGIN = 5.0;

    public CardGridPane(StringProperty[][] cardGrid) {
        setPadding(new Insets(GRID_PADDING));
        setHgap(GRID_GAP);
        setVgap(GRID_GAP);
        setAlignment(Pos.CENTER);

        // Populate grid with CardPane objects
        for (int row = 0; row < cardGrid.length; row++) {
            for (int col = 0; col < cardGrid[row].length; col++) {
                CardPane cardPane = new CardPane(cardGrid[row][col]);
                setMargin(cardPane, new Insets(CARD_MARGIN));
                add(cardPane, col, row); // Add to GridPane
            }
        }
    }
}
