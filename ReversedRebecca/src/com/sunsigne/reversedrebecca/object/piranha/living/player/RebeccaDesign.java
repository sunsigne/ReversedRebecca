package com.sunsigne.reversedrebecca.object.piranha.living.player;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class RebeccaDesign {

	////////// INVENTORY OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static DESIGN_TYPE type;

	public static DESIGN_TYPE getType() {
		if (type == null)
			type = new RebeccaDesign().getRegisteredType();

		return type;
	}

	public static void setType(DESIGN_TYPE type) {
		RebeccaDesign.type = type;
	}

	private DESIGN_TYPE getRegisteredType() {
		String registeredType = new FileTask().read(userData, getValueToRead(), file);
		for (DESIGN_TYPE tempType : DESIGN_TYPE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return DESIGN_TYPE.DEFAULT;
	}

	private static String getValueToRead() {
		return "RebeccaDesign";
	}

	public void registerType(DESIGN_TYPE livingType) {
		new FileTask().write(getValueToRead(), file, livingType.getName());
		type = null;
	}

	////////// LIVING TYPE ////////////

	public enum DESIGN_TYPE {
		DEFAULT("default"), GOTHIC("gothic");

		DESIGN_TYPE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public DESIGN_TYPE getPrevious() {
			switch (name) {
			case "default":
				return DESIGN_TYPE.GOTHIC;
			case "gothic":
				return DESIGN_TYPE.DEFAULT;
			}
			return DESIGN_TYPE.DEFAULT;
		}

		public DESIGN_TYPE getNext() {
			switch (name) {
			case "default":
				return DESIGN_TYPE.GOTHIC;
			case "gothic":
				return DESIGN_TYPE.DEFAULT;
			}
			return DESIGN_TYPE.DEFAULT;
		}
	}

}
