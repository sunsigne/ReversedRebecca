package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.LittleBombLockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.BombKeyPuzzle;

public class YellowBombKeyPuzzle extends BombKeyPuzzle {

	public YellowBombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombKeyObject getBombKey(Puzzle puzzle, boolean critical) {
		return new BombKeyObject(puzzle, critical, 0, 0);
	}

	@Override
	public BombLockObject getBombLock(Puzzle puzzle, boolean critical, int x, int y) {
		return new LittleBombLockObject(puzzle, critical, x, y);
	}

	private int amount;

	@Override
	public int getBombLockAmount() {
		if (amount == 0)
			amount = new RandomGenerator().getIntBetween(9, 10);
		return amount;
	}

	@Override
	public void createPuzzle() {
		int up = (int) Math.ceil((float) getBombLockAmount() / 2f);
		int down = (int) Math.floor((float) getBombLockAmount() / 2f);
		boolean rad = new RandomGenerator().getBoolean();

		createBombKey(DIRECTION.LEFT);
		createBombLocks(rad ? up : down);

		createBombKey(DIRECTION.RIGHT);
		createBombLocks(rad ? down : up);
	}

}
