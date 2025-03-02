package com.sunsigne.reversedrebecca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Infos {

	////////// GAME ////////////

	public static final String NAME = "Reversed Rebecca";
	public static final String VERSION = "v.0.4.2.20";
	// public static final boolean IS_DEV_VERSION =
	// System.getProperty("java.class.path").contains("\\git\\");
	public static boolean IS_DEV_VERSION = true;
	public static final ImageIcon ICON = new ImageIcon("icon.png");

	////////// USEFUL ////////////

	public static final String USERNAME = new FormattedString().capitalize(getUserName());
	public static final String DAYOFTHEWEEK = getDayOfTheWeek();

	private static String getUserName() {
		String path;
		String userName;

		// Windows register analysed
		path = "HKLM:/SOFTWARE/Microsoft/Windows/CurrentVersion/Authentication/LogonUI";
		userName = searchUserName(path, "LastLoggedOnDisplayName");
		if (userName != null)
			return userName;

		// Word Office register analysed
		path = "HKCU:/SOFTWARE/Microsoft/Office/Common/UserInfo";
		userName = searchUserName(path, "UserName");
		if (userName != null)
			return userName;

		// Steam Login analysed
		path = "HKCU:/SOFTWARE/Valve/Steam";
		String userName1 = searchUserName(path, "AutoLoginUser");
		String userName2 = searchUserName(path, "LastGameNameUsed");

		// Default Computer name
		userName = System.getProperty("user.name");

		if (userName1 == null && userName2 == null)
			return userName;

		userName = getMostProbableActualName(userName1, userName2, userName);
		return userName;
	}

	private static String searchUserName(String path, String value) {
		String userName = null;

		try {
			String command = "powershell.exe Get-ItemProperty -Path '" + path + "'";
			Process powerShellProcess = Runtime.getRuntime().exec(command);
			powerShellProcess.getOutputStream().close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.contains(value)) {
					userName = line.split(" : ")[1];
					if (userName.contains(" "))
						userName = userName.split(" ")[0];
					break;
				}
			}

			reader.close();
		} catch (Exception e) {
			// shit happens
		}

		return userName;
	}

	private static String getMostProbableActualName(String userName1, String userName2, String userName3) {
		
		String not_a_name = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String name1 = userName1;
		String name2 = userName2;
		String name3 = userName3;

		if (userName1.contains(userName2) && userName1.length() != userName2.length())
			name2 = not_a_name;
		if (userName1.contains(userName3) && userName1.length() != userName3.length())
			name3 = not_a_name;
		if (userName2.contains(userName1) && userName2.length() != userName1.length())
			name1 = not_a_name;
		if (userName2.contains(userName3) && userName2.length() != userName3.length())
			name3 = not_a_name;
		if (userName3.contains(userName1) && userName3.length() != userName1.length())
			name1 = not_a_name;
		if (userName3.contains(userName2) && userName3.length() != userName2.length())
			name2 = not_a_name;

		// pseudos are often longer than actual names
		if (name1.length() > name2.length())
			name1 = not_a_name;
		if (name2.length() > name1.length())
			name2 = not_a_name;
		if (name1.length() > name3.length())
			name1 = not_a_name;
		if (name3.length() > name1.length())
			name3 = not_a_name;
		if (name2.length() > name3.length())
			name2 = not_a_name;
		if (name3.length() > name2.length())
			name3 = not_a_name;

		if (name1 != not_a_name && name2 == not_a_name && name3 == not_a_name)
			return name1;
		if (name1 == not_a_name && name2 != not_a_name && name3 == not_a_name)
			return name2;
		if (name1 == not_a_name && name2 == not_a_name && name3 != not_a_name)
			return name3;

		return userName3;
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
