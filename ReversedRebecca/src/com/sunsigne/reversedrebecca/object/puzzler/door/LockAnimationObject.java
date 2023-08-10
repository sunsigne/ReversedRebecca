package com.sunsigne.reversedrebecca.object.puzzler.door;

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
		String path = "textures/puzzler/" + super.getName() + "_";

		lockedImage = new ImageTask().loadImage(path + "0");
		unlockedImage = new ImageTask().loadImage(path + "0");

		currentImage = lockedImage;
	}

}
