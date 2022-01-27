package com.sunsigne.reversedrebecca.ressources.lang;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public interface Translatable {

	default String getTranslatedText(String info, String path, int lineToRead) {
		String text = new FileTask().read(lineToRead, "texts/" + Language.getInstance().getLang() + "/" + path);
		return text;
	}

}
