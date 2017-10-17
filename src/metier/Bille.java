package metier;

import util.Coordonnees;

public class Bille extends Elements{

	private String apparence = "default";
	private int dimension = 15;

	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public Bille(Coordonnees coord, String apparence) {
		super(coord);
		this.apparence = apparence;
	}
	
	public Bille(Coordonnees coord) {
		super(coord);
	}

	@Override
	public String toString() {
		return "Bille [apparence=" + apparence + " Coord = "+ this.getCoordonnees().toString() +"]";
	}

}
