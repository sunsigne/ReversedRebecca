package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WalkingRender implements Behavior {

	public WalkingRender(ExtraBehaviorsObject object) {
		this.object = object;
		initAnimations();
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 10;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		if (object.isMotionless())
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

	private void initAnimations() {
		walking[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.LEFT));
		walking[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.RIGHT));
		walking[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.UP));
		walking[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.DOWN));
	}

	private BufferedImage getImage(String imageName) {
		return new ImageTask().loadImage("textures/characters/" + object.getName() + "/walking_" + imageName + ".png");
	}

	private BufferedImage[] getImages(DIRECTION direction) {

		BufferedImage img0 = getImage(direction.getName() + "_1");
		BufferedImage img1 = getImage(direction.getName() + "_0");
		BufferedImage img2 = getImage(direction.getName() + "_1");
		BufferedImage img3 = getImage(direction.getName() + "_2");

		return new BufferedImage[] { img0, img1, img2, img3 };
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		int facing = object.getFacing().getNum();
		BufferedImage img = facing > -1 ? walking[facing].getState() : getImage("ground");
		g.drawImage(img, object.getX(), object.getY(), object.getWidth(), object.getHeight(), null);
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}