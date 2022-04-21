package com.sunsigne.reversedrebecca.object.piranha.living;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.SickAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.StandingAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.WalkingAnimation;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class LivingObject extends PiranhaObject implements CollisionDetector {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it.
	public LivingObject(String name, int x, int y) {
		super(name, x, y);
//		loadAnimations();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
//		loadAnimations();
	}

	////////// FACING ////////////

	private boolean flagX, flagY;

	protected void updateWatchingDirection() {
		if (isMotionlessbyX())
			flagX = false;
		if (isMotionlessbyY())
			flagY = false;

		if (!flagY && getVelX() < 0) {
			setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		if (!flagY && getVelX() > 0) {
			setFacing(DIRECTION.RIGHT);
			flagX = true;
		}

		if (!flagX && getVelY() < 0) {
			setFacing(DIRECTION.UP);
			flagY = true;
		}
		if (!flagX && getVelY() > 0) {
			setFacing(DIRECTION.DOWN);
			flagY = true;
		}
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return true;
	}
	
	////////// TICK ////////////

	@Override
	public void tick() {
		if (!isBeingPushed())
			updateWatchingDirection();

//		getAnimation().run();
	}

	////////// TEXTURE ////////////
/*
	private LivingAnimation standingAnimation;
	private LivingAnimation walkingAnimation;
	private LivingAnimation sickAnimation;

	private void loadAnimations() {
		standingAnimation = new StandingAnimation(this);
		walkingAnimation = new WalkingAnimation(this);
		sickAnimation = new SickAnimation(this);
	}

	// trouver un moyen de reset (freeze) l'animation qui ne tourne pas !
*/
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
//		if (getAnimation() != null)
//			g.drawImage(getAnimation().getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}
/*
	private LivingAnimation getAnimation() {
		if (isMotionless() | isBeingPushed())
			return standingAnimation;

		switch (getCondition()) {
		case GOOD:
			return walkingAnimation;
		case SICK:
			return sickAnimation;
		}

		return standingAnimation;
	}
*/
}
