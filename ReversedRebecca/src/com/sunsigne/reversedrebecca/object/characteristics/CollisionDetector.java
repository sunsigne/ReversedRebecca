package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public interface CollisionDetector extends Position {

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
		int y = getY() + getHeight() / 8;
		int w = getWidth() / 6;
		int h = 6 * getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsRight() {
		int x = getX() + 5 * getWidth() / 6;
		int y = getY() + getHeight() / 8;
		int w = getWidth() / 6;
		int h = 6 * getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsUp() {
		int x = getX() + getWidth() / 4;
		int y = getY();
		int w = getWidth() / 2;
		int h = getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown() {
		int x = getX() + getWidth() / 4;
		int y = getY() + getHeight() / 2;
		int w = getWidth() / 2;
		int h = getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}
}
