package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public interface PlayerAvoider extends Pusher, PathSearcher {

	////////// PLAYER AVOIDER ////////////

	boolean isPlayerBlockingAvoider();

	void setPlayerBlockingAvoider(boolean playerBlockingAvoider);

	AVOIDERTYPE getPlayerAvoiderType();

	default void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {

		if (playerAvoiderType == AVOIDERTYPE.PUSH_HURT)
			setHurtWhenPushing(true);
		else
			setHurtWhenPushing(false);

		switch (playerAvoiderType) {

		case AROUND:
		case STOP:
			setPushingDirection(null);
			break;
		case PUSH:
		case PUSH_HURT:
			setPushingDirection(getImpliedPushingDirection(this));
			break;
		case PUSH_LEFT:
			setPushingDirection(PUSHING_DIRECTION.LEFT);
			break;
		case PUSH_RIGHT:
			setPushingDirection(PUSHING_DIRECTION.RIGHT);
			break;
		case PUSH_UP:
			setPushingDirection(PUSHING_DIRECTION.UP);
			break;
		case PUSH_DOWN:
			setPushingDirection(PUSHING_DIRECTION.DOWN);
			break;
		}
	}

	private PUSHING_DIRECTION getImpliedPushingDirection(Pusher pusher) {
		if (pusher instanceof LivingObject)
			return PUSHING_DIRECTION.FACING_OF_PUSHER;
		else
			return PUSHING_DIRECTION.OPPOSITE_OF_PUSHABLE;
	}

	////////// AVOIDER TYPE ////////////

	public enum AVOIDERTYPE {
		AROUND("around"), STOP("stop"), PUSH("push"), PUSH_HURT("push_hurt"), PUSH_LEFT("push_left"),
		PUSH_RIGHT("push_right"), PUSH_UP("push_up"), PUSH_DOWN("push_down");

		private String name;

		AVOIDERTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
