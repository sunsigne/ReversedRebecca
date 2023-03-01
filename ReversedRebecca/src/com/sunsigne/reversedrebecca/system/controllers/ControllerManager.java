package com.sunsigne.reversedrebecca.system.controllers;

import java.awt.Cursor;

import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ControllerManager {

	////////// SIGNELTON ////////////

	private static ControllerManager instance;

	public static ControllerManager getInstance() {
		if (instance == null)
			instance = new ControllerManager();
		return instance;
	}

	////////// GAMEPAD ////////////

	private boolean usingGamepad;

	public boolean isUsingGamepad() {
		return usingGamepad;
	}

	public void setUsingGamepad(boolean usingGamepad) {
		if (this.usingGamepad != usingGamepad)
			updateGamepadDiplay(usingGamepad);

		this.usingGamepad = usingGamepad;
	}

	////////// UPDATE ////////////

	private static Cursor cursor;

	private static void updateGamepadDiplay(boolean active) {
		if (active)
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
