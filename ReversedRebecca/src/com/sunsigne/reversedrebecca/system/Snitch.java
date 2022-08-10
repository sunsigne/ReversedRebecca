package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Snitch {

	private static boolean ACTIVATED = true;
	private static final String file = "for_dev.csv";
	private boolean userData = true;
	private static final FileTask task = new FileTask();

	public Snitch() {
		if (ACTIVATED == false) {
			if (task.doesExist(userData, file))
				task.delete(userData, file);
			return;
		}

		if (task.doesExist(userData, file) == false)
			new FileTask().write(file, "");
	}

	////////// WORLD ////////////

	public void registerEntry(String text) {
		if (ACTIVATED == false)
			return;

		String content = new FileTask().read(userData, file);
		String new_content = content + System.getProperty("line.separator")
				+ new FormatedString().getNoSpecialCharacter(text);
		task.write(file, new_content);
	}

}
