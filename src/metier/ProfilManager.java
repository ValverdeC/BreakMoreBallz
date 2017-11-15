package metier;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import util.CsvFileHelper;

public class ProfilManager {
	private List<Profil> profiles;
	private static String fileName = "src/metier/profils.csv";
	
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
			Profil currentProfile = new Profil(Integer.parseInt(data.get(i)[0]), data.get(i)[1]);
			this.profiles.add(currentProfile);
		}
	}
	
	
	//METHODS
	public void createProfil(String pseudo) {
		int id = this.profiles.get(this.profiles.size()-1).getId()+1;
		Profil tempProfil = new Profil(id, pseudo);
		this.profiles.add(this.profiles.size(), tempProfil);
		
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
	
	public void editProfil(int id, String pseudo) {
		List<Profil> toSaveProfiles = new LinkedList<Profil>();
		for (Profil profil : profiles) {
			if (profil.getId() != id) {
				toSaveProfiles.add(profil);
			}
			else{
				profil.setPseudo(pseudo);
				toSaveProfiles.add(profil);
			}
		}
		
		this.profiles = toSaveProfiles;
		
		saveToCsv();
	}

	

	//LINK TO MODEL
	public List<String[]>gatherData() {		
		return CsvFileHelper.readFile(this.getFileName());
	}
	
	
	private void saveToCsv() {
		CsvFileHelper.writeToFile(this.profiles, this.getFileName());
	}

	
	//MAIN
	public static void main(String[] args) {
		int choix;
		
		do {
			ProfilManager manager = new ProfilManager();
			List<Profil> allProfiles = manager.getProfiles();
			
			System.out.println("list all profiles \n"+
								"_______________\n" +
								"| ID | PSEUDO |\n" +
								"_______________"
								);
			
			for (Iterator iterator = allProfiles.iterator(); iterator.hasNext();) {
				Profil profil = (Profil) iterator.next();
				System.out.println("| " + profil.getId() + " | " + profil.getPseudo() + " |");
			}
			
			System.out.println("\n");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("(1) ajouter, (2) editer, (3) supprimer, (4) quitter : ");
			choix = sc.nextInt();
			
			if(choix == 1){
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Veuillez saisir un pseudo : ");
				String pseudo = sc2.nextLine();
				manager.createProfil(pseudo);
			}
			
			if(choix == 2){
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Veuillez saisir l'id a editer : ");
				int id = sc.nextInt();
				System.out.print("Veuillez saisir le nouveau pseudo : ");
				String pseudo = sc2.nextLine();
				manager.editProfil(id, pseudo);
			}
			
			if(choix == 3){
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Veuillez saisir l'id a delete : ");
				int id = sc.nextInt();
				manager.deleteProfil(id);
			}
			
		} while (choix != 4);
		System.out.println("END");
	}
}