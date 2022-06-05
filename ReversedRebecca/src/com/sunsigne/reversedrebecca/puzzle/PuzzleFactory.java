package com.sunsigne.reversedrebecca.puzzle;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public interface PuzzleFactory {

	default void autoWin(GenericListener actionOnWinning) {
		actionOnWinning.doAction();
		new SoundTask().play(SOUNDTYPE.SOUND, getVictorySound());
	}

	////////// SOUND ////////////

	String getVictorySound();

}
