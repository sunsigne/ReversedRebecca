package com.sunsigne.reversedrebecca.ressources.menu.achievement;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign.DESIGN_TYPE;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Achievement {

	public static String file = "achievements.csv";

	public Achievement(String name, int location, boolean hidden, BufferedImage image, BufferedImage image_gothic,
			BufferedImage image_locked) {
		this.name = name;
		this.location = location;
		this.hidden = hidden;
		this.image = image;
		this.image_gothic = image_gothic;
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
	private BufferedImage image_gothic;

	public BufferedImage getImage() {
		if (image_gothic == null)
			return image;

		if (RebeccaDesign.getType() == DESIGN_TYPE.GOTHIC)
			return image_gothic;

		return image;
	}

	private BufferedImage image_locked;

	public BufferedImage getImageLocked() {
		return image_locked;
	}

}
