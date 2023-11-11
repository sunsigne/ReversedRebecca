package com.sunsigne.reversedrebecca.ressources.lang;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Translatable {

	private boolean userData = false;

	public String getStrictTranslatedText(String valueToRead, String filePath) {
		return new FileTask().read(userData, valueToRead, "texts/" + Language.getInstance().getLang() + "/" + filePath);
	}

	public String getTranslatedText(String valueToRead, String filePath) {

		// configured language
		String text = getStrictTranslatedText(valueToRead, filePath);
		if (text.isEmpty() == false)
			return text;

		// if not available, search for another valid languague
		text = searchAvailableLanguage(valueToRead, filePath);
		if (text.isEmpty() == false)
			return text;

		// if not available, display an error message in the configured language
		text = new FileTask().read(userData, "texts/" + Language.getInstance().getLang() + "/lang/error.txt");
		if (text.isEmpty() == false)
			return text;

		// if not available, display an error message in english
		text = new FileTask().read(userData, "texts/" + "english" + "/lang/error.txt");
		return text;
	}

	public BufferedImage getTranslatedFlagImage() {
		BufferedImage img = new ImageTask().loadImage("texts/" + Language.getInstance().getLang() + "/" + "lang/flag");
		return img;
	}

	private String searchAvailableLanguage(String valueToRead, String path) {

		// try english as default language
		String text = new FileTask().read(userData, valueToRead, "texts/" + "english" + "/" + path);
		if (!text.isEmpty())
			return text;

		// search for any available language
		File file = new File(FilePath.RESSOURCES_PATH + "texts");
		var lang_list = new ArrayList<String>(Arrays.asList(file.list()));

		for (String tempLang : lang_list) {
			text = new FileTask().read(userData, valueToRead, "texts/" + tempLang + "/" + path);
			if (!text.isEmpty())
				return text;
		}
		return text;
	}

}
