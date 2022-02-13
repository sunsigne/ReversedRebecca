package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class KeyPuzzleFactory {

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
		}
		// should not occurs
		return new CyanKeyPuzzle(actionOnWinning);
	}

}
