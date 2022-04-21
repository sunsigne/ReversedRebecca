package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class LivingAnimation {

	public LivingAnimation(LivingObject living) {
		this.living = living;
		loadAnimations();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// BEHAVIOR ////////////

	private LivingObject living;

	////////// TICK ////////////

	public abstract int getAnimationTime();

	private int time = getAnimationTime();

	public void run() {
		if (getAnimationTime() == -1)
			return;

		time--;
		if (time < 0) {
			time = getAnimationTime();
			for (int i = 0; i < cycloid.length; i++) {
				cycloid[i].cycle();
			}
		}
	}

	public void freeze() {
		for (int i = 0; i < cycloid.length; i++) {
			cycloid[i].setState(0);
		}
		time = getAnimationTime();
	}

	////////// TEXTURE ////////////

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] cycloid = new Cycloid[4];

	private void loadAnimations() {
		cycloid[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		cycloid[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		cycloid[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		cycloid[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
	}

	protected BufferedImage[] loadAnimation(DIRECTION direction) {

		String path = getName() + "_" + direction.getName();
		BufferedImage img0 = loadImage(path + "_0");
		BufferedImage img1 = loadImage(path + "_1");

		return new BufferedImage[] { img0, img1 };
	}

	protected BufferedImage loadImage(String imageName) {
		String imagePath = "textures/characters/" + living.getName() + "/" + imageName;

		BufferedImage img = new ImageTask().loadImage(imagePath, true);

		// load error character instead of missing texture
		if (img == null)
			img = new ImageTask().loadImage(imagePath.replace(living.getName(), "error"));

		return img;
	}

	////////// RENDER ////////////

	public BufferedImage getImage() {
		int facing = living.getFacing().getNum();
		return cycloid[facing].getState();
	}

}