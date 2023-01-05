package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class DigPuzzleObject extends PuzzleObject {

	public DigPuzzleObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
	}

	////////// USEFULL ////////////

	protected float getFloatRow(int row) {
		return ((float) row / Size.L) - 0.5f;
	}

	protected float getFloatCol(int col) {
		return ((float) col / Size.L) - 0.25f;
	}

	////////// PUZZLE ////////////

	@Override
	public DigPuzzle getPuzzle() {
		return (DigPuzzle) super.getPuzzle();
	}

}
