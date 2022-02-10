package com.sunsigne.reversedrebecca.puzzle;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
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

	public int getCol(int col) {
		return 2 * Size.XS + col * Size.L;
	}

	public int getRow(int row) {
		return Size.XS + row * Size.L;
	}

	////////// NAME ////////////

	public abstract String getName();

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
	
	////////// PUZZLE ////////////
	
	public abstract void createPuzzle();
	
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
	}

	private void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();

		for (int col = 0; col < 13; col++) {
			handler.addObject(new WallPuzzle(this, getCol(col), getRow(0)));
			handler.addObject(new WallPuzzle(this, getCol(col), getRow(7)));
		}
		for (int row = 0; row < 8; row++) {
			handler.addObject(new WallPuzzle(this, getCol(0), getRow(row)));
			handler.addObject(new WallPuzzle(this, getCol(13), getRow(row)));
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
