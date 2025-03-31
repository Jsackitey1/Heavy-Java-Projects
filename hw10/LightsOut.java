import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class LightsOut extends Application {
    private int size;
    private Button[][] lights;
    private final Background ON_BACKGROUND = new Background(
            new BackgroundFill(Color.YELLOW, new CornerRadii(10), new Insets(1)));
    private final Background OFF_BACKGROUND = new Background(
            new BackgroundFill(Color.BLACK, new CornerRadii(10), new Insets(1)));
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        // Size selection stage
        Stage sizeStage = new Stage();
        sizeStage.setTitle("Lights Out Size");

        VBox sizeBox = new VBox(5);
        sizeBox.setAlignment(Pos.CENTER);

        Label prompt = new Label("Please select a size:");
        ToggleGroup group = new ToggleGroup();

        // Create radio buttons for sizes 3-9
        for (int i = 3; i <= 9; i++) {
            RadioButton rb = new RadioButton(String.valueOf(i));
            rb.setToggleGroup(group);
            if (i == 5)
                rb.setSelected(true);
            sizeBox.getChildren().add(rb);
        }

        Button createButton = new Button("Create Puzzle");
        createButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            size = Integer.parseInt(selected.getText());
            sizeStage.close();
            createPuzzleStage(primaryStage);
        });

        sizeBox.getChildren().addAll(prompt, createButton);

        Scene sizeScene = new Scene(sizeBox, 200, 300);
        sizeStage.setScene(sizeScene);
        sizeStage.show();
    }

    private void createPuzzleStage(Stage primaryStage) {
        primaryStage.setTitle("Lights Out");
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(250);
        primaryStage.setWidth(60 * size);
        primaryStage.setHeight(60 * size + 60);

        BorderPane root = new BorderPane();

        // Create grid of lights
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(85, 85, 85), null, null)));

        lights = new Button[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button light = new Button();
                light.setPrefSize(50, 50);
                light.setBackground(OFF_BACKGROUND);
                final int row = i;
                final int col = j;
                light.setOnAction(e -> press(row, col));
                lights[i][j] = light;
                grid.add(light, j, i);
            }
        }

        // Create bottom buttons
        HBox bottomBox = new HBox(20);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPrefHeight(60);

        Button randomizeButton = new Button("Randomize");
        randomizeButton.setOnAction(e -> randomize());

        Button chaseButton = new Button("Chase Lights");
        chaseButton.setOnAction(e -> chaseLights());

        bottomBox.getChildren().addAll(randomizeButton, chaseButton);

        root.setCenter(grid);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial randomization
        randomize();
    }

    private void press(int row, int col) {
        toggleLight(row, col);
        if (row > 0)
            toggleLight(row - 1, col);
        if (row < size - 1)
            toggleLight(row + 1, col);
        if (col > 0)
            toggleLight(row, col - 1);
        if (col < size - 1)
            toggleLight(row, col + 1);
    }

    private void toggleLight(int row, int col) {
        if (isLightOn(row, col)) {
            lights[row][col].setBackground(OFF_BACKGROUND);
        } else {

            lights[row][col].setBackground(ON_BACKGROUND);
        }
    }

    private boolean isLightOn(int row, int col) {
        return lights[row][col].getBackground().equals(ON_BACKGROUND);
    }

    private void randomize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextDouble() < 0.5) {
                    press(i, j);
                }
            }
        }
    }

    private void chaseLights() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (isLightOn(i, j)) {
                    press(i + 1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
