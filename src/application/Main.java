package application;
	
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.ViewComponent;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.Node;

public class Main extends GameApplication {
	
	Menu menu = new Menu();
	ProfileManagerGUI profile = new ProfileManagerGUI();
	PlateauGUI plateau = new PlateauGUI(this);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("BreakMoreBallz");
        settings.setVersion("beta-0.1");
        settings.setWidth(400);
        settings.setHeight(800);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setCloseConfirmation(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
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
