package com.sunsigne.reversedrebecca.ressources.sound;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Volume {

	////////// VOLUME ////////////

	String file = "options.csv";
	boolean userData = true;

	void refreshVolume();

	String getValueToRead();

	public default String getRegisteredVolume() {
		String default_volume = "1";

		if (new FileTask().doesExist(userData, file) == false)
			return default_volume;

		String volume = new FileTask().read(userData, getValueToRead(), file);

		if (volume.isEmpty() == false)
			return volume;

		return default_volume;
	}

	public default double loadVolume() {
		return Double.parseDouble(getRegisteredVolume());
	}

	public default void registerVolume(double newVolume) {
		String value = String.valueOf(Math.round(getFormatedVolume(newVolume) * 100.0) / 100.0);
		new FileTask().write(getValueToRead(), file, value);
		refreshVolume();
	}

	private double getFormatedVolume(double volume) {
		if (volume > 2.0)
			return 2.0;
		if (volume < 0)
			return 0;
		return volume;
	}

}
