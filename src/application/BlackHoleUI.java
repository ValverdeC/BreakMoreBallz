package application;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BlackHoleUI extends Parent {
	Circle element = new Circle();
	
	public BlackHoleUI() {
		element.setFill(Color.BLACK);
		element.setCenterX(20);
		element.setCenterY(20);
		element.setRadius(10);
		
		this.getChildren().add(element);
	}
}
