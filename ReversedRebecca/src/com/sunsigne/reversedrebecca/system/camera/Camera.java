package com.sunsigne.reversedrebecca.system.camera;

import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.system.Window;

public class Camera {

	////////// POSITION ////////////

	private static float x, y;

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		Camera.x = x;
	}

	public void setY(float y) {
		Camera.y = y;
	}

	public boolean isObjectVisible(Position position) {
		int px = position.getX() + position.getWidth() / 2;
		int py = position.getY() + position.getHeight() / 2;

		if (px > -x && px < -x + Window.WIDHT) {
			if (py > -y && py < -y + Window.HEIGHT) {
				return true;
			} else
				return false;
		} else
			return false;
	}

}
