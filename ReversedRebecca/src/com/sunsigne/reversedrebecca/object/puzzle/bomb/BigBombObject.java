package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class BigBombObject extends BombObject {

	public BigBombObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x + Size.S, y, 3 * Size.L, 3 * Size.L);
	}

}
