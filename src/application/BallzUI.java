package application;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
		LinearGradient lg1;
		
		// Apparence de la ballz
		if (ball.isMalus()) {
			Stop[] stops = new Stop[] { new Stop(0, Color.LIGHTSTEELBLUE), new Stop(1, Color.DARKBLUE)};
			lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
		} else {
			Stop[] stops = new Stop[] { new Stop(0, Color.LIGHTPINK), new Stop(1, Color.BLUEVIOLET)};
			lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
		}
		ballz.setWidth(38);
		ballz.setHeight(38);
		ballz.setArcHeight(20);
		ballz.setArcWidth(20);
		ballz.setFill(lg1);
		
		this.life.setFill(Color.WHITE);
		this.life.setText(Integer.toString(this.ball.getLife()));
		this.life.setX(40f);
		this.life.setY(40f);
		this.pane.getChildren().addAll(ballz,life);
		
		this.getChildren().add(pane);
	}
}
