package com.sunsigne.reversedrebecca.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Snitch {

	private static final FileTask task = new FileTask();
	private static final String file = "dev_data.txt";
	private static boolean ACTIVATED = start();
	private boolean userData = true;

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

		boolean error = true;
		int num = 1;
		String path = null;

		while (error) {
			// create crash_repport_xx.txt in AppData/Roaming
			path = "crash_repport_" + new FormatedString().getNumber(num) + ".txt";
			error = task.doesExist(true, path);
			if (error)
				error = task.isEmptyFile(true, path);
			num++;
		}

		try {
			PrintStream console = new PrintStream(new FileOutputStream(Infos.USERDATA_PATH + path));
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
