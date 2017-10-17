package metier;

import util.Coordonnees;

public class Lanceur extends Elements{

	private String apparence = "default";

	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}

	public Lanceur(String apparence, Coordonnees coord) {
		super(coord);
		this.apparence = apparence;
	}
	
	public Lanceur(Coordonnees coord) {
		super(coord);
	}

	@Override
	public String toString() {
		return "Lanceur [apparence=" + apparence + " x = "+ this.getX() + " y = "+ this.getY() +"]";
	}
	
	
}
