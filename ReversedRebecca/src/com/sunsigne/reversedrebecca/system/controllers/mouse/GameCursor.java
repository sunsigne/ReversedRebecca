package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GameCursor {

	public Cursor getDefaultCursor() {
		return Cursor.getDefaultCursor();
	}

	public void setVisible(boolean visible) {

		Cursor cursor = getEmptyCursor();

		if (visible)
			cursor = getGameCursor();

		Game.getInstance().setCursor(cursor);
	}

	public Cursor getGameCursor() {

		BufferedImage gameCursorImg = new ImageTask().loadImage("game_cursor");
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(gameCursorImg, new Point(0, 0), "game cursor");

		return cursor;
	}

	private Cursor getEmptyCursor() {

		BufferedImage emptyImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(emptyImg, new Point(0, 0), "empty cursor");

		return cursor;
	}

}
