package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class PeripheralScreen extends PeripheralObject {

	public PeripheralScreen(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("PeripheralScreen", FilePath.PUZZLE));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "screen";
	}

}
