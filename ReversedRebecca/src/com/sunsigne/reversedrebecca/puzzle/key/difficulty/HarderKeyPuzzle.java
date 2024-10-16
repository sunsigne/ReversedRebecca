package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.UpsideDownLockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class HarderKeyPuzzle extends CyanKeyPuzzle {

	public HarderKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
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
		return new KeyObject(this);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(4, true);
	}

}
