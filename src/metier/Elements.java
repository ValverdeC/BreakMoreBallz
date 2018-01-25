package metier;

import util.Coordonnees;

public abstract class Elements {
	private Coordonnees coord;
	private String name;
	private boolean touched = false;
	
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

	public void setCoordonnees(Coordonnees newCoord) {
		this.coord = newCoord;		
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}
	
}
