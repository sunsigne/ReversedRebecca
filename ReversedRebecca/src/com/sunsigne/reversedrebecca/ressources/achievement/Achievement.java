package com.sunsigne.reversedrebecca.ressources.achievement;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Achievement {

	private String file = "achievements.csv";

	public Achievement(String name, int location, boolean hidden, BufferedImage image, BufferedImage image_locked) {
		this.name = name;
		this.location = location;
		this.hidden = hidden;
		this.image = image;
		this.image_locked = image_locked;
	}

	private int location;

	protected int getLocation() {
		return location;
	}

	private boolean hidden;

	public boolean isHidden() {
		return hidden;
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
