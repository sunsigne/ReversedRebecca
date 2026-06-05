package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class LivingOption {

	////////// INVENTORY OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static LIVING_TYPE type;

	public static LIVING_TYPE getType() {
		if (type == null)
			type = new LivingOption().getRegisteredType();
		return type;
	}

	public static void setType(LIVING_TYPE type) {
		LivingOption.type = type;
	}

	private LIVING_TYPE getRegisteredType() {
		String registeredType = new FileTask().read(userData, getValueToRead(), file);
		for (LIVING_TYPE tempType : LIVING_TYPE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return LIVING_TYPE.DEFAULT;
	}

	private static String getValueToRead() {
		return "PreferredAttraction";
	}

	public void registerType(LIVING_TYPE livingType) {
		new FileTask().write(getValueToRead(), file, livingType.getName());
		type = null;
	}

	////////// LIVING TYPE ////////////

	public enum LIVING_TYPE {
		DEFAULT("default"), MALE("male"), FEMALE("female");

		LIVING_TYPE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public LIVING_TYPE getPrevious() {
			switch (name) {
			case "default":
				return LIVING_TYPE.FEMALE;
			case "male":
				return LIVING_TYPE.DEFAULT;
			case "female":
				return LIVING_TYPE.MALE;
			}
			return null;
		}

		public LIVING_TYPE getNext() {
			switch (name) {
			case "default":
				return LIVING_TYPE.MALE;
			case "male":
				return LIVING_TYPE.FEMALE;
			case "female":
				return LIVING_TYPE.DEFAULT;
			}
			return null;
		}
	}

}
