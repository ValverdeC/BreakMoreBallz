package util;

import java.util.Random;

public class Services {
	
	public Services() {}
	
	public int randomGenerator(int start, int end) {
		Random rand = new Random();
		return rand.nextInt((end - start) + 1) + start;
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
