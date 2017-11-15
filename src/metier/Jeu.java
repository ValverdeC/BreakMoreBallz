package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import util.Coordonnees;
import util.ElementsList;
import util.Services;

public class Jeu {

	private Profil profil;
	private Lanceur lanceur;
	private List<Bille> bille;
	private TreeMap<Coordonnees, Elements> elements;
	private Services service = new Services();
	
	public Jeu(Profil profil) {
		super();
		this.profil = profil;
		this.bille = new ArrayList<Bille>();
		this.elements = new TreeMap<>();
		this.initJeu();
	}
	
	public void nextTurn() {
		for (Entry<Coordonnees, Elements> entry : elements.entrySet()) {
		    if (entry.getValue().getName().equals(ElementsList.Ballz.name())) {
		    	System.out.println(entry.getValue());
		    }
		}
	}
	
	public Profil getProfil() {
		return profil;
	}

	public void initJeu() {
		this.initElementList();
		int nbOfBallz = this.service.randomGenerator(1, 10);
		System.out.println(nbOfBallz);
		for(int i = 0; i < nbOfBallz; i++) {
			int x = this.service.randomGenerator(0, 9);
			while(this.service.thereSomethingHere(elements, new Coordonnees(x, 1))) {
				x = this.service.randomGenerator(0, 9);
			}
			elements.put(new Coordonnees(x, 1), new Ballz(new Coordonnees(x, 1)));				
		}
	}
	
	private void initElementList() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				Coordonnees coord = new Coordonnees(x, y);
				this.elements.put(coord, new EmptyElement(coord));
			}
		}
	}
	
	public TreeMap<Coordonnees, Elements> getElements() {
		return elements;
	}

	public void setElements(TreeMap<Coordonnees, Elements> elements) {
		this.elements = elements;
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
	
}
