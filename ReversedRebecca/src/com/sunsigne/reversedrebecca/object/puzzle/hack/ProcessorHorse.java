package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ProcessorHorse extends ProcessorEatable {

	public ProcessorHorse(Puzzle puzzle) {
		super(puzzle, "Horse");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "image_1";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		super.doVirusAction();
		getVirus().setDisguised(true);
	}

}
