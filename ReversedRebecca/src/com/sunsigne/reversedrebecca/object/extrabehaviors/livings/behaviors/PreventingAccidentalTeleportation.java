package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.system.Size;

public class PreventingAccidentalTeleportation implements TickBehavior {

	public PreventingAccidentalTeleportation(LivingObject living) {
		this.living = living;

		registered_X = living.getX();
		registered_Y = living.getY();
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// TICK ////////////

	private int registered_X;
	private int registered_Y;

	@Override
	public void tick() {

		if (goingTooFast()) {
			living.setX(registered_X);
			living.setY(registered_Y);
			living.setMotionless();
		}

		registered_X = living.getX();
		registered_Y = living.getY();
	}

	private boolean goingTooFast() {
		return goingTooFast(DIRECTION.LEFT) | goingTooFast(DIRECTION.RIGHT) | goingTooFast(DIRECTION.UP)
				| goingTooFast(DIRECTION.DOWN);
	}

	private boolean goingTooFast(DIRECTION facing) {
		switch (facing) {

		case LEFT:
			return living.getX() < registered_X - Size.M;
		case RIGHT:
			return living.getX() > registered_X + Size.M;
		case UP:
			return living.getY() < registered_Y - Size.M;
		case DOWN:
			return living.getY() > registered_Y + Size.M;
		default:
			return false;
		}
	}

}