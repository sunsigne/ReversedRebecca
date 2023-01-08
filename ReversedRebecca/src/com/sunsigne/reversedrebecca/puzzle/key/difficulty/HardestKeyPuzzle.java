package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class HardestKeyPuzzle extends CyanKeyPuzzle {

	public HardestKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public LockObject getLock() {
		return new LockObject(this, isCritical);
	}

	@Override
	public KeyObject getKey() {
		return new KeyObject(this);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(6, true);
	}

}
