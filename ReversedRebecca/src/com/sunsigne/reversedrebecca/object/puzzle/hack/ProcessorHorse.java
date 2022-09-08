package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ProcessorHorse extends ProcessorEatable {

	public ProcessorHorse(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Horse", FilePath.PUZZLE));
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

	@Override
	public String getVirusActionSound() {
		String sound = new RandomGenerator().getBoolean() ? "horse_0" : "horse_1";
		return sound;
	}

}
