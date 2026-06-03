package com.sunsigne.reversedrebecca.puzzle;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public interface PuzzleFactory {

	////////// PUZZLE ////////////

	Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing);

	default void autoWin(GenericListenerBoolean actionOnWinning) {
		actionOnWinning.doAction(false);
		new SoundTask().playSound(SOUNDTYPE.SOUND, getVictorySound());
	}

	////////// SOUND ////////////

	String getOpeningSound();

	String getVictorySound();

	////////// SHAKE ////////////

	default SHAKE getVictoryShake() {
		return null;
	}

}
