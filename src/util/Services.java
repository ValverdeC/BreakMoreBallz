package util;

import java.util.Random;

public class Services {
	
	public Services() {}
	
	public int randomGenerator(int start, int end) {
		Random rand = new Random();
		return rand.nextInt((end - start) + 1) + start;
	}
	
	public int randomGeneratorWithChance(int start, int end, int chance, int limit) {
		Random rand = new Random();
		int nb = rand.nextInt((end - start) + 1) + start;
		
		if (nb >= limit && this.trueOrFalseRandom(chance)) {
			// Pas de chance on garde ce nombre
			return nb;
		} else if (nb < limit) {
			// Le nombre était en dessous de la limite
			return nb;
		} else {
			// Tu as de la chance, le nombre au dessus de la limite est pas gardé, on retire un nombre
			return this.randomGeneratorWithChance(start, end, chance, limit);
		}
	}
	
	public boolean trueOrFalseRandom(int percentage) {
		Random rand = new Random();
		int draw =  rand.nextInt(100);
		if (draw > percentage) {
			return false;
		} else {
			return true;
		}
	}
}
