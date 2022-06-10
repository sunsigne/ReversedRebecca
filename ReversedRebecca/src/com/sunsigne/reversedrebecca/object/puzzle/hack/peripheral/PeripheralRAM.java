package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralRAM extends PeripheralObject {

	public PeripheralRAM(Puzzle puzzle) {
		super(puzzle, "RAM");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cpu";
	}

}
