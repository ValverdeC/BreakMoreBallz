package metier;

public class Bille {
	
	private String apparence = "default";

	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}

	@Override
	public String toString() {
		return "Bille [apparence=" + apparence + "]";
	}
	

}
