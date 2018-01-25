package metier;

import util.Difficultes;

public class ParametresPlateau {
	private double dimensionX;
	private double dimensionY;
	private String imageBackground;
	private String music;
	private Difficultes difficulte;
	private ProfilManager profilManager;
	private Profil tempProfil1;
	private Profil tempProfil2;
	
	public ParametresPlateau() {
		super();
		this.difficulte = Difficultes.Normal;
	}
	
	public double getDimensionX() {
		return dimensionX;
	}
	public void setDimensionX(double dimensionX) {
		this.dimensionX = dimensionX;
	}
	public double getDimensionY() {
		return dimensionY;
	}
	public void setDimensionY(double dimensionY) {
		this.dimensionY = dimensionY;
	}
	public String getImageBackground() {
		return imageBackground;
	}
	public void setImageBackground(String imageBackground) {
		this.imageBackground = imageBackground;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public Difficultes getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(Difficultes difficulte) {
		this.difficulte = difficulte;
	}
	public Profil getTempProfil1() {
		return tempProfil1;
	}

	public void setTempProfil1(Profil tempProfil1) {
		this.tempProfil1 = tempProfil1;
	}

	public Profil getTempProfil2() {
		return tempProfil2;
	}

	public void setTempProfil2(Profil tempProfil2) {
		this.tempProfil2 = tempProfil2;
	}

	public ProfilManager getProfilManager() {
		return profilManager;
	}
	public void setProfilManager(ProfilManager profilManager) {
		this.profilManager = profilManager;
	}

	
	//Check if all fields are correctly initialized
	public boolean checkParametres(){
		boolean	result = false;
		
		if(this.tempProfil1 != null){
			if(this.tempProfil2 != null){
				result = true;				
			}
		}
		
		return result;
	}
}


