package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Menu extends Parent {
	Rectangle fond_menu = new Rectangle();
	ImageView iv1 = new ImageView();
	Button playButton = new Button("JOUER");
	Button profileButton = new Button("PROFILES");
	
	public Menu() {
		Image image = new Image(getClass().getResourceAsStream("background.jpg"));
        iv1.setImage(image);
				
		fond_menu.getStyleClass().add("menu");
		
        fond_menu.setWidth(410);
        fond_menu.setHeight(710);
        
        
        playButton.setTranslateX(180);
        playButton.setTranslateY(550);
        
        profileButton.setTranslateX(172);
        profileButton.setTranslateY(590);
        
        this.getChildren().add(fond_menu);
        this.getChildren().add(iv1);
        this.getChildren().add(playButton);
        this.getChildren().add(profileButton);
        
        profileButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  setProfileView();
	          }
        });
        
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  setPlayView();
	          }
        });
	}
	
	protected void setProfileView() {
		Main main = new Main();
		resetView();
		main.setProfileView();
	}
	
	protected void setPlayView() {
		Main main = new Main();
		resetView();
		main.setPlayView();
	}
	
	private void resetView() {
	    this.getChildren().remove(fond_menu);
		this.getChildren().remove(iv1);
		this.getChildren().remove(playButton);
		this.getChildren().remove(profileButton);		
	}
}
