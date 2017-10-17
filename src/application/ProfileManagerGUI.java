package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ProfileManagerGUI extends Parent {

	public ProfileManagerGUI() {
		Rectangle fond_profile_manager = new Rectangle();
		
//		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		Rectangle fond_menu = new Rectangle();
		
		fond_profile_manager.getStyleClass().add("menu");
        
		fond_profile_manager.setWidth(410);
		fond_profile_manager.setHeight(710);
        //fond_menu.setFill(Color.WHITE)
        
        this.getChildren().add(fond_profile_manager);
        
        Button btn = new Button();
        
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Add new profile");
        this.getChildren().add(btn);
	}
}
