package com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class UnlitBombKeyObject extends MovingBombKeyObject {

	public UnlitBombKeyObject(Puzzle puzzle, boolean critical, boolean moving, int x, int y) {
		super(puzzle, critical, x, y);

		if (moving == false)
			defineNewYSpeed(0);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		goesUpAndDown();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return isCritical() ? 2 : 1;
	}

	private BufferedImage image;

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_bomb_unlit");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

}
