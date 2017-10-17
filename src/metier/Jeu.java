package metier;

public class Jeu {

	private Profil profil;
	private Lanceur lanceur;
	private Bille bille;

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public Lanceur getLanceur() {
		return lanceur;
	}

	public void setLanceur(Lanceur lanceur) {
		this.lanceur = lanceur;
	}

	public Bille getBille() {
		return bille;
	}

	public void setBille(Bille bille) {
		this.bille = bille;
	}
	
	@Override
	public String toString() {
		return "Jeu [profil=" + profil.getPseudo() + ", lanceur=" + lanceur.getApparence() + ", bille=" + bille.getApparence() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
