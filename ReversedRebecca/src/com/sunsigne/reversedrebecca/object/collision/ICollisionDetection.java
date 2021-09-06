package com.sunsigne.reversedrebecca.object.collision;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;

public interface ICollisionDetection {

	public CollisionDetector getCollisionDetector();

	public default Rectangle getBoundsLeft(GameObject collidingObject) {
		int x = collidingObject.getX();
		int y = collidingObject.getY() + collidingObject.getHeight() / 8;
		int w = collidingObject.getWidth() / 6;
		int h = 6 * collidingObject.getHeight() / 8;
		return new Rectangle(x, y + h / 8, w / 6, 6 * w / 8);
	}

	public default Rectangle getBoundsRight(GameObject collidingObject) {
		int x = collidingObject.getX() + 5 * collidingObject.getWidth() / 6;
		int y = collidingObject.getY() + collidingObject.getHeight() / 8;
		int w = collidingObject.getWidth() / 6;
		int h = 6 * collidingObject.getHeight() / 8;
		return new Rectangle(x + 5 * w / 6, y + h / 8, w / 6, 6 * w / 8);
	}

	public default Rectangle getBoundsTop(GameObject collidingObject) {
		int x = collidingObject.getX() + collidingObject.getWidth() / 4;
		int y = collidingObject.getY();
		int w = collidingObject.getWidth() / 2;
		int h = collidingObject.getHeight() / 2;
		return new Rectangle(x + w / 4, y, w / 2, h / 2);
	}

	public default Rectangle getBoundsDown(GameObject collidingObject) {
		int x = collidingObject.getX() + collidingObject.getWidth() / 4;
		int y = collidingObject.getY() + collidingObject.getHeight() / 2;
		int w = collidingObject.getWidth() / 2;
		int h = collidingObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

}
