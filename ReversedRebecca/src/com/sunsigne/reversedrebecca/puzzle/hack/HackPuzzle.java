package com.sunsigne.reversedrebecca.puzzle.hack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.LockObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class HackPuzzle extends Puzzle {

	public HackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
		new GameCursor().setVisible(false);
		
		createVirus();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hack";
	}

	////////// PUZZLE ////////////
	
	private void createVirus() {		
		PuzzleObject virus = new VirusObject(this, getCol(3), getRow(6));
		LAYER.PUZZLE.addObject(virus);
	}

/*
	protected void createRandompWalls(int numOfWalls) {
		if (numOfWalls <= 0)
			return;

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage img = getWallTexture();
		int safeRow = getRow(new RandomGenerator().getIntBetween(1, 6));

		int count = 0;

		while (count < numOfWalls) {
			count++;

			int radCol = getCol(new RandomGenerator().getIntBetween(2, 11));
			int radRow;
			do {
				radRow = getRow(new RandomGenerator().getIntBetween(1, 6));
			} while (radRow == safeRow);

			handler.addObject(new WallPuzzle(img, radCol, radRow));
			handler.addObject(new KillPuzzleObject(this, radCol, radRow));
		}
	}
*/
	////////// OPEN ////////////

	@Override
	protected BufferedImage getWallTexture() {
		String rad = String.valueOf(Math.round(Math.random()));
		return new ImageTask().loadImage("textures/puzzle/" + getName() + "_wall_" + rad);
	}

	@Override
	protected void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage img = null;

		for (int col = 0; col < 13; col++) {
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(0)));
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(7)));
		}
		for (int row = 0; row < 8; row++) {
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(0), getRow(row)));
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(13), getRow(row)));
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color blue = new Color(10, 10, 50, 240);
		g.setColor(blue);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
