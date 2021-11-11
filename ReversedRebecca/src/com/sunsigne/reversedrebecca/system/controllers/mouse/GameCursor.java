package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class GameCursor implements Updatable {

	////////// VISIBILITY ////////////
	
	public void setVisible(boolean visible) {
		BufferedImage emptyImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(emptyImg, new Point(0, 0), "empty cursor");
		Cursor cursor = visible ? Cursor.getDefaultCursor() : emptyCursor;
		Game.getInstance().setCursor(cursor);
	}
	
	////////// POSITION ////////////

	private static Point pos;

	public Point getPos() {
		return pos;
	}
		
	////////// TICK ////////////

	@Override
	public void tick() {
		pos = MouseInfo.getPointerInfo().getLocation();
	}

	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {

	}

}
