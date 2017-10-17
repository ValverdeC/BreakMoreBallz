package metier;

import util.Coordonnees;

public abstract class Elements {
	private Coordonnees coord;
	
	public void setCoordonnees(double x, double y) {
		this.coord = new Coordonnees(x,y);
	}
	
	public Coordonnees getCoordonnees() {
		return coord;
	}
	
	public double getY() {
		return coord.getY();
	}
	
	public double getX() {
		return coord.getX();
	}

	public Elements(Coordonnees coord) {
		super();
		this.coord = coord;
	}
	
}
