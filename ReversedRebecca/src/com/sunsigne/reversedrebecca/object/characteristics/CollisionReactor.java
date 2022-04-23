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
		collidingReaction(detectorObject, true, null);
	}

	default void collidingReaction(CollisionDetector detectorObject, boolean blockPass, GenericListener listener) {

		boolean actionDone = false;

		TilePos tilePos = new TilePos();

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds())) {
			if (blockPass) {
				if (detectorObject instanceof Player)
					detectorObject.setX(getX() + getBounds().width);
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
					detectorObject.setX(getX() - detectorObject.getWidth());
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
					detectorObject.setY(getY() + getBounds().height);
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
					detectorObject.setY(getY() - detectorObject.getHeight());
				else
					detectorObject.setY(tilePos.getTilePos(getY() - detectorObject.getHeight(), getSize()));
			}

			if (listener != null && !actionDone) {
				actionDone = true;
				listener.doAction();
			}
		}
	}

}
