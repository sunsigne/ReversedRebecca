package com.sunsigne.reversedrebecca.object.puzzle.dig.tool;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public enum DIG_STATE {
	DIG("shovel"), PICK("pickaxe"), CHOP("axe"), PUNCH("hand"), SLASH("sword");

	private BufferedImage image;

	DIG_STATE(String path) {
		image = new ImageTask().loadImage("textures/puzzle/" + "dig_tool_" + path);
	}

	public BufferedImage getImage() {
		return image;
	}
}
