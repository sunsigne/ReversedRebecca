package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;

public class Conductor {

	////////// APPLICATION ////////////

	public void startApp() {

		// LOADING OF MINIMAL RESSOURCES
		new BugFinder().run();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new RessourceLoader().loadMinimalRessources();
		Game.getInstance().forceLoop();

		LAYER.LOADING.addObject(new LoadingScreen());
		new Window(Game.getInstance());
		Game.getInstance().start();

		// LOADING OF RESSOURCES
		new RessourceLoader().loadRessources();
		Game.getInstance().forceLoop();

		new World(FilePath.DAVE000);
	}

	public void stopApp() {
		Game.getInstance().stop();
	}

	////////// GAME ////////////

	public void startGame() {
		new TitleScreen(TitleScreen.PLAY);
		LAYER.LOADING.getHandler().clear();
		PresetMousePos.usingPreset = false;
	}

}
