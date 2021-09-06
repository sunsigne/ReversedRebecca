package com.sunsigne.reversedrebecca.object.collision;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public interface ICollisionDetection {

	public CollisionDetector getCollisionDetector();

	public default Rectangle getBounds(DIRECTION direction, GameObject cDetectorObject) {
		switch (direction) {
		case LEFT:
			return getBoundsLeft(cDetectorObject);
		case RIGHT:
			return getBoundsRight(cDetectorObject);
		case UP:
			return getBoundsUp(cDetectorObject);
		case DOWN:
			return getBoundsDown(cDetectorObject);
		case NULL:
			return null;
		}
		return null;
	}

	private Rectangle getBoundsLeft(GameObject cDetectorObject) {
		int x = cDetectorObject.getX();
		int y = cDetectorObject.getY() + cDetectorObject.getHeight() / 8;
		int w = cDetectorObject.getWidth() / 6;
		int h = 6 * cDetectorObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsRight(GameObject cDetectorObject) {
		int x = cDetectorObject.getX() + 5 * cDetectorObject.getWidth() / 6;
		int y = cDetectorObject.getY() + cDetectorObject.getHeight() / 8;
		int w = cDetectorObject.getWidth() / 6;
		int h = 6 * cDetectorObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsUp(GameObject cDetectorObject) {
		int x = cDetectorObject.getX() + cDetectorObject.getWidth() / 4;
		int y = cDetectorObject.getY();
		int w = cDetectorObject.getWidth() / 2;
		int h = cDetectorObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown(GameObject cDetectorObject) {
		int x = cDetectorObject.getX() + cDetectorObject.getWidth() / 4;
		int y = cDetectorObject.getY() + cDetectorObject.getHeight() / 2;
		int w = cDetectorObject.getWidth() / 2;
		int h = cDetectorObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

}
