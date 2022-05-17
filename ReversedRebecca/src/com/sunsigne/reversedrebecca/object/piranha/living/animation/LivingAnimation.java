package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class LivingAnimation {

	public LivingAnimation(LivingObject living, String name, int animation_time, boolean orientable) {
		this.name = name;
		this.living = living;
		this.animation_time = animation_time; // -1 for no animation
		this.orientable = orientable;
		loadAnimations();
	}

	////////// NAME ////////////

	private String name;

	////////// BEHAVIOR ////////////

	private LivingObject living;

	////////// TICK ////////////

	private int animation_time;
	private int time = animation_time;

	public void run() {
		// no animation case
		if (animation_time == -1)
			return;

		time--;
		if (time < 0) {
			time = animation_time;
			for (int i = 0; i < cycloid.length; i++) {
				cycloid[i].cycle();
			}
		}
	}

	public void freeze() {
		// no animation case
		if (animation_time == -1)
			return;

		for (int i = 0; i < cycloid.length; i++) {
			cycloid[i].setState(0);
		}
		time = animation_time;
	}

	////////// TEXTURE ////////////

	private boolean orientable;

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] cycloid = new Cycloid[4];

	private void loadAnimations() {
		cycloid[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		cycloid[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		cycloid[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		cycloid[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
	}

	private BufferedImage[] loadAnimation(DIRECTION direction) {

		// no orientable case
		String path = name;
		if(orientable)
			path = path + direction.getName();

		// no animation case
		if (animation_time == -1)
			return new BufferedImage[] { loadImage(path) };
		
		BufferedImage img0 = loadImage(path + "_0");
		BufferedImage img1 = loadImage(path + "_1");

		return new BufferedImage[] { img0, img1 };
	}
	
	private BufferedImage loadImage(String imageName) {
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