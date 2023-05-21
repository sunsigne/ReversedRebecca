package com.sunsigne.reversedrebecca.object.characteristics;

public interface MouseObject extends Velocity {

	default boolean replaceMouse() {
		return true;
	}
	
	default void followMouse(int mouseX, int mouseY) {
		setX(mouseX);
		setY(mouseY);
	}
	
	default void keepWithinZone(int mouseX, int mouseY, int xmin, int xmax, int ymin, int ymax) {
		if (mouseX > xmax)
			setX(xmax);
		if (mouseX < xmin)
			setX(xmin);

		if (mouseY > ymax)
			setY(ymax);
		if (mouseY < ymin)
			setY(ymin);
	}
	
}
