import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFxFun extends Application {
	DoubleProperty paneAngle=new SimpleDoubleProperty(0);
	StringProperty paneAngleStr=new SimpleStringProperty("Angle 0");
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button button =new Button("New Stage");
		Pane pane=new Pane();
		pane.getChildren().add(button);
		Scene scene=new Scene(pane,200,200);
		primaryStage.setTitle("Primary Stage title");
		primaryStage.setScene(scene);		
//		circle.centerXProperty().bind(pane.widthProperty().divide(2));

		primaryStage.show();
		
		//Add an action to 
		button.setOnAction(e->{
			Button button2 =new Button("New Stage");
			Pane pane2=new Pane();
			pane.getChildren().add(button2);
			Scene scene2=new Scene(pane2,200,200);
			Stage stage2=new Stage();
			stage2.setTitle("Primary Stage title");
			stage2.setScene(scene2);
			stage2.show();
			stage2.setResizable(false);
		});
		
		Circle circle=new Circle();
		circle.setCenterX(100);
		circle.setCenterY(100);
		circle.setRadius(50);
		circle.setStroke(Color.BLUE);
		circle.setFill(Color.ORANGE);
		pane.getChildren().add(0,circle);
		
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.widthProperty().divide(2));
		
		Pane subPane=new StackPane();
		subPane.setPrefSize(100,100);
		subPane.setTranslateX(100);
		subPane.setTranslateY(100);
		subPane.rotateProperty().bind(paneAngle);
		pane.getChildren().add(subPane);
		
		
		Rectangle square= new Rectangle(100,100);
		square.setStyle("-fx-stroke: black; -fx-filled:red;");
		subPane.getChildren().add(square);
		
		Label label=new Label();
		label.textProperty().bind(paneAngleStr);
		subPane.getChildren().add(label);
		
		
		Button angleBtn=new Button("Increase Angle");
		pane.getChildren().add(angleBtn);
		angleBtn.setTranslateY(35);
		angleBtn.setOnAction(e->{
			paneAngle.set(paneAngle.get()+5);
			paneAngleStr.set("Angle "+ (int)paneAngle.get());
			
			square.setFill(Color.color(Math.random(),Math.random(),Math.random()));
			square.setFill(Color.hsb(paneAngle.get(), 1, 1));
		});
		
		label.setFont(Font.font("serif",FontWeight.BOLD,FontPosture.ITALIC,16));
		
		

	}
	
	public static void main(String [] args) {
		launch(args);
		
	}

}
