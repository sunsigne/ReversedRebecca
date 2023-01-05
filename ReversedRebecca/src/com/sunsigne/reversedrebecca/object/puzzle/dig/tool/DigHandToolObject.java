package com.sunsigne.reversedrebecca.object.puzzle.dig.tool;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class DigHandToolObject extends DigToolObject {

	public DigHandToolObject(Puzzle puzzle, int x_pos_in_menu, int y_pos_in_menu, int w, int h, boolean selectable) {
		super(puzzle, x_pos_in_menu, y_pos_in_menu, w, h,  selectable);
	}

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.PUNCH;
	}

}
