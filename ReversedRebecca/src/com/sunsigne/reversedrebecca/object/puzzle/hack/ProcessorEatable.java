package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class ProcessorEatable extends ProcessorObject {

	public ProcessorEatable(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		removeObject();
		getComputer().removeObject(this);

		if (isFruitFile())
			new AchievementTask().unlockAchievement("fruit");
	}

	@Override
	public String getVirusActionSound() {
		return "virus_bite";
	}

	private boolean isFruitFile() {
		if (text.equalsIgnoreCase(new Translatable().getTranslatedText("Apple", FilePath.PUZZLE)))
			return true;

		if (text.equalsIgnoreCase(new Translatable().getTranslatedText("Banana", FilePath.PUZZLE)))
			return true;

		if (text.equalsIgnoreCase(new Translatable().getTranslatedText("Grapes", FilePath.PUZZLE)))
			return true;

		return false;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}

	@Override
	public int getSheetColCriterion() {
		switch (getName()) {
		case "image":
			return 2;
		case "music_1":
			return 5;
		case "music_2":
			return 6;
		case "music_3":
			return 7;
		}
		return 0;
	}

}
