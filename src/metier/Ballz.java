package metier;

import util.Coordonnees;

/** 
 * Classe metier contenant les informations d'une ballz (Brique)
 * */
public class Ballz extends Elements{
	private int life; // points de vie de la Ballz

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
