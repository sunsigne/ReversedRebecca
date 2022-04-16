package com.sunsigne.reversedrebecca.ressources.sound;

public class SoundVolume implements Volume {

	////////// VOLUME ////////////

	private static double volume = -1;

	public static double getVolume() {
		if (volume < 0)
			volume = new SoundVolume().loadVolume();
		return volume;
	}

	@Override
	public void refreshVolume() {
		volume = -1;
	}

	@Override
	public String getValueToRead() {
		return "Sound";
	}

}
