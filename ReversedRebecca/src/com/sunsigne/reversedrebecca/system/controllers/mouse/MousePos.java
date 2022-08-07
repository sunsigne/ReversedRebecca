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
		int x = (int) b.getX();
		int y = (int) b.getY();
		int[] mousePos = { x, y };

		return mousePos;
	}

	public void setX(int x) {
		try {
			if (mouseOver(get(), 0, 0, Window.WIDHT, Window.HEIGHT))
				new Robot().mouseMove(x, get()[1]);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void setY(int y) {
		try {
			if (mouseOver(get(), 0, 0, Window.WIDHT, Window.HEIGHT))
				new Robot().mouseMove(get()[0], y);
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
