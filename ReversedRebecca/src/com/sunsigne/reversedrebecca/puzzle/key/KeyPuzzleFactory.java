package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public class KeyPuzzleFactory implements PuzzleFactory {

	public KeyPuzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanKeyPuzzle(actionOnWinning);
		case GREEN:
			return new GreenKeyPuzzle(actionOnWinning);
		case YELLOW:
			return new YellowKeyPuzzle(actionOnWinning);
		case ORANGE:
			return new OrangeKeyPuzzle(actionOnWinning);
		case RED:
			return new RedKeyPuzzle(actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	////////// SOUND ////////////

	@Override
	public String getVictorySound() {
		return "sound/door_unlock";
	}

}
