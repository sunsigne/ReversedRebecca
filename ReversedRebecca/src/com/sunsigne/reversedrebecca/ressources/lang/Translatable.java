package com.sunsigne.reversedrebecca.ressources.lang;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Translatable {

	public String getTranslatedText(String valueToRead, String path) {
		String text = new FileTask().read(valueToRead, "texts/" + Language.getInstance().getLang() + "/" + path);
		return text;
	}

	public BufferedImage getTranslatedFlagImage() {
		BufferedImage img = new ImageTask().loadImage("texts/" + Language.getInstance().getLang() + "/" + "lang/flag");
		return img;
	}
}
