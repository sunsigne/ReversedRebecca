package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;

public class DialogueKey implements Key {

	////////// KEY ////////////

	private static int key;

	public static int getKey() {
		if (key == 0)
			key = new DialogueKey().loadKey();
		return key;
	}

	@Override
	public void refreshKey() {
		key = 0;
	}

	@Override
	public String getValueToRead() {
		return "Dialogue";
	}

	////////// GAMEPAD ////////////

	public static int getGamepadKey() {
		return ButtonEvent.B;
	}

}
