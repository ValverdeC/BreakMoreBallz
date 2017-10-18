package application;
	
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.component.ViewComponent;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.Node;

public class Main extends GameApplication {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("BreakMoreBallz");
        settings.setVersion("beta-0.1");
        settings.setWidth(400);
        settings.setHeight(700);
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
		Menu menu = new Menu();
		setView(menu);
	}
	
	protected void setProfileView() {
		ProfileManagerGUI profileGUI = new ProfileManagerGUI();
		setView(profileGUI);
	}
	
	protected void setView(Node view) {		
		ViewComponent viewComponent = new ViewComponent();
		viewComponent.setView(view);
		
		Entity entity  = Entities.makeScreenBounds(0);
		entity.addComponent(viewComponent);
		FXGL.getApp().getGameWorld().addEntity(entity);
	}
}
