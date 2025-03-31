import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Chomp extends Application {
    private static final String PATH = "http://cs.gettysburg.edu/~tneller/cs112/chomp/images/";
    private static final Image COOKIE_IMAGE = new Image(PATH + "cookie.png");
    private static final Image SKULL_IMAGE = new Image(PATH + "cookie-skull.png");
    private static final Image BLACK_IMAGE = new Image(PATH + "black.png");

    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 50;
    private boolean[][] board;
    private GridPane gridPane;
    private boolean isEndGame;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the board
        board = new boolean[GRID_SIZE][GRID_SIZE];
        isEndGame = true;

        // Create the grid pane
        gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setPadding(new Insets(1));

        // Create buttons for each cell
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = createGridButton(row, col);
                gridPane.add(button, col, row);
            }
        }

        // Create and show the scene
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Chomp");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Prevent window resizing
        primaryStage.show();
    }

    private Button createGridButton(int row, int col) {
        ImageView imageView = new ImageView(BLACK_IMAGE);
        imageView.setFitWidth(CELL_SIZE);
        imageView.setFitHeight(CELL_SIZE);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-padding: 0;");
        button.setMinSize(CELL_SIZE, CELL_SIZE);
        button.setMaxSize(CELL_SIZE, CELL_SIZE);

        button.setOnAction(e -> handleButtonClick(row, col));

        return button;
    }

    private void handleButtonClick(int row, int col) {
        if (isEndGame) {
            // Start new game
            initializeNewGame(row, col);
        } else if (board[row][col]) {
            // Make a move - only if clicking an unchomped square
            makeMove(row, col);
        }
    }

    private void initializeNewGame(int endRow, int endCol) {
        // Reset the board
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                board[r][c] = false;
            }
        }

        // Set up the initial game state
        for (int r = 0; r <= endRow; r++) {
            for (int c = 0; c <= endCol; c++) {
                board[r][c] = true;
            }
        }

        isEndGame = false;
        updateDisplay();
    }

    private void makeMove(int row, int col) {
        // Chomp the rectangle from (row, col) to the lower-right corner
        for (int r = row; r < GRID_SIZE; r++) {
            for (int c = col; c < GRID_SIZE; c++) {
                if (board[r][c]) { // Only chomp if the square is currently unchomped
                    board[r][c] = false;
                }
            }
        }

        // Check if game is over (poisoned square is chomped)
        if (!board[0][0]) {
            isEndGame = true;
        }

        updateDisplay();
    }

    private void updateDisplay() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = (Button) gridPane.getChildren().get(row * GRID_SIZE + col);
                ImageView imageView = (ImageView) button.getGraphic();

                if (board[row][col]) {
                    if (row == 0 && col == 0) {
                        imageView.setImage(SKULL_IMAGE);
                    } else {
                        imageView.setImage(COOKIE_IMAGE);
                    }
                } else {
                    imageView.setImage(BLACK_IMAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
