package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class StarDestroyerUI extends Parent {
	StackPane layout = new StackPane();
	Arc a3 = new Arc(10, 10, 25, 25, 252.5, 40);
	
	public StarDestroyerUI() {
		a3.setType(ArcType.ROUND);
		a3.setFill(Color.BLACK);
		a3.setStrokeWidth(3);

        layout.setPadding(new Insets(5, 9, 5, 9));

		layout.getChildren().add(a3);
		this.getChildren().add(layout);
	}
}
