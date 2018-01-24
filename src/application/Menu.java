package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import metier.ProfilManager;

/** Classe permettant d'afficher le menu */
public class Menu extends Parent {
	Rectangle fond_menu = new Rectangle(); // Fond
	ImageView iv1 = new ImageView(); // ImageView permettant d'afficher l'image de fond
	Button playButton = new Button("JOUER");
	Button profileButton = new Button("PROFILES");
	Button exitButton = new Button("QUITTER");
	ProfilManager profilManager = new ProfilManager();
	
	public Menu() {
        // Ajout de l'image de fond
		Image image = new Image(getClass().getResourceAsStream("background.jpg")); // Image de fond
        iv1.setImage(image);
        
        // Ajout de la feuille de style css
        this.getStylesheets().add("application/application.css");
        
        // Bouton jouer
        playButton.setTranslateX(170);
        playButton.setTranslateY(560);
        playButton.getStyleClass().add("menu-button");
        // Bouton profils
        profileButton.setTranslateX(170);
        profileButton.setTranslateY(610);
        profileButton.getStyleClass().add("menu-button");
        
        // Bouton quitter
        exitButton.setTranslateX(170);
        exitButton.setTranslateY(660);
        exitButton.getStyleClass().add("menu-button");
        
        // Ajout des �l�ments a la vue menu
        this.getChildren().add(fond_menu);
        this.getChildren().add(iv1);
        this.getChildren().add(playButton);
        this.getChildren().add(profileButton);
        this.getChildren().add(exitButton);

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
        
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	     new EventHandler<MouseEvent>() {
    	       @Override
    	       public void handle(MouseEvent e) {
    	    	   System.exit(0);
    	       }
        });
	}
	
	/** Permet d'afficher la vue profils */
	protected void setProfileView() {
		Main main = new Main();
		resetView();
		main.setProfileView();
	}
	
	/** Permet d'afficher la vue jeu */
	protected void setPlayView() {
		Main main = new Main();
		resetView();
		main.setParamView();
	}
	/** Permet de supprimer les �l�ments de la vue menu (pour en r�afficher d'autres) */
	private void resetView() {
	    this.getChildren().remove(fond_menu);
		this.getChildren().remove(iv1);
		this.getChildren().remove(playButton);
		this.getChildren().remove(profileButton);		
	}
}
