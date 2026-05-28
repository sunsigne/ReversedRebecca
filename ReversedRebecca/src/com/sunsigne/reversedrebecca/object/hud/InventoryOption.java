package com.sunsigne.reversedrebecca.object.hud;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class InventoryOption {

	////////// INVENTORY OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static INVENTORY_TYPE type;

	public static INVENTORY_TYPE getType() {
		if (type == null)
			type = new InventoryOption().getRegisteredType();
		return type;
	}

	public static void setType(INVENTORY_TYPE type) {
		InventoryOption.type = type;
	}

	private INVENTORY_TYPE getRegisteredType() {
		String registeredType = new FileTask().read(userData, getValueToRead(), file);
		for (INVENTORY_TYPE tempType : INVENTORY_TYPE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return INVENTORY_TYPE.IMMERSIVE;
	}

	private static String getValueToRead() {
		return "Inventory";
	}

	public void registerType(INVENTORY_TYPE inventoryType) {
		new FileTask().write(getValueToRead(), file, inventoryType.getName());
		type = null;
	}

	////////// INVENTORY TYPE ////////////

	public enum INVENTORY_TYPE {
		IMMERSIVE("immersive"), VISIBLE("visible");

		INVENTORY_TYPE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public INVENTORY_TYPE getPrevious() {
			switch (name) {
			case "immersive":
				return INVENTORY_TYPE.VISIBLE;
			case "visible":
				return INVENTORY_TYPE.IMMERSIVE;
			}
			return null;
		}

		public INVENTORY_TYPE getNext() {
			switch (name) {
			case "visible":
				return INVENTORY_TYPE.IMMERSIVE;
			case "immersive":
				return INVENTORY_TYPE.VISIBLE;
			}
			return null;
		}
	}

}
