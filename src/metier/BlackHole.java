package metier;

import util.Coordonnees;

public class BlackHole extends Elements {
	boolean touched = false;

	public BlackHole(Coordonnees coord) {
		super(coord);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}

}
