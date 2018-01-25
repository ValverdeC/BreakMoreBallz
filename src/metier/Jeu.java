package metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import application.JeuUI;
import util.Bonus;
import util.Coordonnees;
import util.ElementsList;
import util.Services;


public class Jeu {

	private Profil profil;
	private Lanceur lanceur;
	private List<Bille> billes;
	private TreeMap<Coordonnees, Elements> elements;
	private Services service = new Services();
	private int turn;
	private int nbBallzDetruits;
	private int nbBilles;
	private JeuUI ui;
	private static final List<Bonus> BONUS = Collections.unmodifiableList(Arrays.asList(Bonus.values()));
	private Difficulty difficulty;
	
	// Constructeur
	
	public Jeu(Profil profil, double dimX, double dimY, JeuUI ui, Difficulty difficulty) {
		this.difficulty = difficulty;
		this.turn = 1;
		this.ui = ui;
		this.profil = profil;
		this.billes = new ArrayList<Bille>();
		this.elements = new TreeMap<>();
		this.initJeu(this.elements);
		this.nbBilles = 1;
	}
	/**
	 * @return Si le jeu est fini ou pas
	 */
	public boolean nextTurn() {
		boolean end = false;
		TreeMap<Coordonnees, Elements> newElements = new TreeMap<>();
		this.turn++;
		initJeu(newElements);
		this.clearBillesVerticalAndHorizontalLasersLists();
		
		for (Entry<Coordonnees, Elements> entry : elements.entrySet()) {
			Elements ballz = entry.getValue();
			
		    if (ballz.getName().equals(ElementsList.Ballz.name()) || ballz.getName().equals(ElementsList.BilleBonus.name())) {
		    	Coordonnees newCoord = new Coordonnees(ballz.getX(), ballz.getY() + 1);
		    	ballz.setCoordonnees(newCoord);
		    	newElements.put(newCoord, ballz);
		    } else if (
		    	ballz.getName().equals(ElementsList.BlackHole.name()) ||
		    	ballz.getName().equals(ElementsList.HorizontalLaser.name()) ||
		    	ballz.getName().equals(ElementsList.BilleMultiplicator.name()) ||
		    	ballz.getName().equals(ElementsList.VerticalLaser.name()) ||
		    	ballz.getName().equals(ElementsList.StarDestroyer.name()) ||
		    	ballz.getName().equals(ElementsList.BouncingBall.name())
		    ) {
    			if (!ballz.isTouched()) {
    				Coordonnees newCoord = new Coordonnees(ballz.getX(), ballz.getY() + 1);
    		    	ballz.setCoordonnees(newCoord);
    		    	newElements.put(newCoord, ballz);
    			} else {
    				Coordonnees newCoord = new Coordonnees(ballz.getX(), ballz.getY() + 1);
    		    	newElements.put(newCoord, new EmptyElement(newCoord));
    			}
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
		
		// Init des nouvelles Ballz aléatoirement avec pourcentage de chance d'avoir 7 ballz et +
		int nbOfBallz = this.service.randomGeneratorWithChance(1, 9, this.difficulty.getBallzLimitChance(), this.difficulty.getBallzLimit());
		for(int i = 0; i < nbOfBallz; i++) {
			int x = this.service.randomGenerator(0, 9);
			while(this.thereSomethingHere(elements, new Coordonnees(x, 1))) {
				x = this.service.randomGenerator(0, 9);
			}
			elements.put(new Coordonnees(x, 1), new Ballz(new Coordonnees(x, 1), this.turn));				
		}
		
		// Ajout sur le plateau de la nouvelle bille à attraper, position aléatoire
		int randomIndex = this.getRandomIndex(elements);
		elements.put(new Coordonnees(randomIndex, 1), new BilleBonus(new Coordonnees(randomIndex, 1)));
		
		// On a une chance sur 3 d'avoir un bonus sur le plateau s'il y a une place libre
		if (this.isThereFreePlace(elements) && this.service.trueOrFalseRandom(this.difficulty.getBonus())) {
			switch (this.getRandomBonus()) {
				case HorizontalLaser:
					randomIndex = this.getRandomIndex(elements);
					elements.put(new Coordonnees(randomIndex, 1), new HorizontalLaser(new Coordonnees(randomIndex, 1)));
					break;
				case BilleMultiplicator:
					randomIndex = this.getRandomIndex(elements);
					elements.put(new Coordonnees(randomIndex, 1), new BilleMultiplicator(new Coordonnees(randomIndex, 1)));
					break;
				case VerticalLaser:
					randomIndex = this.getRandomIndex(elements);
					elements.put(new Coordonnees(randomIndex, 1), new VerticalLaser(new Coordonnees(randomIndex, 1)));
					break;
				case StarDestroyer:
					randomIndex = this.getRandomIndex(elements);
					elements.put(new Coordonnees(randomIndex, 1), new StarDestroyer(new Coordonnees(randomIndex, 1)));
					break;
				case BouncingBall:
					randomIndex = this.getRandomIndex(elements);
					elements.put(new Coordonnees(randomIndex, 1), new BouncingBall(new Coordonnees(randomIndex, 1)));
					break;
			}
		}
		if (this.isThereFreePlace(elements) && this.service.trueOrFalseRandom(this.difficulty.getMalus())) {
			randomIndex = this.getRandomIndex(elements);
			elements.put(new Coordonnees(randomIndex, 1), new BlackHole(new Coordonnees(randomIndex, 1)));
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
		
		if(!element.getName().equals(ElementsList.EmptyElement.name())) {
			res = true;
		}
		
		return res;
	}


	public int getNbBallzDetruits() {
		return nbBallzDetruits;
	}


	public void setNbBallzDetruits(int nbBallzDetruits) {
		this.nbBallzDetruits = nbBallzDetruits;
	}
	
	public void incrementNbBallzDetruits() {
		this.nbBallzDetruits++;
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
	
	public int getNbBilles() {
		return nbBilles;
	}
	
	public void setNbBilles(int nbBilles) {
		this.nbBilles = nbBilles;
	}
	
	public boolean isThereFreePlace(TreeMap<Coordonnees, Elements> elements) {
		boolean res = false;
		int x;
		
		for (x = 0;x < 10;x++) {
			if (!this.thereSomethingHere(elements, new Coordonnees(x, 1))) {
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	private int getRandomIndex(TreeMap<Coordonnees, Elements> elements) {
		List<Integer> freeIndex = new ArrayList<Integer>();
		int x = 0;
		int index;
		
		for (x = 0;x < 10;x++) {
			if (!this.thereSomethingHere(elements, new Coordonnees(x, 1))) {
				freeIndex.add(x);
			}
		}
		
		index = this.service.randomGenerator(0, freeIndex.size() - 1);
		return freeIndex.get(index);
	}
	
	public void clearBillesVerticalAndHorizontalLasersLists() {
		for (Bille bille : this.billes) {
			bille.clearVerticalLaserList();
			bille.clearHorizontalLaserList();
		}
	}
	
	private boolean isBallzOrEmptyElement(Elements element) {
		if (element instanceof Ballz || element instanceof EmptyElement) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addMalusBallz(int nb) {
		int incrementLife = this.turn == 1 ? this.turn : this.turn/2;
		
		for (int i = 0;i < nb; i++) {
			int x = this.service.randomGenerator(0, 9);
			int y = this.service.randomGenerator(1, 7);
			Elements element = this.elements.get(new Coordonnees(x, y));
			while (!this.isBallzOrEmptyElement(element)) {
				x = this.service.randomGenerator(0, 9);
				y = this.service.randomGenerator(1, 7);
				element = this.elements.get(new Coordonnees(x, y));
			}
			if (element instanceof Ballz) {
				((Ballz) element).setLife(((Ballz) element).getLife() + incrementLife);
				((Ballz) element).setMalus();
			} else {
				Ballz ballz = new Ballz(element.getCoordonnees(), incrementLife);
				ballz.setMalus();
				this.elements.put(element.getCoordonnees(), ballz);
			}
		}
		this.refreshView();
	}
	
	public boolean isThereAnyBallzOnPlate() {
		for (int x = 0;x < 10;x++) {
			for (int y = 0;y < 10;y++) {
				if (this.elements.get(new Coordonnees(x, y)) instanceof Ballz) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Bonus getRandomBonus() {
		return BONUS.get(new Random().nextInt(BONUS.size()));
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}
