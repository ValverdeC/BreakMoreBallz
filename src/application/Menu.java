package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu extends Parent {
	
	public Menu(Stage stage) {
		Rectangle fond_menu = new Rectangle();
				
		fond_menu.getStyleClass().add("menu");
		
        fond_menu.setWidth(410);
        fond_menu.setHeight(710);
        
        Button playButton = new Button("Jouer");
        Button profileButton = new Button("Profiles");
        
        playButton.setTranslateX(180);
        playButton.setTranslateY(330);
        
        profileButton.setTranslateX(175);
        profileButton.setTranslateY(370);
        
        this.getChildren().add(fond_menu);
        this.getChildren().add(playButton);
        this.getChildren().add(profileButton);
        
        profileButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  BorderPane root = new BorderPane();
	        	  Scene scene = new Scene(root, 400, 700);
	        	  ProfileManagerGUI profileManager = new ProfileManagerGUI(stage);
	        	  root.getChildren().add(profileManager);
	        	  
	        	  stage.setScene(scene);
	          }
        });        
	}
}
