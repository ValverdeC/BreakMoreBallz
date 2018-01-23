package metier;

import util.Coordonnees;

public class HorizontalLaser extends Elements {
	boolean touched = false;

	public HorizontalLaser(Coordonnees coord) {
		super(coord);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}

}
