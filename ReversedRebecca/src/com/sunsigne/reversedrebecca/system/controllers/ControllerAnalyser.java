package com.sunsigne.reversedrebecca.system.controllers;

import java.awt.Cursor;

import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ControllerAnalyser {

	private static boolean usingGamepad;
	
	public static void setUsingGamepad(boolean usingGamepad) {
		if(ControllerAnalyser.usingGamepad != usingGamepad)
			updateGamepadDiplay(usingGamepad);
		
		ControllerAnalyser.usingGamepad = usingGamepad;
	}

	private static Cursor cursor;

	private static void updateGamepadDiplay(boolean active) {
		if(active)
			activeGamepadDisplay();
		else
			desactiveGamepadDisplay();
	}

	private static void activeGamepadDisplay() {
		cursor = Game.getInstance().getCursor();
		new GameCursor().setCursor(CURSOR_TYPE.NULL);
	}
	
	private static void desactiveGamepadDisplay() {
		Game.getInstance().setCursor(cursor);		
	}

}
