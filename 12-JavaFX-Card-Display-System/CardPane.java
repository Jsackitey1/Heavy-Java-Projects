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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

public class CardPane extends Pane {

    // Constants for card dimensions, image paths, and layout
    // Standard poker card aspect ratio
    // Path to suit images
    // Radius for rounded card corners
    // Margin between card edge and content
    // Padding between symbols in the grid
    private static final double ASPECT_RATIO = 635.0 / 889.0;
    private static final String IMAGE_PATH = "./suits/"; // Changed to use relative path
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
            try {
                Image image = new Image(imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image: " + imagePath);
                    continue;
                }
                suitImages[i] = new ImageView(image);
                suitImages[i].setPreserveRatio(true); // Maintain aspect ratio of images
            } catch (Exception e) {
                System.err.println("Exception loading image: " + imagePath);
                e.printStackTrace();
            }
        }
    }

    // Instance variables for card properties and layout
    private StringProperty cardStr; // The card string (e.g., "AS" for Ace of Spades)
    private Rectangle cardBackground; // Background rectangle for the card
    private GridPane symbolGrid; // Grid to hold suit symbols

    public CardPane(StringProperty cardStr) {
        super();
        this.cardStr = cardStr;

        // Set a uniform preferred size for all cards
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

        // Ensure aspect ratio is maintained
        if (width / height > ASPECT_RATIO) {
            width = height * ASPECT_RATIO;
        } else {
            height = width / ASPECT_RATIO;
        }

        // Center the card background
        double cardX = (getWidth() - width) / 2;
        double cardY = (getHeight() - height) / 2;

        cardBackground.setWidth(width);
        cardBackground.setHeight(height);
        cardBackground.setX(cardX);
        cardBackground.setY(cardY);

        // Compute available space considering margins
        double availableWidth = width - (2 * CARD_MARGIN);
        double availableHeight = height - (2 * CARD_MARGIN);

        // Calculate symbol size based on available space
        // Use a smaller portion of the available space to ensure symbols fit
        double maxSymbolWidth = availableWidth / 3; // Account for 3 columns plus some padding
        double maxSymbolHeight = availableHeight / 5.5; // Account for 5 rows plus some padding
        double symbolSize = Math.min(maxSymbolWidth, maxSymbolHeight);

        // Update all symbol sizes in the grid
        for (javafx.scene.Node node : symbolGrid.getChildren()) {
            if (node instanceof ImageView) {
                ImageView symbol = (ImageView) node;
                symbol.setFitWidth(symbolSize);
                symbol.setFitHeight(symbolSize);
            }
        }

        // Calculate grid dimensions
        double gridWidth = (3 * symbolSize) + (2 * SYMBOL_PADDING); // 3 columns with 2 gaps
        double gridHeight = (5 * symbolSize) + (4 * SYMBOL_PADDING); // 5 rows with 4 gaps

        // Center the symbol grid within the card
        double gridX = cardX + (width - gridWidth) / 2;
        double gridY = cardY + (height - gridHeight) / 2;

        symbolGrid.setLayoutX(gridX);
        symbolGrid.setLayoutY(gridY);
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

        // Calculate symbol positions based on rank value
        if (rankValue == 1) {
            // Ace: center symbol
            ImageView symbol = new ImageView(suitImages[suitIndex].getImage());
            symbol.setPreserveRatio(true);
            symbolGrid.add(symbol, 1, 2); // Center position
        } else if (rankValue == 2) {
            // Two: top and bottom
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbolGrid.add(symbol1, 1, 0); // Top center
            symbolGrid.add(symbol2, 1, 4); // Bottom center
        } else if (rankValue == 3) {
            // Three: top, center, bottom
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbolGrid.add(symbol1, 1, 0); // Top center
            symbolGrid.add(symbol2, 1, 2); // Center
            symbolGrid.add(symbol3, 1, 4); // Bottom center
        } else if (rankValue == 4) {
            // Four: corners
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Top left
            symbolGrid.add(symbol2, 2, 0); // Top right
            symbolGrid.add(symbol3, 0, 4); // Bottom left
            symbolGrid.add(symbol4, 2, 4); // Bottom right
        } else if (rankValue == 5) {
            // Five: corners plus center
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Top left
            symbolGrid.add(symbol2, 2, 0); // Top right
            symbolGrid.add(symbol3, 1, 2); // Center
            symbolGrid.add(symbol4, 0, 4); // Bottom left
            symbolGrid.add(symbol5, 2, 4); // Bottom right
        } else if (rankValue == 6) {
            // Six: two columns of three
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol6 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbol6.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Left column
            symbolGrid.add(symbol2, 0, 2);
            symbolGrid.add(symbol3, 0, 4);
            symbolGrid.add(symbol4, 2, 0); // Right column
            symbolGrid.add(symbol5, 2, 2);
            symbolGrid.add(symbol6, 2, 4);
        } else if (rankValue == 7) {
            // Seven: six plus center top
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol6 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol7 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbol6.setPreserveRatio(true);
            symbol7.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Left column
            symbolGrid.add(symbol2, 0, 2);
            symbolGrid.add(symbol3, 0, 4);
            symbolGrid.add(symbol4, 2, 0); // Right column
            symbolGrid.add(symbol5, 2, 2);
            symbolGrid.add(symbol6, 2, 4);
            symbolGrid.add(symbol7, 1, 1); // Center top
        } else if (rankValue == 8) {
            // Eight: seven plus center bottom
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol6 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol7 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol8 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbol6.setPreserveRatio(true);
            symbol7.setPreserveRatio(true);
            symbol8.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Left column
            symbolGrid.add(symbol2, 0, 2);
            symbolGrid.add(symbol3, 0, 4);
            symbolGrid.add(symbol4, 2, 0); // Right column
            symbolGrid.add(symbol5, 2, 2);
            symbolGrid.add(symbol6, 2, 4);
            symbolGrid.add(symbol7, 1, 1); // Center top
            symbolGrid.add(symbol8, 1, 3); // Center bottom
        } else if (rankValue == 9) {
            // Nine: eight plus center
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol6 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol7 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol8 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol9 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbol6.setPreserveRatio(true);
            symbol7.setPreserveRatio(true);
            symbol8.setPreserveRatio(true);
            symbol9.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Left column
            symbolGrid.add(symbol2, 0, 2);
            symbolGrid.add(symbol3, 0, 4);
            symbolGrid.add(symbol4, 2, 0); // Right column
            symbolGrid.add(symbol5, 2, 2);
            symbolGrid.add(symbol6, 2, 4);
            symbolGrid.add(symbol7, 1, 1); // Center top
            symbolGrid.add(symbol8, 1, 3); // Center bottom
            symbolGrid.add(symbol9, 1, 2); // Center
        } else if (rankValue == 10) {
            // Ten: nine plus top center
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol6 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol7 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol8 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol9 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol10 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbol6.setPreserveRatio(true);
            symbol7.setPreserveRatio(true);
            symbol8.setPreserveRatio(true);
            symbol9.setPreserveRatio(true);
            symbol10.setPreserveRatio(true);
            symbolGrid.add(symbol1, 0, 0); // Left column
            symbolGrid.add(symbol2, 0, 2);
            symbolGrid.add(symbol3, 0, 4);
            symbolGrid.add(symbol4, 2, 0); // Right column
            symbolGrid.add(symbol5, 2, 2);
            symbolGrid.add(symbol6, 2, 4);
            symbolGrid.add(symbol7, 1, 1); // Center top
            symbolGrid.add(symbol8, 1, 3); // Center bottom
            symbolGrid.add(symbol9, 1, 2); // Center
            symbolGrid.add(symbol10, 1, 0); // Top center
        } else if (rankValue == 11) { // Jack
            // Jack: special pattern
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbolGrid.add(symbol1, 1, 0); // Top center
            symbolGrid.add(symbol2, 0, 2); // Left center
            symbolGrid.add(symbol3, 1, 2); // Center
            symbolGrid.add(symbol4, 2, 2); // Right center
            symbolGrid.add(symbol5, 1, 4); // Bottom center
        } else if (rankValue == 12) { // Queen
            // Queen: special pattern
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbolGrid.add(symbol1, 1, 0); // Top center
            symbolGrid.add(symbol2, 0, 2); // Left center
            symbolGrid.add(symbol3, 1, 2); // Center
            symbolGrid.add(symbol4, 2, 2); // Right center
            symbolGrid.add(symbol5, 1, 4); // Bottom center
        } else if (rankValue == 13) { // King
            // King: special pattern
            ImageView symbol1 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol2 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol3 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol4 = new ImageView(suitImages[suitIndex].getImage());
            ImageView symbol5 = new ImageView(suitImages[suitIndex].getImage());
            symbol1.setPreserveRatio(true);
            symbol2.setPreserveRatio(true);
            symbol3.setPreserveRatio(true);
            symbol4.setPreserveRatio(true);
            symbol5.setPreserveRatio(true);
            symbolGrid.add(symbol1, 1, 0); // Top center
            symbolGrid.add(symbol2, 0, 2); // Left center
            symbolGrid.add(symbol3, 1, 2); // Center
            symbolGrid.add(symbol4, 2, 2); // Right center
            symbolGrid.add(symbol5, 1, 4); // Bottom center
        }

        // Add components back to pane
        getChildren().addAll(cardBackground, symbolGrid);
    }
}
