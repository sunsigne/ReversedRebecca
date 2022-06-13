package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class AntivirusObject extends ProcessorEatable {

	public AntivirusObject(Puzzle puzzle, String text) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + text, file));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "antivirus";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		super.doVirusAction();
		destroyAntivirus();
	}

	////////// ANTIVIRUS ////////////

	public abstract void antivirusAction();

	public abstract void destroyAntivirus();

}
