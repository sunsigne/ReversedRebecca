package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralPrinter extends PeripheralObject {

	public PeripheralPrinter(Puzzle puzzle, int x, int y) {
		super(puzzle, "Printer", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "printer";
	}

}
