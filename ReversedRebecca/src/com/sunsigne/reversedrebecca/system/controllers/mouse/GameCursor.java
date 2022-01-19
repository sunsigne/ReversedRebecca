package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GameCursor {

	public void setVisible(boolean visible) {
		
		Cursor cursor = getEmptyCursor();

		if (visible)
			cursor = Cursor.getDefaultCursor();

		Game.getInstance().setCursor(cursor);
	}

	private Cursor getEmptyCursor() {

		BufferedImage emptyImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(emptyImg, new Point(0, 0), "empty cursor");

		return cursor;
	}

}
