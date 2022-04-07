package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

public class WatchingDirection implements TickBehavior, Facing {

	public WatchingDirection(LivingObject living) {
		this.living = living;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// FACING ////////////

	private boolean flagX, flagY;

	@Override
	public DIRECTION getFacing() {
		return living.getFacing();
	}

	@Override
	public void setFacing(DIRECTION facing) {
		living.setFacing(facing);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		updateWatchingDirection();
	}

	private void updateWatchingDirection() {
		if (living.isMotionlessbyX())
			flagX = false;
		if (living.isMotionlessbyY())
			flagY = false;

		if (!flagY && living.getVelX() < 0) {
			setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		if (!flagY && living.getVelX() > 0) {
			setFacing(DIRECTION.RIGHT);
			flagX = true;
		}

		if (!flagX && living.getVelY() < 0) {
			setFacing(DIRECTION.UP);
			flagY = true;
		}
		if (!flagX && living.getVelY() > 0) {
			setFacing(DIRECTION.DOWN);
			flagY = true;
		}
	}
	
}