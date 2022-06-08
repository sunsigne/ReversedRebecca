package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorDesktop extends ProcessorFolder {

	public ProcessorDesktop(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, "Desktop", puzzle.getCol(1), puzzle.getRow(1), processors);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public String getVirusActionSound() {
		return null;
	}
	
	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		if (flag)
			return;

		doVirusAction();
	}

}
