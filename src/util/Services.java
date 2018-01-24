package util;

import java.util.Random;

public class Services {
	
	public Services() {}
	
	public int randomGenerator(int start, int end) {
		Random rand = new Random();
		return rand.nextInt((end - start) + 1) + start;
	}
	
	public int randomGeneratorWithChance(int start, int end, int chance) {
		Random rand = new Random();
		int nb = rand.nextInt((end - start) + 1) + start;
		
		if (nb >= 7 && this.trueOrFalseRandom(chance)) {
			return nb;
		} else if (nb < 7) {
			return nb;
		} else {
			return this.randomGeneratorWithChance(start, end, chance);
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
