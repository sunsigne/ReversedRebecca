package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class PeripheralKeyboard extends PeripheralObject {

	public PeripheralKeyboard(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("PeripheralKeyboard", file));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "keyboard";
	}

}
