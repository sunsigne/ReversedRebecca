package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.system.controllers.GameKeyboardInput;
import com.sunsigne.reversedrebecca.system.main.Game;

public class Conductor {

	public static final DebugMode DEBUG_MODE = new DebugMode();
	public static final GameKeyboardInput KEYBOARD = new GameKeyboardInput();
	
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

		new Window(Game.getInstance());

		Game.getInstance().start();
		
		Game.getInstance().addKeyListener(KEYBOARD);
		Game.getInstance().requestFocus();

		loadLevel();

	}

	public static void stopApp() {
		System.exit(1);
	}

	////////// LOAD LEVEL ////////////

	private static void loadLevel() {

		Player.get().start();
		new Wall(500, 300).start();
		new Wall(900, 600).start();
		
		Game.getInstance().forceLoop();
		setState(STATE.READY);

	}

}
