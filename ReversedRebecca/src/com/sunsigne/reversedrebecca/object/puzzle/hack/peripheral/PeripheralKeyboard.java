package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralKeyboard extends PeripheralObject {

	public PeripheralKeyboard(Puzzle puzzle) {
		super(puzzle, "Keyboard");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "keyboard";
	}

}
