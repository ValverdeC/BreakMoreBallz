package metier;

import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import util.Coordonnees;

public class Bille  {

	private String apparence = "default";
    private final DoubleProperty vitesseX ; // pixels par seconde
    private final DoubleProperty vitesseY ; 
    private final ReadOnlyDoubleWrapper vitesse ;
    private final double rayon; // rayon en pixels
    private boolean lance = false;
    private boolean alreadyLance = false;
    private ArrayList<Coordonnees> verticalLasers = new ArrayList<Coordonnees>();
    private ArrayList<Coordonnees> billeMultiplicators = new ArrayList<Coordonnees>();

    private final Circle vue;

    public Bille(double centerX, double centerY, double rayon,
            double vitesseX, double vitesseY) {

        this.vue = new Circle(centerX, centerY, rayon);
        this.vitesseX = new SimpleDoubleProperty(this, "vitesseX", vitesseX);
        this.vitesseY = new SimpleDoubleProperty(this, "vitesseY", vitesseY);
        this.vitesse = new ReadOnlyDoubleWrapper(this, "vitesse");
        vitesse.bind(Bindings.createDoubleBinding(new Callable<Double>() {
            
            @Override
            public Double call() throws Exception {
                final double vitX = getVitesseX();
                final double vitY = getVitesseY();
                return sqrt(vitX * vitX + vitY * vitY);
            }
        }, this.vitesseX, this.vitesseY));
        this.rayon = rayon;
        vue.setRadius(rayon);

    }
    
    public final double getVitesseX() {
        return vitesseX.get();
    }

    public final void setVitesseX(double vitesseX) {
        this.vitesseX.set(vitesseX);
    }

    public final double getVitesseY() {
        return vitesseY.get();
    }

    public final void setVitesseY(double vitesseY) {
        this.vitesseY.set(vitesseY);
    }

	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}

	public ReadOnlyDoubleWrapper getVitesse() {
		return vitesse;
	}

	public double getRayon() {
		return rayon;
	}

	public Circle getVue() {
		return vue;
	}
    
    public final double getX() {
        return vue.getCenterX();
    }

    public final void setX(double centerX) {
        vue.setCenterX(centerX);
    }

    public final DoubleProperty xProperty() {
        return vue.centerXProperty();
    }
    
    public final double getY() {
        return vue.getCenterY();
    }

    public final void setY(double centerY) {
        vue.setCenterY(centerY);
    }

    public final DoubleProperty yProperty() {
        return vue.centerYProperty();
    }

	public boolean isLance() {
		return lance;
	}

	public void setLance(boolean lance) {
		this.lance = lance;
	}

	public boolean isAlreadyLance() {
		return alreadyLance;
	}

	public void setAlreadyLance(boolean alreadyLance) {
		this.alreadyLance = alreadyLance;
	}

	public ArrayList<Coordonnees> getVerticalLasers() {
		return verticalLasers;
	}

	public void setVerticalLasers(ArrayList<Coordonnees> verticalLasers) {
		this.verticalLasers = verticalLasers;
	}
	
	public void addVerticalLaser(Coordonnees verticalLasers) {
		this.verticalLasers.add(verticalLasers);
	}
	
	public boolean knowThisVerticalLaser(Coordonnees verticalLasers) {
		return this.verticalLasers.contains(verticalLasers);
	}
	
	public void clearVerticalLaserList() {
		this.verticalLasers.clear();
	}

	public ArrayList<Coordonnees> getBilleMultiplicators() {
		return billeMultiplicators;
	}

	public void setBilleMultiplicators(ArrayList<Coordonnees> billeMultiplicators) {
		this.billeMultiplicators = billeMultiplicators;
	}
	
	public void addBilleMultiplicator(Coordonnees billeMultiplicator) {
		this.billeMultiplicators.add(billeMultiplicator);
	}
	
	public boolean knowThisBilleMultiplicator(Coordonnees billeMultiplicator) {
		return this.billeMultiplicators.contains(billeMultiplicator);
	}
	
	public void clearBilleMultiplicatorList() {
		this.billeMultiplicators.clear();
	}
}
