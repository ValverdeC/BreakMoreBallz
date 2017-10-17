package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu2 extends Parent {
	
	public Menu2() {
		Rectangle fond_menu = new Rectangle();
				
		fond_menu.getStyleClass().add("menuu");
		
        fond_menu.setWidth(410);
        fond_menu.setHeight(710);
        
        Button button = new Button("Close");
        
        this.getChildren().add(fond_menu);
        this.getChildren().add(button);
        
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  Stage stage = (Stage) button.getScene().getWindow();
	        	  stage.close();
	          }
        });
        
	}		
}
