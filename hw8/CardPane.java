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
    private static final double ASPECT_RATIO = 635.0 / 889.0; // Standard poker card aspect ratio
    private static final String IMAGE_PATH = "./suits/"; // Path to suit images
    private static final double CORNER_RADIUS = 20.0; // Radius for rounded card corners
    private static final double CARD_MARGIN = 20.0; // Margin between card edge and content
    private static final double SYMBOL_PADDING = 10.0; // Padding between symbols in the grid

    // Static arrays for suit images and their properties
    private static final ImageView[] suitImages; // Array to hold suit images
    private static final String[] SUITS = { "club", "heart", "spade", "diamond" }; // Suit names
    private static final String[] COLORS = { "green", "pink", "black", "blue" }; // Suit colors

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
        cardBackground.setFill(Color.WHITE); // White background
        cardBackground.setStroke(Color.BLACK); // Black border
        cardBackground.setStrokeWidth(2); // Border thickness
        cardBackground.setArcWidth(CORNER_RADIUS); // Rounded corners
        cardBackground.setArcHeight(CORNER_RADIUS);

        // Initialize symbol grid with proper padding and alignment
        symbolGrid = new GridPane();
        symbolGrid.setAlignment(Pos.CENTER); // Center the grid
        symbolGrid.setHgap(SYMBOL_PADDING); // Horizontal gap between symbols
        symbolGrid.setVgap(SYMBOL_PADDING); // Vertical gap between symbols
        symbolGrid.setPadding(new Insets(CARD_MARGIN)); // Padding around the grid

        // Add components to the pane
        getChildren().addAll(cardBackground, symbolGrid);

        // Add listeners for resizing and card changes
        widthProperty().addListener((obs, oldVal, newVal) -> updateSize());
        heightProperty().addListener((obs, oldVal, newVal) -> updateSize());
        cardStr.addListener((obs, oldVal, newVal) -> changeCard());

        // Initial card update to display the card
        changeCard();
    }

    // Method to update the size of the card and its components
    private void updateSize() {
        double width = getWidth();
        double height = getHeight();

        // Maintain aspect ratio of the card
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
        double availableWidth = width - (2 * CARD_MARGIN); // Space for symbols
        double availableHeight = height - (2 * CARD_MARGIN);

        // Calculate symbol size based on available space
        double symbolSize = Math.min(availableWidth, availableHeight) * 0.15;
        for (ImageView symbol : suitImages) {
            symbol.setFitWidth(symbolSize);
            symbol.setFitHeight(symbolSize);
        }

        // Center the symbol grid within the card
        symbolGrid.setLayoutX(cardBackground.getX() + CARD_MARGIN);
        symbolGrid.setLayoutY(cardBackground.getY() + CARD_MARGIN);
    }

    // Method to update the card display based on the card string
    private void changeCard() {
        String card = cardStr.get().toUpperCase(); // Get card string in uppercase
        if (card.length() != 2) // Ensure card string is valid
            return;

        char rank = card.charAt(0); // Extract rank (e.g., 'A', '2', 'K')
        char suit = card.charAt(1); // Extract suit (e.g., 'C', 'H', 'S', 'D')

        // Clear all existing children to rebuild the card
        getChildren().clear();

        // Rebuild card background
        cardBackground = new Rectangle();
        cardBackground.setFill(Color.WHITE);
        cardBackground.setStroke(Color.BLACK);
        cardBackground.setStrokeWidth(2);
        cardBackground.setArcWidth(CORNER_RADIUS);
        cardBackground.setArcHeight(CORNER_RADIUS);

        // Rebuild symbol grid
        symbolGrid = new GridPane();
        symbolGrid.setAlignment(Pos.CENTER);
        symbolGrid.setHgap(SYMBOL_PADDING);
        symbolGrid.setVgap(SYMBOL_PADDING);
        symbolGrid.setPadding(new Insets(CARD_MARGIN));

        // Determine rank value (e.g., Ace = 1, King = 13)
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
            return; // Invalid rank

        // Determine suit index (e.g., 'C' = 0, 'H' = 1)
        int suitIndex;
        switch (suit) {
            case 'C':
                suitIndex = 0; // Club
                break;
            case 'H':
                suitIndex = 1; // Heart
                break;
            case 'S':
                suitIndex = 2; // Spade
                break;
            case 'D':
                suitIndex = 3; // Diamond
                break;
            default:
                return; // Invalid suit
        }

        // Calculate grid dimensions based on rank value
        int gridSize = (int) Math.ceil(Math.sqrt(rankValue));

        // Add symbols to the grid
        for (int i = 0; i < rankValue; i++) {
            int row = i / gridSize; // Row index
            int col = i % gridSize; // Column index
            ImageView symbol = new ImageView(suitImages[suitIndex].getImage());
            symbol.setPreserveRatio(true); // Maintain aspect ratio
            symbolGrid.add(symbol, col, row); // Add symbol to grid
        }

        // Add components back to the pane
        getChildren().addAll(cardBackground, symbolGrid);
    }
}
