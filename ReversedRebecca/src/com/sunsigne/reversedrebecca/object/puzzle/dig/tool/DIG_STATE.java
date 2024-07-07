package com.sunsigne.reversedrebecca.object.puzzle.dig.tool;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;

public enum DIG_STATE implements SheetableImage {
	DIG("shovel"), PICK("pickaxe"), CHOP("axe"), PUNCH("hand"), SLASH("sword"), CRITICAL("critical");

	private String tool;

	DIG_STATE(String tool) {
		this.tool = tool;
	}

	////////// UPDATABLE ////////////

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetSize() {
		return 3*16;
	}

	@Override
	public int getSheetColCriterion() {
		switch (tool) {
		case "hand":
			return 1;
		case "shovel":
			return 2;
		case "pickaxe":
			return 3;
		case "axe":
			return 4;
		case "sword":
			return 5;
		case "critical":
			return 6;
		}
		return 0;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "dig_tool");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

}
