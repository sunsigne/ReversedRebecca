package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class BombPuzzleFactory {

	public BombPuzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanBombPuzzle(actionOnWinning);
		case GREEN:
			return new GreenBombPuzzle(actionOnWinning);
		case YELLOW:
			return new YellowBombPuzzle(actionOnWinning);
		case ORANGE:
			return new OrangeBombPuzzle(actionOnWinning);
		case RED:
			return new RedBombPuzzle(actionOnWinning);
		}
		// should not occurs
		return new CyanBombPuzzle(actionOnWinning);
	}

}
