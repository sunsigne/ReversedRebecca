package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
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

	public enum CURSOR_TYPE implements SheetableImage {
		NULL("null"), NORMAL("game");

		private Cursor cursor;
		private String name;

		CURSOR_TYPE(String name) {
			this.name = name;
		}

		public Cursor getCursor() {
			if (cursor == null) {
				BufferedImage sheet = new ImageTask().loadImage("textures/" + "cursor");
				BufferedImage image = getSheetSubImage(sheet);
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(rescaleImage(image), new Point(0, 0), name);
			}
			return cursor;
		}

		////////// UPDATABLE ////////////

		@Override
		public void tick() {

		}

		@Override
		public void render(Graphics g) {

		}

		////////// TEXTURE ////////////

		@Override
		public int getSheetSize() {
			return 32;
		}

		@Override
		public int getSheetRowCriterion() {
			return 1;
		}

		@Override
		public int getSheetColCriterion() {
			switch (name) {
			case "null":
				return 1;
			case "game":
				return 2;
			}
			return 0;
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
