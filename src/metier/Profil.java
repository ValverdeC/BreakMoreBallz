package metier;

public class Profil {
	
	private String pseudo;
	private String optionsPath;
	private int id;
	
	public Profil(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getOptions() {
		return optionsPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOptions(String options) {
		this.optionsPath = options;
	}

	@Override
	public String toString() {
		return id+","+pseudo+"\n";
	}

}
