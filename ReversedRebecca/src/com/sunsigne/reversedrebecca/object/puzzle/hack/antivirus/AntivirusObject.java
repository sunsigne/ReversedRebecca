package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public abstract class AntivirusObject extends ProcessorEatable {

	// Antivirus' tick when the HackComputer does, even when not in the Handler
	public AntivirusObject(Puzzle puzzle, String text) {
		super(puzzle, text);
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
