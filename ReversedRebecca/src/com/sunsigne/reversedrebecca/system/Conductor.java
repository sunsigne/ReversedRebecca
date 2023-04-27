package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Conductor {

	////////// START & STOP ////////////

	public void startApp() {

		LAYER.LOADING.addObject(new LoadingScreen());
		new BugFinder().run();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new Window(Game.getInstance());
		Game.getInstance().start();

		// LOADING OF MINIMAL RESSOURCES
		new RessourceLoader().loadMinimalRessources();
		Game.getInstance().forceLoop();

		// LOADING OF RESSOURCES
		new RessourceLoader().loadRessources();
		Game.getInstance().forceLoop();

		new TitleScreen(TitleScreen.PLAY);
		LAYER.LOADING.getHandler().clear();
		PresetMousePos.usingPreset = false;
	}

	public void stopApp() {
		Game.getInstance().stop();
	}

}
