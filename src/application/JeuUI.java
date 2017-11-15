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
	//private List<Ballz> ballz = new TestUtils().getBallzList();
	private GridPane grid = new GridPane();
	Jeu jeu = new Jeu(new Profil());
	
    public JeuUI() {
    	background.setWidth(400);
    	background.setHeight(350);
    	background.setStyle("-fx-fill: white;");
    	background.setStroke(Color.BLACK);
    	background.setStrokeWidth(2);
    	background.setStrokeType(StrokeType.INSIDE);
    	grid.setMinSize(400, 350);
    	grid.setMaxSize(400, 350);
    	
    	initJeu(this.jeu.getElements());
    	
    	this.getChildren().add(background);
    	this.getChildren().add(grid);
    }
    
    public void nextTurn() {
    	this.jeu.nextTurn();
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
