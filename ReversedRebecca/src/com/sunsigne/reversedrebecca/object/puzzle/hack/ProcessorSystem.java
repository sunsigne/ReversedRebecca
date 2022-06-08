package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorSystem extends ProcessorFolder {

	public ProcessorSystem(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, "system", puzzle.getCol(1), puzzle.getRow(1), processors);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer";
	}

}
