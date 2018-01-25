package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import metier.Jeu;
import metier.Profil;

public class RightBarUI  extends Parent {

	// Conteneur
	VBox vbox = new VBox();
	// Pseudo du joueur
	Label pseudoJ;
	// Score du joueur
	Label scoreJ;
	// Nombre de billes du joueur
	Label nbBillesJ;
	
	// Entete pseudo
	Label pseudoT;
	// Entete score
	Label scoreT;
	// Entete nb de billes
	Label nbBillesT;
	// Interstice dans la vbox, label vide
	Label interstice = new Label("");
	
	Button back_button = new Button("Finir");
	
	// Arriere plan de la barre latérale
	private Rectangle background = new Rectangle();
	// Dimensions de l'arrière plan
	private Double dimX = (double) 99;
	private Double dimY = (double) 400;
	
	// Variable score pour incrément du score en temps réel
	int score = 0;
	// Variable profil pour stockage du score en fin de partie
	private Profil profilJ;
	
	private PlateauGUI plateau;
	
	public RightBarUI(Profil profil, int numeroJoueur, PlateauGUI plateau) {
		this.plateau = plateau;
        // Ajout de la feuille de style css
        this.getStylesheets().add("application/application.css");
        
        // Initialisation de l'arrière plan
        //Dimensions du cadre de jeu
    	background.setWidth(dimX);
    	background.setHeight(dimY);
    	// Style css du cadre
    	background.getStyleClass().add("rbui-background");
    	// Bordures du cadre
    	background.setStroke(Color.MEDIUMSLATEBLUE);
    	background.setStrokeWidth(2);
    	background.setStrokeType(StrokeType.INSIDE);
    	
        // Initialisation des Labels
        pseudoT = new Label("Pseudo");
		pseudoJ = new Label(profil.getPseudo());
		scoreT = new Label("Score");
		scoreJ = new Label("0");
		nbBillesT = new Label("Billes");
		nbBillesJ = new Label("1");
		
		// Initialisation du profil
		this.profilJ = profil;
		
		// Ajout du titre "Joueur" en haut de la vBox
		Text title = new Text("Joueur " + numeroJoueur);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(title);
		
		// Modification de la disposition
		vbox.setSpacing(8);
		vbox.setAlignment(Pos.CENTER);
		// Ajout des Labels au conteneur vBox
		vbox.getChildren().add(interstice);
		vbox.getChildren().add(pseudoT);
		vbox.getChildren().add(pseudoJ);
		vbox.getChildren().add(scoreT);
		vbox.getChildren().add(scoreJ);
		vbox.getChildren().add(nbBillesT);
		vbox.getChildren().add(nbBillesJ);
		vbox.getChildren().add(back_button);
		
		back_button.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	new EventHandler<MouseEvent>() {
    	    	@Override
    	        public void handle(MouseEvent e) {
    	    		quitGame();
    	        }
        	}
        );
        back_button.getStyleClass().add("profil-button");

		// Ajout du style css au conteneur vbox
		vbox.getStyleClass().add("rbui-vbox");
		
		// Ajout du style css aux Labels T
		pseudoT.getStyleClass().add("rbui-labelT");
		scoreT.getStyleClass().add("rbui-labelT");
		nbBillesT.getStyleClass().add("rbui-labelT");
		// Ajout du style css aux Labels J
		pseudoJ.getStyleClass().add("rbui-labelJ");
		scoreJ.getStyleClass().add("rbui-labelJ");
		nbBillesJ.getStyleClass().add("rbui-labelJ");

		// Ajout des éléments a la barre latérale
		this.getChildren().add(background);
		this.getChildren().add(vbox);
		
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void incrementerScore() {
		this.score += 10;
		scoreJ.setText(Integer.toString(score));
	}

	public void updateNbBilles(int newNbBilles) {
		nbBillesJ.setText(Integer.toString(newNbBilles));
	}
	
	public void resetInfos() {
		this.score = 0;
		scoreJ.setText(Integer.toString(score));
		nbBillesJ.setText("0");
	}
	
	public void quitGame() {
		Alert alert = new Alert(AlertType.INFORMATION, "Retour au menu ?", ButtonType.YES, ButtonType.NO);
		
		this.plateau.stopAnimation();
		
		alert.setOnHidden(evt -> {
			if (alert.getResult() == ButtonType.YES) {
				this.plateau.resetView();
    			this.plateau.app.setMenuView();
    		} else {
    			this.plateau.startAnimation(this.plateau.jeuCourant.getGrid(), this.plateau.jeuCourant.lanceur);
    		}
			this.plateau.updateHighScores();
		});

	    alert.show();
	}
}
