package com.sunsigne.reversedrebecca.puzzle.bomb.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzle;

public class GreenBombPuzzle extends BombPuzzle {

	public GreenBombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, int x, int y) {
		return new BombObject(puzzle, x, y);
	}

	@Override
	public int getBombAmount() {
		return 4;
	}

	@Override
	public void createPuzzle() {
		createBombs();
		setRandomMaxCountBetween(2, 5);
	}

}
