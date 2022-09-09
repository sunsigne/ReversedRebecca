package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class PeripheralAudio extends PeripheralObject {

	public PeripheralAudio(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("PeripheralAudio", FilePath.PUZZLE));
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
		new SoundTask().playSound(SOUNDTYPE.SOUND, "bip_long");
	}

	
}
