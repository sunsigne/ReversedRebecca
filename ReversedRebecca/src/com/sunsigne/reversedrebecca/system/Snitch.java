package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Snitch {

	private static boolean ACTIVATED = true;
	private static final String file = new FileTask().getUserDataPath() + "for_dev.csv";
	private static final FileTask task = new FileTask();

	public Snitch() {
		if (ACTIVATED == false) {
			if (task.doesExist(file))
				task.delete(file);
			return;
		}

		if (task.doesExist(file) == false)
			new FileTask().write(file, "");
	}

	////////// WORLD ////////////

	public void registerEntry(String text) {
		if (ACTIVATED == false)
			return;

		String content = new FileTask().read(file);
		String new_content = content + System.getProperty("line.separator")
				+ new FormatedString().getNoSpecialCharacter(text);
		task.write(file, new_content);
	}

}
