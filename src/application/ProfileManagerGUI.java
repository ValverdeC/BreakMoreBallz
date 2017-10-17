package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ProfileManagerGUI extends Parent {

	public ProfileManagerGUI(Stage stage) {
		Rectangle fond_profile_manager = new Rectangle();
		
		fond_profile_manager.getStyleClass().add("menu");
        
		fond_profile_manager.setWidth(410);
		fond_profile_manager.setHeight(710);
        
        this.getChildren().add(fond_profile_manager);
        
        Button btn = new Button();
        Button backBtn = new Button("Retour");
        
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Add new profile");
        this.getChildren().add(btn);
        this.getChildren().add(backBtn);
        
        backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  BorderPane root = new BorderPane();
	        	  Scene scene = new Scene(root, 400, 700);
	        	  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        	  Menu menu = new Menu(stage);
	        	  root.getChildren().add(menu);
	        	  
	        	  stage.setScene(scene);
	          }
        });
        
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	for (int i = 0; i < 10; i++) {
					btn.setLayoutX(btn.getLayoutX() + 1);
				}
	          }
        });
	}
}
