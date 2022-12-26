package com.sunsigne.reversedrebecca.puzzle.bomb.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BigBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzle;

public class EasierBombPuzzle extends BombPuzzle {

	public EasierBombPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, boolean critical, int x, int y) {
		return new BigBombObject(puzzle, critical, x, y);
	}

	@Override
	public int getBombAmount() {
		return 3;
	}

	@Override
	public void createPuzzle() {
		createBombs();
		setRandomMaxCountBetween(1, 3);
	}

}
