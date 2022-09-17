package com.sunsigne.reversedrebecca.object.puzzle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class PuzzleObject extends GameObject {

	public PuzzleObject(Puzzle puzzle, int x, int y) {
		this(puzzle, x, y, Size.L, Size.L);
	}

	public PuzzleObject(Puzzle puzzle, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.puzzle = puzzle;
	}

	////////// USEFULL ////////////

	protected int getCol(int col) {
		return (col / Size.L);
	}

	protected int getRow(int row) {
		return (row / Size.L);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : UNKOWNED";
		return clazz + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// PUZZLE ////////////

	private Puzzle puzzle;

	public Puzzle getPuzzle() {
		return puzzle;
	}

}
