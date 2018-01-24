package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VerticalLaserUI extends Parent {
	StackPane layout = new StackPane();
	Rectangle element = new Rectangle();
	
	public VerticalLaserUI() {
		element.setFill(Color.GREEN);
		element.setWidth(5);
		element.setHeight(25);
        layout.getChildren().addAll(element);
        layout.setPadding(new Insets(0, 7.5, 0, 7.5));

		this.getChildren().add(layout);
	}
}
