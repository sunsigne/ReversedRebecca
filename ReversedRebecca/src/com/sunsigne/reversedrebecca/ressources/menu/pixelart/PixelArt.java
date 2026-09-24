package com.sunsigne.reversedrebecca.ressources.menu.pixelart;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class PixelArt {

	public static String file = "pixelarts.csv";

	public PixelArt(String name, int location, DIRECTION facing, boolean hasText, BufferedImage image, BufferedImage image_locked) {
		this.name = name;
		this.location = location;
		this.facing = facing;
		this.hasText = hasText;
		this.image = image;
		this.image_locked = image_locked;
	}

	private int location;

	protected int getLocation() {
		return location;
	}

	private DIRECTION facing;

	public DIRECTION getFacing() {
		return facing;
	}

	private boolean hasText;

	public boolean hasText() {
		return hasText;
	}
	
	public boolean isUnlocked() {
		return Boolean.parseBoolean(new FileTask().read(true, name, file));
	}

	public void unlocked() {
		new FileTask().write(name, file, "true");
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		return image;
	}

	private BufferedImage image_locked;

	public BufferedImage getImageLocked() {
		return image_locked;
	}

}
