package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.ressources.IRessources;

public class SheetBank implements IRessources {

	public SheetBank() {
		startRessources();
	}

	////////// MAP OR LIST ////////////

	private static Map<SheetBank, BufferedImage> sheets = new HashMap<>();

	// public for devs
	public static void addImage(SheetBank sheetBank, BufferedImage bufferedImage) {

		if (bufferedImage != null)
			sheets.put(sheetBank, bufferedImage);
	}

	public static BufferedImage getImage(SheetBank sheetBank) {
		return sheets.get(sheetBank);
	}

	////////// MINIMAL RESSOURCES ////////////

	@Override
	public void loadMinimalRessources() {

	}

	////////// RESSOURCES ////////////

	protected static final SheetBank TOOL_SHEET = new SheetBank();

	@Override
	public void loadRessources() {

		ImageTask imageTask = new ImageTask();

		addImage(TOOL_SHEET, imageTask.loadImage("textures\\tool_sheet.png"));
	}

}
