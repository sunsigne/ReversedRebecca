package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.RenderBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WalkingRender implements TickBehavior, RenderBehavior {

	public WalkingRender(LivingObject living) {
		this.living = living;
		loadAnimations();
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 15;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		if (living.isMotionless())
			freezeAnimation();
		else
			runAnimation();
	}

	private void freezeAnimation() {
		for (int i = 0; i < walking.length; i++) {
			walking[i].setState(0);
		}
		time = ANIMATION_TIME;
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			for (int i = 0; i < walking.length; i++) {
				walking[i].cycle();
			}
		}
	}

	////////// TEXTURE ////////////

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] walking = new Cycloid[4];
	private BufferedImage[] standing = new BufferedImage[4];
	private BufferedImage ground;

	private void loadAnimations() {
		walking[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		walking[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		walking[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		walking[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));

		standing[DIRECTION.LEFT.getNum()] = loadImage("standing_" + DIRECTION.LEFT.getName());
		standing[DIRECTION.RIGHT.getNum()] = loadImage("standing_" + DIRECTION.RIGHT.getName());
		standing[DIRECTION.UP.getNum()] = loadImage("standing_" + DIRECTION.UP.getName());
		standing[DIRECTION.DOWN.getNum()] = loadImage("standing_" + DIRECTION.DOWN.getName());

		ground = loadImage("ground");
	}

	private BufferedImage[] loadAnimation(DIRECTION direction) {

		BufferedImage img0 = loadImage("walking_" + direction.getName() + "_0");
		BufferedImage img1 = loadImage("walking_" + direction.getName() + "_1");

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

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), living.getX(), living.getY(), living.getWidth(), living.getHeight(), null);
	}

	private BufferedImage getImage() {
		int facing = living.getFacing().getNum();

		if (facing == -1)
			return ground;

		if (living.isMotionless())
			return standing[facing];

		return walking[facing].getState();
	}

}