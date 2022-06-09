package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralCDPlayer extends PeripheralObject {

	public PeripheralCDPlayer(Puzzle puzzle) {
		super(puzzle, "CD Player");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "CD_player";
	}

}
