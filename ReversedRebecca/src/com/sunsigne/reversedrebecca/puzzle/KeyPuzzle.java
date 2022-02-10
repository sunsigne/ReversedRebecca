package com.sunsigne.reversedrebecca.puzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.key.KeyPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.LockPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.UpsideDownLockObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class KeyPuzzle extends Puzzle {

	public KeyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		super(difficulty, actionOnWinning);
		new GameCursor().setVisible(false);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		LAYER.PUZZLE.addObject(new LockPuzzleObject(this, getCol(1), getRow(4)));
		LAYER.PUZZLE
				.addObject(new KeyPuzzleObject(this, getCol(12), getRow(new RandomGenerator().getIntBetween(1, 6))));

		for (int row = 1; row <= 6; row++) {
			LAYER.PUZZLE.addObject(new KillPuzzleObject(this, getCol(0), getRow(row)));
		}
	}

	private void createRandompWalls(int numOfWalls) {
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

	private void doubleKeySpeed() {
		for (Updatable object : LAYER.PUZZLE.getHandler().getList()) {
			if (object instanceof KeyPuzzleObject == false)
				continue;

			KeyPuzzleObject key = (KeyPuzzleObject) object;
			key.speed = key.speed * 2;
			// refresh of the speed
			key.setVelY(new RandomGenerator().getBoolean() ? key.speed : -key.speed);
			break;
		}
	}

	private void upsideDownLock() {
		for (Updatable object : LAYER.PUZZLE.getHandler().getList()) {
			if (object instanceof LockPuzzleObject == false)
				continue;

			LAYER.PUZZLE.getHandler().removeObject(object);
			break;
		}
		LAYER.PUZZLE.addObject(new UpsideDownLockObject(this, getCol(1), getRow(4)));
	}

	////////// DIFFICULTY ////////////

	@Override
	public void createCyanPuzzle() {
		createRandompWalls(1);
	}

	@Override
	public void createGreenPuzzle() {
		createRandompWalls(4);
	}

	@Override
	public void createYellowPuzzle() {
		createRandompWalls(25);
	}

	@Override
	public void createOrangePuzzle() {
		createRandompWalls(25);
		doubleKeySpeed();
	}

	@Override
	public void createRedPuzzle() {
		createRandompWalls(25);
		doubleKeySpeed();
		upsideDownLock();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		g.setColor(green);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
