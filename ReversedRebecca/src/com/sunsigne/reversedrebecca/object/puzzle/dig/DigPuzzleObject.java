package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class DigPuzzleObject extends PuzzleObject implements TickFree, MouseUserEvent {

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

	////////// SIZE ////////////

	@Override
	public boolean isSelected() {
		if (isCritical() == false)
			return MouseUserEvent.super.isSelected();

		boolean row_rect = mouseOver(new MousePos().get(), getRowRect()) && isClickable();
		boolean col_rect = mouseOver(new MousePos().get(), getColRect()) && isClickable();

		return row_rect || col_rect;
	}

	private int[] getRowRect() {
		int[] rect = new int[4];
		rect[0] = getX() - getWidth();
		rect[1] = getY();
		rect[2] = 3 * getWidth();
		rect[3] = getHeight();
		return rect;
	}

	private int[] getColRect() {
		int[] rect = new int[4];
		rect[0] = getX();
		rect[1] = getY() - getHeight();
		rect[2] = getWidth();
		rect[3] = 3 * getHeight();
		return rect;
	}

	////////// PUZZLE ////////////

	@Override
	public DigPuzzle getPuzzle() {
		return (DigPuzzle) super.getPuzzle();
	}

	public boolean isCritical() {
		return getPuzzle().getState() == DIG_STATE.CRITICAL;
	}
	
}
