package application;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

	@SuppressWarnings({ "unchecked", "unchecked", "unchecked", "unchecked" })
	public ProfileManagerGUI() {
		//INIT DES ELEMENTS GRAPHIQUES
		iv1.setImage(background);
        this.getChildren().add(iv1);
        
        add_input.setLayoutX(45);
        add_input.setLayoutY(50);
        add_input.setPrefWidth(300);
        this.getChildren().add(add_input);
                
        add_button.setLayoutX(362);
        add_button.setLayoutY(50);
        add_button.setText("Add");
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
        this.getChildren().add(back_button);
                
        //Creation des colonnes du tableau
        TableColumn pseudo = new TableColumn("Pseudo");
        pseudo.prefWidthProperty().bind(table.widthProperty().subtract(2));
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

        //Initialisation du tableau
		table.setPrefSize(360, 350);
        data = FXCollections.observableArrayList(manager.getProfiles());
        table.setEditable(true);
        
        table.getColumns().addAll(pseudo);
        table.setItems(data);
        
        vbox.setSpacing(5);
        vbox.getChildren().addAll(table);
        vbox.setLayoutX(45);
        vbox.setLayoutY(100);
 
        this.getChildren().addAll(vbox);
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
