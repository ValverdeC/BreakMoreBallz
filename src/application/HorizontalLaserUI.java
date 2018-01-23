package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HorizontalLaserUI extends Parent {
	StackPane layout = new StackPane();
	Rectangle element = new Rectangle();
	
	public HorizontalLaserUI() {
		element.setFill(Color.GREEN);
		element.setWidth(25);
		element.setHeight(5);
        layout.getChildren().addAll(element);
        layout.setPadding(new Insets(0, 7.5, 0, 7.5));

		this.getChildren().add(layout);
	}
}
