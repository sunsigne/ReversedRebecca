package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.game.Test;
import com.sunsigne.reversedrebecca.system.controllers.GameKeyboardInput;
import com.sunsigne.reversedrebecca.system.main.Game;

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
	
	private static boolean running;
	
	public static void startApp() {
		if (running)
			return;
		
		setState(STATE.LOADING);
			
		new Window(Game.getInstance());
		
		
		Game.getInstance().addKeyListener(new GameKeyboardInput());
		// this line ask for keyboard priority when game starts.
		Game.getInstance().requestFocus();
		
		// WARNING !!!!!!! cette fonction doit �tre utilis� plus t�t �galement, pour lancer l'�cran de chargement
		// (les ticks ne se lance qu'apr�s l'avoir invoqu�)
		Game.getInstance().start();
		setState(STATE.READY);
		
		new Test().start();
	}

	public static void stopApp() {
		System.exit(1);
	}

}
