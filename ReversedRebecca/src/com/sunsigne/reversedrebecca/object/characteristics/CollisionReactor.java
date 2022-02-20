package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.TilePos;
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

	boolean isBlockingPath();
	
	default void blockPath(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, true, null);
	}

	default void collidingReaction(CollisionDetector detectorObject, boolean blockPass, GenericListener listener) {

		TilePos tilePos = new TilePos();
		int size = Size.M;
		
		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setX(tilePos.getTilePos(getX()/* + getMiniX() */ + getBounds().width, size));
			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setX(tilePos.getTilePos(getX() /* + getMiniX() */ - detectorObject.getWidth(), size));
			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.UP).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setY(tilePos.getTilePos(getY()/* + getMiniY() */ + getBounds().height, size));
			if (listener != null)
				listener.doAction();
		}
		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setY(tilePos.getTilePos(getY() /* + getMiniY() */ - detectorObject.getHeight(), size));
			if (listener != null)
				listener.doAction();

		}
	}

}
