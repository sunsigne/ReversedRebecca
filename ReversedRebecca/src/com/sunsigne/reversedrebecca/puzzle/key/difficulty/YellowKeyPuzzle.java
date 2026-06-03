package com.sunsigne.reversedrebecca.puzzle.key.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzle;

public class YellowKeyPuzzle extends KeyPuzzle {

	public YellowKeyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public LockObject getLock() {
		return new LockObject(this, isCritical);
	}

	@Override
	public KeyObject getKey() {
		return new KeyObject(this, isCritical);
	}

	@Override
	public void createPuzzle() {
		createLock();
		createKey();
		createRandomWalls(25, false);
	}

}
