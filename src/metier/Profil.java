package metier;

public class Profil {
	
	private String pseudo;
	private String optionsPath;
	private int id;
	private int highScore;
	
	//Create a new profile
	public Profil(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
		this.highScore = 0;
	}
	
	public Profil(int id, String pseudo, int highScore) {
		this.id = id;
		this.pseudo = pseudo;
		this.highScore = highScore;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
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
		return id+","+pseudo+","+highScore+"\n";
	}

}
