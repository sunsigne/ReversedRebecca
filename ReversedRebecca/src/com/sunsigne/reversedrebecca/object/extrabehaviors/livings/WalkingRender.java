package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WalkingRender implements Behavior {

	public WalkingRender(LivingObject living) {
		this.living = living;
		initAnimations();
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}
	
	////////// POSITION ////////////
	
	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}
	
	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}
	
	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}
	
	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}
	
	////////// SIZE ////////////
	
	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}
	
	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
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

	private void initAnimations() {
		walking[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.LEFT));
		walking[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.RIGHT));
		walking[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.UP));
		walking[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(getImages(DIRECTION.DOWN));
	}

	private BufferedImage getImage(String imageName) {
		return new ImageTask().loadImage("textures/characters/" + living.getName() + "/walking_" + imageName + ".png");
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

		int facing = living.getFacing().getNum();
		BufferedImage img = facing > -1 ? walking[facing].getState() : getImage("ground");
		g.drawImage(img, living.getX(), living.getY(), living.getWidth(), living.getHeight(), null);
	}
	
	//////////COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}