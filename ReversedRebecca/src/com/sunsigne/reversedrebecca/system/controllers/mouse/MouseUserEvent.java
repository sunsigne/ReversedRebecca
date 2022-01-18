package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseEvent;

public interface MouseUserEvent {

	////////// MOUSE ////////////
	
	MouseController getMouseController();

	void mousePressed(MouseEvent e);

	void mouseReleased(MouseEvent e);
	
	default boolean mouseOver(MouseEvent e, int x, int y, int width, int height) {
		int mx = e.getX();
		int my = e.getY();
		
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	default boolean mouseOver(MouseEvent e, int[] rect) {
		if (rect.length == 4)
			return mouseOver(e, rect[0], rect[1], rect[2], rect[3]);
		else
			System.err.println("the method mouseOver(e, rect) is not used correcly");
		return false;
	}
}
