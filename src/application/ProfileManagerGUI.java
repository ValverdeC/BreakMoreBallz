package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ProfileManagerGUI extends Parent {

	public ProfileManagerGUI() {
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
	        	  setMenuView();
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
	
	protected void setMenuView() {
		Main main = new Main();
		main.setMenuView();
	}
}
