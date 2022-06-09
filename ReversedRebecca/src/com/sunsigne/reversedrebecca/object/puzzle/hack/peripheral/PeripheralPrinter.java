package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralPrinter extends PeripheralObject {

	public PeripheralPrinter(Puzzle puzzle) {
		super(puzzle, "Printer");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "printer";
	}

}
