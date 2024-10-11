package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public interface MouseObject extends Velocity {

	default boolean replaceMouse() {
		return true;
	}

	default void followMouse(int mouseX, int mouseY) {
		if (Math.abs(Math.abs(getX()) - Math.abs(mouseX)) > 5)
			setX(mouseX);
		if (Math.abs(Math.abs(getY()) - Math.abs(mouseY)) > 5)
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

	default void keepMouseWithinZone(int mouseX, int mouseY, int xmin, int xmax, int ymin, int ymax) {
		MousePos mousePos = new MousePos();

		if (mouseX > xmax)
			mousePos.setX(xmax);
		if (mouseX < xmin)
			mousePos.setX(xmin);

		if (mouseY > ymax)
			mousePos.setY(ymax);
		if (mouseY < ymin)
			mousePos.setY(ymin);
	}

}
