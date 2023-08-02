package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DiscoPlayerArrowObject extends PuzzleObject implements TickFree, CollisionDetector {

	public DiscoPlayerArrowObject(Puzzle puzzle, DIRECTION facing, int x) {
		super(puzzle, false, x, 200);
		this.facing = facing;
	}

	private DIRECTION facing;

	////////// NAME ////////////

	protected String getName() {
		return "PLAYER ARROW";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + "FACING:" + facing.getName();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_player_arrow_" + facing.getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////
	
	@Override
	public Rectangle getBounds(DIRECTION direction) {
		switch (direction) {
		case UP:
			return getBoundsUp();
		case DOWN:
			return getBoundsDown();
		default :
			return new Rectangle(getX(), getY() + getHeight() / 2 - 5, getWidth(), 10);
		}
	}

	private Rectangle getBoundsUp() {
		int x = getX() + getWidth() / 3;
		int y = getY();
		int w = getWidth() / 3;
		int h = getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown() {
		int x = getX() + getWidth() / 3;
		int y = getY() + 7 * getHeight() / 8;
		int w = getWidth() / 3;
		int h = getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}
	
}
