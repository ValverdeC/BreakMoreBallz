package application;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class PlateauGUI extends Parent {
	
	Jeu jeuUn = new Jeu();
	Jeu jeuDeux = new Jeu();
	
	public PlateauGUI() {
		GridPane grid = new GridPane();
		grid.add(jeuUn, 0, 0);
		grid.add(jeuDeux, 0, 1);
		
		this.getChildren().add(grid);
	}

}
