package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ProcessorDesktop extends ProcessorFolder {

	public ProcessorDesktop(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, new Translatable().getTranslatedText("ProcessorDesktop", FilePath.PUZZLE), processors);
	}

	////////// USEFULL ////////////

	@Override
	protected void organizeProcessors() {
		int size = processors.length;

		// no back button, processors start at top left
		for (int index = 0; index < size; index++) {
			processors[index].setX(getCase(index)[0]);
			processors[index].setY(getCase(index)[1]);

			// trash (last processor) is at the bottom right
			if (processors[index] instanceof ProcessorTrash) {
				processors[index].setX(getCase(17)[0]);
				processors[index].setY(getCase(17)[1]);
			}
		}
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
