package application;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;

public class BallzUI extends Parent {
	Rectangle ballz = new Rectangle();
	
	public BallzUI() {
		ballz.getStyleClass().add("ballz");
		ballz.setWidth(40);
		ballz.setHeight(40);
		
		this.getChildren().add(ballz);
	}
}
