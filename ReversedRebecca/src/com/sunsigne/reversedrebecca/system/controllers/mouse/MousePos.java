package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import com.sunsigne.reversedrebecca.system.Window;

public class MousePos {

	public int[] get() {

		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) (b.getX() / Window.SCALE_X);
		int y = (int) (b.getY() / Window.SCALE_Y);
		int[] mousePos = { x, y };

		return mousePos;
	}

	public void setX(int x) {
		setX(x, false);
	}

	public void setX(int x, boolean paramount) {
		try {
			if (mouseOver(get(), 0, 0, Window.WIDHT, Window.HEIGHT) || paramount)
				new Robot().mouseMove((int) (x  * Window.SCALE_X), get()[1]);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void setY(int y) {
		setY(y, false);
	}
	
	public void setY(int y, boolean paramount) {
		try {
			if (mouseOver(get(), 0, 0, Window.WIDHT, Window.HEIGHT) || paramount)
				new Robot().mouseMove(get()[0], (int) (y  * Window.SCALE_Y));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private boolean mouseOver(int[] mousePos, int x, int y, int width, int height) {
		int mx = mousePos[0];
		int my = mousePos[1];

		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

}
