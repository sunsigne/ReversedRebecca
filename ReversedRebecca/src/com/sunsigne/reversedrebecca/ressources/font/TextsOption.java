package com.sunsigne.reversedrebecca.ressources.font;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class TextsOption {

	////////// CAMERA OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static TEXTS_SIZE size;

	public static TEXTS_SIZE getType() {
		if (size == null)
			size = new TextsOption().getRegisteredType();
		return size;
	}

	public static float getSize() {
		if(size == null)
			return 1.3f;
		
		switch (size) {
		case SMALL:
			return 1.1f;
		case MEDIUM:
			return 1.3f;
		case LARGE:
			return 1.5f;
		default:
			return 1.3f;
		}
	}

	private TEXTS_SIZE getRegisteredType() {
		String registeredType = new FileTask().read(userData, getValueToRead(), file);
		for (TEXTS_SIZE tempType : TEXTS_SIZE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return TEXTS_SIZE.MEDIUM;
	}

	private static String getValueToRead() {
		return "Texts";
	}

	public void registerType(TEXTS_SIZE textsSize) {
		new FileTask().write(getValueToRead(), file, textsSize.getName());
		size = null;
	}

	////////// TEXTS SIZE ////////////

	public enum TEXTS_SIZE {
		SMALL("small"), MEDIUM("medium"), LARGE("large");
		;

		TEXTS_SIZE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public TEXTS_SIZE getPrevious() {
			switch (name) {
			case "small":
				return TEXTS_SIZE.LARGE;
			case "medium":
				return TEXTS_SIZE.SMALL;
			case "large":
				return TEXTS_SIZE.MEDIUM;
			}
			return null;
		}

		public TEXTS_SIZE getNext() {
			switch (name) {
			case "small":
				return TEXTS_SIZE.MEDIUM;
			case "medium":
				return TEXTS_SIZE.LARGE;
			case "large":
				return TEXTS_SIZE.SMALL;
			}
			return null;
		}
	}

}
