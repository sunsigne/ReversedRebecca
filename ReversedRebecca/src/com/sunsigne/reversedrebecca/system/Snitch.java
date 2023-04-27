package com.sunsigne.reversedrebecca.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Snitch {

	private final FileTask task = new FileTask();

	protected boolean start() {
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
			PrintStream console = new PrintStream(new FileOutputStream(FilePath.USERDATA_PATH + path));
			System.setErr(console);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return true;
	}

}
