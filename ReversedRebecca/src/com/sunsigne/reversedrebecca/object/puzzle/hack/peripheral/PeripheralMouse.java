package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralMouse extends PeripheralObject {

	public PeripheralMouse(Puzzle puzzle) {
		super(puzzle, "Mouse");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "mouse";
	}

}
