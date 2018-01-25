package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BouncingBallUI extends Parent {
	StackPane layout = new StackPane();
	Circle circle = new Circle();
	
	public BouncingBallUI() {
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.CHARTREUSE);
		circle.setCenterX(20);
		circle.setCenterY(20);
		circle.setRadius(10);

        layout.getChildren().addAll(circle);
        layout.setPadding(new Insets(5, 9, 5, 9));
		
		this.getChildren().add(layout);
	}
}
