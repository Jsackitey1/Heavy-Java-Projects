import javafx.application.Application;

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

    // Constants for card dimensions, image paths, and layout
    // Standard poker card aspect ratio
    // Path to suit images
    // Radius for rounded card corners
    // Margin between card edge and content
    // Padding between symbols in the grid
    private static final double ASPECT_RATIO = 635.0 / 889.0;
    private static final String IMAGE_PATH = " /Courses/cs112/images/suits/";
    private static final double CORNER_RADIUS = 20.0;
    private static final double CARD_MARGIN = 20.0;
    private static final double SYMBOL_PADDING = 10.0;

    // Static arrays for suit images and their properties
    // Array to hold suit images
    // Suit names
    // Suit colors
    private static final ImageView[] suitImages;
    private static final String[] SUITS = { "club", "heart", "spade", "diamond" };
    private static final String[] COLORS = { "green", "pink", "black", "blue" };

    static {
        // Initialize suit images once to avoid reloading them multiple times
        suitImages = new ImageView[4];
        for (int i = 0; i < 4; i++) {
            String imagePath = IMAGE_PATH + "suit-" + COLORS[i] + "-" + SUITS[i] + "-100.png";
            Image image = new Image(imagePath);
            suitImages[i] = new ImageView(image);
            suitImages[i].setPreserveRatio(true); // Maintain aspect ratio of images
        }
    }

    // Instance variables for card properties and layout
    private StringProperty cardStr; // The card string (e.g., "AS" for Ace of Spades)
    private Rectangle cardBackground; // Background rectangle for the card
    private GridPane symbolGrid; // Grid to hold suit symbols

    public CardPane(StringProperty cardStr) {
        super();
        this.cardStr = cardStr;

        // Set preferred size to match standard poker card dimensions
        setPrefSize(635, 889);

        // Initialize card background with rounded corners
        cardBackground = new Rectangle();
        cardBackground.setFill(Color.WHITE);
        cardBackground.setStroke(Color.BLACK);
        cardBackground.setStrokeWidth(2);
        cardBackground.setArcWidth(CORNER_RADIUS);
        cardBackground.setArcHeight(CORNER_RADIUS);

        // Initialize symbol grid with proper padding
        symbolGrid = new GridPane();
        symbolGrid.setAlignment(Pos.CENTER);
        symbolGrid.setHgap(SYMBOL_PADDING);
        symbolGrid.setVgap(SYMBOL_PADDING);
        symbolGrid.setPadding(new Insets(CARD_MARGIN));

        // Add components to pane
        getChildren().addAll(cardBackground, symbolGrid);

        // Add listeners for resizing and card changes
        widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue();
            double newHeight = newWidth / ASPECT_RATIO;
            setHeight(newHeight);
            updateSize();
        });

        heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue();
            double newWidth = newHeight * ASPECT_RATIO;
            setWidth(newWidth);
            updateSize();
        });

        cardStr.addListener((obs, oldVal, newVal) -> changeCard());

        // Initial card update
        changeCard();
    }

    private void updateSize() {
        double width = getWidth();
        double height = getHeight();

        // Ensure we maintain aspect ratio
        if (width / height > ASPECT_RATIO) {
            width = height * ASPECT_RATIO;
        } else {
            height = width / ASPECT_RATIO;
        }

        // Center the card background
        cardBackground.setWidth(width);
        cardBackground.setHeight(height);
        cardBackground.setX((getWidth() - width) / 2);
        cardBackground.setY((getHeight() - height) / 2);

        // Update symbol grid size and position
        double availableWidth = width - (2 * CARD_MARGIN);
        double availableHeight = height - (2 * CARD_MARGIN);

        // Calculate symbol size based on available space and grid dimensions
        // Use the smaller of width/3 or height/5 to ensure symbols fit in grid
        double symbolWidth = availableWidth / 3;
        double symbolHeight = availableHeight / 5;
        double symbolSize = Math.min(symbolWidth, symbolHeight) * 0.8; // 80% of cell size to prevent touching

        for (ImageView symbol : suitImages) {
            symbol.setFitWidth(symbolSize);
            symbol.setFitHeight(symbolSize);
        }

        // Center the symbol grid
        symbolGrid.setLayoutX(cardBackground.getX() + CARD_MARGIN);
        symbolGrid.setLayoutY(cardBackground.getY() + CARD_MARGIN);
    }

    // Method to update the card display based on the card string
    private void changeCard() {
        String card = cardStr.get().toUpperCase();
        if (card.length() != 2)
            return;

        char rank = card.charAt(0);
        char suit = card.charAt(1);

        // Clear all existing children
        getChildren().clear();

        // Rebuild card background
        cardBackground = new Rectangle();
        cardBackground.setFill(Color.WHITE);
        cardBackground.setStroke(Color.BLACK);
        cardBackground.setStrokeWidth(2);
        cardBackground.setArcWidth(CORNER_RADIUS);
        cardBackground.setArcHeight(CORNER_RADIUS);

        // Rebuild symbol grid with fixed 5x3 layout
        symbolGrid = new GridPane();
        symbolGrid.setAlignment(Pos.CENTER);
        symbolGrid.setHgap(SYMBOL_PADDING);
        symbolGrid.setVgap(SYMBOL_PADDING);
        symbolGrid.setPadding(new Insets(CARD_MARGIN));

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
                break;
            case 'H':
                suitIndex = 1;
                break;
            case 'S':
                suitIndex = 2;
                break;
            case 'D':
                suitIndex = 3;
                break;
            default:
                return;
        }

        // Fixed grid dimensions: 5 rows, 3 columns
        final int GRID_ROWS = 5;
        final int GRID_COLS = 3;

        // Add symbols to grid
        for (int i = 0; i < rankValue; i++) {
            int row = i / GRID_COLS;
            int col = i % GRID_COLS;
            if (row < GRID_ROWS) { // Only add if within grid bounds
                ImageView symbol = new ImageView(suitImages[suitIndex].getImage());
                symbol.setPreserveRatio(true);
                symbolGrid.add(symbol, col, row);
            }
        }

        // Add components back to pane
        getChildren().addAll(cardBackground, symbolGrid);
    }
}
