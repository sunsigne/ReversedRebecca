package com.sunsigne.reversedrebecca.object.puzzler.vent;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.NullHoleObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class VentObject extends NullHoleObject {

	public VentObject(DIRECTION facing, int x, int y) {
		super(facing, x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "vent";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1 + getFacing().getNum();
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "vent");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "vent" + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, 1, getSheetRowCriterion(), getSheetWidth() + 2,
					getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// INTERACTION ////////////

	@Override
	public String getGoSound() {
		return "vent";
	}

}
