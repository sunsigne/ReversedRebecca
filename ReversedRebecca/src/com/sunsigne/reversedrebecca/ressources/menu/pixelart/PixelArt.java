package com.sunsigne.reversedrebecca.ressources.menu.pixelart;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class PixelArt {

	public static String file = "pixelarts.csv";

	public PixelArt(String name, int location, DIRECTION facing, BufferedImage image, BufferedImage image_locked) {
		this.name = name;
		this.location = location;
		this.facing = facing;
		this.image = image;
		this.image_locked = image_locked;

		this.hasText = new Translatable().getStrictTranslatedText(name, FilePath.PIXEL_ART).isEmpty() == false;
		this.hasLockedText = new Translatable().getStrictTranslatedText(name + "Locked", FilePath.PIXEL_ART)
				.isEmpty() == false;
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
	private boolean hasLockedText;

	public boolean hasText(boolean locked) {
		if (locked)
			return hasLockedText;
		return hasText;
	}

	public boolean isLocked() {
		return Boolean.parseBoolean(new FileTask().read(true, name, file)) == false;
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
