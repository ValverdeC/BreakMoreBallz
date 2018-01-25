package application;

import java.util.LinkedList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import metier.Profil;
import metier.ProfilManager;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;

public class ProfileManagerGUI extends Parent {
	Button add_button = new Button();
	Button back_button = new Button("Retour");
	ProfilManager manager = new ProfilManager();

    Image background = new Image(getClass().getResourceAsStream("profiles_background.jpg"));
	
	TextField add_input = new TextField ();
	private TableView table = new TableView();
	private ObservableList<Profil> data;
	ImageView iv1 = new ImageView();
	final VBox vbox = new VBox();
	final ComboBox backgroundSelector = new ComboBox();
	final ComboBox launcherSelector = new ComboBox();
	
	Image backgroundPreview;
	Image launcherPreview;
	ImageView ivBackground = new ImageView();
	ImageView ivLauncher = new ImageView();
	boolean firstEdit = true;
	
	Profil selectedProfil;
	

	@SuppressWarnings({ "unchecked" })
	public ProfileManagerGUI() {
		//INIT DES ELEMENTS GRAPHIQUES
		iv1.setImage(background);
        this.getChildren().add(iv1);
        // Ajout de la feuille de style css
        this.getStylesheets().add("application/application.css");
       
        back_button.setTranslateX(5);
        back_button.setTranslateY(5);
        
        add_input.setLayoutX(70);
        add_input.setLayoutY(50);
        add_input.setPrefWidth(300);
        this.getChildren().add(add_input);
                
        add_button.setLayoutX(387);
        add_button.setLayoutY(50);
        add_button.setText("Add");
        add_button.getStyleClass().add("profil-button");
        add_button.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	new EventHandler<MouseEvent>() {
    	    	@Override
    	        public void handle(MouseEvent e) {
    	        	 String pseudo = add_input.getText();
    	        	 add_input.clear();
    	        	 data.add(manager.createProfil(pseudo)); 	  
    	        }
            }
        );
        this.getChildren().add(add_button);
        
        back_button.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	new EventHandler<MouseEvent>() {
    	    	@Override
    	        public void handle(MouseEvent e) {
    	    		setMenuView();
    	        }
        	}
        );
        back_button.getStyleClass().add("profil-button");
        this.getChildren().add(back_button);
                
        //Creation des colonnes du tableau
        TableColumn pseudo = new TableColumn("Pseudo");
        pseudo.prefWidthProperty().bind(table.widthProperty().divide(2).subtract(2));
		pseudo.setCellFactory(TextFieldTableCell.forTableColumn());
        pseudo.setCellValueFactory(
        		new PropertyValueFactory<Profil,String>("pseudo")
        );
		pseudo.setOnEditCommit(
		    new EventHandler<CellEditEvent<Profil, String>>() {
		        @Override
		        public void handle(CellEditEvent<Profil, String> t) {
		        	((Profil) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPseudo(t.getNewValue());
		        	LinkedList<Profil> profiles = new LinkedList<Profil>();
		        	data.forEach((currentProfil)->{
		        		profiles.add(currentProfil);
		        	});
		        	manager.saveAllProfiles(profiles);
		        }
		    }
		);
		
		TableColumn highScore = new TableColumn("High Score");
		highScore.prefWidthProperty().bind(table.widthProperty().divide(2).subtract(1));
		highScore.setCellValueFactory( new PropertyValueFactory<Profil, Integer>("highScore"));

	    //TableView
        //Initialisation du tableau
		table.setPrefSize(360, 350);
        data = FXCollections.observableArrayList(manager.getProfiles());
       
        table.setEditable(true);
        
        table.getColumns().addAll(pseudo, highScore);
        table.setItems(data);
             
        vbox.setSpacing(5);
        vbox.getChildren().addAll(table);
        vbox.setLayoutX(70);
        vbox.setLayoutY(100);
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        	displayForm(((Profil) newSelection));
        });
        
        launcherSelector.getItems().addAll(
        	"commander",
            "fighter",
            "interceptor",
            "leopard",
            "spaceship",
            "millenium"
        );
        launcherSelector.setLayoutX(70);
        launcherSelector.setLayoutY(650);
        launcherSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
            	launcherPreview = new Image(getClass().getResourceAsStream(t1 + ".png"));            	
            	ivLauncher.setImage(launcherPreview);

            	manager.editProfil(selectedProfil.getId(), selectedProfil.getPseudo(), t1);
            }    
        });
        
        ivLauncher.setLayoutX(270);
        ivLauncher.setLayoutY(620);
        ivLauncher.setFitWidth(100);
        ivLauncher.setFitHeight(100);    	
 
        this.getChildren().addAll(vbox);
	}
	
	public void displayForm(Profil profil) {
		//If the user select a profil for the first time, it's display the graphical settings
		if(firstEdit){
			this.getChildren().addAll(ivLauncher, launcherSelector);
			firstEdit = false;
		}
		selectedProfil = profil;
		
		launcherSelector.getSelectionModel().select(profil.getLauncher());
	    launcherPreview = new Image(getClass().getResourceAsStream(profil.getLauncher() + ".png"));
	    ivLauncher.setImage(launcherPreview);
	}
	
	protected void setMenuView() {
		Main main = new Main();
		resetView();
		main.setMenuView();
	}
	
	private void resetView() {
		this.getChildren().remove(iv1);
		this.getChildren().remove(add_button);
		this.getChildren().remove(back_button);
		this.getChildren().remove(add_input);
		this.getChildren().remove(vbox);
	}
}
