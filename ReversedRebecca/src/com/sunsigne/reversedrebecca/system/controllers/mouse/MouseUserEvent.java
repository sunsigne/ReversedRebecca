package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public interface MouseUserEvent extends Position {

	////////// MOUSE ////////////

	MouseController getMouseController();

	void mousePressed(MouseEvent e);

	void mouseReleased(MouseEvent e);

	default boolean isSelected() {
		return mouseOver(new MousePos().get(), getRect()) && isClickable();
	}
	
	default boolean isClickable() {
		for (MouseListener listener : Game.getInstance().getMouseListeners()) {
			if (listener == getMouseController())
				return true;
		}
		return false;
	}

	default boolean mouseOver(MouseEvent e, int x, int y, int width, int height) {
		int mx = e.getX();
		int my = e.getY();
		int[] mousePos = { mx, my };

		return mouseOver(mousePos, x, y, width, height);
	}

	default boolean mouseOver(int[] mousePos, int x, int y, int width, int height) {
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

	default boolean mouseOver(MouseEvent e, int[] rect) {
		if (rect.length == 4)
			return mouseOver(e, rect[0], rect[1], rect[2], rect[3]);
		else
			System.err.println("the method mouseOver(e, rect) is not used correcly");
		return false;
	}

	default boolean mouseOver(int[] mousePos, int[] rect) {
		if (rect.length == 4)
			return mouseOver(mousePos, rect[0], rect[1], rect[2], rect[3]);
		else
			System.err.println("the method mouseOver(mousePos, rect) is not used correcly");
		return false;
	}

}
