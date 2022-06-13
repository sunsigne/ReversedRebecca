package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class PeripheralMouse extends PeripheralObject {

	public PeripheralMouse(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("PeripheralMouse", file));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "mouse";
	}

}
