package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class HackPuzzleFactory {

	public HackPuzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanHackPuzzle(actionOnWinning);
		case GREEN:
			return new CyanHackPuzzle(actionOnWinning);
		case YELLOW:
			return new CyanHackPuzzle(actionOnWinning);
		case ORANGE:
			return new CyanHackPuzzle(actionOnWinning);
		case RED:
			return new CyanHackPuzzle(actionOnWinning);
		}
		// should not occurs
		return new CyanHackPuzzle(actionOnWinning);
	}

}
