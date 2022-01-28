package com.sunsigne.reversedrebecca.pattern;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class MousePos {

	public int[] get() {
		
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();		
		int x = (int) b.getX();
		int y = (int) b.getY();
		int[] mousePos = {x, y};
		
		return mousePos;
	}
	
}
