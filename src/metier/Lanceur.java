package metier;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import metier.Bille;
import util.Coordonnees;
import util.CoordonneesDouble;

/**
 * Classe stockant les informations propres au lanceur d'un joueur
 * */
public class Lanceur{
	private String apparence = "default";
	private List<Bille> billes; 
	private double angle = Math.toRadians(90);
	private CoordonneesDouble coord;
	private static final double VITESSE = -400 ;
	private static final double RAYON = 5 ;
	private int nbJoueur;
	private List<Bille> temporaryBilles; 
	
	
	/** Constructeur du lanceur
	 * @param pCoord coordonnées du lanceur
	 */
    public Lanceur(CoordonneesDouble pCoord) {
    	this.billes = new ArrayList<Bille>();
    	this.temporaryBilles = new ArrayList<Bille>();
		this.coord = pCoord;
	}

    /**
     * Crée le nombre de billes entré en paramètre
     * @param numBalls
     */
	public void createBalls(int numBalls){
		
		// Pour chaque bille, on initialise ses coordonnées avec celles du lanceur
		// On lui donne une acceleration basée sur l'angle du lanceur
		// coord.getY()*nbJoueur+(50*(nbJoueur-1)) cette formule est complexe car elle depend du numéro
        for (int i = 0; i < numBalls; i++) {
            Bille ball = new Bille(coord.getX(), coord.getY()*nbJoueur+(50*(nbJoueur-1)), RAYON, VITESSE*cos(angle), VITESSE*sin(angle));
            billes.add(ball);
            
        }
    }
	
	/** 
	 * Permet de mettre à jours l'angle du lanceur, et l'acceleration des billes en fonction 
	 * des coordonnées passées en paramètre
	 * @param ex coordonnée x de depart
	 * @param ey coordonnée y de depart
	 * @param sx coordonnée x d'arrivé
	 * @param sy coordonnée y d'arrivé
	 */
	public void updateCoord(double ex, double ey, double sx, double sy) {
		angle = Math.toDegrees(Math.atan2(ey - sy, ex - sx));
		angle += 180;
		angle = Math.toRadians(angle);
		for(Bille b : billes) {
			if(!b.isLance()) {
				b.setVitesseX(VITESSE*cos(angle));
				b.setVitesseY(VITESSE*sin(angle));
				b.setX(sx);
				b.setY(sy);
			}
		}
	}
	
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = Math.toRadians(angle);
	}

	public CoordonneesDouble getCoord() {
		return coord;
	}

	public void setCoord(CoordonneesDouble coord) {
		this.coord = coord;
	}

	public List<Bille> getBilles() {
		return billes;
	}

	public void setBilles(List<Bille> billes) {
		this.billes = billes;
	}
	
	public void setNbJoueur(int nb) {
		this.nbJoueur = nb;
	}
	
	public int getNbJoueur() {
		return this.nbJoueur;
	}
	
	public void addTemporaryBille(Bille bille, Coordonnees multiplicatorCoord) {
		Bille newBille = new Bille(bille.getX(), bille.getY(), bille.getRayon(), -bille.getVitesseX(), bille.getVitesseY());
		newBille.addBilleMultiplicator(multiplicatorCoord);
		newBille.setLance(false);
		newBille.setAlreadyLance(false);
		newBille.getVue().setFill(Color.DEEPPINK);
		this.temporaryBilles.add(newBille);
	}
	
	public boolean checkTemporaryBilles() {
		return this.temporaryBilles.size() > 0;
	}

	public List<Bille> getTemporaryBilles() {
		return temporaryBilles;
	}
}
