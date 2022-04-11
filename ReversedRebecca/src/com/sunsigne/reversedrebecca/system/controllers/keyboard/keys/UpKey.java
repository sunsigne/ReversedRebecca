package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

public class UpKey implements DirectionalKey {

	////////// KEY ////////////

	private static int key;

	public static int getKey() {
		if (key == 0)
			key = new UpKey().loadKey();
		return key;
	}

	@Override
	public void refreshKey() {
		key = 0;
		updateKeyMoveKeys();
	}

	@Override
	public String getValueToRead() {
		return "Up";
	}

}
