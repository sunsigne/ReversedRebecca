package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Key {

	////////// KEY ////////////

	String file = "userdata/options.csv";

	void refreshKey();
	
	String getValueToRead();

	public default String getRegisteredKey() {
		return new FileTask().read(getValueToRead(), file);
	}

	public default int loadKey() {
		return KeyEvent.getExtendedKeyCodeForChar(getRegisteredKey().charAt(0));
	}

	public default void registerKey(String newKey) {
		new FileTask().write(getValueToRead(), file, newKey);
		refreshKey();
	}

}
