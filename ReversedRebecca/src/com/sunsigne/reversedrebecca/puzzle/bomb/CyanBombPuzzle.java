package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class CyanBombPuzzle extends BombPuzzle {

	public CyanBombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, int x, int y) {
		return new BombObject(puzzle, x, y);
	}

	@Override
	public void createPuzzle() {
		setRandomMaxCountBetween(1, 3);
	}

	@Override
	public String getVictorySound() {
		return "sound/explosion_large";
	}

}
