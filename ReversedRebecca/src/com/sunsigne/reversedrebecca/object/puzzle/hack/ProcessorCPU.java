package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorCPU extends ProcessorObject {

	public ProcessorCPU(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cpu";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		getHandler().removeObject(this);
		getComputer().removeObject(this);
	}

	@Override
	public String getVirusActionSound() {
		return "sound/virus_bite";
	}

}
