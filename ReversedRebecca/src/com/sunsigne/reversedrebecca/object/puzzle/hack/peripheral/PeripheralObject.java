package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public abstract class PeripheralObject extends ProcessorEatable {

	public PeripheralObject(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return getName() == "cpu" ? 2 : 3;
	}

	@Override
	public int getSheetColCriterion() {
		switch (getName()) {
		case "cpu":
		case "network_map":
			return 1;
		case "printer":
			return 2;
		case "CD_player":
			return 3;
		case "mouse":
			return 4;
		case "keyboard":
			return 5;
		case "audio":
			return 6;
		case "screen":
			return 7;
		}
		return 0;
	}

	
}
