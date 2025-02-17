package com.sunsigne.reversedrebecca.puzzle.bomb.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.FastBombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzle;

public class OrangeBombPuzzle extends BombPuzzle {

	public OrangeBombPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, boolean critical, int x, int y) {
		return new FastBombObject(puzzle, critical, x, y);
	}

	@Override
	public int getBombAmount() {
		return 4;
	}

	@Override
	public void createPuzzle() {
		createBombs();
		setRandomMaxCountBetween(2, 5);
		createBullets(6);
	}

}
