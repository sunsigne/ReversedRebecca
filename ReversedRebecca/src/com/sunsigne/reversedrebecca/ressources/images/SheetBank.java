package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.util.AnnotationBank.Ressources;

@Ressources
public class SheetBank {

	private SheetBank(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	////////// IMAGE ////////////

	private BufferedImage bufferedImage;

	public BufferedImage getImage() {
		return bufferedImage;
	}

	////////// RESSOURCES ////////////

	private static ImageTask imageTask = new ImageTask();

	protected static final SheetBank TOOL_SHEET = new SheetBank(imageTask.loadImage("textures\\tool_sheet.png"));

}
