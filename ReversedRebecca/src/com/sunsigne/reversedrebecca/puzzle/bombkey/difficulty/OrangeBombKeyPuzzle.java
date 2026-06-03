package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.LittleBombLockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.BombKeyPuzzle;

public class OrangeBombKeyPuzzle extends BombKeyPuzzle {

	public OrangeBombKeyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombKeyObject getBombKey(Puzzle puzzle, boolean critical) {
		return new BombKeyObject(puzzle, critical, 0, 0);
	}

	@Override
	public BombLockObject getBombLock(Puzzle puzzle, boolean critical, BombKeyObject bomb, int x, int y) {
		return new LittleBombLockObject(puzzle, critical, bomb, false, x, y);
	}

	@Override
	public int getBombLockAmount() {
		return isCritical ? 2 : 9;
	}

	@Override
	public void createPuzzle() {
		boolean rad = new RandomGenerator().getBoolean();
		int up = (int) Math.ceil((float) getBombLockAmount() / 2f);
		int down = (int) Math.floor((float) getBombLockAmount() / 2f);

		createBombKey(DIRECTION.LEFT);
		createBombLocks(rad ? up : down);

		createBombKey(DIRECTION.RIGHT);
		createBombLocks(rad ? down : up);
	}

}
