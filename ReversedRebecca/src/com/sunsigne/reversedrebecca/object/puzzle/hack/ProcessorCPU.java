package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorCPU extends ProcessorEatable {

	public ProcessorCPU(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cpu";
	}

}
