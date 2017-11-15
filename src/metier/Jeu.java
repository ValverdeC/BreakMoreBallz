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
	private int turn;
	
	public Jeu(Profil profil) {
		super();
		this.profil = profil;
		this.bille = new ArrayList<Bille>();
		this.elements = new TreeMap<>();
		this.initJeu(this.elements);
		this.turn = 1;
	}
	
	public void nextTurn() {
		TreeMap<Coordonnees, Elements> newElements = new TreeMap<>();
		initJeu(newElements);
		this.turn++;
		for (Entry<Coordonnees, Elements> entry : elements.entrySet()) {
			Elements ballz = entry.getValue();
			
		    if (ballz.getName().equals(ElementsList.Ballz.name())) {
		    	Coordonnees newCoord = new Coordonnees(ballz.getX(), ballz.getY() + 1);
		    	ballz.setCoordonnees(newCoord);
		    	newElements.put(newCoord, ballz);
		    }
		}
		this.elements = newElements;
		
	}
	
	public Profil getProfil() {
		return profil;
	}

	public void initJeu(TreeMap<Coordonnees, Elements> jeu) {
		this.initElementList(jeu);
		int nbOfBallz = this.service.randomGenerator(1, 10);
		System.out.println(nbOfBallz);
		for(int i = 0; i < nbOfBallz; i++) {
			int x = this.service.randomGenerator(0, 9);
			while(this.thereSomethingHere(jeu, new Coordonnees(x, 1))) {
				x = this.service.randomGenerator(0, 9);
			}
			jeu.put(new Coordonnees(x, 1), new Ballz(new Coordonnees(x, 1), this.turn));				
		}
	}
	
	private void initElementList(TreeMap<Coordonnees, Elements> list) {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				Coordonnees coord = new Coordonnees(x, y);
				list.put(coord, new EmptyElement(coord));
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

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public boolean thereSomethingHere(TreeMap<Coordonnees, Elements> elements, Coordonnees coord) {
		boolean res = false;
		Elements element = elements.get(coord);
		
		if(element.getName().equals(ElementsList.Ballz.name())) {
			res = true;
		}
		
		return res;
	}
	
	public boolean thereBallzOnLastLine(Jeu jeu) {
		boolean res = false;
		
		for (int i = 0; i < 10; i++) {
			Coordonnees coord = new Coordonnees(i, 9);
			if(jeu.getElements().get(coord).getName().equals(ElementsList.Ballz.name())) {
				res = true;
			}
		}
		
		return res;
	}
}
