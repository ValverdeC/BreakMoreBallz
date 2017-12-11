package application;

import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Bille;

public class TestLanceurBilles extends Application {
	
    private static final double DIMENSIONX = 600;
    private static final double DIMENSIONY = 800;
    private static final int NBBILLES = 1;
    private LanceurFX lanceur = new LanceurFX(DIMENSIONX/2,DIMENSIONY-50,DIMENSIONX/2,DIMENSIONY);
    private Pane field;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Creation du terrain et du lanceur
		field = new Pane();
		field.getChildren().add(lanceur);
		// Au clic de souris, supprime les billes deja présentes et en crée de nouvelle (et les lance)
		field.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                		for(Bille bille : lanceur.getLanceur().getBilles()) {
                			field.getChildren().remove(bille.getVue());
                		}
                    	lanceur.getLanceur().getBilles().clear();
						lanceur.createBilles(NBBILLES);
                		for(Bille bille : lanceur.getLanceur().getBilles()) {
                			field.getChildren().add(bille.getVue());
                    		bille.setLance(true);
                		}
                		event.consume();
                    }
                });
		
		field.addEventHandler(MouseEvent.MOUSE_MOVED,
				new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                    	lanceur.orienterLanceur(mouseEvent.getX(),mouseEvent.getY());
                    	
                    }

                });

		
        final Scene scene = new Scene(field, DIMENSIONX, DIMENSIONY);
        primaryStage.setScene(scene);
        primaryStage.show();
		
        startAnimation(field, lanceur);
	}
	
	//Gestion des animations (collision + déplacement)
    private void startAnimation(final Pane field, LanceurFX lanceur) {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                	checkCollision(DIMENSIONX,DIMENSIONY);
                    long elapsedTime = timestamp - lastUpdateTime.get();
                    updateWorld(elapsedTime, lanceur);
                }
                lastUpdateTime.set(timestamp);
            }

        };
        timer.start();
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
    	for (Bille b : lanceur.getLanceur().getBilles()) {
    		double xVel = b.getVitesseX();
            double yVel = b.getVitesseY();
            if ((b.getX() - b.getRayon() <= 0 && xVel < 0)
                    || (b.getX() + b.getRayon() >= maxX && xVel > 0)) {
                b.setVitesseX(-xVel);
            }
            if (b.getY() - b.getRayon() <= 0 && yVel < 0) {
                b.setVitesseY(-yVel);
            }
            if (b.getY() + b.getRayon() >= maxY && yVel > 0) {
            	field.getChildren().remove(b.getVue());
            }
    	}
    }

}