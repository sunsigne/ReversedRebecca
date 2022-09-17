package com.sunsigne.reversedrebecca.object.puzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class WallPuzzle extends GameObject implements TickFree {

	public WallPuzzle(BufferedImage image, int x, int y) {
		super(x, y, Size.L, Size.L);
		this.image = image;
	}

	////////// USEFULL ////////////

	private int getCol(int col) {
		return (col / Size.L);
	}

	private int getRow(int row) {
		return (row / Size.L);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : WALL";
		return clazz + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// RENDER ////////////

	private BufferedImage image;

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

}
