package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.MovingBombObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.BombKeyPuzzle;

public class CyanBombKeyPuzzle extends BombKeyPuzzle {

	public CyanBombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBombLock(Puzzle puzzle, boolean critical, int x, int y) {
		return new MovingBombObject(puzzle, critical, x, y);
	}

	@Override
	public int getBombLockAmount() {
		return 3;
	}

	@Override
	public void createPuzzle() {
		createBombKey();
	}

}
