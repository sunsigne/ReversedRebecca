package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

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
			new Robot().mouseMove(x, get()[1]);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void setY(int y) {
		try {
			new Robot().mouseMove(get()[0], y);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
