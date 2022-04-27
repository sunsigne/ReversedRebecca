package com.sunsigne.reversedrebecca.ressources.lang;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Translatable {

	public String getTranslatedText(String valueToRead, String path) {
		
		// configured language
		String text = new FileTask().read(valueToRead, "texts/" + Language.getInstance().getLang() + "/" + path);
		if (text.isEmpty() == false)
			return text;

		// if not available, search for another valid languague
		text = searchAvailableLanguage(valueToRead, path);
		if (text.isEmpty() == false)
			return text;

		// if not available, display an error message in the configured language
		text = new FileTask().read("texts/" + Language.getInstance().getLang() + "/lang/error.txt");
		if (text.isEmpty() == false)
			return text;
		
		// if not available, display an error message in english
		text = new FileTask().read("texts/" + "english" + "/lang/error.txt");
		return text;
	}

	public BufferedImage getTranslatedFlagImage() {
		BufferedImage img = new ImageTask().loadImage("texts/" + Language.getInstance().getLang() + "/" + "lang/flag");
		return img;
	}

	private String searchAvailableLanguage(String valueToRead, String path) {

		// try english as default language
		String text = new FileTask().read(valueToRead, "texts/" + "english" + "/" + path);
		if (!text.isEmpty())
			return text;

		// search for any available language
		File file = new File("ressources/texts");
		var lang_list = new ArrayList<String>(Arrays.asList(file.list()));

		for (String tempLang : lang_list) {
			text = new FileTask().read(valueToRead, "texts/" + tempLang + "/" + path);
			if (!text.isEmpty())
				return text;
		}
		return text;
	}

}
