package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorSystem extends ProcessorFolder {

	public ProcessorSystem(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, "system", processors);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer";
	}

}
