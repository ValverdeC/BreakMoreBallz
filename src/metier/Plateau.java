package metier;

/** 
 * Classe metier decrivant le plateau de jeu contenant les cadres de jeu des joueurs
 * 
 */
public class Plateau {
	
	private Jeu jeuJ1;
	private Jeu jeuJ2;
	private Jeu jeuCourant;
	private Jeu jeuNonCourant;
	private ParametresPlateau parametres;
	private Difficulty difficulty;
	
	// Constructeur
	public Plateau(Jeu jeuJ1, Jeu jeuJ2) {
		this.jeuJ1 = jeuJ1;
		this.jeuJ2 = jeuJ2;
		this.jeuCourant = jeuJ1;
		this.setJeuNonCourant(jeuJ2);
	}
	
	// Accesseurs
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
	
	public Jeu getJeuCourant() {
		return jeuCourant;
	}

	public void setJeuCourant(Jeu jeuCourant) {
		this.jeuCourant = jeuCourant;
	}
	
	public Jeu getJeuNonCourant() {
		return jeuNonCourant;
	}

	public void setJeuNonCourant(Jeu jeuNonCourant) {
		this.jeuNonCourant = jeuNonCourant;
	}

	// Méthodes
    /**
     * Permet de changer le joueur courant.
     */
    public void switchJoueur() {
        if (jeuCourant == jeuJ1) {
            jeuCourant = jeuJ2;
            jeuNonCourant = jeuJ1;
        } else {
            jeuCourant = jeuJ1;
            jeuNonCourant = jeuJ2;
        }
    }

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	

}
