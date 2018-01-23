package application;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import metier.Ballz;
import metier.Elements;
import metier.Jeu;

public class BallzUI extends Parent {
	Jeu jeu;
	Ballz ball;
	Rectangle rect = new Rectangle();
	Text life = new Text();
	
	StackPane pane = new StackPane();
	
	public BallzUI(Elements elementUI, Jeu jeu) {
		this.jeu = jeu;
		ball = (Ballz) elementUI;
		rect.getStyleClass().add("ballz");
		rect.setWidth(40);
		rect.setHeight(40);
		rect.setArcHeight(20);
		rect.setArcWidth(20);
		rect.setFill(Color.LIGHTCORAL);
		
		this.life.setText(Integer.toString(this.ball.getLife()));
		this.life.setX(40f);
		this.life.setY(40f);
		
		this.pane.getChildren().addAll(rect, life);
		
		this.getChildren().add(pane);
	}
}
