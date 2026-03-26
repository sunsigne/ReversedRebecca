package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public interface CollisionReactor extends Position {

	public default Rectangle getBounds() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new Rectangle(x, y, w, h);
	}

	////////// COLLISION ////////////

	void collidingReaction(CollisionDetector detectorObject);

	boolean isBlockingSight();

	boolean isBlockingPath();

	default void blockPath(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, isBlockingPath(), null);
	}

	default void collidingReaction(CollisionDetector detectorObject, boolean blockPass, GenericListener listener) {
		if (blockPass && multipleCollisionsCase(detectorObject, blockPass, listener))
			return;

		boolean actionDone = false;

		TilePos tilePos = new TilePos();

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds())) {
			if (blockPass) {
				if (detectorObject instanceof Player)
					detectorObject.setX(getBounds().x + getBounds().width);
				else
					detectorObject.setX(tilePos.getTilePos(getX() + getBounds().width, getSize()));
			}
			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}
		}

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(getBounds())) {
			if (blockPass) {
				if (detectorObject instanceof Player)
					detectorObject.setX(getBounds().x - detectorObject.getWidth());
				else
					detectorObject.setX(tilePos.getTilePos(getX() - detectorObject.getWidth(), getSize()));
			}

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}
		}

		if (detectorObject.getBounds(DIRECTION.UP).intersects(getBounds())) {
			if (blockPass) {
				if (detectorObject instanceof Player)
					detectorObject.setY(getBounds().y + getBounds().height);
				else
					detectorObject.setY(tilePos.getTilePos(getY() + getBounds().height, getSize()));
			}

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}
		}
		
		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds())) {
			if (blockPass) {
				if (detectorObject instanceof Player)
					detectorObject.setY(getBounds().y - detectorObject.getHeight());
				else
					detectorObject.setY(tilePos.getTilePos(getY() - detectorObject.getHeight(), getSize()));
			}

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}
		}
	}

	private boolean multipleCollisionsCase(CollisionDetector detectorObject, boolean blockPass,
			GenericListener listener) {

		// you can't handle with accuracy multiple collisions without knowing from where
		// the object is coming from
		if (detectorObject instanceof Velocity == false)
			return false;

		Velocity velocityObject = (Velocity) detectorObject;

		if (velocityObject.isMotionless())
			return false;

		int velX = velocityObject.getVelX();
		int velY = velocityObject.getVelY();
		int speed = velocityObject.getSpeed();

		// besure the scan process only if the object is "pushed"
		if (Math.abs(velX) <= speed && Math.abs(velY) <= speed)
			return false;

		boolean left = detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds());
		boolean right = detectorObject.getBounds(DIRECTION.RIGHT).intersects(getBounds());
		boolean up = detectorObject.getBounds(DIRECTION.UP).intersects(getBounds());
		boolean down = detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds());

		if (left && right == false && up == false && down == false)
			return false;
		if (left == false && right && up == false && down == false)
			return false;
		if (left == false && right == false && up && down == false)
			return false;
		if (left == false && right == false && up == false && down)
			return false;

		boolean actionDone = false;

		TilePos tilePos = new TilePos();

		// going left = coming from right
		if (Math.abs(velX) >= Math.abs(velY) && velX < 0) {
			if (detectorObject instanceof Player)
				detectorObject.setX(getBounds().x + getBounds().width);
			else
				detectorObject.setX(tilePos.getTilePos(getX() + getBounds().width, getSize()));

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}

			return true;
		}

		// going right = coming from left
		if (Math.abs(velX) >= Math.abs(velY) && velX > 0) {
			if (detectorObject instanceof Player)
				detectorObject.setX(getBounds().x - detectorObject.getWidth());
			else
				detectorObject.setX(tilePos.getTilePos(getX() - detectorObject.getWidth(), getSize()));

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}

			return true;
		}

		// going up = coming from down
		if (Math.abs(velX) < Math.abs(velY) && velY < 0) {
			if (detectorObject instanceof Player)
				detectorObject.setY(getBounds().y + getBounds().height);
			else
				detectorObject.setY(tilePos.getTilePos(getY() + getBounds().height, getSize()));

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}

			return true;
		}

		// going down = coming from up
		if (Math.abs(velX) < Math.abs(velY) && velY > 0) {
			if (detectorObject instanceof Player)
				detectorObject.setY(getBounds().y - detectorObject.getHeight());
			else
				detectorObject.setY(tilePos.getTilePos(getY() - detectorObject.getHeight(), getSize()));

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}

			return true;
		}

		// unkown case
		return false;
	}

}
