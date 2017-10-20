package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Jeu extends Parent {
    public Jeu() {
    	GridPane grid = new GridPane();
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	
    	grid.add(new Button(), 0, 0);
    	
    	this.getChildren().add(grid);
    }
}
