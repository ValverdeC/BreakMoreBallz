package metier;

import util.Coordonnees;

public class BilleMultiplicator extends Elements{
	boolean touched = false;

	public BilleMultiplicator(Coordonnees coord) {
		super(coord);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}

}
