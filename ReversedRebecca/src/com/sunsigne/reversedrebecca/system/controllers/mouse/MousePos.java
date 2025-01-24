package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import com.sunsigne.reversedrebecca.system.Window;

public class MousePos {

	public MousePos() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mouseX = (int) (b.getX() / Window.SCALE_X);
		mouseY = (int) (b.getY() / Window.SCALE_Y);
	}

	private int mouseX;
	private int mouseY;

	public int[] get() {
		return new int[] { mouseX, mouseY };
	}

	public int getX() {
		return mouseX;
	}

	public int getY() {
		return mouseY;
	}

	public void setX(int x) {
		setX(x, false);
	}

	public void setX(int x, boolean paramount) {
		try {
			if (mouseOver(0, 0, Window.WIDHT, Window.HEIGHT) || paramount)
				new Robot().mouseMove((int) (x * Window.SCALE_X), (int) (new MousePos().getY() * Window.SCALE_Y));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void setY(int y) {
		setY(y, false);
	}

	public void setY(int y, boolean paramount) {
		try {
			if (mouseOver(0, 0, Window.WIDHT, Window.HEIGHT) || paramount)
				new Robot().mouseMove((int) (new MousePos().getX() * Window.SCALE_X), (int) (y * Window.SCALE_Y));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private boolean mouseOver(int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width) {
			if (mouseY > y && mouseY < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

}
