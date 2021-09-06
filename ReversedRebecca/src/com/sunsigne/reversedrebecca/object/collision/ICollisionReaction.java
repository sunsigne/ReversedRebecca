package com.sunsigne.reversedrebecca.object.collision;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;

public interface ICollisionReaction {

	void collidingReaction(GameObject cDetectorObject);

	public default Rectangle getBounds(GameObject cReactorObject) {
		int x = cReactorObject.getX();
		int y = cReactorObject.getY();
		int w = cReactorObject.getWidth();
		int h = cReactorObject.getHeight();
		return new Rectangle(x, y, w, h);
	}
}
