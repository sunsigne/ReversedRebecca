package com.sunsigne.reversedrebecca.object.puzzler.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class UnlockAnimationObject extends SuperPuzzlerAnimationObject {

	public UnlockAnimationObject(int x, int y) {
		super(x, y);
		loadImages();
		setY(getY() - Size.XS);
		setVelY(-3);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "door_unlock";
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		switch (time) {
		case ANIMATION_TIME - 18:
			setMotionless();
			currentImage = unlockedImage;
			break;

		case 24:
		case 16:
		case 8:
			currentImage = null;
			break;
		case 20:
		case 12:
		case 4:
			currentImage = unlockedImage;
			break;
		}
	}

	////////// TEXTURE ////////////

	protected BufferedImage lockedImage;
	protected BufferedImage unlockedImage;

	protected void loadImages() {
		BufferedImage image = new ImageTask().loadImage("textures/puzzler/" + getName());

		lockedImage = getSheetSubImage(image, 1);
		unlockedImage = getSheetSubImage(image, 2);

		currentImage = lockedImage;
	}

	protected BufferedImage currentImage;

	@Override
	public BufferedImage getImage() {
		return currentImage;
	}

}
