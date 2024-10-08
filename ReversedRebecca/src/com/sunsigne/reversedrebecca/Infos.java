package com.sunsigne.reversedrebecca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import com.sun.jna.platform.win32.Secur32;
import com.sun.jna.platform.win32.Secur32Util;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Infos {

	////////// GAME ////////////

	public static final String NAME = "Reversed Rebecca";
	public static final String VERSION = "v.0.4.0.5";
	// public static final boolean IS_DEV_VERSION =
	// System.getProperty("java.class.path").contains("\\git\\");
	public static boolean IS_DEV_VERSION = true;
	public static final ImageIcon ICON = new ImageIcon("icon.png");

	////////// USEFUL ////////////

	public static final String USERNAME = getUserName();
	public static final String DAYOFTHEWEEK = getDayOfTheWeek();

	private static String getUserName() {
		// "player"
		String path = "texts/" + Language.getInstance().getLang() + "/" + "user.txt";
		String userName = new FileTask().read(false, path);

		try {
			// JNA attempt to take real name
			userName = Secur32Util.getUserNameEx(Secur32.EXTENDED_NAME_FORMAT.NameDisplay);
		} catch (Exception e) {
			// PowerShell attempt to take real name
			try {
				String command = "powershell.exe Get-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Authentication\\LogonUI'";
				Process powerShellProcess = Runtime.getRuntime().exec(command);
				powerShellProcess.getOutputStream().close();

				BufferedReader reader = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
				String line;

				while ((line = reader.readLine()) != null) {
					if (line.contains("LastLoggedOnDisplayName") == false)
						continue;

					userName = line.split(" : ")[1];
				}				
			} catch (Exception e1) {
				// No futher attempt
				e.printStackTrace();
				e1.printStackTrace();
			}
		}

		if (userName.contains(" "))
			userName = userName.split(" ")[0];

		return userName;
	}

	private static String getDayOfTheWeek() {
		Integer day_value = 1;

		try {
			// Calendar attempt to get the day
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			day_value = cal.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			try {
				// LocalDate attempt to get the day
				LocalDate today = LocalDate.now();
				DayOfWeek dayOfWeek = today.getDayOfWeek();
				day_value = dayOfWeek.getValue() == 7 ? 1 : dayOfWeek.getValue() + 1;
				
			} catch (Exception e1) {
				// No futher attempt
				e.printStackTrace();
				e1.printStackTrace();
			}
		}

		String day = new Translatable().getTranslatedText(day_value.toString(), "day.txt");

		return day;
	};

}
