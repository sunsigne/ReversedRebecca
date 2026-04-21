package com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks;

import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class LittleBombLockObject extends BombLockObject {

	public LittleBombLockObject(Puzzle puzzle, boolean critical, BombKeyObject bomb, int x, int y) {
		super(puzzle, critical, bomb, x, y, Size.M, Size.M);
	}

}
