package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlateauGUI extends Parent {
	
	JeuUI jeuUn = new JeuUI();
	JeuUI jeuDeux = new JeuUI();

	Button jeu1Btn = new Button("Jeu 1");
	Button jeu2Btn = new Button("Jeu 2");
	
	Main app;
	
	public PlateauGUI(Main main) {
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.getStyleClass().add("plateau");
		GridPane grid = new GridPane();
		grid.getStyleClass().add("jeu");
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.add(jeuUn, 1, 0);
		grid.add(jeuDeux, 1, 1);
		grid.add(jeu1Btn, 0, 0);
		grid.add(jeu2Btn, 0, 1);
		
		this.app = main;
		
		jeu1Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  nextTurn1();
	          }
        });
		
		jeu2Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  nextTurn2();
	          }
        });
		
		this.getChildren().add(grid);
	}

	private void nextTurn1() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuUn.nextTurn();
		
		if (res) {
    		alert.showAndWait();

    		if (alert.getResult() == ButtonType.YES) {
    		   this.jeuUn.restartJeu();
    		   this.jeuDeux.restartJeu();
    		} else {
    			this.app.setMenuView();
    		}
    	}
	}
	
	private void nextTurn2() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuDeux.nextTurn();
		
		if (res) {
    		alert.showAndWait();

    		if (alert.getResult() == ButtonType.YES) {
    		   this.jeuUn.restartJeu();
    		   this.jeuDeux.restartJeu();
    		} else {
    			this.app.setMenuView();
    		}
    	}
	}
}
