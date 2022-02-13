package com.sunsigne.reversedrebecca.puzzle.key;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class KeyPuzzle extends Puzzle {

	public KeyPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
		new GameCursor().setVisible(false);
		
		createDeathWall();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}

	////////// PUZZLE ////////////
	
	private void createDeathWall() {
		for (int row = 1; row <= 6; row++) {
			// it is almost invisible in VisibleHitboxMode because this is added before Walls
			LAYER.PUZZLE.addObject(new KillPuzzleObject(this, getCol(0), getRow(row)));
		}
	}
	
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

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		g.setColor(green);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
