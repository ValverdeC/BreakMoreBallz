package metier;

import util.Coordonnees;

public class VerticalLaser extends Elements {
	boolean touched = false;

	public VerticalLaser(Coordonnees coord) {
		super(coord);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched() {
		this.touched = true;
	}

}
