package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

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

}
