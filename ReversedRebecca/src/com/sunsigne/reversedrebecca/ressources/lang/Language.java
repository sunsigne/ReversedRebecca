package com.sunsigne.reversedrebecca.ressources.lang;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Language {

	private String file = new FileTask().getUserDataPath() + "options.csv";
	
	////////// SIGNELTON ////////////

	private Language() {
		lang = new FileTask().read("Language", file);
	}

	private static Language instance = null;

	public static Language getInstance() {
		if (instance == null)
			instance = new Language();
		return instance;
	}
	
	////////// LANGUAGE ////////////

	private String lang;
		
	protected String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		new FileTask().write("LANGUAGE", file, lang);
		this.lang = lang;
	}

}
