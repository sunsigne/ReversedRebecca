package com.sunsigne.reversedrebecca.puzzle;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class Puzzle implements Updatable, Difficulty {

	public Puzzle(LVL difficulty, GenericListener actionOnWinning) {
		this.difficulty = difficulty;
		this.actionOnWinning = actionOnWinning;
	}

	////////// USEFULL ////////////

	public int getCol(int col) {
		return 2 * Size.XS + col * Size.L;
	}

	public int getRow(int row) {
		return Size.XS + row * Size.L;
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// PUZZLE ////////////

	public abstract void createPuzzle();

	////////// DIFFICULTY ////////////

	private LVL difficulty;

	@Override
	public LVL getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		this.difficulty = difficulty;
	}

	private void createDifficultyModification() {
		switch (difficulty) {
		case NULL:
		case CYAN:
			createCyanPuzzle();
			break;
		case GREEN:
			createGreenPuzzle();
			break;
		case YELLOW:
			createYellowPuzzle();
			break;
		case ORANGE:
			createOrangePuzzle();
			break;
		case RED:
			createRedPuzzle();
			break;
		}
	}

	public abstract void createCyanPuzzle();

	public abstract void createGreenPuzzle();

	public abstract void createYellowPuzzle();

	public abstract void createOrangePuzzle();

	public abstract void createRedPuzzle();

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// OPEN ////////////

	public void openPuzzle() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer())
				tempLayer.getHandler().setFreezeTicking(true);
		}
		// added as first element to render behind objects
		LAYER.PUZZLE.getHandler().getList().add(0, this);

		createWallBorder();
		createPuzzle();
		createDifficultyModification();
	}

	protected BufferedImage getWallTexture() {
		return new ImageTask().loadImage("textures/puzzle/" + getName() + "_wall");
	}

	private void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage img = getWallTexture();

		for (int col = 0; col < 13; col++) {
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(0)));
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(7)));
		}
		for (int row = 0; row < 8; row++) {
			handler.addObject(new WallPuzzle(img, getCol(0), getRow(row)));
			handler.addObject(new WallPuzzle(img, getCol(13), getRow(row)));
		}
	}

	////////// CLOSE ////////////

	private GenericListener actionOnWinning;

	public void closePuzzle(boolean isPuzzleWon) {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer())
				tempLayer.getHandler().setFreezeTicking(false);
		}
		LAYER.PUZZLE.getHandler().clear();
		new GameCursor().setVisible(true);
		if (isPuzzleWon)
			actionOnWinning.doAction();
	}
}
