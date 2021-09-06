package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.object.Foe;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.gui.GUIDebug;
import com.sunsigne.reversedrebecca.object.gui.GUIHealth;
import com.sunsigne.reversedrebecca.system.controllers.GameKeyboardInput;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.main.NeoGame;
import com.sunsigne.reversedrebecca.util.Camera;

public class Conductor {

	public static final DebugMode DEBUG_MODE = new DebugMode();
	public static final GameKeyboardInput KEYBOARD = new GameKeyboardInput();
	private static final GameCursor GAMECURSOR = new GameCursor();
	
	////////// STATE ////////////

	private static STATE state;

	public static STATE getState() {
		return state;
	}

	public static void setState(STATE state) {
		Conductor.state = state;
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
		
		NeoGame.getInstance().addKeyListener(KEYBOARD);
		NeoGame.getInstance().requestFocus();

		new GameCursor().startTick();
		
		loadLevel();

	}

	public static void stopApp() {
		System.exit(1);
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
