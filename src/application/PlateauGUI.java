package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlateauGUI extends Parent {
	
	JeuUI jeuUn = new JeuUI();
	JeuUI jeuDeux = new JeuUI();

	Button backBtn = new Button("Retour");
	
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
		grid.add(backBtn, 1, 0);
		
		backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  nextTurn();
	          }
        });
		
		this.getChildren().add(grid);
	}

	private void nextTurn() {
		this.jeuUn.nextTurn();
		this.jeuDeux.nextTurn();
	}
}
