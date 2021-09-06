package com.sunsigne.reversedrebecca.object.collision;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.util.GenericListener;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public interface ICollisionReaction {

	void collidingReaction(GameObject clnDetectorObject);

	public default Rectangle getBounds(GameObject clnReactorObject) {
		int x = clnReactorObject.getX();
		int y = clnReactorObject.getY();
		int w = clnReactorObject.getWidth();
		int h = clnReactorObject.getHeight();
		return new Rectangle(x, y, w, h);
	}

	public default void blockPass(GameObject clnDetectorObject, GameObject clnReactorObject) {
		collidingReaction(clnDetectorObject, clnReactorObject, true, null);
	}

	public default void collidingReaction(GameObject clnDetectorObject, GameObject clnReactorObject, boolean blockPass,
			GenericListener listener) {

		if (clnDetectorObject.getBounds(DIRECTION.LEFT).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setX((clnReactorObject.getX()
						/* + clnReactorObject.getMiniX() */ + clnReactorObject.getBounds().width));
			if (listener != null)
				listener.doAction();
		}

		if (clnDetectorObject.getBounds(DIRECTION.RIGHT).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setX(
						(clnReactorObject.getX() /* + clnReactorObject.getMiniX() */ - clnDetectorObject.getWidth()));
			if (listener != null)
				listener.doAction();
		}

		if (clnDetectorObject.getBounds(DIRECTION.UP).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setY((clnReactorObject.getY()
						/* + clnReactorObject.getMiniY() */ + clnReactorObject.getBounds().height));
			if (listener != null)
				listener.doAction();
		}
		if (clnDetectorObject.getBounds(DIRECTION.DOWN).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setY(
						(clnReactorObject.getY() /* + clnReactorObject.getMiniY() */ - clnDetectorObject.getHeight()));
			if (listener != null)
				listener.doAction();

		}
	}

}
