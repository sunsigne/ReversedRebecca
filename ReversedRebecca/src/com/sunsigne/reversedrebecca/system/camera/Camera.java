package com.sunsigne.reversedrebecca.system.camera;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
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

	public boolean isObjectVisible(Position position, boolean withRoom) {
		int px = position.getX();
		int py = position.getY();
		int pw = position.getWidth();
		int ph = position.getHeight();
		
		if(withRoom == false) {
			px = px + position.getWidth() / 2;
			py = py + position.getHeight() / 2;;
			pw = 0;
			ph = 0;
		}

		if (px + 3 * pw > -x && px < -x + 2 * pw + Window.WIDHT) {
			if (py + 3 * ph > -y && py < -y + 2 * ph + Window.HEIGHT) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean isObjectCloseFromBorder(Position position, DIRECTION border) {
		int px = position.getX() + position.getWidth() / 2;
		int py = position.getY() + position.getHeight() / 2;

		switch (border) {

		case LEFT:
			if (px > -x && px < -x + Window.WIDHT / 4)
				return true;
			else
				return false;

		case RIGHT:
			if (px > -x + (3 * Window.WIDHT / 4) && px < -x + Window.WIDHT)
				return true;
			else
				return false;

		case UP:
			if (py > -y && py < -y + Window.HEIGHT / 4)
				return true;
			else
				return false;

		case DOWN:
			if (py > -y + (3 * Window.HEIGHT / 4) && py < -y + Window.HEIGHT)
				return true;
			else
				return false;

		default:
			return false;
		}
	}

}
