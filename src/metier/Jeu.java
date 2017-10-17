package metier;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

	private Profil profil;
	private Lanceur lanceur;
	private List<Bille> bille;
	private List<Ballz> ballz;
	
	public Jeu(Profil profil) {
		super();
		this.profil = profil;
		this.bille = new ArrayList<Bille>();
		this.ballz = new ArrayList<Ballz>();

	}

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

	
	public List<Bille> getBille() {
		return bille;
	}

	public void setBille(List<Bille> bille) {
		this.bille = bille;
	}

	public List<Ballz> getBallz() {
		return ballz;
	}

	public void setBallz(List<Ballz> ballz) {
		this.ballz = ballz;
	}
	
}
