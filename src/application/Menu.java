package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
        Button button = new Button("Jouer");
        
        button.setTranslateX(180);
        button.setTranslateY(330);
        
        this.getChildren().add(fond_menu);
        this.getChildren().add(button);
        
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  /*BorderPane root = new BorderPane();
	        	  Scene scene = new Scene(root, 400, 700);
	        	  Menu2 menu2 = new Menu2();
	        	  root.getChildren().add(menu2);
	        	  
	        	  stage.setScene(scene);*/
	          }
        });        
	}
}
