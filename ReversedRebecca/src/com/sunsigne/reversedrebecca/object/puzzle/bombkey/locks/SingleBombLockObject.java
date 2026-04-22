package com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks;

import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class SingleBombLockObject extends BombLockObject {

	public SingleBombLockObject(Puzzle puzzle, boolean critical, BombKeyObject bomb) {
		super(puzzle, critical, bomb, false, Size.M + Size.XS / 4 + puzzle.getCol(5), Size.S / 6 + puzzle.getRow(3), 2*Size.L, 2*Size.L);
	}

	////////// NAME ////////////

	protected String getName() {
		return "SINGLE BOMBLOCK";
	}
	
}