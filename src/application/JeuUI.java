package application;

import java.io.File;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import metier.Ballz;
import metier.BilleBonus;
import metier.BilleMultiplicator;
import metier.BlackHole;
import metier.BouncingBall;
import metier.Difficulty;
import metier.Elements;
import metier.Jeu;
import metier.Profil;
import metier.StarDestroyer;
import metier.VerticalLaser;
import metier.HorizontalLaser;
import util.Coordonnees;

/** 
 * Classe permettant d'afficher le cadre de jeu d'un joueur 
 */
public class JeuUI extends Parent {
	private Rectangle background = new Rectangle();
	private GridPane grid = new GridPane();
	Jeu jeu; 
	private Double dimX = (double) 400;
	private Double dimY = (double) 400;
	LanceurFX lanceur = new LanceurFX(dimX/2,dimY-50,dimX/2,dimY-50);
	// Image du lanceur 
	File fileImg = new File("ressources/images/launcher/leopard.png");
	private Image img = new Image(fileImg.toURI().toString());
	ImageView iv1 = new ImageView();
	
	/** 
	 * Constructeur qui initialise le terrain graphique
	 * Ajout du lanceur au plateau
	 * */    
	public JeuUI(Profil profil, Difficulty difficulty) {
    	jeu = new Jeu(profil, 500, 800, this, difficulty);
    	// Image du lanceur
    	iv1.setImage(img);
    	iv1.setX(dimX/2-35);
    	iv1.setY(dimY-80);
    	// Dimensions du cadre de jeu
    	background.setWidth(400);
    	background.setHeight(400);
    	// Opacité du cadre
    	background.setStyle("-fx-fill: white; -fx-opacity: 0.1");
    	// Bordures du cadre
    	background.setStroke(Color.BLACK);
    	background.setStrokeWidth(2);
    	background.setStrokeType(StrokeType.INSIDE);
    	// Init de la grille de jeu
    	grid.setMinSize(400, 400);
    	grid.setMaxSize(400, 400);
    	
    	initJeu();
    	
    	this.getChildren().add(background);
    	this.getChildren().add(grid);
    	this.getChildren().add(lanceur);
    	this.getChildren().add(iv1);
    }
    
    /**
     * return resultat du nextTurn
     */
    public boolean nextTurn() {
    	boolean res = this.jeu.nextTurn();
    	refreshView();
    	
    	return res;
    }
    
    /** 
     * Actualisation des élements graphiques
     * */
    public void refreshView() {
    	// On supprime les éléments graphiques
    	grid.getChildren().clear();
    	// On les réaffiche à leurs nouvelles coordonnées
    	this.initJeu();
	}

    /** 
     * Génère la première  vague de ballz
     * */
	private void initJeu() {
    	for(Map.Entry<Coordonnees, Elements> element : this.jeu.getElements().entrySet()) {
    		Elements elementUI = element.getValue();
    		if (elementUI instanceof Ballz) {
    			grid.add(new BallzUI(elementUI, this.jeu), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof BilleBonus) {
    			grid.add(new BilleBonusUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof BlackHole) {
    			grid.add(new BlackHoleUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof HorizontalLaser) {
    			grid.add(new HorizontalLaserUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof BilleMultiplicator) {
    			grid.add(new BilleMultiplicatorUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof VerticalLaser) {
    			grid.add(new VerticalLaserUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof StarDestroyer) {
    			grid.add(new StarDestroyerUI(), elementUI.getX(), elementUI.getY());
    		} else if (elementUI instanceof BouncingBall) {
    			grid.add(new BouncingBallUI(), elementUI.getX(), elementUI.getY());
    		} else {
    			grid.add(new EmptyElementUI(), elementUI.getX(), elementUI.getY());
    		}
    	}
    }
	
	/** 
	 * Recharge le jeu à son état initial (tour 1)
	 * */
	public void restartJeu() {
		this.jeu = new Jeu(new Profil(1, "Toto"), 500, 800, this, this.jeu.getDifficulty());
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
	
	public int getNbOfBilles() {
		return this.jeu.getNbBilles();
	}
	
	public void incrementNbOfBilles() {
		this.jeu.setNbBilles(this.jeu.getNbBilles() + 1);
	}
	
	public void incrementNbBallzDetruit() {
		this.jeu.incrementNbBallzDetruits();
	}
	
	public void orienterLanceur() {
		iv1.setRotate(Math.toDegrees(this.lanceur.getLanceur().getAngle())-90);
	}
	
	public int getNbBallzDetruits() {
		return this.jeu.getNbBallzDetruits();
	}
	
	public void resetNbBallzDetruits() {
		this.jeu.setNbBallzDetruits(0);
	}
	
	public boolean isThereAnyBallzOnPlate() {
		return this.jeu.isThereAnyBallzOnPlate();
	}
}
