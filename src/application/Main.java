package application;
	
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.ViewComponent;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.Node;
import metier.Difficulty;
import metier.Profil;
import shared.Constants;

public class Main extends GameApplication {
	
	private  Clip audioClip;  //le son cr�� depuis l'url

	Menu menu = new Menu();
	ProfileManagerGUI profile = new ProfileManagerGUI();
	PlateauGUI plateau;
	ParametresPlateauUi param = new ParametresPlateauUi();
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Methode d'initialisation de l'application
	 */
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("BreakMoreBallz");
        settings.setVersion("1.0.0");
        settings.setWidth(500);
        settings.setHeight(800);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setCloseConfirmation(false);
        settings.setApplicationMode(ApplicationMode.RELEASE);
        
        // Chargement du fichier de son
        try {
			audioClip = AudioSystem.getClip();
			audioClip.open(AudioSystem.getAudioInputStream(new File("src/application/resistance.wav")));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Lancement de la musique en mode boucle
        //audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        
        File gameDir = new File(Constants.DIRECTORY_PATH);
        
        if (gameDir.exists()) {
        	System.out.println(gameDir + " already exists");
        } else if (gameDir.mkdirs()) {
        	System.out.println(gameDir + " was created");
        } else {
        	System.out.println(gameDir + " was not created");
        }
        
        File profiles = new File(Constants.CSV_PATH);
        if(!profiles.exists() && !profiles.isDirectory()) { 
            try {
            	profiles.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        
	}
	
	/** Initialisation du menu principal (choix de la vue Menu) */
	@Override
    protected void initGame() {		
		setMenuView();
    }
	
	/** Permet de switcher l'affichage sur la vue du menu */
	protected void setMenuView() {
		setView(menu);
	}
	/** Permet de switcher l'affichage sur la vue des profils */
	protected void setProfileView() {
		setView(profile);
	}
	/** Permet de switcher l'affichage sur la vue du jeu */
	protected void setParamView() {
		setView(param);
	}
	
	protected void setPlayView(Main app, Profil profil1, Profil profil2, Difficulty difficulty) {
		plateau = new PlateauGUI(app, profil1, profil2, difficulty);
		setView(plateau);
	}
	/** Permet de switcher l'affichage sur la vue choisie */
	protected void setView(Node view) {		
		ViewComponent viewComponent = new ViewComponent();
		viewComponent.setView(view);
		
		Entity entity  = Entities.makeScreenBounds(0);
		entity.addComponent(viewComponent);
		FXGL.getApp().getGameWorld().addEntity(entity);
	}
}
