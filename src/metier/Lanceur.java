package metier;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import metier.Bille;
import util.Coordonnees;

public class Lanceur{

	private String apparence = "default";
	private List<Bille> billes; 
	private double angle = Math.toRadians(90);
	private Coordonnees coord;
	private static final double VITESSE = -600 ;
	private static final double RAYON = 15 ;
	
	
    public Lanceur(Coordonnees pCoord) {
    	this.billes = new ArrayList<Bille>();
		this.coord = pCoord;
	}

    /**
     * Crée le nombre de billes entré en paramètre
     * @param numBalls
     * @throws InterruptedException 
     */
	public void createBalls(int numBalls){
    	
        for (int i = 0; i < numBalls; i++) {
            Bille ball = new Bille(coord.getX(), coord.getY(), RAYON, VITESSE*cos(angle), VITESSE*sin(angle));
            billes.add(ball);
            
        }
    }
	
	public void updateCoord(double ex, double ey, double sx, double sy) {
		angle = Math.toDegrees(Math.atan2(ey - sy, ex - sx));
		angle += 180;
		angle = Math.toRadians(angle);
		for(Bille b : billes) {
			if(!b.isLance()) {
				b.setVitesseX(VITESSE*cos(angle));
				b.setVitesseX(VITESSE*sin(angle));
			}
		}
	}
	
	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = Math.toRadians(angle);
	}

	public Coordonnees getCoord() {
		return coord;
	}

	public void setCoord(Coordonnees coord) {
		this.coord = coord;
	}

	public List<Bille> getBilles() {
		return billes;
	}

	public void setBilles(List<Bille> billes) {
		this.billes = billes;
	}
	
}
