package com.sunsigne.reversedrebecca.object.puzzle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class PuzzleObject extends GameObject {

	public PuzzleObject(Puzzle puzzle, int x, int y) {
		super(x, y, Size.L, Size.L);
		this.puzzle = puzzle;
	}

	////////// PUZZLE ////////////
	
	private Puzzle puzzle;

	public Puzzle getPuzzle() {
		return puzzle;
	}

}
