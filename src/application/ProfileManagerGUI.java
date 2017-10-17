package application;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ProfileManagerGUI extends Parent {

	public ProfileManagerGUI() {
		Rectangle fond_profile_manager = new Rectangle();
		
//		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		fond_profile_manager.setId("profileManagerGUI");
		
		fond_profile_manager.setWidth(600);
		fond_profile_manager.setHeight(300);
		fond_profile_manager.setArcWidth(20);
        fond_profile_manager.setArcHeight(20);
        //fond_menu.setFill(Color.WHITE)
        
        this.setTranslateX(100);
        this.setTranslateY(150);
        this.getChildren().add(fond_profile_manager);
        
        
        Button btn = new Button();
        
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        this.getChildren().add(btn);
	}
}
