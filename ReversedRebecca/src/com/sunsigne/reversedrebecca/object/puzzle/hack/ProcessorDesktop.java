package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorDesktop extends ProcessorFolder {

	public ProcessorDesktop(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, "Desktop", puzzle.getCol(1), puzzle.getRow(1), processors);
	}

	////////// NAME ////////////

	public String getName() {
		return "desktop";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		hideOldFolder();
		displayNewFolder();
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
