package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.object.Foe;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.gui.GUIDebug;
import com.sunsigne.reversedrebecca.object.gui.GUIHealth;
import com.sunsigne.reversedrebecca.ressources.HandlerRessources;
import com.sunsigne.reversedrebecca.ressources.images.ImageBank;
import com.sunsigne.reversedrebecca.ressources.images.SheetBank;
import com.sunsigne.reversedrebecca.system.controllers.GameKeyboardInput;
import com.sunsigne.reversedrebecca.system.main.NeoGame;
import com.sunsigne.reversedrebecca.util.ForceInit;
import com.sunsigne.reversedrebecca.util.LoadingScreen;

public class Conductor {

	public static final DebugMode DEBUG_MODE = new DebugMode();
	public static final GameKeyboardInput KEYBOARD = new GameKeyboardInput();
//	private static final GameCursor GAMECURSOR = new GameCursor();

	////////// STATE ////////////

	private static STATE state;
	private static final LoadingScreen loadingScreen = new LoadingScreen();

	public static STATE getState() {
		return state;
	}

	public static void setState(STATE state) {
		Conductor.state = state;
		loading(state);
	}

	private static void loading(STATE state) {
		if (state == STATE.LOADING)
			loadingScreen.start();
		else
			loadingScreen.stop();
	}

	////////// START & STOP ////////////

	private static boolean running;

	public static void startApp() {
		if (running)
			return;

		running = true;
		setState(STATE.LOADING);

		new Window(NeoGame.getInstance());
		NeoGame.getInstance().start();

		// DUAL CHECKING
//		new DualChecker().start();
		NeoGame.getInstance().forceLoop();

		// LOADING OF MINIMAL RESSOURCES
		forceInit();
		HandlerRessources.getInstance().loadMinimalRessources();
		NeoGame.getInstance().addKeyListener(KEYBOARD);
		NeoGame.getInstance().requestFocus();
//		new GameCursor().startTick();

		// LOADING OF RESSOURCES
		HandlerRessources.getInstance().loadRessources();
		NeoGame.getInstance().forceLoop();

		loadLevel();

	}

	public static void stopApp() {
//		new DualChecker().stop();
		System.exit(1);
	}

	////////// RESSOURCES ////////////

	private static void forceInit() {

		new ForceInit().forceInit(SheetBank.class);
		new ForceInit().forceInit(ImageBank.class);
	}

	////////// LOAD LEVEL ////////////

	private static void loadLevel() {

		Player.get().start();
		new GUIHealth().start();
		new GUIDebug().start();

		new Wall(500, 300).start();
		new Wall(900, 600).start();

		new Foe(1500, 100).start();
		new Foe(1500, 500).start();

		NeoGame.getInstance().forceLoop();
		setState(STATE.READY);

	}

}
