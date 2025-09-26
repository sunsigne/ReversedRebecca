package com.sunsigne.reversedrebecca.characteristics.upgrade;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class UpgradePlayer {

	private String file = "characteristics.csv";
	private boolean userData = true;

	public UpgradePlayer() {
		UpgradeList.getList().addObject(this);
		loadValue();
	}

	////////// USEFUL ////////////

	public UpgradePlayer getUpgrade() {
		return UpgradeList.getList().getObject(this);
	}

	protected abstract UpgradePlayer getInstance();

	private void registerDefaultCharacteristic(String defaultValue) {
		String content = new FileTask().read(userData, file);
		String br = System.getProperty("line.separator");
		String valueLine = getName() + "=" + defaultValue;

		String new_content = content + br + br + valueLine;
		new FileTask().write(file, new_content);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// VALUE ////////////

	private boolean value;

	protected String getDefaultValue() {
		return Boolean.toString(false);
	}

	public void loadValue() {
		String value = new FileTask().read(userData, getName(), file);

		// if the file "characteristics" has no value for the tool, create one
		if (value.isEmpty()) {
			registerDefaultCharacteristic(getDefaultValue().toUpperCase());
			value = getDefaultValue();
		}

		getUpgrade().value = Boolean.parseBoolean(value.toLowerCase());
	}
		
	public boolean getValue() {
		return getUpgrade().value;
	}

	public void setValue(boolean value) {
		getUpgrade().value = value;
	}

}
