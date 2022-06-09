package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralScreen extends PeripheralObject {

	public PeripheralScreen(Puzzle puzzle, int x, int y) {
		super(puzzle, "Screen", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "screen";
	}

}
