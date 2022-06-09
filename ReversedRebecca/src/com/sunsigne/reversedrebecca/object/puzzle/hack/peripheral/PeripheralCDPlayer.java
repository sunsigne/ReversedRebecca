package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralCDPlayer extends PeripheralObject {

	public PeripheralCDPlayer(Puzzle puzzle, int x, int y) {
		super(puzzle, "CD Player", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "CD_player";
	}

}
