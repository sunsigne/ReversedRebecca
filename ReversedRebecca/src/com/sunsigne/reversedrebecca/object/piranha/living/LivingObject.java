package com.sunsigne.reversedrebecca.object.piranha.living;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;

public abstract class LivingObject extends PiranhaObject implements CollisionDetector {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it.
	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		loadAnimation();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		loadAnimation();
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
		if (!isStunned())
			updateWatchingDirection();
		
		animation.run();
	}

	////////// TEXTURE ////////////

	private LivingAnimationHandler animation;

	private void loadAnimation() {
		animation = new LivingAnimationHandler(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(animation.getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
