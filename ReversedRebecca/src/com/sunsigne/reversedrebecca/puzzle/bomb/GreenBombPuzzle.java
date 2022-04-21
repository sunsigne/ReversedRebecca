package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

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
	public void createPuzzle() {
		setRandomMaxCountBetween(2, 5);
	}

	@Override
	public String getVictorySound() {
		return "sound/explosion_large";
	}
	
}
