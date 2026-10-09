package com.sunsigne.reversedrebecca.ressources.menu.drawing;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Drawing {

	public static String file = "drawings.csv";

	public Drawing(String name) {
		this.name = name;
	}

	public boolean isLocked() {
		return Boolean.parseBoolean(new FileTask().read(true, name, file)) == false;
	}

	public void unlocked() {
		new FileTask().write(name, file, "true");
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}

}
