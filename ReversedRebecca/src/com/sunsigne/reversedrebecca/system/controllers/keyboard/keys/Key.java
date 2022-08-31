package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Key {

	////////// KEY ////////////

	String file = "options.csv";
	boolean userData = true;

	void refreshKey();

	String getValueToRead();

	public default String getRegisteredKey() {
		String key = new FileTask().read(userData, getValueToRead(), file);
		int key_code = Integer.parseInt(key.split(":")[1]);
		var analyzer = new KeyAnalyzer(key_code);
		return analyzer.getKeyText();
	}

	public default int loadKey() {
		String key = new FileTask().read(userData, getValueToRead(), file);
		return Integer.decode(key.split(":")[1]);
	}

	public default void registerKey(String newKeyText, int newKeyCode) {
		new FileTask().write(getValueToRead(), file, newKeyText.toUpperCase() + ":" + newKeyCode);
		refreshKey();
	}

}
