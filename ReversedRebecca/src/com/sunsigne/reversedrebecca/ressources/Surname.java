package com.sunsigne.reversedrebecca.ressources;

import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Surname {

	private String file = "save.csv";
	private boolean userData = true;

	////////// SURNAME ////////////

	private static String doug;
	private static String custom1;
	private static String custom2;
	private static String custom3;

	public void registerSurname(String surname, String value) {
		new FileTask().write("surname_" + surname, file, value);
		System.out.println(new FileTask().read(userData, "surname_doug", file));
		refreshSurname();
	}

	private void refreshSurname() {
		doug = null;
		custom1 = null;
		custom2 = null;
		custom3 = null;
	}

	public String getDoug() {
		if (doug != null)
			return doug;

		if (new FileTask().doesExist(userData, file) == false)
			new Save().getLevel(false);

		String value = new FileTask().read(userData, "surname_doug", file);
		String path = "surname/" + "doug.txt";
		doug = new Translatable().getTranslatedText(value, path);

		return doug;
	}

	public String getCustom1() {
		if (custom1 != null)
			return custom1;

		if (new FileTask().doesExist(userData, file) == false)
			new Save().getLevel(false);

		String value = new FileTask().read(userData, "surname_custom1", file);
		String path = "surname/" + "custom1.txt";
		custom1 = new Translatable().getTranslatedText(value, path);

		return custom1;
	}

	public String getCustom2() {
		if (custom2 != null)
			return custom2;

		if (new FileTask().doesExist(userData, file) == false)
			new Save().getLevel(false);

		String value = new FileTask().read(userData, "surname_custom2", file);
		String path = "surname/" + "custom2.txt";
		custom2 = new Translatable().getTranslatedText(value, path);

		return custom2;
	}

	public String getCustom3() {
		if (custom3 != null)
			return custom3;

		if (new FileTask().doesExist(userData, file) == false)
			new Save().getLevel(false);

		String value = new FileTask().read(userData, "surname_custom3", file);
		String path = "surname/" + "custom3.txt";
		custom3 = new Translatable().getTranslatedText(value, path);

		return custom3;
	}

}
