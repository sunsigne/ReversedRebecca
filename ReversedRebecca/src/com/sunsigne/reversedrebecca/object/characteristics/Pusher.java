package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.pattern.GameTimer;

public interface Pusher extends Stunnable, CollisionReactor {

	////////// PUSHING DIRECTION ////////////

	public enum PUSHING_DIRECTION {
		LEFT, RIGHT, UP, DOWN, FACING_OF_PUSHER, OPPOSITE_OF_PUSHABLE;
	}

	////////// PUSHER ////////////

	boolean hurtWhenPushing();

	void setHurtWhenPushing(boolean hurtWhenPushing);

	default int getPushingTime() {
		return 10;
	}

	PUSHING_DIRECTION getPushingDirection();

	void setPushingDirection(PUSHING_DIRECTION pushingDirection);

	default void push(Pushable pushable) {
		if (isStunned()) {
			blockPath(pushable);
			return;
		}

		if (getPushingDirection() == null) {
			return;
		}

		stunPusher();
		stunPushable(pushable);
		pushPushable(pushable);
		hurtPushable(pushable);
	}

	private void stunPusher() {
		if (!hurtWhenPushing())
			return;

		setStunned(true);
		new GameTimer(30, () -> setStunned(false));
	}

	private void stunPushable(Pushable pushable) {
		pushable.setStunned(true);
	}

	private void pushPushable(Pushable pushable) {
		pushable.setSpeedness(SPEEDNESS.SWIFT);
		pushingToward(pushable, getDirection(this, pushable));
		prepareForStop(pushable);
	}

	private void hurtPushable(Pushable pushable) {
		if (!hurtWhenPushing())
			return;

		if (pushable instanceof Health == false)
			return;

		Health health = (Health) pushable;
		health.removeHp();
	}

	private DIRECTION getDirection(Pusher pusher, Pushable pushable) {

		switch (getPushingDirection()) {
		case LEFT:
			return DIRECTION.LEFT;
		case RIGHT:
			return DIRECTION.RIGHT;
		case UP:
			return DIRECTION.UP;
		case DOWN:
			return DIRECTION.DOWN;
		case FACING_OF_PUSHER:
			if (pusher instanceof Facing)
				return ((Facing) pusher).getFacing();
			break;
		case OPPOSITE_OF_PUSHABLE:
			if (pushable instanceof Facing)
				return ((Facing) pushable).getOppositeFacing();
			break;
		}

		return DIRECTION.DOWN;
	}

	private void pushingToward(Pushable pushable, DIRECTION facing) {
		if (facing == DIRECTION.LEFT)
			pushable.setVelX(-pushable.getSpeed());
		if (facing == DIRECTION.RIGHT)
			pushable.setVelX(pushable.getSpeed());
		if (facing == DIRECTION.UP)
			pushable.setVelY(-pushable.getSpeed());
		if (facing == DIRECTION.DOWN)
			pushable.setVelY(pushable.getSpeed());
	}

	private void prepareForStop(Pushable pushable) {
		new GameTimer(getPushingTime(), () -> {
			pushable.setSpeedness(SPEEDNESS.NORMAL);
			pushable.setStunned(false);
		});
	}

}
