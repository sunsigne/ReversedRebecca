package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public abstract class AntivirusObject extends ProcessorObject {

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
		getHandler().removeObject(this);
		getComputer().removeObject(this);
		destroyAntivirus();
	}

	@Override
	public String getVirusActionSound() {
		return "sound/virus_bite";
	}

	////////// ANTIVIRUS ////////////
	
	public abstract void antivirusAction();
	
	public abstract void destroyAntivirus();
	
}
