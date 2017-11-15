package util;

import java.util.Random;

public class Services {
	
	public Services() {}
	
	public int randomGenerator(int start, int end) {
		Random rand = new Random();
		return rand.nextInt((end - start) + 1) + start;
	}
}
