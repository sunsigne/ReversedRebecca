package com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class LittleBombLockObject extends BombLockObject {

	public LittleBombLockObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x, y, Size.M, Size.M);
	}

}
