package com.sunsigne.reversedrebecca.system;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
			path = "crash_report_" + new FormatedString().getNumber(num) + ".txt";
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

	public void openUserData() {
		try {
			File directory = new File(FilePath.USERDATA_PATH);
			Desktop.getDesktop().open(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
