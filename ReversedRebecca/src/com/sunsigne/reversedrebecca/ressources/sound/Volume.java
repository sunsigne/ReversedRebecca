package com.sunsigne.reversedrebecca.ressources.sound;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Volume {

	////////// VOLUME ////////////

	String file = "userdata/options.csv";

	void refreshVolume();

	String getValueToRead();

	public default String getRegisteredVolume() {
		return new FileTask().read(getValueToRead(), file);
	}

	public default double loadVolume() {
		return Double.parseDouble(getRegisteredVolume());
	}

	public default void registerVolume(String newVolume) {
		new FileTask().write(getValueToRead(), file, newVolume);
		refreshVolume();
	}

}
