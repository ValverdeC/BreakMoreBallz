package metier;

import java.util.LinkedList;
import java.util.List;

import shared.Constants;

import java.util.Iterator;
import java.util.LinkedHashMap;

import util.CsvFileHelper;

public class ProfilManager {
	private List<Profil> profiles;
	private static String fileName = Constants.CSV_PATH;
	
	//GETTER AND SETTERS
	public String getFileName() {
		return fileName;
	}
	
	public List<Profil> getProfiles() {
		return profiles;
	}
	
	
	//CONSTRUCTOR
	public ProfilManager() {
		this.profiles = new LinkedList<Profil>();
		List<String[]> data = gatherData();
	
		for (int i = 0; i < data.size(); i++) {
			Profil currentProfile = new Profil(Integer.parseInt(data.get(i)[0]), data.get(i)[1],Integer.parseInt(data.get(i)[2]),data.get(i)[3]);
			this.profiles.add(currentProfile);
		}
	}
	
	
	//METHODS
	public Profil createProfil(String pseudo) {
		int id;
		if(this.profiles.size() == 0){
			id = 1;
		}
		else {
			id = this.profiles.get(this.profiles.size()-1).getId()+1;
		}
		Profil tempProfil = new Profil(id, pseudo);
		this.profiles.add(this.profiles.size(), tempProfil);
		
		saveToCsv();
		
		return tempProfil;
	}
	
	public void saveAllProfiles(LinkedList<Profil> profiles2) {
		this.profiles = profiles2;
		saveToCsv();
	}
	
	public void deleteProfil(int id) {
		List<Profil> toSaveProfiles = new LinkedList<Profil>();
		for (Profil profil : profiles) {
			if (profil.getId() != id) {
				toSaveProfiles.add(profil);
			}
		}
		
		if (toSaveProfiles.equals(profiles)) {
			System.out.println("ERROR: ID NOT FOUND");
		}
		else {
			this.profiles = toSaveProfiles;
		}
			
		saveToCsv();
	}
	
	public void editProfil(int id, String pseudo, String launcher) {
		List<Profil> toSaveProfiles = new LinkedList<Profil>();
		for (Profil profil : profiles) {
			if (profil.getId() != id) {
				toSaveProfiles.add(profil);
			}
			else{
				profil.setLauncher(launcher);
				profil.setPseudo(pseudo);
				toSaveProfiles.add(profil);
			}
		}
		
		this.profiles = toSaveProfiles;
		
		saveToCsv();
	}
	
	public void saveHighScore(int id, int highScore) {
		List<Profil> toSaveProfiles = new LinkedList<Profil>();
		for (Profil profil : profiles) {
			if (profil.getId() != id) {
				toSaveProfiles.add(profil);
			}
			else{
				profil.setHighScore(highScore);
				toSaveProfiles.add(profil);
			}
		}
		
		this.profiles = toSaveProfiles;
		
		saveToCsv();
	}
	
	public List<String> getListPseudo() {
		List<String> pseudoList = new LinkedList<String>();
		
		Iterator<Profil> li = this.profiles.iterator();
		
		while(li.hasNext()){
			pseudoList.add(li.next().getPseudo());
		}

		return pseudoList;
	}
	
	public LinkedHashMap<String, Profil> getMapProfils() {
		LinkedHashMap<String, Profil> mapProfils = new LinkedHashMap<>();
		
		Iterator<Profil> li = this.profiles.iterator();
		
		Profil currentProfil;
			
		while(li.hasNext()){
			currentProfil=li.next();
			mapProfils.put(currentProfil.getPseudo(), currentProfil);
		}
		
		return mapProfils;
	}

	//LINK TO MODEL
	public List<String[]>gatherData() {		
		return CsvFileHelper.readFile(this.getFileName());
	}
	
	
	private void saveToCsv() {
		CsvFileHelper.writeToFile(this.profiles, this.getFileName());
	}

	
	//MAIN
//	public static void main(String[] args) {
//		int choix;
//		
//		do {
//			ProfilManager manager = new ProfilManager();
//			List<Profil> allProfiles = manager.getProfiles();
//			
//			System.out.println("list all profiles \n"+
//								"_______________\n" +
//								"| ID | PSEUDO |\n" +
//								"_______________"
//								);
//			
//			for (Iterator iterator = allProfiles.iterator(); iterator.hasNext();) {
//				Profil profil = (Profil) iterator.next();
//				System.out.println("| " + profil.getId() + " | " + profil.getPseudo() + " |");
//			}
//			
//			System.out.println("\n");
//			
//			Scanner sc = new Scanner(System.in);
//			System.out.print("(1) ajouter, (2) editer, (3) supprimer, (4) quitter : ");
//			choix = sc.nextInt();
//			
//			if(choix == 1){
//				Scanner sc2 = new Scanner(System.in);
//				System.out.print("Veuillez saisir un pseudo : ");
//				String pseudo = sc2.nextLine();
//				manager.createProfil(pseudo);
//			}
//			
//			if(choix == 2){
//				Scanner sc2 = new Scanner(System.in);
//				System.out.print("Veuillez saisir l'id a editer : ");
//				int id = sc.nextInt();
//				System.out.print("Veuillez saisir le nouveau pseudo : ");
//				String pseudo = sc2.nextLine();
//				manager.editProfil(id, pseudo);
//			}
//			
//			if(choix == 3){
//				Scanner sc2 = new Scanner(System.in);
//				System.out.print("Veuillez saisir l'id a delete : ");
//				int id = sc.nextInt();
//				manager.deleteProfil(id);
//			}
//			
//		} while (choix != 4);
//		System.out.println("END");
//	}
}