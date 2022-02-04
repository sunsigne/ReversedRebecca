package com.sunsigne.reversedrebecca.ressources.lang;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Translatable {

	public String getTranslatedText(String info, String path, int lineToRead) {
		String text = new FileTask().read(lineToRead, "texts/" + Language.getInstance().getLang() + "/" + path);
		return text;
	}

	public BufferedImage getTranslatedFlagImage() {
		BufferedImage img = new ImageTask().loadImage("texts/" + Language.getInstance().getLang() + "/lang" + "/flag" + ".png");
		return img;
	}
}
