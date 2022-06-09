package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class PeripheralAudio extends PeripheralObject {

	public PeripheralAudio(Puzzle puzzle, int x, int y) {
		super(puzzle, "Audio", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "audio";
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		super.doVirusAction();
		new SoundTask().play(SOUNDTYPE.SOUND, "sound/bip");
	}

	
}
