package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.MovingBombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.LittleBombLockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.BombKeyPuzzle;

public class HardestBombKeyPuzzle extends BombKeyPuzzle {

	public HardestBombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombKeyObject getBombKey(Puzzle puzzle, boolean critical) {
		return new MovingBombKeyObject(puzzle, critical, 0, 0);
	}

	@Override
	public BombLockObject getBombLock(Puzzle puzzle, boolean critical, BombKeyObject bomb, int x, int y) {
		return new LittleBombLockObject(puzzle, critical, bomb, x, y);
	}

	@Override
	public int getBombLockAmount() {
		return 10;
	}

	@Override
	public void createPuzzle() {
		createBombKey(DIRECTION.UP);
		createBombLocks(4);

		createBombKey(DIRECTION.NULL);
		createBombLocks(5);

		createBombKey(DIRECTION.DOWN);
		createBombLocks(4);
	}

}
