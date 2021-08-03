package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.game.Test;
import com.sunsigne.reversedrebecca.system.controllers.GameKeyboardInput;
import com.sunsigne.reversedrebecca.system.handler.HandlerObject;

public class Conductor {

	////////// STATE ////////////

	private static STATE state;
	
	public static STATE getState() {
		return state;
	}
	
	public static void setState(STATE state) {
		Conductor.state = state;
	}
	
	//////////DEBUG MOD ////////////
	
	private static final DebugMode DEBUG_MODE = new DebugMode();
	
	public static DebugMode getDebugMode() {
		return DEBUG_MODE;
	}

	////////// START & STOP ////////////
	
	protected static void start() {
		
		setState(STATE.LOADING);
			
		new Window(Game.getInstance());
		
		
		Game.getInstance().addKeyListener(new GameKeyboardInput());
		// this line ask for keyboard priority when game starts.
		Game.getInstance().requestFocus();
		
		// WARNING !!!!!!! cette fonction doit être utilisé plus tôt également, pour lancer l'écran de chargement
		// (les ticks ne se lance qu'après l'avoir invoqué)
		Game.getInstance().start();
		setState(STATE.READY);
		
		HandlerObject.getInstance().addObject(new Test());
	}

	public static void stop() {
		System.exit(1);
	}

}
