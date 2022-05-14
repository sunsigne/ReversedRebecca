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

	////////// RENDER ////////////

	private BufferedImage image;

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

}
