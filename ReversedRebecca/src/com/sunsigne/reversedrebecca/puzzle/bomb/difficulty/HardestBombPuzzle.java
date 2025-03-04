package com.sunsigne.reversedrebecca.puzzle.bomb.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.DuplicatingBombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzle;

public class HardestBombPuzzle extends BombPuzzle {

	public HardestBombPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, boolean critical, int x, int y) {
		return new DuplicatingBombObject(puzzle, critical, x, y);
	}

	@Override
	public int getBombAmount() {
		return 3;
	}

	@Override
	public void createPuzzle() {
		createBombs();
		setRandomMaxCountBetween(2, 5);
		createBullets(8);
	}

}
