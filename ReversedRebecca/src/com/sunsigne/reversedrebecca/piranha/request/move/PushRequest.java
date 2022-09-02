package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;

public class PushRequest implements Request {

	////////// REQUEST ////////////

	public PushRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new PushRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PUSH";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof Pushable == false)
			return;

		DIRECTION facing = getFacing(target);
		if (facing == null || facing == DIRECTION.NULL)
			return;

		Pusher pusher = getPusher(object, facing);
		Pushable pushable = (Pushable) object;

		pusher.push(pushable);
	}

	private DIRECTION getFacing(String target) {
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target))
				return tempFacing;
		}
		return null;
	}

	private Pusher getPusher(PiranhaObject object, DIRECTION facing) {

		Pusher pusher = new Pusher() {

			public boolean isStunned() {
				return false;
			}

			public void setStunned(boolean stunned) {
			}

			@Override
			public void collidingReaction(CollisionDetector detectorObject) {
			}

			public boolean isBlockingSight() {
				return false;
			}

			public boolean isBlockingPath() {
				return false;
			}

			public int getX() {
				return object.getX();
			}

			public int getY() {
				return object.getY();
			}

			public void setX(int x) {
			}

			@Override
			public void setY(int y) {
			}

			@Override
			public int getWidth() {
				return Size.M;
			}

			public int getHeight() {
				return Size.M;
			}

			@Override
			public boolean hurtWhenPushing() {
				return false;
			}

			@Override
			public void setHurtWhenPushing(boolean hurtWhenPushing) {
			}

			@Override
			public PUSHING_DIRECTION getPushingDirection() {
				switch (facing) {
				case LEFT:
					return PUSHING_DIRECTION.LEFT;
				case RIGHT:
					return PUSHING_DIRECTION.RIGHT;
				case UP:
					return PUSHING_DIRECTION.UP;
				case DOWN:
					return PUSHING_DIRECTION.DOWN;
				default:
					return null;
				}
			}

			@Override
			public void setPushingDirection(PUSHING_DIRECTION pushingDirection) {
			}
		};

		return pusher;
	}

}
