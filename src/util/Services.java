package util;

import java.util.Random;
import java.util.TreeMap;

import metier.Ballz;
import metier.Elements;

public class Services {
	
	public Services() {}
	
	public int randomGenerator(int start, int end) {
		Random rand = new Random();
		return rand.nextInt((end - start) + 1) + start;
	}
	
	public boolean thereSomethingHere(TreeMap<Coordonnees, Elements> elements, Coordonnees coord) {
		boolean res = false;
		Elements element = elements.get(coord);
		
		if(element instanceof Ballz) {
			res = true;
		}
		
		return res;
	}

}
