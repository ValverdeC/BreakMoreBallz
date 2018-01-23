package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.Timer;

import java.util.List;
import java.util.Map.Entry;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Ballz;
import metier.Bille;
import metier.BilleBonus;
import metier.BilleMultiplicator;
import metier.BlackHole;
import metier.Elements;
import metier.EmptyElement;
import metier.HorizontalLaser;
import util.Coordonnees;

public class PlateauGUI extends Parent {
	
	JeuUI jeuUn = new JeuUI();
	JeuUI jeuDeux = new JeuUI();
	JeuUI jeuCourant = new JeuUI();
	AnimationTimer timer;
	ImageView iv1 = new ImageView();
	
	GridPane grid = new GridPane();
	
	boolean tirInProgress = false;
	Timer tirTimer = new Timer(200, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	displayBille();
			    }
			});
		}
	});
	
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
        	if (!tirInProgress) {
        		jeuCourant.lanceur.orienterLanceur(mouseEvent.getX(),mouseEvent.getY());
        	}
        }
    };
		
	Main app;
	
	public PlateauGUI(Main main) {
		Image image = new Image(getClass().getResourceAsStream("game_background.jpg"));
		iv1.setImage(image);
		this.getChildren().add(iv1);
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
		changerJeuCourant();
		this.app = main;
				
		this.getChildren().add(grid);
	}
	
	public void displayBille() {
		List<Bille> billes = this.jeuCourant.lanceur.getLanceur().getBilles();
		
		if (billes.size() > 0) {
			System.out.println(billes.size());
			for(Bille bille : billes) {
				if (!bille.isLance() && !bille.isAlreadyLance()) {
					this.getChildren().add(bille.getVue());
		    		bille.setLance(true);
		    		bille.setAlreadyLance(true);
		    		break;
				}
			}
		}
				
		if (!isBilleAlive()) {
			this.tirInProgress = false;
			this.tirTimer.stop();
		}
	}
	
	private void nextTurn() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuCourant.nextTurn();
		
		if (res) {
			this.stopAnimation();
			
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
		if (!this.tirInProgress) {
			this.tirInProgress = true;
			for(Bille bille : jeuCourant.lanceur.getLanceur().getBilles()) {
				this.getChildren().remove(bille.getVue());
			}
			
			jeuCourant.lanceur.getLanceur().getBilles().clear();
			jeuCourant.lanceur.createBilles(this.getNbOfBilles());
			
			this.tirTimer.start();
			this.displayBille();
		}
	}
	
	//Gestion des animations (collision + déplacement)
    private void startAnimation(final GridPane field, LanceurFX lanceur) {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                	checkCollision(jeuCourant.getDimX(),jeuCourant.getDimY()*lanceur.getLanceur().getNbJoueur());
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
        this.timer.stop();
        this.tirTimer.stop();
    }
    
	//Mise à jour des éléments graphiques (déplacement des billes)
    private void updateWorld(long elapsedTime, LanceurFX lanceur) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        for (Bille b : this.jeuCourant.lanceur.getLanceur().getBilles()) {
        	if(b.isLance()) {
        		b.setX(b.getX() + elapsedSeconds * b.getVitesseX());
            	b.setY(b.getY() + elapsedSeconds * b.getVitesseY());
        	}
        }
    }
    
    //Gestion des collisions
    private void checkCollision(double maxX, double maxY) {
    	// Collison avec les ballz
    	double ballzX;
    	double ballzY;
    	double distance;

    	for (Bille b : jeuCourant.lanceur.getLanceur().getBilles()) {
    		if (b.isLance()) {
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
	            for (Entry<Coordonnees, Elements> element : jeuCourant.getJeu().getElements().entrySet()) {
	            	if(element.getValue() instanceof Ballz) {
		            	ballzX = element.getKey().getX()*40+20;
		            	ballzY = element.getKey().getY()*40+20+(400*(jeuCourant.lanceur.getLanceur().getNbJoueur()-1));
		            	distance = Math.sqrt(Math.pow(b.getX()-ballzX, 2) + Math.pow(b.getY()-ballzY, 2));
		            	if(distance <= 35){
		            		if(b.getX() >= ballzX + 20 || b.getX() <= ballzX - 20) {
		            			b.setVitesseX(-xVel);
		            		}
		            		if(b.getY() >= ballzY + 20 || b.getY() <= ballzY - 20) {
		            			b.setVitesseY(-yVel);
		            		}
		            		
		            		this.handleCollision(element.getValue(), b);
		            	}
	            	} else if(
	            			element.getValue() instanceof BilleBonus ||
	            			element.getValue() instanceof BlackHole ||
	            			element.getValue() instanceof HorizontalLaser
	            	) {
		            	ballzX = element.getKey().getX()*40+20;
		            	ballzY = element.getKey().getY()*40+20+(400*(jeuCourant.lanceur.getLanceur().getNbJoueur()-1));
		            	distance = Math.sqrt(Math.pow(b.getX()-ballzX, 2) + Math.pow(b.getY()-ballzY, 2));
		            	
		            	if(distance <= 18) {		            		
		            		this.handleCollision(element.getValue(), b);
		            	}
	            	} else if(element.getValue() instanceof BilleMultiplicator) {
		            	ballzX = element.getKey().getX()*40+20;
		            	ballzY = element.getKey().getY()*40+20+(400*(jeuCourant.lanceur.getLanceur().getNbJoueur()-1));
		            	distance = Math.sqrt(Math.pow(b.getX()-ballzX, 2) + Math.pow(b.getY()-ballzY, 2));
		            	
		            	if(distance <= 18) {
		            		if(b.getX() >= ballzX + 20 || b.getX() <= ballzX - 20) {
		            			b.setVitesseX(-xVel);
		            		}
		            		if(b.getY() >= ballzY + 20 || b.getY() <= ballzY - 20) {
		            			b.setVitesseY(-yVel);
		            		}
		            		
		            		this.handleCollision(element.getValue(), b);
		            	}
	            	}
	            	
	            }
	            
	            // Si la bille touche le bas du plateau
	            if (b.getY() + b.getRayon() >= (maxY-1) && yVel > 0) {
	            	b.setLance(false);
	                this.getChildren().remove(b.getVue());
	            }

                if (!this.isBilleAlive()) {
	                stopAnimation();
	                changerJeuCourant();
                }
    		}
    	}
    	this.jeuCourant.lanceur.getLanceur().checkTemporaryBilles();
    }
    
    private void handleCollision(Elements element, Bille bille) {
    	TreeMap<Coordonnees, Elements> tmp = new TreeMap<>();
    	tmp.putAll(jeuCourant.getJeu().getElements());
    	
    	if (element instanceof Ballz) {
    		Ballz ballz = (Ballz) element;
    		if (ballz.getLife() == 1) {
    			tmp.put(ballz.getCoordonnees(), new EmptyElement(ballz.getCoordonnees()));
    		} else {
    			ballz.decrementLife();
    			tmp.put(ballz.getCoordonnees(), ballz);
    		}
    	} else if (element instanceof BilleBonus) {
			tmp.put(element.getCoordonnees(), new EmptyElement(element.getCoordonnees()));
			this.jeuCourant.incrementNbOfBilles();
    	} else if (element instanceof BlackHole) {
    		BlackHole blackHole = (BlackHole) element;
    		blackHole.setTouched();
    		bille.setLance(false);
            this.getChildren().remove(bille.getVue());
    	} else if (element instanceof HorizontalLaser) {
    		HorizontalLaser verticalLaser = (HorizontalLaser) element;
    		verticalLaser.setTouched();
    		int line = verticalLaser.getY();
    		
    		if (!bille.knowThisVerticalLaser(verticalLaser.getCoordonnees())) {
	    		bille.addVerticalLaser(verticalLaser.getCoordonnees());
	    		
	    		for (Entry<Coordonnees, Elements> ballz : tmp.entrySet()) {
	    			Elements b = ballz.getValue();
	    			if (b instanceof Ballz) {
		            	if (b.getY() == line) {
		            		if (((Ballz) b).getLife() == 1) {
		            			tmp.put(b.getCoordonnees(), new EmptyElement(b.getCoordonnees()));
		            		} else {
		            			((Ballz) b).decrementLife();
		            			tmp.put(b.getCoordonnees(), b);
		            		}
		            	}
	            	}
	    		}
    		}
    	} else if (element instanceof BilleMultiplicator) {
    		BilleMultiplicator billeMultiplicator = (BilleMultiplicator) element;
    		billeMultiplicator.setTouched();
    		
    		if (!bille.knowThisBilleMultiplicator(billeMultiplicator.getCoordonnees())) {
	    		bille.addBilleMultiplicator(billeMultiplicator.getCoordonnees());
	    		this.jeuCourant.lanceur.getLanceur().addTemporaryBille(bille, billeMultiplicator.getCoordonnees());
    		}
    	}
		jeuCourant.getJeu().setElements(tmp);
		jeuCourant.refreshView();
    }
    
    private boolean isBilleAlive() {
    	boolean res = false;
    	List<Bille> billes = this.jeuCourant.lanceur.getLanceur().getBilles();
		
		if (billes.size() > 0) {
			
			for(Bille bille : billes) {
				if (bille.isLance() && bille.isAlreadyLance()) {
					res = true;
					break;
				}
			}
		}
		
		return res;
    }

    // On change de jeuCourant et on actualise les écouteurs d'action
	private void changerJeuCourant() {
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_CLICKED, handlerTir);
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_MOVED, handlerVisee);
		
        this.tirInProgress = false;

		this.nextTurn();
		
		if (jeuCourant == jeuUn) {
			jeuCourant = jeuDeux;
		} else {
			jeuCourant = jeuUn;
		}
		
		jeuCourant.addEventHandler(MouseEvent.MOUSE_CLICKED,handlerTir);
		jeuCourant.addEventHandler(MouseEvent.MOUSE_MOVED,handlerVisee);
		
		startAnimation(jeuCourant.getGrid(), jeuCourant.lanceur);
	}
    
	private int getNbOfBilles() {
		return this.jeuCourant.getNbOfBilles();
	}
    
}
