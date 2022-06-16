package com.sunsigne.reversedrebecca.puzzle;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public interface PuzzleFactory {

	////////// PUZZLE ////////////
	
	Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning);
	
	default void autoWin(GenericListener actionOnWinning) {
		actionOnWinning.doAction();
		new SoundTask().play(SOUNDTYPE.SOUND, getVictorySound());
	}

	////////// SOUND ////////////

	String getVictorySound();

}
