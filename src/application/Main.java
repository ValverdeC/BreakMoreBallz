package application;
	
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.ViewComponent;
import com.almasb.fxgl.settings.GameSettings;

	
import javafx.scene.Node;

public class Main extends GameApplication {
	
	private  Clip audioClip;  //le son cr�� depuis l'url

	Menu menu = new Menu();
	ProfileManagerGUI profile = new ProfileManagerGUI();
	PlateauGUI plateau = new PlateauGUI(this);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("BreakMoreBallz");
        settings.setVersion("beta-0.2");
        settings.setWidth(450);
        settings.setHeight(800);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setCloseConfirmation(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        
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
        
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        
	}
	
	@Override
    protected void initGame() {		
		setMenuView();
    }
	
	protected void setMenuView() {
		setView(menu);
	}
	
	protected void setProfileView() {
		setView(profile);
	}
	
	protected void setPlayView() {
		setView(plateau);
	}
	
	protected void setView(Node view) {		
		ViewComponent viewComponent = new ViewComponent();
		viewComponent.setView(view);
		
		Entity entity  = Entities.makeScreenBounds(0);
		entity.addComponent(viewComponent);
		FXGL.getApp().getGameWorld().addEntity(entity);
	}
}
