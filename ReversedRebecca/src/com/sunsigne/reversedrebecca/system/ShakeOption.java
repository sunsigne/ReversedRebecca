package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class ShakeOption {

	////////// SHAKE OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static SHAKE_TYPE type;

	public static SHAKE_TYPE getType() {
		if (type == null)
			type = new ShakeOption().getRegisteredType();
		return type;
	}

	public static void setType(SHAKE_TYPE type) {
		ShakeOption.type = type;
	}

	private SHAKE_TYPE getRegisteredType() {
		String registeredType = new FileTask().read(userData, getValueToRead(), file);
		for (SHAKE_TYPE tempType : SHAKE_TYPE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return SHAKE_TYPE.ON;
	}

	private static String getValueToRead() {
		return "Shake";
	}

	public void registerType(SHAKE_TYPE shakeType) {
		new FileTask().write(getValueToRead(), file, shakeType.getName());
		type = null;
	}

	public void forceShake() {
		if (getType() == SHAKE_TYPE.OFF)
			return;

		new CameraShaker().shaking(5);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_small");
	}

	////////// SHAKE TYPE ////////////

	public enum SHAKE_TYPE {
		ON("on"), OFF("off");

		SHAKE_TYPE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public SHAKE_TYPE getPrevious() {
			switch (name) {
			case "on":
				return SHAKE_TYPE.OFF;
			case "off":
				return SHAKE_TYPE.ON;
			}
			return null;
		}

		public SHAKE_TYPE getNext() {
			switch (name) {
			case "off":
				return SHAKE_TYPE.ON;
			case "on":
				return SHAKE_TYPE.OFF;
			}
			return null;
		}
	}

}
