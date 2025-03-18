import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class CardPane extends Pane {

    private static final double ASPECT_RATIO = 635.0 / 889.0;
    private static final String IMAGE_PATH = "/Courses/cs112/images/suits/";

    private StringProperty cardStr;
    private Rectangle cardBackground;
    private GridPane symbolGrid;
    private ImageView[] suitImages;

    public CardPane(StringProperty cardStr) {
        super();
        this.cardStr = cardStr;

        // Initialize card background
        cardBackground = new Rectangle();
        cardBackground.setFill(Color.WHITE);
        cardBackground.setStroke(Color.BLACK);
        cardBackground.setStrokeWidth(2);

        // Initialize symbol grid
        symbolGrid = new GridPane();
        symbolGrid.setAlignment(Pos.CENTER);
        symbolGrid.setHgap(5);
        symbolGrid.setVgap(5);
        symbolGrid.setPadding(new Insets(10));

        // Initialize suit images
        suitImages = new ImageView[4];
        String[] suits = { "club", "heart", "spade", "diamond" };
        String[] colors = { "green", "pink", "black", "blue" };

        for (int i = 0; i < 4; i++) {
            String imagePath = IMAGE_PATH + "suit-" + colors[i] + "-" + suits[i] + "-100.png";
            Image image = new Image(imagePath);
            suitImages[i] = new ImageView(image);
            suitImages[i].setPreserveRatio(true);
        }

        // Add components to pane
        getChildren().addAll(cardBackground, symbolGrid);

        // Add listeners for resizing and card changes
        widthProperty().addListener((obs, oldVal, newVal) -> updateSize());
        heightProperty().addListener((obs, oldVal, newVal) -> updateSize());
        cardStr.addListener((obs, oldVal, newVal) -> changeCard());

        // Initial card update
        changeCard();
    }

    private void updateSize() {
        double width = getWidth();
        double height = getHeight();

        // Maintain aspect ratio
        if (width / height > ASPECT_RATIO) {
            width = height * ASPECT_RATIO;
        } else {
            height = width / ASPECT_RATIO;
        }

        cardBackground.setWidth(width);
        cardBackground.setHeight(height);

        // Update symbol grid size
        double symbolSize = Math.min(width, height) * 0.15;
        for (ImageView symbol : suitImages) {
            symbol.setFitWidth(symbolSize);
            symbol.setFitHeight(symbolSize);
        }
    }

    private void changeCard() {
        String card = cardStr.get().toUpperCase();
        if (card.length() != 2)
            return;

        char rank = card.charAt(0);
        char suit = card.charAt(1);

        // Clear previous symbols
        symbolGrid.getChildren().clear();

        // Determine rank value
        int rankValue;
        if (rank == 'A')
            rankValue = 1;
        else if (rank == 'T')
            rankValue = 10;
        else if (rank == 'J')
            rankValue = 11;
        else if (rank == 'Q')
            rankValue = 12;
        else if (rank == 'K')
            rankValue = 13;
        else if (Character.isDigit(rank))
            rankValue = Character.getNumericValue(rank);
        else
            return;

        // Determine suit index
        int suitIndex;
        switch (suit) {
            case 'C':
                suitIndex = 0;
                break; // Club
            case 'H':
                suitIndex = 1;
                break; // Heart
            case 'S':
                suitIndex = 2;
                break; // Spade
            case 'D':
                suitIndex = 3;
                break; // Diamond
            default:
                return;
        }

        // Calculate grid dimensions
        int gridSize = (int) Math.ceil(Math.sqrt(rankValue));

        // Add symbols to grid
        for (int i = 0; i < rankValue; i++) {
            int row = i / gridSize;
            int col = i % gridSize;
            ImageView symbol = new ImageView(suitImages[suitIndex].getImage());
            symbol.setPreserveRatio(true);
            symbolGrid.add(symbol, col, row);
        }
    }
}
