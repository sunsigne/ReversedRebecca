package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class HardestKeyPuzzle extends CyanKeyPuzzle {

	public HardestKeyPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public LockObject getLock() {
		return new LockObject(this);
	}

	@Override
	public KeyObject getKey() {
		return new KeyObject(this);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(8, true);
	}

}
