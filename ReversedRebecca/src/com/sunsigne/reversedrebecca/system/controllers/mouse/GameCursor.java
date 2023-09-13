package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GameCursor {

	public static final int SPEED = 12;	
	private static Cursor cursor;

	public void refreshCursor() {
		Game.getInstance().setCursor(cursor);
	}

	public void setCursor(CURSOR_TYPE cursorType) {
		if (cursorType == null)
			cursorType = CURSOR_TYPE.NULL;

		cursor = cursorType.getCursor();

		if (ControllerManager.getInstance().isUsingGamepad() == false)
			Game.getInstance().setCursor(cursor);
	}

	////////// CURSOR TYPE ////////////

	public enum CURSOR_TYPE {
		NULL("null_cursor"), NORMAL("game_cursor");

		private Cursor cursor;

		CURSOR_TYPE(String name) {
			BufferedImage img = new ImageTask().loadImage("textures/cursor/" + name);
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(rescaleImage(img), new Point(0, 0), name);
		}

		public Cursor getCursor() {
			return cursor;
		}
	}

	private static BufferedImage rescaleImage(BufferedImage image) {
		int x = (int) (image.getWidth() / Window.SCALE_X);
		int y = (int) (image.getHeight() / Window.SCALE_Y);

		BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();

		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return img;
	}

}
