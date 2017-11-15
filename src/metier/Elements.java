package metier;

import util.Coordonnees;

public abstract class Elements {
	private Coordonnees coord;
	private String name;
	
	public void setCoordonnees(int x, int y) {
		this.coord = new Coordonnees(x,y);
	}
	
	public Coordonnees getCoordonnees() {
		return coord;
	}
	
	public int getY() {
		return coord.getY();
	}
	
	public int getX() {
		return coord.getX();
	}

	public Elements(Coordonnees coord) {
		super();
		this.coord = coord;
		this.name = getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
