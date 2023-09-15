package com.sunsigne.reversedrebecca.system.controllers;

import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadAdapter;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadManager;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ControllerManager {

	////////// SIGNELTON ////////////

	private ControllerManager() {

	}

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
		if(usingGamepad == false)
			GamepadController.pressing.clear();
		
		if (this.usingGamepad != usingGamepad)
			updateGamepadDiplay(usingGamepad);

		this.usingGamepad = usingGamepad;
	}

	////////// UPDATE ////////////

	private static void updateGamepadDiplay(boolean active) {
		if (active)
			activeGamepadDisplay();
		else
			desactiveGamepadDisplay();
	}

	private static void activeGamepadDisplay() {
		Game.getInstance().setCursor(CURSOR_TYPE.NULL.getCursor());
		resetMousePresetings();
	}

	private static void resetMousePresetings() {
		var list = GamepadManager.getList();
		for (GamepadAdapter tempAdapter : list.getList()) {

			if (tempAdapter instanceof GamepadController == false)
				continue;
			var tempEvent = ((GamepadController) tempAdapter).getGamepadEvent();

			if (tempEvent instanceof MousePreseting == false)
				continue;
			var tempPreset = (MousePreseting) tempEvent;

			tempPreset.setPreset(MousePreseting.NULL);
		}
	}

	private static void desactiveGamepadDisplay() {
		new GameCursor().refreshCursor();
	}

}
