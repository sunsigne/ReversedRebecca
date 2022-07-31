package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Conductor {

	////////// START & STOP ////////////

	public void startApp() {

		LAYER.LOADING.addObject(new LoadingScreen());
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new Window(Game.getInstance());
		Game.getInstance().start();
//		new DualChecker();

		// LOADING OF MINIMAL RESSOURCES
		new RessourceLoader().loadMinimalRessources();
		Game.getInstance().forceLoop();

		// LOADING OF RESSOURCES
		new RessourceLoader().loadRessources();
		Game.getInstance().forceLoop();

		new TitleScreen();
		LAYER.LOADING.getHandler().clear();
	}

	public void stopApp() {
		System.exit(1);
	}

}
