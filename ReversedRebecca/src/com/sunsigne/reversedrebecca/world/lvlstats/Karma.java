package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Karma {

	private String file = "characteristics.csv";
	private boolean userData = true;

	public Karma() {
		loadKarma();
	}

	////////// KARMA ////////////

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private void createCharacteristic(String text, String value) {
		String content = new FileTask().read(userData, file);
		String new_content = text + "=" + value + System.getProperty("line.separator") + content;
		new FileTask().write(file, new_content);
	}

	private void loadKarma() {
		String txtKarma = new FileTask().read(userData, "KARMA", file);

		// if the file "characteristics" has no value for the karma, create one
		if (txtKarma.isEmpty()) {
			txtKarma = "0";
			createCharacteristic(System.getProperty("line.separator") + "KARMA", txtKarma);
		}

		setValue(Integer.parseInt(txtKarma));
	}

	public void registerKarma() {
		FileTask task = new FileTask();
		task.write("KARMA", file, String.valueOf(value));
	}

}