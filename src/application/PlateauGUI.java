package application;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class PlateauGUI extends Parent {
	
	JeuUI jeuUn = new JeuUI();
	JeuUI jeuDeux = new JeuUI();
	
	public PlateauGUI() {
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.getStyleClass().add("plateau");
		GridPane grid = new GridPane();
		grid.getStyleClass().add("jeu");
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.add(jeuUn, 0, 0);
		grid.add(jeuDeux, 0, 1);
		
		this.getChildren().add(grid);
	}

}
