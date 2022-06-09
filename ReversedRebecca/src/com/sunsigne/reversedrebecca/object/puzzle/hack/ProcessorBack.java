package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class ProcessorBack extends ProcessorFolder {

	public ProcessorBack(Puzzle puzzle, ProcessorBack previousback, ProcessorObject... processors) {
		super(puzzle, "back", puzzle.getCol(1), puzzle.getRow(1), processors);
		this.previousback = previousback;
		setX(getCase(0)[0]);
		setY(getCase(0)[1]);
	}

	////////// USEFULL ////////////

	@Override
	protected void organizeProcessors() {
		// of course they shouldn't reorganize their parents !
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "back";
	}

	////////// VIRUS ACTION ////////////

	@Override
	protected void displayNewFolder() {
		// add to the handler all processors contained in the folder ...
		for (int index = 0; index < size; index++) {
			// ... only if still in the computer
			if (getComputer().containsObject(processors[index]) == false)
				continue;

			LAYER.PUZZLE.addObject(processors[index]);
		}
		LAYER.PUZZLE.addObject(previousback);
	}

	@Override
	protected void refreskBack() {

	}

}
