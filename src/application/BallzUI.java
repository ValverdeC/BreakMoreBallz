package application;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import metier.Ballz;
import metier.Elements;
import metier.Jeu;
/** 
 * Classe permettant de modeliser les Ballz sur le plateau
 *  */
public class BallzUI extends Parent {
	Jeu jeu;
	Ballz ball; // Element metier
	Rectangle ballz = new Rectangle(); // Element graphique
	Text life = new Text();
	
	StackPane pane = new StackPane();
	
	public BallzUI(Elements elementUI, Jeu jeu) {
		this.jeu = jeu;
		ball = (Ballz) elementUI;
		ballz.getStyleClass().add("ballz");
		ballz.setWidth(38);
		ballz.setHeight(38);
		ballz.setArcHeight(20);
		ballz.setArcWidth(20);
		ballz.setFill(Color.LIGHTCORAL);
		
		this.life.setText(Integer.toString(this.ball.getLife()));
		this.life.setX(40f);
		this.life.setY(40f);
		this.pane.getChildren().addAll(ballz, life);
		
		this.getChildren().add(pane);
	}
}
