package application;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** 
 * Classe permettant de modeliser les éléments (cases) vides sur le plateau
 *  */
public class EmptyElementUI extends Parent {
	Rectangle element = new Rectangle();
	
	public EmptyElementUI() {
		element.setFill(Color.TRANSPARENT);
		element.setWidth(40);
		element.setHeight(40);
		
		this.getChildren().add(element);
	}
}
