package application;

import java.util.concurrent.TimeUnit;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import metier.Lanceur;
import util.CoordonneesDouble;
import metier.Bille;

/**
 * Classe permettant d'afficher le lanceur sur le plateau
 * */
public class LanceurFX extends Group{

	private final Line line;
	private Lanceur lanceur = new Lanceur(new CoordonneesDouble(0,0));
	private Double ex;
	private Double ey;
	private Double sx;
	private Double sy;
	private Double x;
	private Double maxY;
	private Double minY;
	
	/** 
	 * Constructeur du lanceur
	 * Le lanceur est composé d'un élément graphique Line (pour le viseur)
	 * @param endX coordonnée x du point de depart du viseur
	 * @param endY coordonnée y du point de depart du viseur
	 * @param startX coordonnée x du point d'arrivée du viseur
	 * @param startY coordonnée y du point d'arrivée du viseur
	 */
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
   
    /** 
     * Constructeur utilisé pour initialiser la ligne du viseur 
     * et placer les écouteurs permettants de le déplacer
     */
    public LanceurFX(Line line) {
    super(line);
    this.line = line;
    this.line.setStroke(Color.WHITE);
    this.line.setStrokeWidth(3);
    this.line.setStrokeLineCap(StrokeLineCap.BUTT);
    this.line.getStrokeDashArray().addAll(3d, 21d);
    InvalidationListener updater = o -> {
    	if(null != ex && null != ey) {
    		setEndX(ex);
    		setEndY(ey);
    	}
        
    };

    // ajout des écouteurs
    startXProperty().addListener(updater);
    startYProperty().addListener(updater);
    endXProperty().addListener(updater);
    endYProperty().addListener(updater);
    updater.invalidated(null);
    
	}
    
    /**
     *  Colorise les billes pour les rendre visibles 
     *  @param numBalls nombre de billes à créer
     */
    public void createBilles(int numBalls) {
    	// On crée les billes dans l'objet "metier"
    	lanceur.createBalls(numBalls);
    	// On colore les billes 
    	for(Bille bille : lanceur.getBilles()) {
    		bille.getVue().setFill(Color.RED);
    		bille.getVue().setStroke(Color.YELLOW);
    		bille.getVue().setStrokeWidth(2);
    		bille.getVue().setStrokeType(StrokeType.INSIDE);
    	}
    }
    
    /** 
     * Modifie l'orientation du lanceur en fonction des coordonnées de la souris 
     * @param mx nouvelle coordonnée en x
     * @param my nouvelle coordonnée en y
     */
    public void orienterLanceur(double mx, double my) {
    		ey=my;
    		ex=mx;
	    	setEndX(mx);
    		setEndY(my);
    		// On mets aussi à jours les informations dans l'objet lanceur
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
