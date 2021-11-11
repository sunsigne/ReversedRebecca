package com.sunsigne.reversedrebecca.object.characteristics;

public interface Position {

	////////// POSITION ////////////

	int getX();

	int getY();

	void setX(int x);

	void setY(int y);

	////////// SIZE ////////////

	int getWidth();

	int getHeight();

	default int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX();
		rect[1] = getY();
		rect[2] = getWidth();
		rect[3] = getHeight();
		return rect;
	}
}
