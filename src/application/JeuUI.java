package application;

import java.util.Map;

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
	private Jeu jeu = new Jeu(new Profil(1, "Toto"), 500, 800, this);
	private Double dimX = (double) 400;
	private Double dimY = (double) 400;
	LanceurFX lanceur = new LanceurFX(dimX/2,dimY-50,dimX/2,dimY);
	
    public JeuUI() {
    	background.setWidth(400);
    	background.setHeight(400);
    	background.setStyle("-fx-fill: white;");
    	background.setStroke(Color.BLACK);
    	background.setStrokeWidth(2);
    	background.setStrokeType(StrokeType.INSIDE);
    	grid.setMinSize(400, 400);
    	grid.setMaxSize(400, 400);
    	
    	initJeu();
    	
    	this.getChildren().add(background);
    	this.getChildren().add(grid);
    	this.getChildren().add(lanceur);
    }
    
    /**
     * return resultat du nextTurn
     */
    public boolean nextTurn() {
    	boolean res = this.jeu.nextTurn();
    	refreshView();
    	
    	return res;
    }
    
    public void refreshView() {
    	grid.getChildren().clear();
    	for(Map.Entry<Coordonnees, Elements> element : this.jeu.getElements().entrySet()) {
    		Elements elementUI = element.getValue();
    		System.out.println(element);
    		if (elementUI instanceof Ballz) {
    			grid.add(new BallzUI(elementUI, this.jeu), elementUI.getX(), elementUI.getY());
    		} else {
    			grid.add(new EmptyElementUI(), elementUI.getX(), elementUI.getY());
    		}
    	}
	}

	private void initJeu() {
    	System.out.println("----------------------------------");
    	
    	for(Map.Entry<Coordonnees, Elements> element : this.jeu.getElements().entrySet()) {
    		Elements elementUI = element.getValue();
    		if (elementUI instanceof Ballz) {
    			grid.add(new BallzUI(elementUI, this.jeu), elementUI.getX(), elementUI.getY());
    		} else {
    			grid.add(new EmptyElementUI(), elementUI.getX(), elementUI.getY());
    		}
    	}
    }
	
	public void restartJeu() {
		this.jeu = new Jeu(new Profil(1, "Toto"), 500, 800, this);
		this.refreshView();
		this.initJeu();
	}
	
	public GridPane getGrid(){
		return this.grid;
	}

	public Double getDimX() {
		return dimX;
	}

	public void setDimX(Double dimX) {
		this.dimX = dimX;
	}

	public Double getDimY() {
		return dimY;
	}

	public void setDimY(Double dimY) {
		this.dimY = dimY;
	}
	
	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
}
