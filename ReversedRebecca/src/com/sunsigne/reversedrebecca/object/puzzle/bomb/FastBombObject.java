package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class FastBombObject extends MovingBombObject {
	
	public FastBombObject(Puzzle puzzle, int x, int y) {
		this(puzzle, x, y, 2 * Size.L, 2 * Size.L);
	}
	
	protected FastBombObject(Puzzle puzzle, int x, int y, int w, int h) {
		super(puzzle, x, y, w, h);
		defineNewYSpeed(Size.XS / 3);
	}
	
}
