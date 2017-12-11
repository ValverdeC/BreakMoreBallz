package application;

import java.util.concurrent.TimeUnit;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import metier.Lanceur;
import util.Coordonnees;
import metier.Bille;

public class LanceurFX extends Group{

	private final Line line;
	private Line predicteur;
	private Lanceur lanceur = new Lanceur(new Coordonnees(0,0));
	private Double ex;
	private Double ey;
	private Double sx;
	private Double sy;
	private Double x;
	private Double maxY;
	private Double minY;
	
    public LanceurFX(double endX, double endY, double startX, double startY) {
        this(new Line());
        setX(endX);
        setMaxY(startY+1);
        setMinY(endY-1);
        ex = endX;
        ey = endY;
        sx = startX;
        sy = startY;
		setEndX(ex);
		setEndY(ey);
		setStartX(sx);
		setStartY(sy);
		lanceur.getCoord().setX(sx);
		lanceur.getCoord().setY(sy);
    }
   
    public LanceurFX(Line line) {
    super(line);
    this.line = line;
    InvalidationListener updater = o -> {
    	if(null != ex && null != ey) {
    		setEndX(ex);
    		setEndY(ey);
    	}
        
    };

    // add updater to properties
    startXProperty().addListener(updater);
    startYProperty().addListener(updater);
    endXProperty().addListener(updater);
    endYProperty().addListener(updater);
    updater.invalidated(null);
    
	}
    
    // Colorise les billes pour les rendre visibles
    public void createBilles(int numBalls) {
    	lanceur.createBalls(numBalls);
    	for(Bille bille : lanceur.getBilles()) {
    		bille.getVue().setFill(Color.RED);
    	}
    }
    
    // Modifie l'orientation du lanceur en fonction des coordonnées de la souris
    public void orienterLanceur(double mx, double my) {
    		ey=my;
    		ex=mx;
	    	setEndX(mx);
    		setEndY(my);
    		this.lanceur.updateCoord(ex, ey, sx, sy);
    }
    
    // start/end properties

    public final void setStartX(double value) {
        line.setStartX(value);
    }

    public final double getStartX() {
        return line.getStartX();
    }

    public final DoubleProperty startXProperty() {
        return line.startXProperty();
    }

    public final void setStartY(double value) {
        line.setStartY(value);
    }

    public final double getStartY() {
        return line.getStartY();
    }

    public final DoubleProperty startYProperty() {
        return line.startYProperty();
    }

    public final void setEndX(double value) {
        line.setEndX(value);
    }

    public final double getEndX() {
        return line.getEndX();
    }

    public final DoubleProperty endXProperty() {
        return line.endXProperty();
    }

    public final void setEndY(double value) {
        line.setEndY(value);
    }

    public final double getEndY() {
        return line.getEndY();
    }

    public final DoubleProperty endYProperty() {
        return line.endYProperty();
    }

	public Lanceur getLanceur() {
		return lanceur;
	}

	public void setLanceur(Lanceur lanceur) {
		this.lanceur = lanceur;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getMaxY() {
		return maxY;
	}

	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}

	public Double getMinY() {
		return minY;
	}

	public void setMinY(Double minY) {
		this.minY = minY;
	}

}
