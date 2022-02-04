package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.RenderBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
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

	private final int ANIMATION_TIME = 10;
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
	private BufferedImage ground;

	private void loadAnimations() {
		walking[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		walking[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		walking[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		walking[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
		ground = loadImage("ground");
	}
	
	private BufferedImage[] loadAnimation(DIRECTION direction) {

		BufferedImage img0 = loadImage(direction.getName() + "_1");
		BufferedImage img1 = loadImage(direction.getName() + "_0");
		BufferedImage img2 = loadImage(direction.getName() + "_1");
		BufferedImage img3 = loadImage(direction.getName() + "_2");

		return new BufferedImage[] { img0, img1, img2, img3 };
	}
	
	private BufferedImage loadImage(String imageName) {
		String imagePath = "textures/characters/" + living.getName() + "/walking_" + imageName;
		String backupImagePath = "textures/characters/" + "error" + "/walking_" + imageName;

		return new ImageTask().loadImage(imagePath, backupImagePath, true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), living.getX(), living.getY(), living.getWidth(), living.getHeight(), null);
	}
	
	private BufferedImage getImage() {
		int facing = living.getFacing().getNum();
		
		if (facing > -1)
			return walking[facing].getState();
		else
			return ground;
	}

}