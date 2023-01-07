package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.key.key.FastKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.UpsideDownLockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzle;

public class RedKeyPuzzle extends KeyPuzzle {

	public RedKeyPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public LockObject getLock() {
		if (isCritical)
			return new LockObject(this, true);
		else
			return new UpsideDownLockObject(this);
	}

	@Override
	public KeyObject getKey() {
		return new FastKeyObject(this);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(25, false);
	}

}