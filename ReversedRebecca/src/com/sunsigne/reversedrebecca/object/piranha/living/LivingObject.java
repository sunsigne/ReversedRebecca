package com.sunsigne.reversedrebecca.object.piranha.living;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;

public abstract class LivingObject extends PiranhaObject implements CollisionDetector {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it.
	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		setMustFollowPath(true);
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

	////////// TICK ////////////

	@Override
	public void tick() {
		if (!isBeingPushed())
			updateWatchingDirection();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
