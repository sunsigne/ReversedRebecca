package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;

public class DigPickaxeToolObject extends DigToolObject {

	public DigPickaxeToolObject(Puzzle puzzle) {
		super(puzzle);
	}

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.PICK;
	}

}
