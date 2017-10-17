package metier;

public class Profil {
	
	private String pseudo;
	private String optionsPath;
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getOptions() {
		return optionsPath;
	}

	public void setOptions(String options) {
		this.optionsPath = options;
	}

	@Override
	public String toString() {
		return "Profil [pseudo=" + pseudo + "]";
	}

}
