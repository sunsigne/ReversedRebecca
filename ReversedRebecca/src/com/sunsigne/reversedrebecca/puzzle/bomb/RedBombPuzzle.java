package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.ShrinkBombObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class RedBombPuzzle extends BombPuzzle {

	public RedBombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, int x, int y) {
		return new ShrinkBombObject(puzzle, x, y);
	}

	@Override
	public void createPuzzle() {
		setRandomMaxCountBetween(2, 5);
	}

}
