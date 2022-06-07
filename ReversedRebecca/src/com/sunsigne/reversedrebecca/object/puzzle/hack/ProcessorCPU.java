package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorCPU extends ProcessorObject {

	public ProcessorCPU(Puzzle puzzle, String text, int x, int y) {
		super(puzzle, text, x, y);
	}

	////////// NAME ////////////

	public String getName() {
		return "cpu";
	}

}
