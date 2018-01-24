package application;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import metier.Difficultes;
import metier.ParametresPlateau;
import metier.Profil;
import metier.ProfilManager;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Slider;

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
		this.getStylesheets().add("application/application.css");
		Image image = new Image(getClass().getResourceAsStream("parametres_background.jpg"));
	    iv1.setImage(image);

		fond_menu.getStyleClass().add("menu");

	    fond_menu.setWidth(450);
	    fond_menu.setHeight(800);
	    
        this.getChildren().add(fond_menu);
        this.getChildren().add(iv1);
        
		player1Selector.getItems().addAll(profilManager.getListPseudo());
		player1Selector.valueProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue ov, String t, String t1) {
				manageProfilSelector(t1, 1);
			}    
		});
		
        player1Selector.setLayoutX(45);
        player1Selector.setLayoutY(200);
        
        player2Selector.getItems().addAll(profilManager.getListPseudo());
        player2Selector.valueProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue ov, String t, String t1) {
				manageProfilSelector(t1, 2);
			}
		});
        player2Selector.setLayoutX(45);
        player2Selector.setLayoutY(240);

        this.getChildren().add(player1Selector);
        this.getChildren().add(player2Selector);
        
        facile.setTranslateX(100);
        facile.setTranslateY(500);
        
        normal.setTranslateX(200);
        normal.setTranslateY(500);
        
        difficile.setTranslateX(300);
        difficile.setTranslateY(500);
        
        facile.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Facile);    	        	
    	        }
        });
        
        normal.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Normal);    	        	
    	        }
        });
        
        difficile.addEventHandler(MouseEvent.MOUSE_CLICKED,
    	        new EventHandler<MouseEvent>() {
    	        @Override
    	        public void handle(MouseEvent e) {
    	        	manageDifficulty(Difficultes.Difficile);    	        	
    	        }
        });
        
        facile.getStyleClass().add("profil-button");
		normal.getStyleClass().add("selected-profil-button");
		difficile.getStyleClass().add("profil-button");
		        
        this.getChildren().addAll(facile, normal, difficile);

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
		Main main = new Main();
		resetView();
		main.setPlayView(main, parametrePlateau.getTempProfil1(), parametrePlateau.getTempProfil2());
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