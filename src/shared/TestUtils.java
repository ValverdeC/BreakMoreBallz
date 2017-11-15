package shared;

import java.util.ArrayList;
import java.util.List;

import metier.Ballz;
import util.Coordonnees;

public class TestUtils {
	private List<Ballz> ballzList = new ArrayList<Ballz>();
	
	public TestUtils() {
		for (int i = 0; i < 10; i++) {
			ballzList.add(new Ballz(new Coordonnees(i, i+2), 1));			
		}
	}
	
	public List<Ballz> getBallzList() {
		return ballzList;
	}
}
