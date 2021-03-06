package util;
/** 
 * Classe utilitaire permettant d'ouvrir, �diter et sauvegarder des fichier csv
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import metier.Profil;

public class CsvFileHelper {
	
	public static void writeToFile( List<Profil> profiles, String fileName){
		File csvFile = new File(fileName);
		
		if (!csvFile.exists()) {
			try {
				csvFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				PrintStream l_out = new PrintStream(new FileOutputStream(fileName));
				
				for (Profil profil : profiles) {
					l_out.print(profil.toString());
				}
		
				l_out.flush(); 
				l_out.close(); 
				l_out=null;
			} catch (Exception e) {
				throw new IllegalArgumentException("Error while writing into the file");

			}
		}
	}
	
	public static File getResource(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Le nom du fichier ne peut pas être null");
		}
	
		File file = new File(fileName);
		
		return file;
	}

	public static List<String[]> readFile(String fileName) {
		File file = new File(fileName);
		
		String separator = ",";

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<String[]> result = new ArrayList<String[]>();

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result.add(line.split(separator));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return result;
	}
}