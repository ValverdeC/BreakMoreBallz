package metier;

public class Lanceur {

	private String apparence = "default";

	public String getApparence() {
		return apparence;
	}

	public void setApparence(String apparence) {
		this.apparence = apparence;
	}

	@Override
	public String toString() {
		return "Lanceur [apparence=" + apparence + "]";
	}
	
	
}
