package application;

import java.util.TreeMap;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import metier.Ballz;
import metier.Elements;
import metier.EmptyElement;
import metier.Jeu;
import util.Coordonnees;

public class BallzUI extends Parent {
	Jeu jeu;
	Ballz ball;
	Rectangle ballz = new Rectangle();
	
	public BallzUI(Elements elementUI, Jeu jeu) {
		this.jeu = jeu;
		ball = (Ballz) elementUI;
		ballz.getStyleClass().add("ballz");
		ballz.setWidth(38);
		ballz.setHeight(40);
		ballz.setArcHeight(20);
		ballz.setArcWidth(20);
		ballz.setFill(Color.LIGHTCORAL);
		
		ballz.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  onClick();
	          }
        });
		
		this.getChildren().add(ballz);
	}
	
	private void onClick() {
		TreeMap<Coordonnees, Elements> tmp = new TreeMap<>();
		tmp.putAll(this.jeu.getElements());
		tmp.put(this.ball.getCoordonnees(), new EmptyElement(this.ball.getCoordonnees()));
		this.jeu.setElements(tmp);
		this.jeu.refreshView();
	}
}
