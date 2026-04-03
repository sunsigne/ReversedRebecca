package com.sunsigne.reversedrebecca.puzzle.bomb.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.bombs.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.bombs.EasiestBombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzle;

public class EasiestBombPuzzle extends BombPuzzle {

	public EasiestBombPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, boolean critical, int x, int y) {
		return new EasiestBombObject(puzzle, critical, x, y);
	}

	@Override
	public int getBombAmount() {
		return 3;
	}

	@Override
	public void createPuzzle() {
		createBombs();
		createBullets(9);
	}

}
