package com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class UnlitBombKeyObject extends BombKeyObject {

	public UnlitBombKeyObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

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
