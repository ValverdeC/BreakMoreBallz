package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class Jeu extends Parent {
    public Jeu() {
    	GridPane grid = new GridPane();
    	grid.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    	grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    	
    	grid.add(new Button(), 0, 0);
    	
    	this.getChildren().add(grid);
    }
}
