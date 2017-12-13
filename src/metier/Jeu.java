package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import application.JeuUI;
import util.Coordonnees;
import util.ElementsList;
import util.Services;

import util.Coordonnees;

public class Jeu {

	private Profil profil;
	private Lanceur lanceur;
	private List<Bille> billes;
	private TreeMap<Coordonnees, Elements> elements;
	private Services service = new Services();
	private int turn;
	private double dimensionX;
	private double dimensionY;
	private int nbBallzDetruits;
	private JeuUI ui;
	
	// Constructeur
	
	public Jeu(Profil profil, double dimX, double dimY, JeuUI ui) {
		this.ui = ui;
		this.profil = profil;
		this.billes = new ArrayList<Bille>();
		this.elements = new TreeMap<>();
		this.initJeu(this.elements);
		this.turn = 1;
		this.dimensionX = dimX-100;
		this.dimensionY = dimY/2;
		//Bille premiereBille = new Bille(new Coordonnees(dimX/2,dimY));
		//this.billes.add(premiereBille);
	}
	/**
	 * @return Si le jeu est fini ou pas
	 */
	public boolean nextTurn() {
		boolean end = false;
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
		
		if (this.thereBallzOnLastLine(elements)) {
			end = true;
		}
		
		return end;
	}
	
	
	// Accesseurs
	public Profil getProfil() {
		return profil;
	}

	public void initJeu(TreeMap<Coordonnees, Elements> elements) {
		this.initElementList(elements);
		int nbOfBallz = this.service.randomGenerator(1, 10);
		System.out.println(nbOfBallz);
		for(int i = 0; i < nbOfBallz; i++) {
			int x = this.service.randomGenerator(0, 9);
			while(this.thereSomethingHere(elements, new Coordonnees(x, 1))) {
				x = this.service.randomGenerator(0, 9);
			}
			elements.put(new Coordonnees(x, 1), new Ballz(new Coordonnees(x, 1), this.turn));				
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

	
	public List<Bille> getBilles() {
		return billes;
	}

	public void setBilles(List<Bille> bille) {
		this.billes = bille;
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

	public double getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(double dimensionX) {
		this.dimensionX = dimensionX;
	}

	public double getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(double dimensionY) {
		this.dimensionY = dimensionY;
	}


	public int getNbBallzDetruits() {
		return nbBallzDetruits;
	}


	public void setNbBallzDetruits(int nbBallzDetruits) {
		this.nbBallzDetruits = nbBallzDetruits;
	}
	
	private void jouer() {
		
	}
	
	private void orienterLanceur() {
		
	}
	
	public boolean thereBallzOnLastLine(TreeMap<Coordonnees, Elements> elements) {
		boolean res = false;
		
		for (int i = 0; i < 10; i++) {
			Coordonnees coord = new Coordonnees(i, 9);
			if(elements.get(coord).getName().equals(ElementsList.Ballz.name())) {
				res = true;
			}
		}
		
		return res;
	}
	
	public void refreshView() {
		this.ui.refreshView();
	}
}
