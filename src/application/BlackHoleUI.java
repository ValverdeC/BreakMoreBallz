package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BlackHoleUI extends Parent {
	StackPane layout = new StackPane();
	Circle element = new Circle();
	
	public BlackHoleUI() {
		element.setFill(Color.BLACK);
		element.setCenterX(20);
		element.setCenterY(20);
		element.setRadius(10);
        layout.getChildren().addAll(element);
        layout.setPadding(new Insets(5, 9, 5, 9));

		this.getChildren().add(layout);
	}
}
