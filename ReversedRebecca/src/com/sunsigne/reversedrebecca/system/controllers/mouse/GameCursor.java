package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GameCursor {

	public void setCursor(CURSOR_TYPE cursorType) {
		if (cursorType == null)
			cursorType = CURSOR_TYPE.NULL;

		Game.getInstance().setCursor(cursorType.getCursor());
	}

	////////// CURSOR TYPE ////////////

	public enum CURSOR_TYPE {
		NULL("null_cursor"), NORMAL("game_cursor");

		private Cursor cursor;

		CURSOR_TYPE(String name) {
			BufferedImage img = new ImageTask().loadImage("textures/cursor/" + name);
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), name);
		}

		private Cursor getCursor() {
			return cursor;
		}
	}

}
