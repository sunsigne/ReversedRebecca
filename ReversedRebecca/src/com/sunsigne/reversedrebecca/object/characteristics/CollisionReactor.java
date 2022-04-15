package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;

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

		TilePos tilePos = new TilePos();
		PlayerFinder playerFinder = new PlayerFinder();
		int size = Size.M;

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds())) {
			if (blockPass) {
				if (playerFinder.isPlayerInvolved(detectorObject))
					detectorObject.setX(getX() + getBounds().width);
				else
					detectorObject.setX(tilePos.getTilePos(getX() + getBounds().width, size));
			}
			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(getBounds())) {
			if (blockPass) {
				if (playerFinder.isPlayerInvolved(detectorObject))
					detectorObject.setX(getX() - detectorObject.getWidth());
				else
					detectorObject.setX(tilePos.getTilePos(getX() - detectorObject.getWidth(), size));
			}

			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.UP).intersects(getBounds())) {
			if (blockPass) {
				if (playerFinder.isPlayerInvolved(detectorObject))
					detectorObject.setY(getY() + getBounds().height);
				else
					detectorObject.setY(tilePos.getTilePos(getY() + getBounds().height, size));
			}

			if (listener != null)
				listener.doAction();
		}
		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds())) {
			if (blockPass) {
				if (playerFinder.isPlayerInvolved(detectorObject))
					detectorObject.setY(getY() - detectorObject.getHeight());
				else
					detectorObject.setY(tilePos.getTilePos(getY() - detectorObject.getHeight(), size));
			}

			if (listener != null)
				listener.doAction();
		}
	}

}
