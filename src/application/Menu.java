package application;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Menu extends Parent {
	
	public Menu() {
		Rectangle fond_menu = new Rectangle();
		
		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		fond_menu.setId("menu");
		
        fond_menu.setWidth(600);
        fond_menu.setHeight(300);
        fond_menu.setArcWidth(20);
        fond_menu.setArcHeight(20);
        //fond_menu.setFill(Color.WHITE);
        
        this.setTranslateX(100);
        this.setTranslateY(150);
        this.getChildren().add(fond_menu);
	}
		
}
