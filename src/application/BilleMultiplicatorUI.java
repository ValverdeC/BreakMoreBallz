package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BilleMultiplicatorUI extends Parent {
	StackPane layout = new StackPane();
	Circle element = new Circle();
	Circle element2 = new Circle();
	
	public BilleMultiplicatorUI() {        
        element.setFill(Color.BLUEVIOLET);
		element.setCenterX(10);
		element.setCenterY(10);
		element.setRadius(5);
		element2.setFill(Color.BLUEVIOLET);
		element2.setCenterX(10);
		element2.setCenterY(10);
		element2.setRadius(5);
		element2.setTranslateX(10);
        layout.getChildren().addAll(element, element2);
        layout.setPadding(new Insets(0, 10, 0, 10));
		
		this.getChildren().add(layout);
	}
}
