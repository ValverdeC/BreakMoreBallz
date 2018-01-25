package metier;

/** Classe metier contenant les informations des profils de joueurs */
public class Profil {
	
	private String pseudo;
	private int id;
	private int highScore;
	private String launcher;
	
	//Create a new profile
	public Profil(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
		this.highScore = 0;
		this.launcher = "commander";
	}
	
	public Profil(int id, String pseudo, int highScore, String launcher) {
		this.id = id;
		this.pseudo = pseudo;
		this.highScore = highScore;
//		this.background = background;
		this.launcher = launcher;
	}

	public String getLauncher() {
		return launcher;
	}

	public void setLauncher(String launcher) {
		this.launcher = launcher;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id+","+pseudo+","+highScore+","+launcher+"\n";
	}

}
