package metier;

public class Plateau {
	
	private Jeu jeuJ1;
	private Jeu jeuJ2;
	
	public Plateau(Jeu jeuJ1, Jeu jeuJ2) {
		this.jeuJ1 = jeuJ1;
		this.jeuJ2 = jeuJ2;
	}

	public Jeu getJeuJ1() {
		return jeuJ1;
	}

	public void setJeuJ1(Jeu jeuJ1) {
		this.jeuJ1 = jeuJ1;
	}

	public Jeu getJeuJ2() {
		return jeuJ2;
	}

	public void setJeuJ2(Jeu jeuJ2) {
		this.jeuJ2 = jeuJ2;
	}
	

}
