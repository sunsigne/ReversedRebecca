package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public interface CollisionDetector extends Position {

	public void setLastCollidedObject(CollisionReactor lastCollidedObject);
	
	public CollisionReactor getLastCollidedObject();
	
	public default Rectangle getBounds() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new Rectangle(x, y, w, h);
	}
	
	public default Rectangle getBounds(DIRECTION direction) {
		switch (direction) {
		case LEFT:
			return getBoundsLeft();
		case RIGHT:
			return getBoundsRight();
		case UP:
			return getBoundsUp();
		case DOWN:
			return getBoundsDown();
		case NULL:
			return null;
		}
		return null;
	}

	private Rectangle getBoundsLeft() {
		int x = getX();
		int y = getY() + getHeight() / 3;
		int w = getWidth() / 3;
		int h = getHeight() / 3;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsRight() {
		int x = getX() + 2 * getWidth() / 3;
		int y = getY() + getHeight() / 3;
		int w = getWidth() / 3;
		int h = getHeight() / 3;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsUp() {
		int x = getX() + getWidth() / 3;
		int y = getY();
		int w = getWidth() / 3;
		int h = getHeight() / 3;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown() {
		int x = getX() + getWidth() / 3;
		int y = getY() + 2 * getHeight() / 3;
		int w = getWidth() / 3;
		int h = getHeight() / 3;
		return new Rectangle(x, y, w, h);
	}
}
