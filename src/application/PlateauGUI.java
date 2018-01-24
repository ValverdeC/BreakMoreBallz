package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.Timer;

import java.util.ArrayList;
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
import metier.Profil;
import metier.StarDestroyer;
import metier.VerticalLaser;
import util.Coordonnees;
/** 
 * Classe permettant d'afficher le plateau de jeu, avec les cadres pour les 2 joueurs
 */
public class PlateauGUI extends Parent {
	// Elements test right bar
	RightBarUI rb1 = new RightBarUI(new Profil(1, "Toto"), 1);
	RightBarUI rb2 = new RightBarUI(new Profil(1, "Max"), 2);
	JeuUI jeuUn;
	JeuUI jeuDeux;
	JeuUI jeuCourant;
	JeuUI jeuOppose;
	AnimationTimer timer;
	ImageView iv1 = new ImageView();
	
	GridPane grid = new GridPane();
	
	boolean tirInProgress = false;
	// Ecouteur sur le clic de souris, on lance le tir lors d'un clic
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
	
	Timer temporaryBillesTimer = new Timer(200, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	displayTemporaryBilles();
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
    // Ecouteur permettant de detecter le mouvement de la souris et de reorienter 
    // le lanceur par la meme occasion
	private EventHandler<MouseEvent> handlerVisee = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
        	if (!tirInProgress) {
        		jeuCourant.lanceur.orienterLanceur(mouseEvent.getX(),mouseEvent.getY());
        		jeuCourant.orienterLanceur();
        	}
        }
    };
		
	Main app;
	
	public PlateauGUI(Main main, Profil profil1, Profil profil2) {
		Image image = new Image(getClass().getResourceAsStream("game_background.jpg")); // Image de fond
		jeuUn = new JeuUI(profil1);
		jeuDeux = new JeuUI(profil2);
		jeuCourant = new JeuUI(profil1);
		jeuOppose = new JeuUI(profil2);
		iv1.setImage(image);
		this.getChildren().add(iv1);
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.getStyleClass().add("plateau");
		// On defini le numero des joueurs (utile pour la gestion des coordonnées)
		jeuUn.lanceur.getLanceur().setNbJoueur(1);
		jeuDeux.lanceur.getLanceur().setNbJoueur(2);
		grid.getStyleClass().add("jeu");
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		// On positionne les cadres de jeu sur le plateau
		grid.add(jeuUn, 0, 0);
		grid.add(rb1, 1, 0);
		grid.add(jeuDeux, 0, 1);
		grid.add(rb2, 1, 1);
		changerJeuCourant(); // On initialise le jeu courant avec la methode de changement de jeu
		this.app = main;
				
		this.getChildren().add(grid);
	}
	
	public void displayBille() {
		List<Bille> billes = this.jeuCourant.lanceur.getLanceur().getBilles();
		
		if (billes.size() > 0) {
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

	public void displayTemporaryBilles() {
		List<Bille> billes = this.jeuCourant.lanceur.getLanceur().getTemporaryBilles();
		
		if (billes.size() > 0) {
			for(Bille bille : billes) {
				if (!bille.isLance() && !bille.isAlreadyLance()) {
					this.getChildren().add(bille.getVue());
		    		bille.setLance(true);
		    		bille.setAlreadyLance(true);
		    		break;
				}
			}
		}
				
		if (!isTemporaryBilleAlive()) {
			this.temporaryBillesTimer.stop();
		}
	}
	
	/** 
	 * Permet de passer le tour du joueur courant à l'autre joueur
	 * Check si le joueur à perdu ou non 
	 */
	private void nextTurn() {
		Alert alert = new Alert(AlertType.INFORMATION, "Perdu, recommencer ?", ButtonType.YES, ButtonType.NO);
		boolean res = this.jeuCourant.nextTurn();
		
		if (res) {
			this.stopAnimation();
			
			alert.setOnHidden(evt -> {
				if (alert.getResult() == ButtonType.YES) {
	    		   this.jeuUn.restartJeu();
	    		   this.jeuDeux.restartJeu();
	    		} else {
	    			resetView();
	    			this.app.setMenuView();
	    		}
			});

		    alert.show();
    	}
	}
	
	/** 
	 * Retire les cadres de jeu du plateau 
	 */
	private void resetView() {
		this.getChildren().remove(this.grid);
	}
	
	/** 
	 * Tir les billes du joueur courant à partir de son lanceur
	 * */
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
	
	//Gestion des animations (collision + dï¿½placement)
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

	//Gestion des animations (collision + dï¿½placement)
    private void stopAnimation() {
        this.timer.stop();
        this.tirTimer.stop();
    }
    
	//Mise ï¿½ jour des ï¿½lï¿½ments graphiques (dï¿½placement des billes)
    private void updateWorld(long elapsedTime, LanceurFX lanceur) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        ArrayList<Bille> billes = new ArrayList<Bille>();
        
        billes.addAll(this.jeuCourant.lanceur.getLanceur().getBilles());
        billes.addAll(this.jeuCourant.lanceur.getLanceur().getTemporaryBilles());
        
        for (Bille b : billes) {
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
    	
    	ArrayList<Bille> billes = new ArrayList<Bille>();
        
        billes.addAll(this.jeuCourant.lanceur.getLanceur().getBilles());
        billes.addAll(this.jeuCourant.lanceur.getLanceur().getTemporaryBilles());

    	for (Bille b : billes) {
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
		            	if(distance <= 35 && !b.isAccrossBallz()){
		            		if(b.getX() >= ballzX + 20 || b.getX() <= ballzX - 20) {
		            			b.setVitesseX(-xVel);
		            		}
		            		if(b.getY() >= ballzY + 20 || b.getY() <= ballzY - 20) {
		            			b.setVitesseY(-yVel);
		            		}
		            		
		            		this.handleCollision(element.getValue(), b);
		            	} else if (distance <= 35 && b.isAccrossBallz()) {
		            		this.handleCollision(element.getValue(), b);
		            	}
	            	} else if(
	            			element.getValue() instanceof BilleBonus ||
	            			element.getValue() instanceof BlackHole ||
	            			element.getValue() instanceof HorizontalLaser ||
	            			element.getValue() instanceof VerticalLaser ||
	            			element.getValue() instanceof StarDestroyer
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

                if (!this.isBilleAlive() && !this.isTemporaryBilleAlive()) {
	                stopAnimation();
	                changerJeuCourant();
                }
    		}
    	}
    	
    	if (this.jeuCourant.lanceur.getLanceur().checkTemporaryBilles() && !this.temporaryBillesTimer.isRunning()) {
    		this.temporaryBillesTimer.start();
    	}
    }
    
    private void handleCollision(Elements element, Bille bille) {
    	TreeMap<Coordonnees, Elements> tmp = new TreeMap<>();
    	tmp.putAll(jeuCourant.getJeu().getElements());
    	
    	if (element instanceof Ballz) {
    		Ballz ballz = (Ballz) element;
    		if (ballz.getLife() == 1) {
    			tmp.put(ballz.getCoordonnees(), new EmptyElement(ballz.getCoordonnees()));
    			this.jeuCourant.incrementNbBallzDetruit();
    			this.incrementerScore(jeuCourant.lanceur.getLanceur().getNbJoueur());
    		} else {
    			ballz.decrementLife();
    			tmp.put(ballz.getCoordonnees(), ballz);
    		}
    	} else if (element instanceof BilleBonus) {
			tmp.put(element.getCoordonnees(), new EmptyElement(element.getCoordonnees()));
			this.jeuCourant.incrementNbOfBilles();
			this.incrementerNbBilles(jeuCourant.lanceur.getLanceur().getNbJoueur());
    	} else if (element instanceof BlackHole) {
    		BlackHole blackHole = (BlackHole) element;
    		blackHole.setTouched();
    		bille.setLance(false);
            this.getChildren().remove(bille.getVue());
    	} else if (element instanceof HorizontalLaser) {
    		HorizontalLaser horizontalLaser = (HorizontalLaser) element;
    		horizontalLaser.setTouched();
    		int line = horizontalLaser.getY();
    		
    		if (!bille.knowThisHorizontalLaser(horizontalLaser.getCoordonnees())) {
	    		bille.addHorizontalLaser(horizontalLaser.getCoordonnees());
	    		
	    		for (Entry<Coordonnees, Elements> ballz : tmp.entrySet()) {
	    			Elements b = ballz.getValue();
	    			if (b instanceof Ballz) {
		            	if (b.getY() == line) {
		            		if (((Ballz) b).getLife() == 1) {
		            			tmp.put(b.getCoordonnees(), new EmptyElement(b.getCoordonnees()));
		            			this.jeuCourant.incrementNbBallzDetruit();
		            			// TODO increment score here
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
	    		this.displayTemporaryBilles();
	    		this.temporaryBillesTimer.start();
    		}
    	} else if (element instanceof VerticalLaser) {
    		VerticalLaser verticalLaser = (VerticalLaser) element;
    		verticalLaser.setTouched();
    		int column = verticalLaser.getX();
    		
    		if (!bille.knowThisVerticalLaser(verticalLaser.getCoordonnees())) {
	    		bille.addVerticalLaser(verticalLaser.getCoordonnees());
	    		
	    		for (Entry<Coordonnees, Elements> ballz : tmp.entrySet()) {
	    			Elements b = ballz.getValue();
	    			if (b instanceof Ballz) {
		            	if (b.getX() == column) {
		            		if (((Ballz) b).getLife() == 1) {
		            			tmp.put(b.getCoordonnees(), new EmptyElement(b.getCoordonnees()));
		            			this.jeuCourant.incrementNbBallzDetruit();
		            			// TODO increment score here
		            		} else {
		            			((Ballz) b).decrementLife();
		            			tmp.put(b.getCoordonnees(), b);
		            		}
		            	}
	            	}
	    		}
    		}
    	} else if (element instanceof StarDestroyer) {
    		StarDestroyer starDestroyer = (StarDestroyer) element;
    		starDestroyer.setTouched();
    		tmp.put(element.getCoordonnees(), new EmptyElement(element.getCoordonnees()));
    		bille.setAccrossBallz();
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
    
    private boolean isTemporaryBilleAlive() {
    	boolean res = false;
    	List<Bille> billes = this.jeuCourant.lanceur.getLanceur().getTemporaryBilles();
		
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

    // On change de jeuCourant et on actualise les ï¿½couteurs d'action
	private void changerJeuCourant() {
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_CLICKED, handlerTir);
		jeuCourant.removeEventHandler(MouseEvent.MOUSE_MOVED, handlerVisee);
		
        this.tirInProgress = false;
        
        if (!this.jeuCourant.isThereAnyBallzOnPlate()) {
        	this.jeuCourant.incrementNbOfBilles();
        }
        
        this.jeuOppose.getJeu().addMalusBallz(this.jeuCourant.getNbBallzDetruits() > 4 ? 4 : this.jeuCourant.getNbBallzDetruits());
        this.jeuCourant.resetNbBallzDetruits();

		this.nextTurn();
		
		if (jeuCourant == jeuUn) {
			jeuCourant = jeuDeux;
			jeuOppose = jeuUn;
		} else {
			jeuCourant = jeuUn;
			jeuOppose = jeuDeux;
		}
		
		jeuCourant.addEventHandler(MouseEvent.MOUSE_CLICKED,handlerTir);
		jeuCourant.addEventHandler(MouseEvent.MOUSE_MOVED,handlerVisee);
		
		startAnimation(jeuCourant.getGrid(), jeuCourant.lanceur);
	}
    
	private int getNbOfBilles() {
		return this.jeuCourant.getNbOfBilles();
	}
	
	/** Incremente le score du joueur courant */
	private void incrementerScore(int numJoueur) {
		if(numJoueur == 1) {
			rb1.incrementerScore();
		}else {
			rb2.incrementerScore();
		}
	}
	
	/** Incremente le score du joueur courant */
	private void incrementerNbBilles(int numJoueur) {
		if(numJoueur == 1) {
			rb1.incrementerNbBilles();
		}else {
			rb2.incrementerNbBilles();
		}
	} 
}
