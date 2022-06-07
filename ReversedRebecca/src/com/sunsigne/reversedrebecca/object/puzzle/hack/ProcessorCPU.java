package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class ProcessorCPU extends ProcessorObject {

	public ProcessorCPU(Puzzle puzzle, String text, int x, int y) {
		super(puzzle, text, x, y);
	}

	////////// NAME ////////////

	public String getName() {
		return "cpu";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		getHandler().removeObject(this);
		getComputer().removeObject(this);
		new SoundTask().play(SOUNDTYPE.SOUND, "sound/virus_bite");
	}

}
