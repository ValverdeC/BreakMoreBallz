package metier;

import util.Coordonnees;

public class StarDestroyer extends Elements {
	boolean touched = false;

	public StarDestroyer(Coordonnees coord) {
		super(coord);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}

}
