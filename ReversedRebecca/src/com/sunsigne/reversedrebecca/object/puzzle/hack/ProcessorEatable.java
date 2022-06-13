package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public abstract class ProcessorEatable extends ProcessorObject {

	public ProcessorEatable(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		getHandler().removeObject(this);
		getComputer().removeObject(this);
	}

	@Override
	public String getVirusActionSound() {
		return "virus_bite";
	}
	
}
