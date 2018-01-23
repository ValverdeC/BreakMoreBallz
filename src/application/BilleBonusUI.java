package application;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BilleBonusUI extends Parent {
	Circle element = new Circle();
	
	public BilleBonusUI() {
		element.setFill(Color.GOLD);
		element.setCenterX(20);
		element.setCenterY(20);
		element.setRadius(10);
		
		this.getChildren().add(element);
	}
}
