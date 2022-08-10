package com.sunsigne.reversedrebecca.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Snitch {

	private static boolean ACTIVATED = start();
	private static final String file = "dev_data.txt";
	private boolean userData = true;
	private static final FileTask task = new FileTask();

	public Snitch() {
		if (ACTIVATED == false)
			return;

		if (task.doesExist(userData, file) == false)
			new FileTask().write(file, "");
	}

	////////// USEFUL ////////////

	private static boolean start() {
		if (Infos.IS_DEV_VERSION)
			return false;

		try {
			PrintStream console = new PrintStream(new FileOutputStream(Infos.USERDATA_PATH + "crash_repport.txt"));
			System.setErr(console);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void registerEntry(String text) {
		if (ACTIVATED == false)
			return;

		String content = new FileTask().read(userData, file);
		String new_content = content + System.getProperty("line.separator")
				+ new FormatedString().getNoSpecialCharacter(text);
		task.write(file, new_content);
	}

}
