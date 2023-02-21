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
		getHandler().removeObject(this);
		getComputer().removeObject(this);
		
		if(isFruitFile())
			new AchievementTask().unlockAchievement("fruit");
	}

	@Override
	public String getVirusActionSound() {
		return "virus_bite";
	}
	
	private boolean isFruitFile() {
		if(text.equalsIgnoreCase(new Translatable().getTranslatedText("Apple", FilePath.PUZZLE)))
			return true;
		
		if(text.equalsIgnoreCase(new Translatable().getTranslatedText("Banana", FilePath.PUZZLE)))
			return true;
		
		if(text.equalsIgnoreCase(new Translatable().getTranslatedText("Grapes", FilePath.PUZZLE)))
			return true;
		
		return false;
	}

}
