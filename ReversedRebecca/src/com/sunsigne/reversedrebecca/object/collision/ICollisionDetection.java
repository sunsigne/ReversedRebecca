package com.sunsigne.reversedrebecca.object.collision;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public interface ICollisionDetection {

	public CollisionDetector getCollisionDetector();

	public default Rectangle getBounds(DIRECTION direction, GameObject collidingObject) {
		switch (direction) {
		case LEFT:
			return getBoundsLeft(collidingObject);
		case RIGHT:
			return getBoundsRight(collidingObject);
		case UP:
			return getBoundsUp(collidingObject);
		case DOWN:
			return getBoundsDown(collidingObject);
		case NULL:
			return collidingObject.getBounds();
		}
		return collidingObject.getBounds();
	}

	private Rectangle getBoundsLeft(GameObject collidingObject) {
		int x = collidingObject.getX();
		int y = collidingObject.getY() + collidingObject.getHeight() / 8;
		int w = collidingObject.getWidth() / 6;
		int h = 6 * collidingObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsRight(GameObject collidingObject) {
		int x = collidingObject.getX() + 5 * collidingObject.getWidth() / 6;
		int y = collidingObject.getY() + collidingObject.getHeight() / 8;
		int w = collidingObject.getWidth() / 6;
		int h = 6 * collidingObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsUp(GameObject collidingObject) {
		int x = collidingObject.getX() + collidingObject.getWidth() / 4;
		int y = collidingObject.getY();
		int w = collidingObject.getWidth() / 2;
		int h = collidingObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown(GameObject collidingObject) {
		int x = collidingObject.getX() + collidingObject.getWidth() / 4;
		int y = collidingObject.getY() + collidingObject.getHeight() / 2;
		int w = collidingObject.getWidth() / 2;
		int h = collidingObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

}
