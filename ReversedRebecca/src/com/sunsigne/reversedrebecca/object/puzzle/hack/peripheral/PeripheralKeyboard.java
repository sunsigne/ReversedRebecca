package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralKeyboard extends PeripheralObject {

	public PeripheralKeyboard(Puzzle puzzle, int x, int y) {
		super(puzzle, "Keyboard", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "keyboard";
	}

}
