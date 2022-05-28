package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Key {

	////////// KEY ////////////

	String file = "userdata/options.csv";

	void refreshKey();

	String getValueToRead();

	public default String getRegisteredKey() {
		String key = new FileTask().read(getValueToRead(), file);
		return key.split(":")[0];
	}

	public default int loadKey() {
		String key = new FileTask().read(getValueToRead(), file);
		return Integer.decode(key.split(":")[1]);
	}

	public default void registerKey(String newKeyText, int newKeyCode) {
		new FileTask().write(getValueToRead(), file, newKeyText.toUpperCase() + ":" + newKeyCode);
		refreshKey();
	}

}
