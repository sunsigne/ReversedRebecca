package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.BombKeyPuzzle;

public class CyanBombKeyPuzzle extends BombKeyPuzzle {

	public CyanBombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombKeyObject getBombKey(Puzzle puzzle, boolean critical) {
		return new BombKeyObject(puzzle, critical, 0, 0);
	}

	@Override
	public BombLockObject getBombLock(Puzzle puzzle, boolean critical, int x, int y) {
		return new BombLockObject(puzzle, critical, x, y);
	}

	private int amount;
	
	@Override
	public int getBombLockAmount() {
		if(amount == 0)
			amount = new RandomGenerator().getIntBetween(2, 3);
		return amount;
	}

	@Override
	public void createPuzzle() {
		createBombKey();
		createBombLocks(getBombLockAmount());
	}

}
