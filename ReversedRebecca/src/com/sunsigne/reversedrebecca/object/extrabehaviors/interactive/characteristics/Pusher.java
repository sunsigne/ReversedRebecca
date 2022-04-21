package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.TilePos;

public interface Pusher extends CollisionReactor {

	boolean hurtWhenPushing();

	default int getPushingTime() {
		return 1; // supposed to be 10 ticks
	}

	default void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Pushable)
			push((Pushable) detectorObject);
	}

	default void push(Pushable pushable) {
		shiftPusher();
//		stunPusher();

		pushObject(pushable);
		hurtObject(pushable);
	}

	private void shiftPusher() {
		setX(new TilePos().getTilePos(getX(), getSize()));
		setY(new TilePos().getTilePos(getY(), getSize()));
	}

	private void pushObject(Pushable pushable) {
		
		// paralyse
		pushable.setStunned(true);
		pushable.setMotionless();
		pushable.setSpeedness(SPEEDNESS.FAST);

		// push
		pushingToward(pushable, DIRECTION.DOWN);
		prepareForStop(pushable);
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
		@SuppressWarnings("unused")
		GameTimer timer = new GameTimer(getPushingTime(), () -> {
			
			// stabilize
			pushable.setStunned(false);
			pushable.setMotionless();
			pushable.setSpeedness(SPEEDNESS.NORMAL);
		});
	}

	private void hurtObject(Pushable pushable) {
		if (!hurtWhenPushing())
			return;

		if (pushable instanceof PiranhaPlayer)
			PlayerHealth.getInstance().removeHp();
	}

}
