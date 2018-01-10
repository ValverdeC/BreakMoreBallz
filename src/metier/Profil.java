package metier;

public class Profil {
	
	private String pseudo;
	private int id;
	private int highScore;
	private String background;
	private String launcher;
	
	//Create a new profile
	public Profil(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
		this.highScore = 0;
		this.background = "default";
		this.launcher = "default";
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

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
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
