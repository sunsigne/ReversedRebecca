package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.UpsideDownLockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;

public class HarderKeyPuzzle extends CyanKeyPuzzle {

	public HarderKeyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
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
		return new KeyObject(this, isCritical);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(4, true);
	}

}
