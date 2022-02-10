package com.sunsigne.reversedrebecca.object.puzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.system.Size;

public class WallPuzzle extends GameObject {

	public WallPuzzle(BufferedImage img, int x, int y) {
		super(x, y, Size.L, Size.L);
		this.img = img;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	private BufferedImage img;

	@Override
	public void render(Graphics g) {
		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
	}

}
