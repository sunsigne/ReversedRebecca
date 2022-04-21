package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;

public abstract class LivingObject extends PiranhaObject implements CollisionDetector {

	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		setFollowPath(true);
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
		updateWatchingDirection();
	}

}
