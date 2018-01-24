package application;

import java.util.LinkedHashMap;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import metier.ParametresPlateau;
import metier.Profil;
import metier.ProfilManager;
import util.Difficultes;

public class ParametresPlateauUi extends Parent {
	ParametresPlateau parametrePlateau = new ParametresPlateau();
	ProfilManager profilManager = new ProfilManager();
	
	private LinkedHashMap<String, Profil> mapProfil = profilManager.getMapProfils();

	final ComboBox<String> player1Selector = new ComboBox<String> ();
	final ComboBox<String> player2Selector = new ComboBox<String> ();
	Button facile = new Button("FACILE");
	Button normal = new Button("NORMAL");
	Button difficile = new Button("DIFFICILE");
	Button playButton = new Button("JOUER");
	Rectangle fond_menu = new Rectangle();
	ImageView iv1 = new ImageView();

	public ParametresPlateauUi() {
		//Initialize style sheet
		this.getStylesheets().add("application/application.css");

		//Setup background image
		Image image = new Image(getClass().getResourceAsStream("parametres_background.jpg"));
	    iv1.setImage(image);
        this.getChildren().addAll(iv1, fond_menu);

        
	    //Setup selector for player1
		player1Selector.getItems().addAll(profilManager.getListPseudo());
		player1Selector.valueProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue ov, String t, String t1) {
				manageProfilSelector(t1, 1);
			}    
		});
        player1Selector.setLayoutX(45);
        player1Selector.setLayoutY(200);
        
	    //Setup selector for player2
        player2Selector.getItems().addAll(profilManager.getListPseudo());
        player2Selector.valueProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue ov, String t, String t1) {
				manageProfilSelector(t1, 2);
			}
		});
        player2Selector.setLayoutX(45);
        player2Selector.setLayoutY(240);
        
        this.getChildren().addAll(player1Selector, player2Selector);        
        
        //Setup all difficulty buton
        facile.setTranslateX(100);
        facile.setTranslateY(500);
        facile.getStyleClass().add("profil-button");
        facile.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Facile);    	        	
    	        }
        });
        
        normal.setTranslateX(200);
        normal.setTranslateY(500);
		normal.getStyleClass().add("selected-profil-button");
        normal.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Normal);    	        	
    	        }
        });
        
        difficile.setTranslateX(300);
        difficile.setTranslateY(500);
		difficile.getStyleClass().add("profil-button");
        difficile.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Difficile);    	        	
    	        }
        });
        
        this.getChildren().addAll(facile, normal, difficile);

        
        //Setup play button
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
        		new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	setPlayView();
    	        }
        });
        this.getChildren().add(playButton);       
	}
	
	protected void setPlayView() {
		if(parametrePlateau.checkParametres()){
			Main main = new Main();
			resetView();
			main.setPlayView(main, parametrePlateau.getTempProfil1(), parametrePlateau.getTempProfil2());
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error: no profils selected");
			alert.showAndWait();
		}
	}
	
	private void resetView() {
	    this.getChildren().remove(fond_menu);
		this.getChildren().remove(iv1);
		this.getChildren().remove(playButton);	
	}
	
	public void manageDifficulty(Difficultes currentDiff) {
		parametrePlateau.setDifficulte(currentDiff);
		
		switch (currentDiff) {
		case Facile:
			facile.getStyleClass().removeAll("profil-button");
			facile.getStyleClass().add("selected-profil-button");
			
			normal.getStyleClass().add("profil-button");
			normal.getStyleClass().removeAll("selected-profil-button");
			difficile.getStyleClass().add("profil-button");
			difficile.getStyleClass().removeAll("selected-profil-button");
			break;
			
		case Normal:
			normal.getStyleClass().removeAll("profil-button");
			normal.getStyleClass().add("selected-profil-button");
			
			facile.getStyleClass().add("profil-button");
			facile.getStyleClass().removeAll("selected-profil-button");
			difficile.getStyleClass().add("profil-button");
			difficile.getStyleClass().removeAll("selected-profil-button");
			break;
		
		case Difficile:
			difficile.getStyleClass().removeAll("profil-button");
			difficile.getStyleClass().add("selected-profil-button");
			
			normal.getStyleClass().add("profil-button");
			normal.getStyleClass().removeAll("selected-profil-button");
			facile.getStyleClass().add("profil-button");
			facile.getStyleClass().removeAll("selected-profil-button");
			break;

		default:
			System.out.println("Normal");
			break;
		}
	}
	
	public void manageProfilSelector(String pseudo, int playerNumber){
		if(playerNumber == 1){
			if(parametrePlateau.getTempProfil1() != null){
				player2Selector.getItems().add(parametrePlateau.getTempProfil1().getPseudo());
				player2Selector.getItems().remove(pseudo);
				parametrePlateau.setTempProfil1(mapProfil.get(pseudo));
			}
			else{
				player2Selector.getItems().remove(pseudo);
				parametrePlateau.setTempProfil1(mapProfil.get(pseudo));
			}
		}
		if(playerNumber == 2){
			if(parametrePlateau.getTempProfil2() != null){
				player1Selector.getItems().add(parametrePlateau.getTempProfil2().getPseudo());
				player1Selector.getItems().remove(pseudo);
				parametrePlateau.setTempProfil2(mapProfil.get(pseudo));
			}
			else{
				player1Selector.getItems().remove(pseudo);
				parametrePlateau.setTempProfil2(mapProfil.get(pseudo));
			}
		}
	}
}