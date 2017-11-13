package metier;

import util.Coordonnees;

public class Ballz extends Elements{

	public Ballz(Coordonnees coord) {
		super(coord);
	}

	@Override
	public String toString() {
		return "Ballz ["  + "Coord = " + this.getCoordonnees().toString() +"]";
	}
}
