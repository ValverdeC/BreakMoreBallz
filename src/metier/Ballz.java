package metier;

import util.Coordonnees;

public class Ballz extends Elements{
	private int life;

	public Ballz(Coordonnees coord, int life) {
		super(coord);
		this.life = life;
	}

	@Override
	public String toString() {
		return "Ballz ["  + "Coord = " + this.getCoordonnees().toString() + " Life = " + this.life + "]";
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void decrementLife() {
		this.life--;
	}
}
