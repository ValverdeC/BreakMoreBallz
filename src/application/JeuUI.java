package application;

import java.util.Map;
import java.util.TreeMap;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import metier.Ballz;
import metier.Elements;
import metier.Jeu;
import metier.Profil;
import util.Coordonnees;

public class JeuUI extends Parent {
	private Rectangle background = new Rectangle();
	private GridPane grid = new GridPane();
	private Jeu jeu = new Jeu(new Profil(1, "Toto"));
	
    public JeuUI() {
    	background.setWidth(400);
    	background.setHeight(400);
    	background.setStyle("-fx-fill: white;");
    	background.setStroke(Color.BLACK);
    	background.setStrokeWidth(2);
    	background.setStrokeType(StrokeType.INSIDE);
    	grid.setMinSize(400, 400);
    	grid.setMaxSize(400, 400);
    	
    	initJeu(this.jeu.getElements());
    	
    	this.getChildren().add(background);
    	this.getChildren().add(grid);
    }
    
    public void nextTurn() {
    	this.jeu.nextTurn();
    	refreshView(this.jeu.getElements());
    }
    
    private void refreshView(TreeMap<Coordonnees, Elements> elements) {
    	grid.getChildren().clear();
    	for(Map.Entry<Coordonnees, Elements> element : elements.entrySet()) {
    		Elements elementUI = element.getValue();
    		System.out.println(element);
    		if (elementUI instanceof Ballz) {
    			grid.add(new BallzUI(), elementUI.getX(), elementUI.getY());
    		} else {
    			grid.add(new EmptyElementUI(), elementUI.getX(), elementUI.getY());
    		}
    	}
	}

	private void initJeu(TreeMap<Coordonnees, Elements> elements) {
    	System.out.println("----------------------------------");
    	
    	for(Map.Entry<Coordonnees, Elements> element : elements.entrySet()) {
    		Elements elementUI = element.getValue();
    		if (elementUI instanceof Ballz) {
    			grid.add(new BallzUI(), elementUI.getX(), elementUI.getY());
    		} else {
    			grid.add(new EmptyElementUI(), elementUI.getX(), elementUI.getY());
    		}
    	}
    }
}
