package com.sunsigne.reversedrebecca.object.puzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WallPuzzle extends PuzzleObject {

	public WallPuzzle(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		loadImage();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage img;

	private void loadImage() {
		img = new ImageTask().loadImage("textures/puzzle/wall_" + getPuzzle().getName());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
	}

}
