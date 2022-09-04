package com.sunsigne.reversedrebecca.puzzle.key;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.key.MovingWallPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class KeyPuzzle extends Puzzle {

	public KeyPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
		new GameCursor().setCursor(null);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new KeyPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract LockObject getLock();
	public abstract KeyObject getKey();

	protected void createLock() {
		PuzzleObject lock = getLock();
		lock.setX(getCol(1));
		lock.setY(getRow(4));
		
		LAYER.PUZZLE.addObject(lock);
	}

	protected void createKey() {		
		PuzzleObject key = getKey();
		key.setX(getCol(12));
		key.setY(getRow(new RandomGenerator().getIntBetween(1, 6)));

		LAYER.PUZZLE.addObject(key);
	}

	protected void createRandomWalls(int numOfWalls, boolean moving) {
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

			if(moving)
				handler.addObject(new MovingWallPuzzleObject(this, img, radCol, radRow));
				
			else {
				handler.addObject(new WallPuzzle(img, radCol, radRow));
				handler.addObject(new KillPuzzleObject(this, radCol, radRow));
			}
		}
	}

	////////// OPEN ////////////
	
	@Override
	protected void createWallBorder() {
		super.createWallBorder();
		createDeathWall();
	}
	
	private void createDeathWall() {
		for (int row = 1; row <= 6; row++) {
			// Almost invisible in VisibleHitboxMode because added before Walls
			LAYER.PUZZLE.addObject(new KillPuzzleObject(this, getCol(0), getRow(row)));
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		new TransluantLayer().drawPuzzle(g, green);
	}

}
