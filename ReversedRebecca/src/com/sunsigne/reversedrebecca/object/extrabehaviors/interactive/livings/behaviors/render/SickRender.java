package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.RenderBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class SickRender implements TickBehavior, RenderBehavior {

	public SickRender(LivingObject living) {
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

	private final int ANIMATION_TIME = 30;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		runAnimation();
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			for (int i = 0; i < sick.length; i++) {
				sick[i].cycle();
			}
		}
	}

	////////// TEXTURE ////////////

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] sick = new Cycloid[4];

	private void loadAnimations() {
		sick[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		sick[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		sick[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		sick[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
	}

	private BufferedImage[] loadAnimation(DIRECTION direction) {

		BufferedImage img0 = loadImage("sick_" + direction.getName() + "_0");
		BufferedImage img1 = loadImage("sick_" + direction.getName() + "_1");

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
		return sick[facing].getState();
	}

}