package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

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

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setX((getX()/* + getMiniX() */ + getBounds().width));
			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setX((getX() /* + getMiniX() */ - detectorObject.getWidth()));
			if (listener != null)
				listener.doAction();
		}

		if (detectorObject.getBounds(DIRECTION.UP).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setY((getY()/* + getMiniY() */ + getBounds().height));
			if (listener != null)
				listener.doAction();
		}
		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds())) {
			if (blockPass)
				detectorObject.setY((getY() /* + getMiniY() */ - detectorObject.getHeight()));
			if (listener != null)
				listener.doAction();

		}
	}

}
