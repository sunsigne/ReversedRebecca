package com.sunsigne.reversedrebecca.ressources.achievement;

import java.awt.image.BufferedImage;

public class Achievement {

	public Achievement(String name, int location, boolean hidden, boolean unlocked, BufferedImage image) {
		this.name = name;
		this.location = location;
		this.hidden = hidden;
		this.unlocked = unlocked;
		this.image = image;
	}

	private int location;

	protected int getLocation() {
		return location;
	}

	private boolean hidden;

	public boolean isHidden() {
		return hidden;
	}

	private boolean unlocked;

	public boolean isUnlocked() {
		return unlocked;
	}

	public void unlocked() {
		this.unlocked = true;
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

}
