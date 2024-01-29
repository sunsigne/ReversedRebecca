package com.sunsigne.reversedrebecca.object.puzzler.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class LockAnimationObject extends UnlockAnimationObject {

	public LockAnimationObject(int x, int y) {
		super(x, y);
		setY(getY() - Size.XS - Size.XL / 8);
		setVelY(5);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "door_lock";
	}

	////////// TEXTURE ////////////

	@Override
	protected void loadImages() {
		BufferedImage image = new ImageTask().loadImage("textures/puzzler/" + getName());

		lockedImage = getSheetSubImage(image, 1);
		unlockedImage = getSheetSubImage(image, 1);

		currentImage = lockedImage;
	}

}
