package application;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import metier.Bille;

public class PlateauGUI extends Parent {
	
	JeuUI jeuUn = new JeuUI();
	JeuUI jeuDeux = new JeuUI();
	JeuUI jeuCourant = new JeuUI();
	AnimationTimer timer;
	
	Button jeu1Btn = new Button("Jeu 1");
	Button jeu2Btn = new Button("Jeu 2");
	GridPane grid = new GridPane();
	private static final int NBBILLES = 1;
	private EventHandler<MouseEvent> handlerTir = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	tir();
    		event.consume();
        }
    };
	private EventHandler<MouseEvent> handlerVisee = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
        	jeuCourant.lanceur.orienterLanceur(mouseEvent.getX(),mouseEvent.getY());
        	
        }
    };
	
	Button backBtn = new Button("");
	
	Main app;
	
	public PlateauGUI(Main main) {
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.getStyleClass().add("plateau");
		jeuUn.lanceur.getLanceur().setNbJoueur(1);
		jeuDeux.lanceur.getLanceur().setNbJoueur(2);
		grid.getStyleClass().add("jeu");
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.add(jeuUn, 0, 0);
		grid.add(jeuDeux, 0, 1);
		grid.add(jeu1Btn, 1, 0);
		grid.add(jeu2Btn, 1, 1);
		changerJeuCourant();
		this.app = main;
		
		jeu1Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  nextTurn1();
	          }
        });
		
		jeu2Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	        new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  nextTurn2();
	          }
        });
				
		this.getChildren().add(grid);
	}

	private void nextTurn1() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuUn.nextTurn();
		
		if (res) {
    		alert.showAndWait();

    		if (alert.getResult() == ButtonType.YES) {
    		   this.jeuUn.restartJeu();
    		   this.jeuDeux.restartJeu();
    		} else {
    			resetView();
    			this.app.setMenuView();
    		}
    	}
	}
	
	private void nextTurn2() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuDeux.nextTurn();
		
		if (res) {
    		alert.showAndWait();

    		if (alert.getResult() == ButtonType.YES) {
    		   this.jeuUn.restartJeu();
    		   this.jeuDeux.restartJeu();
    		} else {
    			resetView();
    			this.app.setMenuView();
    		}
    	}
	}
	
	private void resetView() {
		this.getChildren().remove(this.grid);
	}
	
	private void tir() {
		for(Bille bille : jeuCourant.lanceur.getLanceur().getBilles()) {
			this.getChildren().remove(bille.getVue());
		}
		jeuCourant.lanceur.getLanceur().getBilles().clear();
		jeuCourant.lanceur.createBilles(NBBILLES);
		for(Bille bille : jeuCourant.lanceur.getLanceur().getBilles()) {
			this.getChildren().add(bille.getVue());
    		bille.setLance(true);
		}
	}
	
	//Gestion des animations (collision + déplacement)
    private void startAnimation(final GridPane field, LanceurFX lanceur) {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                	checkCollision(jeuCourant.getDimX()+20,jeuCourant.getDimY()*lanceur.getLanceur().getNbJoueur());
                    long elapsedTime = timestamp - lastUpdateTime.get();
                    updateWorld(elapsedTime, lanceur);
                }
                lastUpdateTime.set(timestamp);
            }

        };
        timer.start();
    }

	//Gestion des animations (collision + déplacement)
    private void stopAnimation() {
        timer.stop();
    }
    
	//Mise à jour des éléments graphiques (déplacement des billes)
    private void updateWorld(long elapsedTime, LanceurFX lanceur) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        for (Bille b : lanceur.getLanceur().getBilles()) {
        	if(b.isLance()) {
        		b.setX(b.getX() + elapsedSeconds * b.getVitesseX());
            	b.setY(b.getY() + elapsedSeconds * b.getVitesseY());
        	}
        }	
    }
    
    //Gestion des collisions
    private void checkCollision(double maxX, double maxY) {
    	for (Bille b : jeuCourant.lanceur.getLanceur().getBilles()) {
    		double topY=0;
    		if(jeuCourant.lanceur.getLanceur().getNbJoueur() == 2) {
    			topY = 400;
    		}else {
    			topY = 0;
    		}
    		double xVel = b.getVitesseX();
            double yVel = b.getVitesseY();
            if ((b.getX() - b.getRayon() <= 0 && xVel < 0)
                    || (b.getX() + b.getRayon() >= maxX && xVel > 0)) {
                b.setVitesseX(-xVel);
            }
            if (b.getY() - b.getRayon() <= topY && yVel < 0) {
                b.setVitesseY(-yVel);
            }
            if (b.getY() + b.getRayon() >= maxY*jeuCourant.lanceur.getLanceur().getNbJoueur() && yVel > 0) {
            	this.getChildren().remove(b.getVue());
            	stopAnimation();
            	changerJeuCourant();
            	b.setLance(false);
            	
            }
            
    	}
    }

    // On change de jeuCourant et on actualise les écouteurs d'action
	private void changerJeuCourant() {
        
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_CLICKED, handlerTir);
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_MOVED, handlerVisee);
		
		if(jeuCourant==jeuUn) {
			jeuCourant=jeuDeux;
		}else {
			jeuCourant=jeuUn;
		}
		
		jeuCourant.addEventHandler(MouseEvent.MOUSE_CLICKED,handlerTir);
		jeuCourant.addEventHandler(MouseEvent.MOUSE_MOVED,handlerVisee);
		
		startAnimation(jeuCourant.getGrid(), jeuCourant.lanceur);
	}
    
    
}
