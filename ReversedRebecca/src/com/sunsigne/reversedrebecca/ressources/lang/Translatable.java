package com.sunsigne.reversedrebecca.ressources.lang;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Translatable {

	public String getTranslatedText(String info, String path, int lineToRead) {
		String text = new FileTask().read(lineToRead, "texts/" + Language.getInstance().getLang() + "/" + path);
		return text;
	}

}
